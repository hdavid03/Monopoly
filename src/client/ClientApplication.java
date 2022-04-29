package client;

import game_elements.Player;
import networking.ClientThread;
import server.ServerApplication;
import java.io.IOException;
import java.net.Socket;

public class ClientApplication {
    public static void main(String[] args) {
        try(Socket socket = new Socket(ServerApplication.HOST, ServerApplication.PORT)) {
            Player testPlayer = new Player(0, 0, 200);
            ClientThread clientThread = new ClientThread(socket, testPlayer);
            clientThread.start();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
