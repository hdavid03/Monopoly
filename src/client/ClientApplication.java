package client;

import game_elements.Player;
import networking.ClientThread;
import server.ServerApplication;
import java.io.IOException;
import java.net.Socket;

public class ClientApplication {
    public static void main(String[] args) {
        try(Socket socket = new Socket(ServerApplication.HOST, ServerApplication.PORT)) {
            ClientThread clientThread = new ClientThread(socket);
            clientThread.start();
            clientThread.join();
        } catch(IOException e) {
            e.printStackTrace();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}