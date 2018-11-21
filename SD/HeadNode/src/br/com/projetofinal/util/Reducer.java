package br.com.projetofinal.util;

import br.com.projetofinal.net.SocketWriteMessage;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Reducer {

    private final Map<String, Double> result;
    private int count;
    private final FileManager fm;

    public Reducer() {
        this.result = new HashMap<>();
        String fileName = getClass()
                .getClassLoader().getResource("br/com/projetofinal/ui/nodes.txt").getPath();
        fm = new FileManager(fileName);
        this.count = fm.countLines();
    }

    public int reduce(Map<String, Double> map, Socket socketOut) throws IOException {

        this.result.entrySet().stream().forEach((entry) -> {
            String rsKey = entry.getKey();
            Double rsValue = entry.getValue();

            Double mpValue = map.get(rsKey) + rsValue;

            map.put(rsKey, mpValue);

        });
        this.result.putAll(map);
        this.count--;

        if (this.count <= 0) {
            System.out.println(this.result.toString());
            sendBack(socketOut);

        }

        return this.count;

    }

    private void sendBack(Socket socketOut) throws IOException {
        String ip = socketOut.getLocalAddress().getCanonicalHostName();

        SocketWriteMessage message = new SocketWriteMessage(new Socket(ip, 6000));
        message.write(Crypto64.encode(new Gson().toJson(this.result)));
    }

}
