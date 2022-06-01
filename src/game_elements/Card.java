package game_elements;

import GUI.MonopolyGUI;

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
    public void action(Player player, int cardID, ArrayList<Player> players, Field[] fields, MonopolyGUI monopolyGUI){

    }
}
