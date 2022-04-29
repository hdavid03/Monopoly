package networking;

import game_elements.Player;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerSocketHandler extends Thread {
    private static final int MAX_NUM_OF_CLIENTS = 4;
    private ServerSocket serverSocket;
    private LinkedList<ClientSocketHandler> clientSocketHandlers;
    private ConcurrentLinkedQueue<Player> linkedPlayerQueue;
    private ExecutorService executorService;

    public ServerSocketHandler(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.clientSocketHandlers = new LinkedList<>();
        this.linkedPlayerQueue = new ConcurrentLinkedQueue<>();
        this.executorService = Executors.newFixedThreadPool(MAX_NUM_OF_CLIENTS);
    }

    @Override
    public void run() {
        while(true) {
            if(clientSocketHandlers.size() < MAX_NUM_OF_CLIENTS) {
                try(Socket socket = serverSocket.accept()) {
                    ClientSocketHandler clientSocketHandler = new ClientSocketHandler(socket);
                    clientSocketHandlers.add(clientSocketHandler);
                    executorService.execute(clientSocketHandler);
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
