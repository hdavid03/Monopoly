package networking;

import GUI.MonopolyGUI;
import client.ClientApplication;
import game_elements.Player;
import server.ServerApplication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;

public class ClientThread implements Runnable {
    private Socket socket;
    private Player player;
    private MonopolyGUI gameBoard;
    private boolean running;

    public ClientThread(MonopolyGUI gameBoard) {
        this.running = true;
        this.gameBoard = gameBoard;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
        if( !isRunning() && socket != null && !socket.isClosed() ) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Player getPlayer() {
        return player;
    }

    private void sendPlayerStatusToTheServer(ObjectOutputStream oos) {
        try {
            while(!gameBoard.isReady() && isRunning()) {
                Thread.sleep(500);
            }
            player.setMoney(player.getMoney() + 1);
            StatusMessage message = new StatusMessage(new Player(player), gameBoard.isReady());
            ClientApplication.clientApplicationLogger.log(Level.INFO, player::toString);
            oos.writeObject(message);
            oos.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
            setRunning(false);
        }
    }

    @Override
    public void run() {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try(Socket socket = new Socket(ServerApplication.HOST, ServerApplication.PORT)) {
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            this.player = (Player) ois.readObject();
            this.player.setPlayerName(gameBoard.getUserName());
            this.gameBoard.setPlayerID(this.player.getPlayerID());
            this.socket = socket;
            while(isRunning()) {
                sendPlayerStatusToTheServer(oos);
                ServerMessage serverMessage = (ServerMessage) ois.readObject();
                gameBoard.updateGameBoard(serverMessage);
            }
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
            setRunning(false);
        }
    }
}