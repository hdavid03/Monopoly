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
    private ServerMessage serverMessage;
    private boolean serverIsUpdated = false;
    private boolean clientIsUpdated = false;
    private boolean clientReady;
    private boolean readyDetect = false;
    private boolean lostConnection;
    private static final Logger clientSocketHandlerLogger = Logger.getLogger(ClientSocketHandler.class.getName());

    public ClientSocketHandler(Socket socket, Player player) {
        this.socket = socket;
        this.clientReady = false;
        this.lostConnection = false;
        this.player = player;
    }

    public boolean isClientUpdated() {
        return clientIsUpdated;
    }

    public void setClientIsUpdated(boolean clientIsUpdated) {
        this.clientIsUpdated = clientIsUpdated;
    }

    public void updateServerMessage(ServerMessage message) {
        this.serverMessage = message;
        this.serverIsUpdated = true;
    }

    public boolean isReadyDetect() {
        return readyDetect;
    }

    public void setReadyDetect(boolean readyDetect) {
        this.readyDetect = readyDetect;
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
        this.readyDetect = false;
    }

    private void updatePlayerStatusFromClient(ObjectInputStream ois) {
        try {
            if(!lostConnection) {
                StatusMessage message = (StatusMessage)ois.readObject();
                player = message.getPlayer();
                clientReady = message.isReady();
                clientIsUpdated = true;
                System.out.println("READ CLIENT");
                if(!readyDetect && clientReady) {
                    readyDetect = true;
                }
            }
        } catch(IOException e ) {
            clientSocketHandlerLogger.log(Level.SEVERE, "Disconnected from the server");
            player.setOnline(false);
            setLostConnection(true);
        }catch (ClassNotFoundException e) {
            clientSocketHandlerLogger.log(Level.SEVERE, "Class not found exception");
            player.setOnline(false);
            setLostConnection(true);
        }
    }

    private void sendUpdatedStatusToTheClient(ObjectOutputStream oos) {
        try {
            if (serverMessage != null) {
                oos.writeObject(serverMessage);
            }
        }catch (IOException e) {
            clientSocketHandlerLogger.log(Level.SEVERE, "Lost message from server");
            player.setOnline(false);
            setLostConnection(true);
        }
    }

    private void waitingForServerUpdate() {
        try {
            while (!serverIsUpdated) {
                Thread.sleep(50);
            }
            serverIsUpdated = false;
        }catch(InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(player);
            oos.flush();
            this.player = (Player)ois.readObject();
            while(!lostConnection) {
                updatePlayerStatusFromClient(ois);
                waitingForServerUpdate();
                sendUpdatedStatusToTheClient(oos);
            }
        } catch(IOException | ClassNotFoundException e) {
            clientSocketHandlerLogger.log(Level.SEVERE, "Disconnected from the server");
            player.setOnline(false);
            setLostConnection(true);
        }
    }
}