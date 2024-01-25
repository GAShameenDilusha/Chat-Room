package lk.ijse.fx.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    //implementing singleton design pattern to avoid creating servers...

    private static Server server;
    private final ServerSocket serverSocket;

    private Server() throws IOException {
        this.serverSocket = new ServerSocket(3000);
        System.out.println("Server Started...");

    }

    public static Server getServerSocket() throws IOException {
        return server==null ? server = new Server() : server;
    }


    @Override
    public void run() {
        while(!serverSocket.isClosed()){
            System.out.println(("Listening..."));
            try {
                Socket accepted = serverSocket.accept();
                lk.ijse.fx.server.ClientHandler clientHandler = new lk.ijse.fx.server.ClientHandler(accepted);
                Thread thread = new Thread(clientHandler);
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
