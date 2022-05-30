package game_elements;

import java.util.ArrayList;
import java.util.List;

public class Card {

    int cardID;
    Field field;

    String type;

    public Card(String T) {
        this.type = T;
        this.cardID = (int)(Math.random()*16+1);
    };

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public void action(Player P,int CardNumber) {

    }
}
