package networking;

import game_elements.Player;

import java.io.Serializable;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ServerMessage implements Serializable {
    private ConcurrentLinkedQueue<Player> players;
    private boolean gameIsReady;
    private int nextPlayerID;
    private int lap;

    public ServerMessage(ConcurrentLinkedQueue<Player> players, boolean gameIsReady, int nextPlayerID, int lap) {
        this.players = players;
        this.gameIsReady = gameIsReady;
        this.nextPlayerID = nextPlayerID;
        this.lap = lap;
    }

    public int getLap() {
        return lap;
    }

    public void setLap(int lap) {
        this.lap = lap;
    }

    public ConcurrentLinkedQueue<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ConcurrentLinkedQueue<Player> players) {
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
