package networking;

import GUI.MonopolyGUI;
import client.ClientApplication;
import game_elements.Player;
import server.ServerApplication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;

public class ClientThread implements Runnable {
    private Socket clientSocket;
    private Player player;
    private final MonopolyGUI gameBoard;
    private String ipAddress;
    private boolean running;

    public ClientThread(MonopolyGUI gameBoard, String ipAddress) {
        this.running = true;
        this.gameBoard = gameBoard;
        this.ipAddress = ipAddress;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
        if( !isRunning() && clientSocket != null && !clientSocket.isClosed() ) {
            try {
                clientSocket.close();
            } catch (IOException e) {
                ClientApplication.clientApplicationLogger.log(Level.SEVERE, "There was a problem with closing the client socket");
            }
        }
    }

    public Player getPlayer() {
        return player;
    }

    private void sendPlayerStatusToTheServer(ObjectOutputStream oos) {
        try {
            this.player = gameBoard.getPlayer();
            boolean ready = gameBoard.isReady();
            Transaction transaction = gameBoard.getPlayer().getTransaction();
            StatusMessage message = new StatusMessage(new Player(player), ready);
            ClientApplication.clientApplicationLogger.log(Level.INFO, player::toString);
            oos.writeObject(message);
            oos.flush();
            if(transaction.isActive()) {
                ClientApplication.clientApplicationLogger.log(Level.INFO, () -> transaction + " transaction executed.");
                transaction.setActive(false);
            }
            if(ready && gameBoard.isGameStarted()) gameBoard.setReady(false);
        }catch (IOException e) {
            ClientApplication.clientApplicationLogger.log(Level.SEVERE, "Client could not send last message to the server");
            setRunning(false);
        }
    }

    @Override
    public void run() {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try(Socket socket = new Socket(ipAddress, ServerApplication.PORT)) {
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            this.player = (Player) ois.readObject();
            this.player.setPlayerName(gameBoard.getUserName());
            this.gameBoard.setPlayerID(this.player.getPlayerID());
            this.gameBoard.setPlayer(new Player(this.player));
            this.clientSocket = socket;
            oos.writeObject(new Player(this.player));
            oos.flush();
            while(isRunning() && this.player.isOnline()) {
                sendPlayerStatusToTheServer(oos);
                ServerMessage serverMessage = (ServerMessage) ois.readObject();
                gameBoard.updateGameBoard(serverMessage);
                Thread.sleep(220);
            }
        } catch(IOException | ClassNotFoundException e) {
            ClientApplication.clientApplicationLogger.log(Level.SEVERE, "Client disconnected from the server");
            setRunning(false);
        } catch (InterruptedException e) {
            ClientApplication.clientApplicationLogger.log(Level.SEVERE, "Client disconnected from the server");
            setRunning(false);
            Thread.currentThread().interrupt();
        }
    }
}