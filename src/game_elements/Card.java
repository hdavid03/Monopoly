package game_elements;

import java.util.ArrayList;
import java.util.List;

public abstract class Card {

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
    }
}
