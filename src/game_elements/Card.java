package game_elements;

import GUI.MonopolyGUI;

import java.util.ArrayList;
import java.util.List;

public abstract class Card {

    Field field;

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public abstract void action(Player player, int cardID, ArrayList<Player> players, Field[] fields, MonopolyGUI monopolyGUI);
}
