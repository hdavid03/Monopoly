package game_elements;

import java.io.Serializable;

public class Player extends GameElement implements Serializable {

    private int playerID;
    private int onFieldPosition;
    private int inJailTimer;
    private int money;
    private int railRoadCounter;
    private int utilityCounter;
    private int fieldID;
    private boolean isInJail;

    public Player(int playerID, int onFieldPosition, int money) {
        this.playerID = playerID;
        this.onFieldPosition = onFieldPosition;
        this.inJailTimer = 0;
        this.money = money;
        this.railRoadCounter = 0;
        this.utilityCounter = 0;
        this.fieldID = 0;
        this.isInJail = false;
    }

    @Override
    public String toString() {
        return "ID: " + this.playerID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public int getOnFieldPosition() {
        return onFieldPosition;
    }

    public void setOnFieldPosition(int onFieldPosition) {
        this.onFieldPosition = onFieldPosition;
    }

    public int getInJailTimer() {
        return inJailTimer;
    }

    public void setInJailTimer(int inJailTimer) {
        this.inJailTimer = inJailTimer;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getRailRoadCounter() {
        return railRoadCounter;
    }

    public void setRailRoadCounter(int railRoadCounter) {
        this.railRoadCounter = railRoadCounter;
    }

    public int getUtilityCounter() {
        return utilityCounter;
    }

    public void setUtilityCounter(int utilityCounter) {
        this.utilityCounter = utilityCounter;
    }

    public int getFieldID() {
        return fieldID;
    }

    public void setFieldID(int fieldID) {
        this.fieldID = fieldID;
    }

    public boolean isInJail() {
        return isInJail;
    }

    public void setInJail(boolean inJail) {
        isInJail = inJail;
    }
}
