import game_elements.Card;
import game_elements.Field;
import game_elements.Player;
import game_elements.Table;
import game_elements.table_fields.*;
import game_elements.table_fields.property_fields.Color;
import game_elements.table_fields.property_fields.RailRoadField;
import game_elements.table_fields.property_fields.StreetField;
import game_elements.table_fields.property_fields.UtilityField;

import java.util.ArrayList;
import java.util.Objects;

public class main {

    static void CardAction(Player P, int C, String T, ArrayList<Player> PA, ArrayList<Field> F) {
        if(Objects.equals(T, "Surprise")) {
            switch(C) {
                case 1:
                case 4:
                case 13:
                    P.changeBalance(100);
                    break;
                case 2:
                    P.changeBalance(50);
                    break;
                case 3:
                case 11:
                    P.changeBalance(-50);
                    break;
                case 5:
                    for(int i=0;i<PA.size();i++) {
                        if(PA.get(i).getPlayerID()!=P.getPlayerID()) {
                            PA.get(i).changeBalance(-10);
                            P.changeBalance(10);
                        }
                    }
                    break;
                case 6:
                    int Houses = 0; //40M
                    int Hotels = 0; //115M
                    for(int i=0;i<40;i++) {
                        if(F.get(i) instanceof StreetField) {
                            if(((StreetField) F.get(i)).getOwnerID()==P.getPlayerID()) {
                                if(((StreetField) F.get(i)).isThereHotel()) {
                                    Hotels++;
                                } else {
                                    Houses += ((StreetField) F.get(i)).getHouseCounter();
                                }
                            }
                        }
                    }
                    P.changeBalance(-40*Houses-115*Hotels);
                    break;
                case 7:
                    P.changeBalance(20);
                    break;
                case 8:
                    P.setGOJFcount(P.getGOJFcount()+1);
                    break;
                case 9:
                    P.setInJailTimer(3); P.setInJail(true); P.setOnFieldPosition(10);
                    break;
                case 10:
                    P.changeBalance(10);
                    break;
                case 12:
                    P.changeBalance(200);
                    break;
                case 14:
                    P.changeBalance(-100);
                    break;
                case 15:
                    P.setOnFieldPosition(0); P.changeBalance(200);
                    break;
                case 16:
                    P.changeBalance(15);
                    break;
            }
        }
        else if (Objects.equals(T, "Fortune")) {
            switch(C) {
                case 1:
                case 6:
                    P.setFortune(P.getFortune()+1);
                    break;
                case 2:
                    P.changeBalance(15);
                    break;
                case 3:
                    P.setOnFieldPosition(0);
                    break;
                case 4:
                    P.setInJailTimer(3); P.setInJail(true); P.setOnFieldPosition(10);
                    break;
                case 5:
                    P.changeBalance(150);
                    break;
                case 7:
                    P.setOnFieldPosition(P.getOnFieldPosition()-3);
                    break;
                case 8:
                    P.setOnFieldPosition(39);
                    break;
                case 9:
                    P.setGOJFcount(P.getGOJFcount()+1);
                    break;
                case 10:
                    int c = (int)(P.getOnFieldPosition()/20);
                    c = (c+1)*12;
                    P.setOnFieldPosition(c);
                    if(F.get(c) instanceof UtilityField) {
                        if(!((UtilityField) F.get(c)).getOwnership()) {
                            // Prompt Play to Buy
                            ((UtilityField) F.get(c)).setOwnership(true);
                            ((UtilityField) F.get(c)).setOwnerID(P.getPlayerID());
                            P.changeBalance(((UtilityField) F.get(c)).getValue());
                        }
                        else {
                            int Die1 = P.throwDice();
                            int Die2 = P.throwDice();
                            P.changeBalance(-10*(Die1+Die2));
                            PA.get(((UtilityField) F.get(c)).getOwnerID()).changeBalance(10*(Die1+Die2));
                        }
                    }
                    break;
                case 11:
                    int Houses = 0; //25M
                    int Hotels = 0; //100M
                    for(int i=0;i<40;i++) {
                        if(F.get(i) instanceof StreetField) {
                            if(((StreetField) F.get(i)).getOwnerID()==P.getPlayerID()) {
                                if(((StreetField) F.get(i)).isThereHotel()) {
                                    Hotels++;
                                } else {
                                    Houses += ((StreetField) F.get(i)).getHouseCounter();
                                }
                            }
                        }
                    }
                    P.changeBalance(-25*Houses-100*Hotels);
                    break;
                case 12:
                    for(int i=0;i<PA.size();i++) {
                        if(PA.get(i).getPlayerID()!=P.getPlayerID()) {
                            P.changeBalance(-50);
                            PA.get(i).changeBalance(50);
                        }
                    }
                    break;
                case 13:
                    P.setOnFieldPosition(11);
                    if(P.getOnFieldPosition()>11) { P.changeBalance(200); }
                    break;
                case 14:
                case 15:
                    // min(d(Player,Vasút)), megvásárolható vagy 2*rent() tulajnak
                    int c = (int)(P.getOnFieldPosition()/10);
                    c = (c+1)*5;
                    P.setOnFieldPosition(c);
                    if(F.get(c) instanceof RailRoadField) {
                        if(!((RailRoadField) F.get(c)).getOwnership()) {
                            // Prompt Player to Buy
                            ((RailRoadField) F.get(c)).setOwnership(true);
                            ((RailRoadField) F.get(c)).setOwnerID(P.getPlayerID());
                            P.changeBalance(-((RailRoadField) F.get(c)).getValue());
                        }
                        else {
                            int Die1 = P.throwDice();
                            int Die2 = P.throwDice();
                            P.changeBalance(-2*(Die1+Die2));
                            PA.get(((RailRoadField) F.get(c)).getOwnerID()).changeBalance(2*(Die1+Die2));
                        }
                    }
                    break;
                case 16:
                    P.changeBalance(50);
                    break;
            }
        }
    }

    public static void main(String[] args) {

        GUI.LaunchPage launchPage = new GUI.LaunchPage();

        //Placeholder PlayerArray
        ArrayList<Player> PlayerArray = new ArrayList<>(3);

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

            Player p = PlayerArray.get(i);

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
                            if(p.getFortune()>0) {
                                p.setFortune(p.getFortune()-1);
                                p.changeBalance(200);
                            }
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
                    if(p.getGOJFcount()>0) {
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
                    int CID = (int)(Math.random()*16+1);
                    CardAction(p,CID,"Chance",PlayerArray,Fields);
                }
                // Community Chest Field
                if(Fields[pos] instanceof CommunityChestField) {
                    /*Prompt Player to Draw Surprise*/
                    //Draw Card, Effect Card
                    int CID = (int)(Math.random()*16+1);
                    CardAction(p,CID,"Surprise",PlayerArray,Fields);
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
                        for(int i=0;i<40;i++) {
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
}
