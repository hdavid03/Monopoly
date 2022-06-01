package game_elements;

public class SurpriseCard extends Card {

    @Override
    public void action(Player P, int cardNumber) {
        switch (cardNumber) {
            case 1:
                P.changeBalance(100);
            case 2:
                P.changeBalance(50);
            case 3:
                P.changeBalance(-50);
            case 4:
                P.changeBalance(100);
            case 5:
                // Gyűjt be mindenkitől 10M-t, DE LEHETETLEN ÍGY IMPLEMENTÁLNI
            case 6:
                // Házért 40M-t, szállodáért 115M-t, DE LEHETETLEN ÍGY IMPLEMENTÁLNI
            case 7:
                P.changeBalance(20);
            case 8:
                // Get Out Of Jail, aktívan kell Player prompt-ra bejátszani, szóval nemtom
            case 9:
                //   P.setInJailTimer(3); P.setInJail(true); P.setOnFieldPosition(/*JailPos*/);
            case 10:
                P.changeBalance(10);
            case 11:
                P.changeBalance(-50);
            case 12:
                P.changeBalance(200);
            case 13:
                P.changeBalance(100);
            case 14:
                P.changeBalance(-100);
            case 15:
                P.setFieldID(0);
                P.changeBalance(200);
            case 16:
                P.changeBalance(15);
            default:
                System.out.println("HIBA");
        }
    }
}
