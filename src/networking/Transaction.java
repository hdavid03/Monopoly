package networking;

public class Transaction {

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
        new Transaction(true, true, 0, value);
    }

    public Transaction(int value, int playerID) {
        new Transaction(false, true, playerID, value);
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
