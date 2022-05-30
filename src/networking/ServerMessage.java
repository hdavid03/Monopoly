package networking;

import game_elements.Player;

import java.io.Serializable;
import java.util.Queue;

public class ServerMessage implements Serializable {
    private Queue<Player> players;
    private boolean gameIsReady;
    private int nextPlayerID;

    public ServerMessage(Queue<Player> players, boolean gameIsReady, int nextPlayerID) {
        this.players = players;
        this.gameIsReady = gameIsReady;
        this.nextPlayerID = nextPlayerID;
    }

    public Queue<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Queue<Player> players) {
        this.players = players;
    }

    public boolean isGameIsReady() {
        return gameIsReady;
    }

    public void setGameIsReady(boolean gameIsReady) {
        this.gameIsReady = gameIsReady;
    }

    public int getNextPlayerID() {
        return nextPlayerID;
    }

    public void setNextPlayerID(int nextPlayerID) {
        this.nextPlayerID = nextPlayerID;
    }
}
