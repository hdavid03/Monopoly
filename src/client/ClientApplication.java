package client;

import GUI.LaunchPage;
import GUI.MonopolyGUI;
import networking.ClientThread;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientApplication {
    public static final Logger clientApplicationLogger = Logger.getLogger(ClientApplication.class.getName());

    public static void main(String[] args) {
        GUI.LaunchPage launchPage = new LaunchPage();
        while (!launchPage.clientWantToConnect()) {
            waitInMilliseconds(500);
        }

        MonopolyGUI gui = new MonopolyGUI(launchPage.getUsernameTextField().getText());
        ClientThread clientThread = new ClientThread(gui, launchPage.getIpAddressTextField().getText());
        Thread thread = new Thread(clientThread);
        thread.start();

        gui.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                clientThread.setRunning(false);
                if(clientThread.getPlayer() != null) clientThread.getPlayer().setOnline(false);
                try {
                    thread.join();
                } catch (InterruptedException exception) {
                    ClientApplication.clientApplicationLogger.log(Level.SEVERE, "Client interrupted before closing window");
                    Thread.currentThread().interrupt();
                }
            }
        });
    }

    private static void waitInMilliseconds(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            ClientApplication.clientApplicationLogger.log(Level.SEVERE, "Client interrupted while it was waiting");
            Thread.currentThread().interrupt();
        }
    }
}