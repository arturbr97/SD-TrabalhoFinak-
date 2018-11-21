package br.com.projetofinal.net;

import br.com.projetofinal.util.Crypto64;
import br.com.projetofinal.util.FileManager;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Map;
import javax.swing.JTextArea;

public class ClientNodeResolver implements Runnable {

    private final Socket socket;
    private final BufferedReader reader;
    private final JTextArea output;

    public ClientNodeResolver(Socket socket, JTextArea jTOutput) throws IOException {
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.output = jTOutput;
    }

    @Override
    public void run() {
        try {
            String ln;
            while ((ln = this.reader.readLine()) != null) {
                String data = Crypto64.decode(ln);
                formatOutput(data);
            }
        } catch (IOException ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
    }

    private void formatOutput(String data) {
        Map<String, Double> map = new Gson().fromJson(data, Map.class);
        StringBuilder sbuf = new StringBuilder();
        sbuf.append("--------------RESULTADO--------------\n");

        map.entrySet().stream().forEach((entry) -> {
            String key = entry.getKey();
            Double value = entry.getValue();
            sbuf.append(key).append(": ").append(String.format("%.0f", value)).append("\n");
        });

        calculeTime(sbuf);

        this.output.setText(sbuf.toString());
    }

    private void calculeTime(StringBuilder sbuf) {
        String nodesDBPath = getClass().getClassLoader().getResource("br/com/projetofinal/ui/perform.txt").getPath();
        FileManager fm = new FileManager(nodesDBPath);
        try{
        Long ini = Long.parseLong(fm.readData());
        Long time = System.currentTimeMillis() - ini;
        sbuf.append("\nTempo: ").append(time).append(" ms").append("\n");
        fm.write("");
        }catch(NumberFormatException e){}
    }

}
