package game_elements;

import game_elements.GameElement;
import game_elements.Player;

import java.util.ArrayList;
import java.util.List;

public class Table extends GameElement {
    ArrayList<Player> playerArray;

    public Player getPlayerByID(int PID) { return this.playerArray[PID]; }
}
