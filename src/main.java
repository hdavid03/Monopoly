import GUI.MonopolyGUI;

import java.util.concurrent.TimeUnit;

public class main {
    public static void main(String[] args) throws InterruptedException {

        GUI.LaunchPage launchPage = new GUI.LaunchPage();

        TimeUnit.SECONDS.sleep(5);

        for (int i = 1; i < 40; i++) {
            int j = 0;
            for (int k = 0; k < 4; k++) {
                GUI.MonopolyGUI.goingonfields(i, j, k);
                j++;
                TimeUnit.SECONDS.sleep(3);
            }
        }



    }
}
