package game_elements;

import GUI.MonopolyGUI;
import client.ClientApplication;
import game_elements.table_fields.property_fields.StreetField;
import networking.Transaction;

import java.util.ArrayList;
import java.util.logging.Level;

public class SurpriseCard extends Card {

    @Override
    public void action(Player player, int cardID, ArrayList<Player> players, Field[] fields, MonopolyGUI monopolyGUI) {
        switch(cardID) {
            case 0, 3, 12 -> player.changeBalance(100);
            case 1 -> player.changeBalance(50);
            case 2, 10 -> player.changeBalance(-50);
            case 4 -> {
                player.changeBalance(players.size() * 10);
                player.setTransaction(new Transaction(-10));
            }
            case 5 -> player.changeBalance(-40*player.getHouseCounter()-115*player.getHotelCounter());
            case 6 -> player.changeBalance(20);
            case 7 -> {
                if (player.isInJail()) {
                    player.setInJail(false);
                    player.setInJailTimer(0);
                } else {
                    player.setFreeJail(true);
                }
            }
            case 8 -> {
                player.setInJailTimer(3);
                player.setInJail(true);
                monopolyGUI.goingOnFields(10);
            }
            case 9 -> player.changeBalance(10);
            case 11 -> player.changeBalance(200);
            case 13 -> player.changeBalance(-100);
            case 14 -> monopolyGUI.goingOnFields(0);
            case 15 -> player.changeBalance(25);
            default -> ClientApplication.clientApplicationLogger.log(Level.SEVERE, () -> "Error in the action of SurpriseCard method.");
        }

    }
}
