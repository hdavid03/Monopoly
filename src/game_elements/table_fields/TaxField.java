package game_elements.table_fields;

import game_elements.Field;

public class TaxField extends Field {


    int value;

    public TaxField(int fieldID, int V) {
        super(fieldID);
        this.value = V;
    }

    @Override
    protected void action() {

    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
