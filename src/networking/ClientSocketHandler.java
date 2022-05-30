package networking;

import game_elements.Player;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientSocketHandler implements Runnable {

    private Socket socket;
    private Player player;
    private Queue<Player> playerQueue;
    private ServerMessage serverMessage;
    private boolean clientReady;
    private boolean lostConnection;
    private static final Logger clientSocketHandlerLogger = Logger.getLogger(ClientSocketHandler.class.getName());

    public ClientSocketHandler(Socket socket, Player player) {
        this.socket = socket;
        this.clientReady = false;
        this.lostConnection = false;
        this.player = player;
    }

    public void updatePlayerQueue(Queue<Player> players) {
        this.playerQueue = players;
    }

    public void updateServerMessage(ServerMessage message) { this.serverMessage = message; }

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
                StatusMessage message = (StatusMessage)ois.readObject();
                player = message.getPlayer();
                clientReady = message.isReady();
            }
        } catch(IOException | ClassNotFoundException e) {
            clientSocketHandlerLogger.log(Level.SEVERE, e.getMessage());
            player.setPlayerOnline(false);
            setLostConnection(true);
        }
    }

    private void sendUpdatedStatusToTheClient(ObjectOutputStream oos) {
        try {
            if (serverMessage != null) {
                oos.writeObject(serverMessage);
            }
        }catch (IOException e) {
            clientSocketHandlerLogger.log(Level.SEVERE, e.getMessage());
            player.setPlayerOnline(false);
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
                sendUpdatedStatusToTheClient(oos);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}