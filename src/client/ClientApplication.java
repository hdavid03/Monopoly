package client;

import GUI.LaunchPage;
import GUI.MonopolyGUI;
import networking.ClientThread;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

public class ClientApplication {
    public static final Logger clientApplicationLogger = Logger.getLogger(ClientApplication.class.getName());

    public static void main(String[] args) {
        GUI.LaunchPage launchPage = new LaunchPage();
        while (!launchPage.clientWantToConnect()) {
            waitInMilliseconds(500);
        }

        MonopolyGUI gui = new MonopolyGUI(launchPage.getUsernameTextField().getText());
        ClientThread clientThread = new ClientThread(gui);
        Thread thread = new Thread(clientThread);
        thread.start();

        gui.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                clientThread.setRunning(false);
                clientThread.getPlayer().setPlayerOnline(false);
                try {
                    thread.join();
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        });
    }

    private static void waitInMilliseconds(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}