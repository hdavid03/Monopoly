import game_elements.Field;
import game_elements.Player;
import game_elements.Table;
import game_elements.table_fields.*;
import game_elements.table_fields.property_fields.Color;
import game_elements.table_fields.property_fields.RailRoadField;
import game_elements.table_fields.property_fields.StreetField;
import game_elements.table_fields.property_fields.UtilityField;

import java.util.ArrayList;

public class main {

    public static void main(String[] args) {

        GUI.LaunchPage launchPage = new GUI.LaunchPage();


        Table table = new Table;

        Field[] Fields = new Field[40];
        for(int i=0;i<40;i++) {
            // Go Field
            if(i==0) { new GoField(i); }
            // Jail Field
            if(i==10) { Fields[i] = new JailField(i); }
            // Free Parking Field
            if(i==20) { Fields[i] = new FreeParkingField(i); }
            // GoToJail Field
            if(i==30) { Fields[i] = new GoToJailField(i); }
            // CC Field
            if(i==2 || i==17 || i==33) { Fields[i] = new CommunityChestField(i); }
            // Chance Field
            if(i==7 || i==22 || i==36) { Fields[i] = new ChanceField(i); }
            // Tax Field
            if(i==4 || i==38) { Fields[i] = new TaxField(i,100); }

            // Utilities Field
            if(i==12 || i==28) { Fields[i] = new UtilityField(i,150); }
            // Railroad Field
            if(i==5 || i==15 || i== 25 || i==35) { Fields[i] = new RailRoadField(i,200,25); }

            //Streets
            if(i==1 || i==3) {
                Fields[i] = new StreetField(i,Color.BROWN,50,250);
            }
            if(i==6 || i==8 || i==9) {
                Fields[i] = new StreetField(i, Color.WHITE,50,250);
            }
            if(i==11 || i==13 || i==14) {
                Fields[i] = new StreetField(i,Color.PURPLE,100,500);
            }
            if(i==16 || i==18 || i==19) {
                Fields[i] = new StreetField(i,Color.ORANGE,100,500);
            }
            if(i==21 || i==23 || i==24) {
                Fields[i] = new StreetField(i,Color.ORANGE,150,750);
            }
            if(i==26 || i==27 || i==29) {
                Fields[i] = new StreetField(i,Color.YELLOW,150,750);
            }
            if(i==31 || i==32 || i==34) {
                Fields[i] = new StreetField(i,Color.GREEN,200,1000);
            }
            if(i==37 || i==39) {
                Fields[i] = new StreetField(i,Color.BLUE,200,1000);
            }
        }


        // Turn iteration all Players
        for(int i=0;i<PlayerArray.size();i++) {

            Player p;   // p = PlayerArray.get(i)

            if( p.getIsInJail() ) {
                p.setInJailTimer(p.getInJailTimer()-1);
            }
            else {

                //Check JailTimer, release IF 0
                if( p.getInJailTimer() == 0 ) { p.setInJail(false); }

                //Dice rolling, up to 3 doubles then Jail
                int D1 = 0; int D2 = 0; int Rolled = 0;
                while( D1==D2 && !p.isInJail() ) {
                    int Die1 = p.throwDice();
                    int Die2 = p.throwDice();
                    Rolled++;

                    if(Rolled==3) { p.setInJail(true); p.setInJailTimer(3); }
                    else {
                        if(p.startPassCheck(Die1+Die2)) {
                            p.setOnFieldPosition(p.getOnFieldPosition()+Die1+Die2-40);
                            p.changeBalance(200);
                        }
                        else { p.setOnFieldPosition(p.getOnFieldPosition()+Die1+Die2); }
                    }

                    D1 = Die1; D2 = Die2;
                }

                // CheckField
                int pos = p.getOnFieldPosition();
                // Go Field
                if(Fields[pos] instanceof GoField) {
                    p.changeBalance(200);
                }
                // Free Parking
                    // Nothing haha
                // Jail
                    // Nothing haha
                // GoToJail Field
                if(Fields[pos] instanceof GoToJailField) {
                    if(/*Player has GOOJF Card*/) {
                        //Prompt to play card OR NOT
                        if(/*Player doesn't play card*/) {
                            p.setInJail(true);
                            p.setInJailTimer(3);
                        }
                    }
                    else {
                        p.setInJail(true);
                        p.setInJailTimer(3);
                    }
                }
                // Tax Field
                if(Fields[pos] instanceof TaxField) {
                    p.changeBalance(-((TaxField) Fields[i]).getValue());
                }

                // Chance Field
                if(Fields[pos] instanceof ChanceField) {
                    /*Prompt Player to Draw Chance*/
                    //Draw Card, effect Card
                }
                // Community Chest Field
                if(Fields[pos] instanceof CommunityChestField) {
                    /*Prompt Player to Draw Chance*/
                    //Draw Card, Effect Card
                }

                // Utilities
                if(Fields[pos] instanceof UtilityField) {
                    if(!((UtilityField) Fields[pos]).getOwnership()) {
                        //Prompt Player to buy
                        if(/*Player buys*/) {
                            p.changeBalance(-((UtilityField) Fields[pos]).getValue());
                            ((UtilityField) Fields[pos]).setOwnership(true);
                            ((UtilityField) Fields[pos]).setOwnerID(p.getPlayerID());
                        }
                    } else if (((UtilityField) Fields[pos]).getOwnerID()==p.getPlayerID()) {
                        //Prompt Player sell
                        if(/*Player sells*/) {
                            p.changeBalance(((UtilityField) Fields[pos]).getValue());
                            ((UtilityField) Fields[pos]).setOwnership(false);
                        }
                    } else if(((UtilityField) Fields[pos]).getOwnerID()!=p.getPlayerID()) {
                        int owned=0;
                        for(int i=0;i<40;i++) {
                            if(Fields[i] instanceof UtilityField && ((UtilityField) Fields[i]).getOwnership()) {
                                owned++;
                            }
                        }
                        //Prompt dice roll player, get Die1, Die2 value
                        p.changeBalance( -( 4+6*((owned==2)?1:0) )/*(Die1+Die2)*/ );

                    }
                }
                // Railroads
                if(Fields[pos] instanceof RailRoadField) {
                    if(!((RailRoadField) Fields[pos]).getOwnership()) {
                        //Prompt Player to buy
                        if(/*Player buys*/) {
                            p.changeBalance(-((RailRoadField) Fields[pos]).getValue());
                            ((RailRoadField) Fields[pos]).setOwnership(true);
                            ((RailRoadField) Fields[pos]).setOwnerID(p.getPlayerID());
                        }
                    } else if (((RailRoadField) Fields[pos]).getOwnerID()==p.getPlayerID()) {
                        //Prompt Player sell
                        if (/*Player sells*/) {
                            p.changeBalance(((RailRoadField) Fields[pos]).getValue());
                            ((RailRoadField) Fields[pos]).setOwnership(false);
                        }
                    } else if (((RailRoadField) Fields[pos]).getOwnerID()!=p.getPlayerID()) {
                        int owned=0;
                        for() {
                            if(Fields[i] instanceof RailRoadField && ((RailRoadField) Fields[i]).getOwnership()) {
                                owned++;
                            }
                        }
                        p.changeBalance(-((RailRoadField) Fields[pos]).getRent()*2^(owned-1));
                    }
                }
                // Streets                          "You are now about to witness the strength of Street Knowledge"
                if(Fields[pos] instanceof StreetField) {
                    if(!((StreetField) Fields[pos]).getOwnership()) {
                        //Prompt player to Buy
                        if(/*Player buys*/) {
                            p.changeBalance(-((StreetField) Fields[pos]).getEEnumerator().price);
                            ((StreetField) Fields[pos]).setOwnership(true);
                            ((StreetField) Fields[pos]).setOwnerID(p.getPlayerID());
                        }
                    } else if (((StreetField) Fields[pos]).getOwnerID()==p.getPlayerID()) {
                        //Prompt to Sell
                        if(/*Player sells*/) {
                            if(((StreetField) Fields[pos]).isThereHotel()) {
                                ((StreetField) Fields[pos]).setHotel(false);
                                p.changeBalance(((StreetField) Fields[pos]).getHotelBuildCost()/2);
                            } else if (((StreetField) Fields[pos]).getHouseCounter()>0) {
                                ((StreetField) Fields[pos]).setHouseCounter(0);
                                p.changeBalance(((StreetField) Fields[pos]).getHouseBuildCost()*((StreetField) Fields[pos]).getHouseCounter()/2);
                            }
                            p.changeBalance(((StreetField) Fields[pos]).getEEnumerator().price/2);
                        }
                        //ALTERNATE BRANCH
                        //Prompt to Develop
                        if(((StreetField) Fields[pos]).getHouseCounter()<4) {
                            // Prompt build house
                            if(/*Player builds house*/) {
                                p.changeBalance(-((StreetField) Fields[pos]).getHouseBuildCost());
                                ((StreetField) Fields[pos]).setHouseCounter(((StreetField) Fields[pos]).getHouseCounter()+1);
                            }
                        } else if (((StreetField) Fields[pos]).getHouseCounter()<4 && !((StreetField) Fields[pos]).isThereHotel()) {
                            // Prompt hotel building
                            if(/*Player Builds*/) {
                                ((StreetField) Fields[pos]).setHouseCounter(0);
                                ((StreetField) Fields[pos]).setThereHotel(true);
                                p.changeBalance(-((StreetField) Fields[pos]).getHotelBuildCost());
                            }
                        }
                    } else if (((StreetField) Fields[pos]).getOwnerID()!=p.getPlayerID()) {
                        int rent=0;
                        if(((StreetField) Fields[pos]).getHouseCounter()==0) {
                            rent = ((StreetField) Fields[pos]).getEEnumerator().rent;
                        } else if (((StreetField) Fields[pos]).getHouseCounter()<=4) {
                            switch(((StreetField) Fields[pos]).getHouseCounter()) {
                                case 1 -> rent = ((StreetField) Fields[pos]).getEEnumerator().rentWith1House;
                                case 2 -> rent = ((StreetField) Fields[pos]).getEEnumerator().rentWith2House;
                                case 3 -> rent = ((StreetField) Fields[pos]).getEEnumerator().rentWith3House;
                                case 4 -> rent = ((StreetField) Fields[pos]).getEEnumerator().rentWith4House;
                            }
                        } else if (((StreetField) Fields[pos]).isThereHotel()) {
                            rent = ((StreetField) Fields[pos]).getEEnumerator().rentWithHotel;
                        }
                        p.changeBalance(-rent);
                        Table.getPlayerByID(((StreetField) Fields[pos]).getOwnerID()).changeBalance(rent);
                    }
                }

            }

        }
    }

    /*
    *
    * SETUP ALL DATABASE
    *   Cards
    *   Board
    *   Players
    *
    * Turn order pseudocode
    *
    * FOR p=1:Num(Players)      // MATLAB style array indexing
    *
    *       CheckField
    *           IF DrawCard -> Draw
    *               EFFECT Card
    *                   CHANGE Player STATE
    *           IF CHANGE Player STATE THEN CHANGE Player STATE
    *
    * END
    *
    * */
}
