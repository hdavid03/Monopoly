package game_elements;

public class ChanceCard extends Card {

    @Override
    public void action(Player P, int cardID) {
        switch(cardID) {
            case 1:
                //Add to Player, give 200 when Pass Go
            case 2: P.changeBalance(15);
            case 3: P.setOnFieldPosition(0);
            case 4: P.setInJailTimer(3); P.setInJail(true); //P.setOnFieldPosition(/*JailPos*/);
            case 5: P.changeBalance(150);
            case 6:
                //When Pass Go, pick up 200, de ez mindden Pass-nál történik szóval miafasz?
            case 7: P.setOnFieldPosition(P.getOnFieldPosition()-3);
            case 8: //P.setOnFieldPosition(/*Dunakorzó*/);
            case 9:
                // Get Out Of Jail
            case 10:
                // min(d(Player,Közmű)), megvásárolható vagy 10*(Roll1+Roll2) tulajnak
            case 11:
                // Házért 25M-t, szállodáért 100M-t, DE LEHETETLEN ÍGY IMPLEMENTÁLNI
            case 12:
                // Mindenkinek 50M-t, DE ÍGY LEHETETLEN IMPLEMENTÁLNI
            case 13: //P.setOnFieldPosition(); // If Pass Go, P.changeBalance(200);
            case 14:
                // min(d(Player,Vasút)), megvásárolható vagy 2*rent() tulajnak
            case 15:
                // min(d(Player,Vasút)), megvásárolható vagy 2*rent() tulajnak
            case 16: P.changeBalance(50);
            default:
                System.out.println("HIBA");
        }
    }
}
