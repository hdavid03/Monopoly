package game_elements;

public abstract class Field extends GameElement {

    int fieldID;
    int playerCounter = 0;

    public Field(int Pos) {
        this.fieldID = Pos;
    }
    protected abstract void action();

    void playerArrives() {
        playerCounter++;
        action();
    }

    public void playerGoes() {
        playerCounter--;
    }

    public int getFieldID() { return fieldID; }
}
