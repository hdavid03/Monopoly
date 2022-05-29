package game_elements.table_fields.property_fields;

import game_elements.table_fields.PropertyField;

public class UtilityField extends PropertyField {

    private int value;

    public UtilityField(int Pos, int V) {
        super(Pos);
        this.value = V;
    }

    @Override
    protected void action() {
        //
    }

    public int getValue() { return this.value; }
}
