package com.elvin.atmosphere.clients;

import java.io.PrintWriter;
import java.net.Socket;

public class RaspberryClient {

    private String host = "192.168.11.99";
    private int port = 8888;
    private PrintWriter output = null;
    private Socket socket;
    
    public void connect() throws Exception{
        socket = new Socket(host, port);
        output = new PrintWriter(socket.getOutputStream());
    }
    
    public void sendToRpi(String data){
        if(data == null)
            return;
        output.println(data + "\n\r");
        output.flush();
    }
    
    public void close() throws Exception{
        output.println("LEDOFF" + "\n\r");
        output.flush();
        socket.close();
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
    
    
}
