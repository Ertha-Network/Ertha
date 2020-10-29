package net.ertha.Ertha;

import java.io.*;
import java.net.Socket;

public class SocketClientConnection implements Runnable {
    private Ertha e;
    private Socket server;
    private BufferedReader in;

    public SocketClientConnection(Ertha ertha, Socket socket){
        e = ertha;
        server = socket;
        try {
            in = new BufferedReader(new InputStreamReader(server.getInputStream()));
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
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
