/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetofinal.net;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author hilton
 */
public class SocketWriteMessage {

    private final Socket socket;
    private final PrintWriter writer;

    public SocketWriteMessage(Socket socket) throws IOException {
        this.socket = socket;
        this.writer = new PrintWriter(socket.getOutputStream(), true);
    }
    
    public void write(String message) throws IOException{
        this.writer.write(message);
        this.writer.flush();
        this.socket.close();
    }
    
    public void writeMult(String message) throws IOException{
        this.writer.write(message);
        this.writer.flush();
    }
    
    public void close() throws IOException{
        if(this.socket != null){
            this.socket.close();
        }
    }
}
