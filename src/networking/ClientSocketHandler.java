package networking;

import game_elements.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocketHandler implements Runnable {

    Socket socket;

    public ClientSocketHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            while(true) {
                Player player = (Player) ois.readObject();
                System.out.println(player.getPlayerID() + " " + player.getMoney() + " " + player.getOnFieldPosition());
                Thread.sleep(500);
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
