package networking;

import game_elements.Player;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerSocketHandler extends Thread {
    private static final int MAX_NUM_OF_CLIENTS = 4;
    private static final int SOCKET_TIMEOUT = 500;
    private static final int STARTER_MONEY = 1500;
    private final ServerSocket serverSocket;
    private final LinkedList<ClientSocketHandler> clientSocketHandlers;
    private final ConcurrentLinkedQueue<Player> players;
    private final ExecutorService executorService;
    private final ArrayList<Integer> playerIDs = new ArrayList<>();
    private int nextPlayerID = 0;
    private int nextClientSocketHandlerID = 0;
    private int turn = 0;
    private boolean gameIsRunning = true;
    private boolean nextTurnReady = false;
    private static final Logger serverSocketHandlerLogger = Logger.getLogger(ServerSocketHandler.class.getName());

    public ServerSocketHandler(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.clientSocketHandlers = new LinkedList<>();
        this.players = new ConcurrentLinkedQueue<>();
        this.executorService = Executors.newFixedThreadPool(MAX_NUM_OF_CLIENTS);
    }

    private boolean gameCanBeStarted() {
        int numOfClients = clientSocketHandlers.size();
        if(numOfClients == 0) return false;
        boolean playersAreReady = true;
        for(ClientSocketHandler ch : clientSocketHandlers) {
            playersAreReady &= ch.isClientReady();
        }
        return noMorePlayersThanAllowed() && playersAreReady;
    }

    private boolean isNextTurnReady() {
        boolean ready = deleteDisconnectedClients();
        if (ready) {
            clientSocketHandlers.get(nextClientSocketHandlerID).setClientReady(false);
            turn++;
            nextClientSocketHandlerID = turn % playerIDs.size();
            ServerSocketHandler.serverSocketHandlerLogger.log(Level.INFO, "Next turn is ready.");
            nextPlayerID = playerIDs.get(nextClientSocketHandlerID);
        }
        return ready;
    }

    private boolean deleteDisconnectedClients() {
        boolean ready = clientSocketHandlers.get(nextClientSocketHandlerID).isReadyDetect();
        for(int i = 0; i < clientSocketHandlers.size(); i++) {
            if(!clientSocketHandlers.get(i).getPlayer().isOnline()) {
                clientSocketHandlers.get(i).setLostConnection(true);
                String message = String.format("ID: %d name: %s disconnected.", playerIDs.get(i), clientSocketHandlers.get(i).getPlayer().getPlayerName());
                ServerSocketHandler.serverSocketHandlerLogger.log(Level.INFO, message);
                playerIDs.remove(i);
                clientSocketHandlers.remove(i);
                if(clientSocketHandlers.isEmpty()) {
                    gameIsRunning = false;
                    return false;
                }
                nextClientSocketHandlerID = Math.abs((nextClientSocketHandlerID - 1) % clientSocketHandlers.size());
                break;
            }
        }
        return ready;
    }

    private boolean noMorePlayersThanAllowed() {
        return (clientSocketHandlers.size() < MAX_NUM_OF_CLIENTS) && (clientSocketHandlers.size() > 1);
    }

    private Socket tryToAcceptAClientRequest() throws IOException{
        Socket socket = null;
        try {
            socket = serverSocket.accept();
        } catch(SocketTimeoutException e) {
            serverSocketHandlerLogger.log(Level.INFO, "Waiting for connections");
        }
        return socket;
    }

    private void startClientHandler(Socket socket) {
        if(socket != null) {
            Player player = new Player(players.size(), players.size(), STARTER_MONEY);
            players.add(player);
            playerIDs.add(player.getPlayerID());
            serverSocketHandlerLogger.log(Level.INFO, () -> player.toString() + " player has connected!");
            ClientSocketHandler clientSocketHandler = new ClientSocketHandler(socket, player);
            clientSocketHandlers.add(clientSocketHandler);
            executorService.execute(clientSocketHandler);
        }
    }

    private void waitForUpdateClients() {
        try {
            Thread.sleep(500);
        } catch(InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    private void updateStatusOfPlayers() {
        if(!clientSocketHandlers.isEmpty()) {
            ArrayList<Player> updatedPlayers = new ArrayList<>();
            for (ClientSocketHandler ch : clientSocketHandlers) {
                updatedPlayers.add(ch.getPlayer());
            }
            players.clear();
            players.addAll(updatedPlayers);
        }
    }

    private void updateClientHandlers(boolean ready) {
        for (ClientSocketHandler ch : clientSocketHandlers) {
            ServerMessage serverMessage = new ServerMessage(new ConcurrentLinkedQueue<>(players), ready, nextPlayerID, turn);
            ch.updateServerMessage(serverMessage);
            if(ready) {
                ch.setClientReady(false);
            }
        }
    }

    @Override
    public void run() {
        try {
            serverSocket.setSoTimeout(SOCKET_TIMEOUT);
            boolean start = false;
            while(!start) {
                start = isStartGame();
            }
            while(gameIsRunning) {
                game();
            }
        } catch(IOException e) {
            serverSocketHandlerLogger.log(Level.SEVERE, "There was a problem with server-client communication");
        }
    }

    private void game() {
        waitForUpdateClients();
        updateStatusOfPlayers();
        nextTurnReady = isNextTurnReady();
        updateClientHandlers(this.nextTurnReady);
        if (nextTurnReady) setNextTurn();
    }

    private boolean isStartGame() throws IOException {
        boolean start;
        Socket socket = tryToAcceptAClientRequest();
        startClientHandler(socket);
        waitForUpdateClients();
        updateStatusOfPlayers();
        start = gameCanBeStarted();
        updateClientHandlers(start);
        for(ClientSocketHandler ch : clientSocketHandlers) {
            serverSocketHandlerLogger.log(Level.INFO, () -> ch.getPlayer().toString() + " player is online");
        }
        clientSocketHandlers.removeIf(ClientSocketHandler::isLostConnection);
        return start;
    }

    private void setNextTurn() {
        this.nextTurnReady = false;
    }
}