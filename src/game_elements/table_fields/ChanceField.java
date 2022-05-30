package game_elements.table_fields;


import game_elements.Field;

public class ChanceField extends Field {

    public ChanceField(int fieldID) {
        super(fieldID);
    }

    @Override
    protected void action() {
        // húzz egy kártyát
    }
}
