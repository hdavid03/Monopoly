package game_elements;

import networking.Transaction;
import java.io.Serializable;
import java.util.ArrayList;

public class Player extends GameElement implements Serializable {

    private String playerName;
    private int playerID;
    private int onFieldPosition;
    private int inJailTimer;
    private int money;
    private int railRoadCounter;
    private int utilityCounter;
    private int houseCounter;
    private int hotelCounter;
    private int fieldID;
    private boolean isInJail;
    private boolean online;
    private boolean hasFreeJail;
    private boolean insolvency;
    private ArrayList<Integer> ownedFieldIDs;
    private String extras;
    private Transaction transaction;

    public Player(int playerID, int onFieldPosition, int money) {
        this.playerID = playerID;
        this.onFieldPosition = onFieldPosition;
        this.inJailTimer = 0;
        this.money = money;
        this.railRoadCounter = 0;
        this.utilityCounter = 0;
        this.houseCounter = 0;
        this.hotelCounter = 0;
        this.fieldID = 0;
        this.isInJail = false;
        this.online = true;
        this.hasFreeJail = false;
        this.insolvency = false;
        this.extras = "";
        this.ownedFieldIDs = new ArrayList<>();
        this.transaction = new Transaction();
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
        this.online = player.isOnline();
        this.hasFreeJail = player.getfreeJail();
        this.insolvency = player.isInsolvency();
        this.extras = player.getExtras();
        this.ownedFieldIDs = new ArrayList<>(player.getOwnedFieldIDs());
        this.transaction = new Transaction(player.getTransaction());
    }

    public String getExtras() {
        return extras;
    }

    public boolean isInsolvency() {
        return insolvency;
    }

    public void setInsolvency(boolean insolvency) {
        this.insolvency = insolvency;
    }

    public void setExtras(String string) {
        this.extras = string;
    }

    public ArrayList<Integer> getOwnedFieldIDs() {
        return ownedFieldIDs;
    }

    public void setOwnedFieldIDs(ArrayList<Integer> ownedFieldIDs) {
        this.ownedFieldIDs = ownedFieldIDs;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        if(!isInsolvency()) {
            this.transaction = transaction;
        }
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
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

    public int getHouseCounter() {
        return houseCounter;
    }

    public void setHouseCounter(int houseCounter) {
        this.houseCounter = houseCounter;
    }

    public int getHotelCounter() {
        return hotelCounter;
    }

    public void setHotelCounter(int hotelCounter) {
        this.hotelCounter = hotelCounter;
    }

    public void addOwnedFieldID(int fieldID) {
        this.ownedFieldIDs.add(fieldID);
    }

    @Override
    public String toString() {
        return "ID: " + this.playerID + " Name: " + this.playerName + " Money: " + this.money + " Owned field IDs : " + this.ownedFieldIDs;
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

    public void setInJail(boolean inJail) {
        isInJail = inJail;
    }

    public void changeBalance(int change) {
        if (Integer.signum(change) == -1 && Math.abs(change) > this.getMoney()) {
            this.setInsolvency(true);
        }
        this.setMoney(this.getMoney() + change);
    }

    public void startPassCheck(int newFieldID) {
        if (newFieldID < fieldID && !isInJail) {
            this.changeBalance(200);
        }
    }

    public void setFreeJail(boolean isFreeJail) {
        this.setExtras("Szabadulás");
        hasFreeJail = isFreeJail;
        inJailTimer = 0;
    }

    public boolean getfreeJail() {
        return hasFreeJail;
    }

}