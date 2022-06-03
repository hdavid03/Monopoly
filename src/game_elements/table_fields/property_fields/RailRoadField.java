package game_elements.table_fields.property_fields;

import game_elements.table_fields.PropertyField;

public class RailRoadField extends PropertyField {

    public RailRoadField(int fieldID, int value, int rent) {
        super(fieldID, value, rent);
    }

    @Override
    public int rent() {
        return rent;
    }

    @Override
    protected void action() {

    }
}
