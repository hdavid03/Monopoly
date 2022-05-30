package game_elements.table_fields.property_fields;

import game_elements.table_fields.PropertyField;

public class RailRoadField extends PropertyField {
    private int value;
    private int rent;
    public RailRoadField(int fieldID, int value, int rent) {
        super(fieldID);
        this.value = value;
        this.rent = rent;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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
