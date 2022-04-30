package networking;

import game_elements.Player;
import game_elements.Table;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;
    private Table table;
    private Player player;

    public ClientThread(Socket socket) {
        this.socket = socket;
        this.table = new Table();
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
                oos.writeObject(player);
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
