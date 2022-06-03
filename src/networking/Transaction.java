package networking;

import java.io.Serializable;

public class Transaction implements Serializable {

    private boolean all = false;
    private boolean active = false;
    private int playerID;
    private int value;

    public Transaction() {
        this.active = false;
        this.all = false;
        this.playerID = 0;
        this.value = 0;
    }

    public Transaction(boolean all, boolean active, int playerID, int value) {
        this.all = all;
        this.active = active;
        this.playerID = playerID;
        this.value = value;
    }

    public Transaction(int value) {
        this(true, true, 0, value);
    }

    public Transaction(Transaction transaction) {
        this.value = transaction.getValue();
        this.all = transaction.isAll();
        this.playerID = transaction.getPlayerID();
        this.active = transaction.isActive();
    }

    public Transaction(int value, int playerID) {
        this(false, true, playerID, value);
    }

    @Override
    public String toString() {
        return "id: " + playerID + "value : " + value + "active: " + active;
    }
    public boolean isAll() {
        return all;
    }

    public void setAll(boolean all) {
        this.all = all;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
