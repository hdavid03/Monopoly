package game_elements;

public abstract class Field extends GameElement {

    int fieldID;
    int playerCounter;

    /**
     * TaxField --> fizess adót
     * GoField --> semmi
     * GoToJailField --> menj a börtönbe
     */
    protected abstract void action();

    void playerArrives() {
        playerCounter++;
        action();
    }

    void playerGoes() {
        playerCounter--;
    }

    public int getFieldID() { return fieldID; }
}
