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
    private static final int SOCKET_TIMEOUT = 500;
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

    private boolean gameIsReady() {
        boolean playersAreReady = true;
        boolean noMorePlayersThanAllowed = clientSocketHandlers.size() < MAX_NUM_OF_CLIENTS;
        for(ClientSocketHandler ch : clientSocketHandlers) {
            playersAreReady &= ch.isClientReady();
        }
        return noMorePlayersThanAllowed && playersAreReady;
    }

    @Override
    public void run() {
        try {
            serverSocket.setSoTimeout(SOCKET_TIMEOUT);
            while(true) {
                if(!gameIsReady()) {
                    Socket socket = serverSocket.accept();
                    Player player = new Player(linkedPlayerQueue.size(),linkedPlayerQueue.size(),200);
                    linkedPlayerQueue.add(player);
                    ClientSocketHandler clientSocketHandler = new ClientSocketHandler(socket, player);
                    clientSocketHandlers.add(clientSocketHandler);
                    executorService.execute(clientSocketHandler);
                    for(ClientSocketHandler ch : clientSocketHandlers) {
                        System.out.println(ch.getPlayer().toString());
                    }
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
