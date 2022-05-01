package networking;

import game_elements.Player;
import game_elements.Table;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientRunnableTask implements Runnable {
    private Socket socket;
    private Table table;
    private Player player;

    public ClientRunnableTask(Socket socket) {
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
                System.out.println("message send");
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
