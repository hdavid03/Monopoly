package networking;

import game_elements.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class ClientSocketHandler implements Runnable {

    private Socket socket;
    private Player player;
    private boolean clientReady;

    public ClientSocketHandler(Socket socket, Player player) {
        this.socket = socket;
        this.clientReady = false;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isClientReady() {
        return clientReady;
    }

    public void setClientReady(boolean clientReady) {
        this.clientReady = clientReady;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(this.socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
            oos.writeObject(this.player);
            oos.flush();
            while(true) {
                this.player = (Player)ois.readObject();
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
