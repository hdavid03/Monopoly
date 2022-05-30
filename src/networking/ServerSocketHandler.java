package networking;

import game_elements.Player;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerSocketHandler extends Thread {
    private static final int MAX_NUM_OF_CLIENTS = 4;
    private static final int SOCKET_TIMEOUT = 500;
    private final ServerSocket serverSocket;
    private final LinkedList<ClientSocketHandler> clientSocketHandlers;
    private final ConcurrentLinkedQueue<Player> players;
    private final ExecutorService executorService;
    private int nextPlayerID = 0;
    private int lap = 0;
    private static final Logger serverSocketHandlerLogger = Logger.getLogger(ServerSocketHandler.class.getName());

    public ServerSocketHandler(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.clientSocketHandlers = new LinkedList<>();
        this.players = new ConcurrentLinkedQueue<>();
        this.executorService = Executors.newFixedThreadPool(MAX_NUM_OF_CLIENTS);
    }

    private boolean gameIsReady() {
        int numOfClients = clientSocketHandlers.size();
        if(numOfClients == 0) return false;
        boolean playersAreReady = true;
        for(ClientSocketHandler ch : clientSocketHandlers) {
            playersAreReady &= ch.isClientReady();
        }
        return noMorePlayersThanAllowed() && playersAreReady;
    }

    private boolean noMorePlayersThanAllowed() {
        return (clientSocketHandlers.size() < MAX_NUM_OF_CLIENTS) && (clientSocketHandlers.size() > 1);
    }

    private Socket tryToAcceptAClientRequest() throws IOException{
        Socket socket = null;
        try {
            socket = serverSocket.accept();
        } catch(SocketTimeoutException e) {
            serverSocketHandlerLogger.log(Level.INFO, e::getMessage);
        }
        return socket;
    }

    private void startClientHandler(Socket socket) {
        if(socket != null) {
            Player player = new Player(players.size(), players.size(), 200);
            players.add(player);
            serverSocketHandlerLogger.log(Level.INFO, () -> player.toString() + " player has connected!");
            ClientSocketHandler clientSocketHandler = new ClientSocketHandler(socket, player);
            clientSocketHandlers.add(clientSocketHandler);
            executorService.execute(clientSocketHandler);
        }
    }

    private void updateStatusOfPlayers() {
        LinkedList<Player> updatedPlayers = new LinkedList<>();
        for (ClientSocketHandler ch : clientSocketHandlers) {
            updatedPlayers.add(ch.getPlayer());
        }
        players.clear();
        players.addAll(updatedPlayers);
    }

    void updateClientHandlers() {
        for(ClientSocketHandler ch : clientSocketHandlers) {
            ServerMessage message = null;
            if(gameIsReady()) {
                message = new ServerMessage(players, true, nextPlayerID, lap);
            } else {
                message = new ServerMessage(players, false, nextPlayerID, lap);
                nextPlayerID += lap % players.size();
                lap++;
            }
            ch.updateServerMessage(message);
        }
    }

    @Override
    public void run() {
        try {
            serverSocket.setSoTimeout(SOCKET_TIMEOUT);
            while(!gameIsReady()) {
                Socket socket = tryToAcceptAClientRequest();
                startClientHandler(socket);
                for(ClientSocketHandler ch : clientSocketHandlers) {
                    serverSocketHandlerLogger.log(Level.INFO, () -> ch.getPlayer().toString() + " player is online");
                }
                updateStatusOfPlayers();
                updateClientHandlers();
                clientSocketHandlers.removeIf(ClientSocketHandler::isLostConnection);
                players.removeIf(Player::isOffline);
            }
            while(true) {
                for(ClientSocketHandler ch : clientSocketHandlers) {
                    serverSocketHandlerLogger.log(Level.INFO, () -> ch.getPlayer().toString() + " player is online");
                }
                updateStatusOfPlayers();
                updateClientHandlers();
                clientSocketHandlers.removeIf(ClientSocketHandler::isLostConnection);
                players.removeIf(Player::isOffline);
            }
        } catch(IOException e) {
            serverSocketHandlerLogger.log(Level.SEVERE, e::getMessage);
        }
    }
}