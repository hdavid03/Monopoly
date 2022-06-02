package game_elements;

import GUI.MonopolyGUI;
import game_elements.table_fields.property_fields.RailRoadField;
import game_elements.table_fields.property_fields.StreetField;
import game_elements.table_fields.property_fields.UtilityField;

import java.util.ArrayList;

public class ChanceCard extends Card {

    @Override
    public void action(Player player, int cardID, ArrayList<Player> players, Field[] fields, MonopolyGUI monopolyGUI) {
        int c;

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
                player.startPassCheck(5);
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
                break;
            case 10:
                int Houses = 0; //25M
                int Hotels = 0; //100M
                for(int i=0;i<40;i++) {
                    if(fields[i] instanceof StreetField streetField) {
                        if(streetField.getOwnerID()==player.getPlayerID()) {
                            if(streetField.isThereHotel()) {
                                Hotels++;
                            } else {
                                Houses += streetField.getHouseCounter();
                            }
                        }
                    }
                }
                player.changeBalance(-25*Houses-100*Hotels);
                break;
            case 11:
                //meglepetes12-es kép
                //mindenkinek fizetsz 50milkát, te írod!!!!!
                //kommentbe Bence things
                /*
                for(int i=0;i<players.size();i++) {
                    if(players.get(i).getPlayerID()!=player.getPlayerID()) {
                        player.changeBalance(-50);
                        players.get(i).changeBalance(50);
                    }
                }*/
                break;
            case 12:
                player.startPassCheck(11);
                monopolyGUI.goingOnFields(11);
                break;
            case 13:
            case 14:
                //vasútak: 5, 15, 25, 35
                int newFieldID2 = player.getFieldID();
                newFieldID2 = Math.abs(newFieldID2 - 5);
                if((Math.abs(newFieldID2 - 15)) < newFieldID2){
                    newFieldID2 = Math.abs(newFieldID2 - 15);
                    player.setFieldID(15);
                }
                if((Math.abs(newFieldID2 - 25)) < newFieldID2){
                    newFieldID2 = Math.abs(newFieldID2 - 25);
                    player.setFieldID(25);
                }
                if((Math.abs(newFieldID2 - 35)) < newFieldID2){
                    player.setFieldID(35);
                }
                break;
            case 15:
                player.changeBalance(50);
                break;
            default:
                System.out.println("HIBA");
        }

    }
}
