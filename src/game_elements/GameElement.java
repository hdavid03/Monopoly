package game_elements;
import resources.*;

import java.io.File;

public abstract class GameElement {

    private Position position;
    private File image;

    GameElement(){
        position = new Position(0, 0);
        image = new File("/home/hdavid03/file.png");
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
