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
                if(fields[newFieldID] instanceof UtilityField utilityField) {
                    if(!utilityField.getOwnership()) {
                        // Prompt Play to Buy
                        ((UtilityField) fields[fieldID]).setOwnership(true);
                        ((UtilityField) fields[fieldID]).setOwnerID(player.getPlayerID());
                        player.changeBalance(((UtilityField) fields[fieldID]).getValue());
                    }
                    else {
                    }
                }
                break;
            case 10:
                int Houses = 0; //25M
                int Hotels = 0; //100M
                for(int i=0;i<40;i++) {
                    if(fields[i] instanceof StreetField) {
                        if(((StreetField) fields[i]).getOwnerID()==player.getPlayerID()) {
                            if(((StreetField) fields[i]).isThereHotel()) {
                                Hotels++;
                            } else {
                                Houses += ((StreetField) fields[i]).getHouseCounter();
                            }
                        }
                    }
                }
                player.changeBalance(-25*Houses-100*Hotels);
                break;
            case 11:
                for(int i=0;i<players.size();i++) {
                    if(players.get(i).getPlayerID()!=player.getPlayerID()) {
                        player.changeBalance(-50);
                        players.get(i).changeBalance(50);
                    }
                }
                break;
            case 12:
                player.startPassCheck(11);
                player.setOnFieldPosition(11);
                monopolyGUI.goingOnFields(11);
                break;
            case 13:
            case 14:
                // min(d(Player,Vasút)), megvásárolható vagy 2*rent() tulajnak
                c = (int)(player.getOnFieldPosition()/10);
                c = (c+1)*5;
                player.setOnFieldPosition(c);
                monopolyGUI.goingOnFields(c);
                if(fields[c] instanceof RailRoadField) {
                    if(!((RailRoadField) fields[c]).getOwnership()) {
                        // Prompt Player to Buy
                        ((RailRoadField) fields[c]).setOwnership(true);
                        ((RailRoadField) fields[c]).setOwnerID(player.getPlayerID());
                        player.changeBalance(-((RailRoadField) fields[c]).getValue());
                    }
                    else {
                    }
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
