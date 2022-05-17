package game_elements;

import java.util.ArrayList;
import java.util.List;

public class Card {

    int cardID;
    Field field;

    String type;

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public void action(Player P,int CardNumber) {
        if( this.type == "Surprise" ) {
            switch(CardNumber) {
                case 1: P.changeBalance(100);
                case 2: P.changeBalance(50);
                case 3: P.changeBalance(-50);
                case 4: P.changeBalance(100);
                case 5:
                    // Gyűjt be mindenkitől 10M-t, DE LEHETETLEN ÍGY IMPLEMENTÁLNI
                case 6:
                    // Házért 40M-t, szállodáért 115M-t, DE LEHETETLEN ÍGY IMPLEMENTÁLNI
                case 7: P.changeBalance(20);
                case 8:
                    // Get Out Of Jail, aktívan kell Player prompt-ra bejátszani, szóval nemtom
                case 9:
                    P.setInJailTimer(3); P.setInJail(true); P.setOnFieldPosition(/*JailPos*/);
                case 10: P.changeBalance(10);
                case 11: P.changeBalance(-50);
                case 12: P.changeBalance(200);
                case 13: P.changeBalance(100);
                case 14: P.changeBalance(-100);
                case 15: P.setOnFieldPosition(0); P.changeBalance(200);
                case 16: P.changeBalance(15);
            }
        }
        else if ( this.type == "Fortune" ) {
            switch(CardNumber) {
                case 1:
                    //Add to Player, give 200 when Pass Go
                case 2: P.changeBalance(15);
                case 3: P.setOnFieldPosition(0);
                case 4: P.setInJailTimer(3); P.setInJail(true); P.setOnFieldPosition(/*JailPos*/);
                case 5: P.changeBalance(150);
                case 6:
                    //When Pass Go, pick up 200, de ez mindden Pass-nál történik szóval miafasz?
                case 7: P.setOnFieldPosition(P.getOnFieldPosition()-3);
                case 8: P.setOnFieldPosition(/*Dunakorzó*/);
                case 9:
                    // Get Out Of Jail
                case 10:
                    // min(d(Player,Közmű)), megvásárolható vagy 10*(Roll1+Roll2) tulajnak
                case 11:
                    // Házért 25M-t, szállodáért 100M-t, DE LEHETETLEN ÍGY IMPLEMENTÁLNI
                case 12:
                    // Mindenkinek 50M-t, DE ÍGY LEHETETLEN IMPLEMENTÁLNI
                case 13: P.setOnFieldPosition(); // If Pass Go, P.changeBalance(200);
                case 14:
                    // min(d(Player,Vasút)), megvásárolható vagy 2*rent() tulajnak
                case 15:
                    // min(d(Player,Vasút)), megvásárolható vagy 2*rent() tulajnak
                case 16: P.changeBalance(50);
            }
        }
    }
}
