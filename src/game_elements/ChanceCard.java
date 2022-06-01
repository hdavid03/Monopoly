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
            case 5:
                player.setplayerpassgo(true);
                System.out.println("Setting player pass-go");
                System.out.println(player.getPlayerID() + " ID player setting playerpassgo " + player.getplayerpassgo());
                break;
            case 1:
                player.changeBalance(15);
                break;
            case 2:
                player.setOnFieldPosition(0);
                monopolyGUI.goingOnFields(0);
                break;
            case 3:
                player.setInJailTimer(3);
                player.setInJail(true);
                player.setOnFieldPosition(10);
                monopolyGUI.goingOnFields(10);
                break;
            case 4:
                player.changeBalance(150);
                break;
            case 6:
                player.setOnFieldPosition(player.getOnFieldPosition()-3);
                monopolyGUI.goingOnFields(player.getOnFieldPosition()-3);
                break;
            case 7:
                player.setOnFieldPosition(39);
                monopolyGUI.goingOnFields(39);
                break;
            case 8:
                player.setfreeJail(true);
                break;
            case 9:
                c = (int)(player.getOnFieldPosition()/20);
                c = (c+1)*12;
                player.setOnFieldPosition(c);
                monopolyGUI.goingOnFields(c);
                if(fields[c] instanceof UtilityField) {
                    if(!((UtilityField) fields[c]).getOwnership()) {
                        // Prompt Play to Buy
                        ((UtilityField) fields[c]).setOwnership(true);
                        ((UtilityField) fields[c]).setOwnerID(player.getPlayerID());
                        player.changeBalance(((UtilityField) fields[c]).getValue());
                    }
                    else {
                        int Die1 = player.throwDice();
                        int Die2 = player.throwDice();
                        player.changeBalance(-10*(Die1+Die2));
                        players.get(((UtilityField) fields[c]).getOwnerID()).changeBalance(10*(Die1+Die2));
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
                player.setOnFieldPosition(11);
                monopolyGUI.goingOnFields(11);
                if(player.getOnFieldPosition()>11) { player.changeBalance(200); }
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
                        int Die1 = player.throwDice();
                        int Die2 = player.throwDice();
                        player.changeBalance(-2*(Die1+Die2));
                        players.get(((RailRoadField) fields[c]).getOwnerID()).changeBalance(2*(Die1+Die2));
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
