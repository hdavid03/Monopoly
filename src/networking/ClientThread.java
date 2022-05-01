package networking;

import client.ClientApplication;
import game_elements.Player;
import game_elements.Table;
import server.ServerApplication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.TimerTask;
import java.util.logging.Level;

public class ClientThread extends Thread {
    private Socket socket;
    private Table table;
    private Player player;
    private boolean running;

    public ClientThread(Socket socket) {
        this.table = new Table();
        this.running = false;
        this.socket = socket;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            this.player = (Player) ois.readObject();
            while(true) {
                player.setMoney(player.getMoney() + 1);
                player = new Player(player);
                System.out.println(player.toString());
                oos.writeObject(player);
                oos.flush();
                Thread.sleep(600);
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch(InterruptedException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
