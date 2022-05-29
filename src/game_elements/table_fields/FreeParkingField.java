package game_elements.table_fields;

import game_elements.Field;

public class FreeParkingField extends Field {

    public FreeParkingField(int Pos) {
        super(Pos);
    }

    @Override
    protected void action() {
        // ingyen parkolj
    }
}
