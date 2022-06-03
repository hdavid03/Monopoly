package game_elements.table_fields.property_fields;

import game_elements.table_fields.PropertyField;

public class UtilityField extends PropertyField {

    public UtilityField(int fieldID, int value) {
        super(fieldID, value, 100);
    }

    @Override
    protected void action() {
        //
    }

    public int rent() {
        return rent;
    }
}
