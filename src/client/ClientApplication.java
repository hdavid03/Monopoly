package client;

import GUI.LaunchPage;
import GUI.MonopolyGUI;
import networking.ClientThread;
import server.ServerApplication;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientApplication {
    public static final Logger clientApplicationLogger = Logger.getLogger(ClientApplication.class.getName());
    public static void main(String[] args) {
        GUI.LaunchPage launchPage = new LaunchPage();
        while(!launchPage.clientWantToConnect()) {
            System.out.println("wait");
            waitInMilliseconds(500);
        }
        MonopolyGUI gui = new MonopolyGUI(launchPage.getUsernameTextField().getText());
        
    }

    private static void waitInMilliseconds(int ms) {
        try {
            Thread.sleep(ms);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void connectToServer(String playerName) {
        try(Socket socket = new Socket(ServerApplication.HOST, ServerApplication.PORT)) {
            ClientThread clientThread = new ClientThread(socket, playerName);
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