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
                ///meglepetes5
                //minden játékostól begyűjteni 50 milkát



                //Bence stuff kommentbe
                /*
                for(int i=0;i<players.size();i++) {
                    if(players.get(i).getPlayerID()!=player.getPlayerID()) {
                        players.get(i).changeBalance(-10);
                        player.changeBalance(10);
                    }
                }
                */
                break;
            case 5:
                int Houses = 0; //40M
                int Hotels = 0; //115M
                    for (int i = 0; i < 40; i++) {
                        if (fields[i] instanceof StreetField streetField) {
                            if (streetField.getOwnerID() == player.getPlayerID()) {
                                if (streetField.isThereHotel()) {
                                    Hotels++;
                                } else {
                                    Houses += streetField.getHouseCounter();
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
                if(player.isInJail()) {
                    player.setInJail(false);
                    player.setInJailTimer(0);
                } else {
                    player.setFreeJail(true);
                }
                break;
            case 8:
                player.setInJailTimer(3);
                player.setInJail(true);
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
                monopolyGUI.goingOnFields(0);
                break;
            case 15:
                player.changeBalance(25);
                break;
            default:
                System.out.println("HIBA");
        }

    }
}
