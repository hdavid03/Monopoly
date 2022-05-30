package game_elements.table_fields;

import game_elements.Field;

public class FreeParkingField extends Field {

    public FreeParkingField(int fieldID) {
        super(fieldID);
    }

    @Override
    protected void action() {
        // ingyen parkolj
    }
}
