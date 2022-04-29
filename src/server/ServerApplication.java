package server;

import networking.ServerSocketHandler;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerApplication {
    public static final int PORT = 9000;
    public static final String HOST = "localhost";

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(PORT)) {
            ServerSocketHandler serverSocketHandler = new ServerSocketHandler(serverSocket);
            serverSocketHandler.start();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
