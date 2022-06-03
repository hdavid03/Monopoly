package game_elements.table_fields;

import game_elements.Field;

public abstract class PropertyField extends Field {
    private int value;
    protected int rent;
    private int ownerID;
    private boolean thereIsOwner;

    public PropertyField(int fieldID, int value, int rent) {
        super(fieldID);
        this.thereIsOwner = false;
        this.value = value;
        this.rent = rent;
    }

    public boolean isThereOwner() {
        return thereIsOwner;
    }

    public void setValue(int V) { this.value = V; }
    public int getValue() { return this.value; }

    //public void setRentValue(int R) { this.rent = R; }
    public abstract int rent();

    public void setOwnerID(int O) { this.ownerID = O; }
    public int getOwnerID() { return this.ownerID; }

    public void setOwnership(boolean B) { this.thereIsOwner = B; }
    public boolean getOwnership() { return this.thereIsOwner; }

    //public void rent(Player P) { ...; }       Ez itt nem megy.

    //public void buy(Player P) { ...; }        Dettó.
}
