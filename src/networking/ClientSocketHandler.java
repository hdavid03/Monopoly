package networking;

import game_elements.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientSocketHandler implements Runnable {

    private Socket socket;
    private Player player;
    private boolean clientReady;
    private boolean lostConnection;
    private static final Logger clientSocketHandlerLogger = Logger.getLogger(ClientSocketHandler.class.getName());

    public ClientSocketHandler(Socket socket, Player player) {
        this.socket = socket;
        this.clientReady = false;
        this.lostConnection = false;
        this.player = player;
    }

    public boolean isLostConnection() {
        return lostConnection;
    }

    public void setLostConnection(boolean lostConnection) {
        this.lostConnection = lostConnection;
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

    private void updatePlayerStatus(ObjectInputStream ois) {
        try {
            if(!lostConnection) {
                player = (Player)ois.readObject();
            }
        } catch(IOException | ClassNotFoundException e) {
            clientSocketHandlerLogger.log(Level.SEVERE, e.getMessage());
            setLostConnection(true);
        }
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(player);
            oos.flush();
            while(!lostConnection) {
                updatePlayerStatus(ois);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}