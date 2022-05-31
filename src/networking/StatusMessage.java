package networking;

import game_elements.Player;

import java.io.Serializable;

public class StatusMessage implements Serializable {
    private Player player;
    private boolean ready;
    private boolean gameBoardIsUpdated;

    public StatusMessage(Player player, boolean ready, boolean gameBoardIsUpdated) {
        this.player = player;
        this.ready = ready;
        this.gameBoardIsUpdated = gameBoardIsUpdated;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }
}
