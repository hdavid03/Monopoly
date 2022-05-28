import game_elements.Player;

import java.util.ArrayList;

public class main {
    public static void main(String[] args) {

        GUI.LaunchPage launchPage = new GUI.LaunchPage();

        for(int i=0;i<PlayerArray.size();i++) {

        }

    }

    /*
    *
    * SETUP ALL DATABASE
    *   Fields
    *   Cards
    *   Board
    *   Players
    *   Die1, Die2
    *
    * Turn order pseudocode
    *
    * FOR p=1:Num(Players)      // MATLAB style array indexing
    *
    *   IF p.getInJailTimer() == 0
    *       p.setInJail(FALSE)
    *
    *   Player p DOES IF NOT JAILED
    *       ROLL 2xDie
    *
    *       Move(Die1+Die2)
    *           IF p.startPassCheck(Die1+Die2)
    *               p.ChangeBalance(200);
    *               p.setOnFieldPosition(p.getOnFieldPosition()+Die1+Die2-40);
    *           ELSE
    *               p.setOnFieldPosition(p.getOnFieldPosition()+Die1+Die2);
    *       CheckField
    *           IF DrawCard -> Draw
    *               EFFECT Card
    *                   CHANGE Player STATE
    *           IF CHANGE Player STATE THEN CHANGE Player STATE
    *           IF Property OR Utility
    *               IF Property Owner NULL ALLOW Buy
    *               IF Owned PAY RENT IF OWNER=/=Player
    *               IF Owned AND OWNER==Player ALLOW PropertyDevelopment
    *                   //Detail PropertyDevelopment Later
    *           IF GoToJail
    *               p.setInJailTimer(3);
    *               p.setInJail(TRUE)
    *               IF GetOutOfJailFree IN PlayerCards
    *                   PROMPT PlayCard OR NOT PlayCard
    *                       IF PlayCard
    *
    *       IF Die1==Die2 GOTO ROLL
    *
    *   Player p DOES IF JAILED
    *       p.setInJailTimer(p.getInJailTimer()-1);
    *
    * END
    *
    * */
}
