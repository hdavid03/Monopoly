package game_elements.table_fields.property_fields;

import game_elements.table_fields.PropertyField;

public class RailRoadField extends PropertyField {
    private int rentValue;
    public RailRoadField(int fieldID, int value, int rent) {
        super(fieldID, value);
        this.rentValue = rent;
    }

    public int getRentValue() {
        return rentValue;
    }

    public void setRentValue(int rentValue) {
        this.rentValue = rentValue;
    }

    @Override
    protected void action() {

    }
}
