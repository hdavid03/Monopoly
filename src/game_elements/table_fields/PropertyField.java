package game_elements.table_fields;

import game_elements.Field;

public abstract class PropertyField extends Field {
    //private int value;
    //private int rent;
    private int ownerID;
    private boolean isThereOwner;

    public PropertyField(int fieldID) {
        super(fieldID);
        this.isThereOwner = false;
    }

    //public void setValue(int V) { this.value = V; }
    //public int getValue() { return this.value; }

    //public void setRent(int R) { this.rent = R; }
    //public int getRent() { return this.rent; }

    public void setOwnerID(int O) { this.ownerID = O; }
    public int getOwnerID() { return this.ownerID; }

    public void setOwnership(boolean B) { this.isThereOwner = B; }
    public boolean getOwnership() { return this.isThereOwner; }

    //public void rent(Player P) { ...; }       Ez itt nem megy.

    //public void buy(Player P) { ...; }        Dettó.
}
