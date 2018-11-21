package br.com.projetofinal.net;

import br.com.projetofinal.util.Crypto64;
import br.com.projetofinal.util.DataTransferModel;
import br.com.projetofinal.util.FileManager;
import br.com.projetofinal.util.NodeDataTransferModel;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.List;

public class ClientHeadNodeResolver implements Runnable {

    private final Socket socket;
    private final BufferedReader reader;
    private final int nodes;
    private final FileManager fm;

    public ClientHeadNodeResolver(Socket socket) throws IOException {
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String nodesDBPath = getClass().getClassLoader().getResource("br/com/projetofinal/ui/nodes.txt").getPath();
        fm = new FileManager(nodesDBPath);
        this.nodes = fm.countLines();
    }

    @Override
    public void run() {
        try {
            String ln;
            while ((ln = this.reader.readLine()) != null) {

                String data = Crypto64.decode(ln);

                DataTransferModel model = new Gson().fromJson(data, DataTransferModel.class);

                String keywords = model.getKeywords();
                String text = model.getText();

                //Splitting
                String[] keywordsSplitting = keywords.split(",");
                String[] textSplitting = text.split(" ");

                int textSplittingLen = textSplitting.length / this.nodes;
                int pos = 0;

                List<String[]> hosts = fm.simpleReadData();

                //Mapping
                for (String[] host : hosts) {

                    if (host[4].equalsIgnoreCase("0")) {
                        continue;
                    }

                    String[] textFragment = new String[textSplittingLen];
                    System.arraycopy(textSplitting, pos, textFragment, 0, textSplittingLen);

                    NodeDataTransferModel transferModel = new NodeDataTransferModel(keywordsSplitting, textFragment);
                    String mapping = Crypto64.encode(new Gson().toJson(transferModel));

                    String ip = host[2];
                    int port = Integer.parseInt(host[3]);
                    SocketWriteMessage message = new SocketWriteMessage(new Socket(ip, port));
                    message.write(mapping);

                    pos += textSplittingLen;
                }
            }
        } catch (IOException ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
    }

}
