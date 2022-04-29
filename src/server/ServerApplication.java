package server;

import networking.ServerSocketHandler;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerApplication {
    public static final int PORT = 3001;
    public static final String HOST = "localhost";

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(PORT)) {
            ServerSocketHandler serverSocketHandler = new ServerSocketHandler(serverSocket);
            serverSocketHandler.start();
            serverSocketHandler.join();
        } catch(IOException e) {
            e.printStackTrace();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
