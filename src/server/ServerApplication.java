package server;

import networking.ServerSocketHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerApplication {
    public static final int PORT = 3001;
    public static final String HOST = "localhost";
    private static final Logger serverApplicationLogger = Logger.getLogger(ServerApplication.class.getName());

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(PORT)) {
            ServerSocketHandler serverSocketHandler = new ServerSocketHandler(serverSocket);
            serverSocketHandler.start();
            serverSocketHandler.join();
        } catch(IOException e) {
            serverApplicationLogger.log(Level.SEVERE, e::getMessage);
        } catch(InterruptedException e) {
            serverApplicationLogger.log(Level.SEVERE, e::getMessage);
            Thread.currentThread().interrupt();
        }
    }
}