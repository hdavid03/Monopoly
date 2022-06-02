package game_elements.table_fields.property_fields;

import game_elements.table_fields.PropertyField;

public class StreetField extends PropertyField {

    private int houseCounter = 0;
    private boolean isThereHotel = false;
    private int houseBuildCost;
    private int hotelBuildCost;
    private PropertyFieldColor EEnumerator;
    //private int[] rentPrices;

    public StreetField(int fieldID, PropertyFieldColor color, int value, int house, int hotel) {
        super(fieldID, value);
        this.EEnumerator = color;
        this.houseBuildCost = house;
        this.hotelBuildCost = hotel;
    }

    @Override
    protected void action() {

    }

    // Getter-Setters go here
    public int getHouseCounter() { return this.houseCounter; }
    public void setHouseCounter(int C) { this.houseCounter = C; }
    public void setHotel(boolean H) { this.isThereHotel = H; }

    public void BuildHouse() {
        this.setHouseCounter(this.getHouseCounter()+1);
        // p.changeBalance();
    }

    public void BuildHotel() {
        this.setHouseCounter(0);
        this.setHotel(true);
        // Player.changeBalance() valahol
    }


    public boolean isThereHotel() {
        return isThereHotel;
    }

    public void setThereHotel(boolean thereHotel) {
        isThereHotel = thereHotel;
    }

    public int getHouseBuildCost() {
        return houseBuildCost;
    }

    public void setHouseBuildCost(int houseBuildCost) {
        this.houseBuildCost = houseBuildCost;
    }

    public int getHotelBuildCost() {
        return hotelBuildCost;
    }

    public void setHotelBuildCost(int hotelBuildCost) {
        this.hotelBuildCost = hotelBuildCost;
    }

    public PropertyFieldColor getEEnumerator() {
        return EEnumerator;
    }

    public void setEEnumerator(PropertyFieldColor EEnumerator) {
        this.EEnumerator = EEnumerator;
    }
}
