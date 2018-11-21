package br.com.projetofinal.net;

import br.com.projetofinal.core.Counter;
import br.com.projetofinal.util.Crypto64;
import br.com.projetofinal.util.NodeDataTransferModel;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;

public class NodeResolver implements Runnable {

    private final Socket socket;
    private final BufferedReader reader;

    public NodeResolver(Socket socket) throws IOException {
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            String ln;
            while ((ln = this.reader.readLine()) != null) {
                String data = Crypto64.decode(ln);
                NodeDataTransferModel transferModel
                        = new Gson().fromJson(data, NodeDataTransferModel.class);

                String mapping = Counter.tell(transferModel.getKeywordsSplitting(),
                        transferModel.getTextFragment()).toString();

                String ip = (((InetSocketAddress) socket.getRemoteSocketAddress()).getAddress()).toString().replace("/", "");
                int port = socket.getLocalPort();

                System.out.println("Node: " + mapping);
                SocketWriteMessage message = new SocketWriteMessage(new Socket(ip, 8000));
                message.write(Crypto64.encode(mapping));
                this.socket.close();

            }
        } catch (IOException ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
    }

}
