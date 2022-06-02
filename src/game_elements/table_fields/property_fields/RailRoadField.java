package game_elements.table_fields.property_fields;

import game_elements.table_fields.PropertyField;

public class RailRoadField extends PropertyField {
    private int rent;
    public RailRoadField(int fieldID, int value, int rent) {
        super(fieldID, value);
        this.rent = rent;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    @Override
    protected void action() {

    }
}
