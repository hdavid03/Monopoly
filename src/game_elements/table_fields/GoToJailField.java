package game_elements.table_fields;

import game_elements.Field;

public class GoToJailField extends Field {

    public GoToJailField(int Pos) {
        super(Pos);
    }

    @Override
    protected void action() {
        //Ez itten nem tud SEMMIT se csinálni. Az ilyen logikát csak main-ben (UI) lehet ütemezni
    }
}
