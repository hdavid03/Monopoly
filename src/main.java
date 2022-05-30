import java.util.concurrent.TimeUnit;

public class main {
    public static void main(String[] args) throws InterruptedException {

        GUI.LaunchPage launchPage = new GUI.LaunchPage();

        TimeUnit.SECONDS.sleep(5);


        while(true){
            for (int fieldID = 1; fieldID < 40; fieldID++) {
                int playerCount = 0;
                for (int playerID = 0; playerID < 4; playerID++) {
                    GUI.MonopolyGUI.goingonfields(fieldID, playerCount, playerID);
                    GUI.MonopolyGUI.fieldImage(fieldID);
                    GUI.MonopolyGUI.HouseHotelMonitoring(fieldID);
                    GUI.MonopolyGUI.getTablefieldsID_local(fieldID);
                    GUI.MonopolyGUI.getPlayerID_local(playerID);


                    playerCount++;
                    TimeUnit.SECONDS.sleep(3);
                }
            }
        }




    }
}
