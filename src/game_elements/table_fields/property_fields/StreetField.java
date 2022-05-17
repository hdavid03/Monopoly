package game_elements.table_fields.property_fields;

import game_elements.table_fields.PropertyField;

public class StreetField extends PropertyField {

    private int houseCounter;
    private boolean isThereHotel;
    private int houseBuildCost;
    private int hotelBuildCost;
    private Color EEnumerator;
    private int[] rentPrices;

    @Override
    protected void action() {

    }

    // Getter-Setters go here
    public int getHouseCounter() { return this.houseCounter; }
    public void setHouseCounter(int C) { this.houseCounter = C; }
    public void setHotel() { this.isThereHotel = true; }

    public void BuildHouse() { this.setHouseCounter(this.getHouseCounter()+1); }

    public void BuildHotel() {
        this.setHouseCounter(0);
        this.setHotel();
        // Player.changeBalance() valahol
    }
}
