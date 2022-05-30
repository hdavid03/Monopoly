package game_elements;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player extends GameElement implements Serializable {

    private String playerName;
    private int playerID;
    private int onFieldPosition;
    private int inJailTimer;
    private int money;
    private int railRoadCounter;
    private int utilityCounter;
    private ArrayList<String> extras;
    private int fieldID;
    private boolean isInJail;
    private boolean playerOnline;

    public Player(int playerID, int onFieldPosition, int money) {
        this.playerID = playerID;
        this.onFieldPosition = onFieldPosition;
        this.inJailTimer = 0;
        this.money = money;
        this.railRoadCounter = 0;
        this.utilityCounter = 0;
        this.fieldID = 0;
        this.isInJail = false;
        this.playerOnline = true;
        this.extras = new ArrayList<>();
    }

    public Player(Player player) {
        this.playerID = player.getPlayerID();
        this.playerName = player.getPlayerName();
        this.onFieldPosition = player.getOnFieldPosition();
        this.inJailTimer = player.getInJailTimer();
        this.money = player.getMoney();
        this.railRoadCounter = player.getRailRoadCounter();
        this.utilityCounter = player.getUtilityCounter();
        this.fieldID = player.getFieldID();
        this.isInJail = player.isInJail();
    }

    public List<String> getExtras() {
        return extras;
    }

    public void setExtras(ArrayList<String> extras) {
        this.extras = extras;
    }

    public boolean isOffline() {
        return !playerOnline;
    }

    public void setPlayerOnline(boolean playerOnline) {
        this.playerOnline = playerOnline;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public boolean isInJail() {
        return isInJail;
    }

    public Player(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public String toString() {
        return "ID: " + this.playerID + "Name: " + this.playerName + " Money: " + this.money;
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

    public boolean getIsInJail() {
        return isInJail;
    }

    public void setInJail(boolean inJail) {
        isInJail = inJail;
    }

    public void changeBalance(int change) { this.setMoney(this.getMoney()+change); }

    public int throwDice() { return (int)(Math.random()*6 + 1); }

    public boolean startPassCheck(int Move) {
        return this.getOnFieldPosition() + Move >= 40;
    }

}