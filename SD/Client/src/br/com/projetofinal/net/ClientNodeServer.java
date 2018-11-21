package br.com.projetofinal.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JTextArea;

public class ClientNodeServer implements Runnable {
    
    private JTextArea output;

    public ClientNodeServer() {
    }

    public ClientNodeServer(JTextArea jTOutput) {
        this.output = jTOutput;
    }

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(6000);
            while (true) {
                Socket socket = server.accept();
                new Thread(new ClientNodeResolver(socket, this.output)).start();
            }
        } catch (IOException ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
    }

}
