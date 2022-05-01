package client;

import networking.ClientRunnableTask;
import server.ServerApplication;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientApplication {
    private static final Logger clientApplicationLogger = Logger.getLogger(ClientApplication.class.getName());
    public static void main(String[] args) {
        try(Socket socket = new Socket(ServerApplication.HOST, ServerApplication.PORT)) {
            Thread clientThread = new Thread(new ClientRunnableTask(socket));
            clientThread.start();
            clientThread.join();
        } catch(IOException e) {
            clientApplicationLogger.log(Level.SEVERE, e::getMessage);
        } catch(InterruptedException e) {
            clientApplicationLogger.log(Level.SEVERE, e::getMessage);
            Thread.currentThread().interrupt();
        }
    }
}