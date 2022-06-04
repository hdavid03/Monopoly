package game_elements;

public abstract class GameElement {

    private Position position;

    GameElement(){
        position = new Position(0, 0);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString(){
        return Integer.toString(position.getX() + position.getY());
    }
}
