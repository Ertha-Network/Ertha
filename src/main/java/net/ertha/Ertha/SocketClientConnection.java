package net.ertha.Ertha;

import java.io.*;
import java.net.Socket;

public class SocketClientConnection implements Runnable {
    private Ertha e;
    private Socket server;
    private BufferedReader in;
    private PrintWriter out;

    public SocketClientConnection(Ertha ertha, Socket socket){
        e = ertha;
        server = socket;
        try {
            in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            out = new PrintWriter(server.getOutputStream(), true);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    @Override
    public void run() {
        while (true){
            try {
                String serverResponse = in.readLine();
                System.out.println("Server response: "+ serverResponse);
                if(serverResponse.equals("ping")){
                    out.write("pong");
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
    public void send(String message){
        out.write(message);
    }
}
