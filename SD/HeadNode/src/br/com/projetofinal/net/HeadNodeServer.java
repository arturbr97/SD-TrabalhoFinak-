package br.com.projetofinal.net;

import br.com.projetofinal.util.Crypto64;
import br.com.projetofinal.util.Reducer;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class HeadNodeServer implements Runnable {

    private ServerSocket sOutput;
    private Reducer reducing;

    public HeadNodeServer() {
        this.reducing = new Reducer();
    }

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(5000);
            while (true) {
                Socket socket = server.accept();
                new Thread(new ClientHeadNodeResolver(socket)).start();
                initServerOutputHandler(socket);
            }
        } catch (IOException ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
    }

    private void initServerOutputHandler(Socket socketOut) {

        new Thread(() -> {
            try {

                if (this.sOutput == null) {
                    this.sOutput = new ServerSocket(8000);
                }

                while (true) {
                    Socket s = sOutput.accept();
                    BufferedReader reader
                            = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    Map<String, Double> map = new Gson().fromJson(Crypto64.decode(reader.readLine()), HashMap.class);

                    if (this.reducing == null) {
                        this.reducing = new Reducer();
                    }

                    //Reduce
                    if (reducing.reduce(map, socketOut) <= 0) {
                        reducing = null;
                    }

                    reader.close();
                    s.close();

                }
            } catch (IOException ex) {
                System.out.println("Erro Out: " + ex.getMessage());
            }
        }).start();
    }

}
