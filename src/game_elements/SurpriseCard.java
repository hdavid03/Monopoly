package game_elements;

import GUI.MonopolyGUI;
import game_elements.table_fields.property_fields.StreetField;

import java.util.ArrayList;

public class SurpriseCard extends Card {

    @Override
    public void action(Player player, int cardID, ArrayList<Player> players, Field[] fields, MonopolyGUI monopolyGUI) {
        switch(cardID) {
            case 0:
            case 3:
            case 12:
                player.changeBalance(100);
                break;
            case 1:
                player.changeBalance(50);
                break;
            case 2:
            case 10:
                player.changeBalance(-50);
                break;
            case 4:
                for(int i=0;i<players.size();i++) {
                    if(players.get(i).getPlayerID()!=player.getPlayerID()) {
                        players.get(i).changeBalance(-10);
                        player.changeBalance(10);
                    }
                }
                break;
            case 5:
                int Houses = 0; //40M
                int Hotels = 0; //115M
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
                player.changeBalance(-40*Houses-115*Hotels);
                break;
            case 6:
                player.changeBalance(20);
                break;
            case 7:
                player.setFreeJail(true);
                break;
            case 8:
                player.setInJailTimer(3);
                player.setInJail(true);
                player.setOnFieldPosition(10);
                monopolyGUI.goingOnFields(10);
                break;
            case 9:
                player.changeBalance(10);
                break;
            case 11:
                player.changeBalance(200);
                break;
            case 13:
                player.changeBalance(-100);
                break;
            case 14:
                player.setOnFieldPosition(0);
                monopolyGUI.goingOnFields(0);
                player.changeBalance(200);
                break;
            case 15:
                player.changeBalance(15);
                break;
            default:
                System.out.println("HIBA");
        }
    }
}
