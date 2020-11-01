package net.ertha.Ertha;

import java.io.IOException;
import java.net.Socket;

public class SocketClient {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 25530;
    public Socket socket;

    private final Ertha e;

    public SocketClient(Ertha ertha) {
        e = ertha;

        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);


            SocketClientConnection scc = new SocketClientConnection(e, socket);
            new Thread(scc).start();
            scc.send("ping");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

}
