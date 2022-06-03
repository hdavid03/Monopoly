package game_elements;

import GUI.MonopolyGUI;
import game_elements.table_fields.property_fields.RailRoadField;
import game_elements.table_fields.property_fields.StreetField;
import game_elements.table_fields.property_fields.UtilityField;
import networking.Transaction;

import javax.swing.*;
import java.util.ArrayList;

public class ChanceCard extends Card {

    @Override
    public void action(Player player, int cardID, ArrayList<Player> players, Field[] fields, MonopolyGUI monopolyGUI) {
        switch(cardID) {
            case 0:
                monopolyGUI.goingOnFields(24);
                break;
            case 1:
                player.changeBalance(15);
                break;
            case 2:
                monopolyGUI.goingOnFields(0);
                break;
            case 3:
                player.setInJailTimer(3);
                player.setInJail(true);
                monopolyGUI.goingOnFields(10);
                break;
            case 4:
                player.changeBalance(150);
                break;
            case 5:
                monopolyGUI.goingOnFields(5);
                break;
            case 6:
                monopolyGUI.goingOnFields(player.getFieldID()-3);
                break;
            case 7:
                monopolyGUI.goingOnFields(39);
                break;
            case 8:
                if(player.isInJail()) {
                    player.setInJail(false);
                    player.setInJailTimer(0);
                } else {
                    player.setFreeJail(true);
                }
                break;
            case 9:
                int fieldID = player.getFieldID();
                int newFieldID = (Math.abs(fieldID - 12) > (Math.abs(fieldID - 28))) ? 28 : 12;
                monopolyGUI.goingOnFields(newFieldID);
                if(fields[newFieldID] instanceof UtilityField utilityField) {
                    if(utilityField.isThereOwner()) {
                        monopolyGUI.popUpMessage("Dobj egy számot, és fizess a tulajdonosnak!", JOptionPane.INFORMATION_MESSAGE);
                        monopolyGUI.setUserInterAction(true);
                        monopolyGUI.setThrowButton();
                    }
                }
                break;
            case 10:
                player.changeBalance(-25*player.getHouseCounter()-100*player.getHotelCounter());
                break;
            case 11:
                player.setTransaction(new Transaction(50));
                player.changeBalance(- (players.size() - 1) * 50);
                break;
            case 12:
                monopolyGUI.goingOnFields(11);
                break;
            case 13:
            case 14:
                //vasútak: 5, 15, 25, 35
                int fieldID2 = player.getFieldID();
                int[] distances = {Math.abs(fieldID2 - 5), Math.abs(fieldID2 - 15), Math.abs(fieldID2 - 25), Math.abs(fieldID2 - 35)};
                int min = distances[0];
                int newFieldID2 = 5;
                for(int i = 1; i < distances.length; i++) {
                    if(distances[i] < min) {
                        min = distances[i];
                        newFieldID2 = 5 + i*10;
                    }
                }
                monopolyGUI.goingOnFields(newFieldID2);
                break;
            case 15:
                player.changeBalance(50);
                break;
            default:
                System.out.println("HIBA");
        }

    }
}
