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
    private ServerMessage serverMessage;
    private int nextPlayerID = 0;
    private int turn = 0;
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
        return clientSocketHandlers.get(nextPlayerID).isClientReady();
    }

    private void waitForNextPlayer() {
        try {
            while(!clientSocketHandlers.get(nextPlayerID).isClientReady()) {
                Thread.sleep(50);
                System.out.println(nextPlayerID);
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
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
            serverSocketHandlerLogger.log(Level.INFO, () -> String.valueOf(players.size()));
            //serverSocketHandlerLogger.log(Level.INFO, () -> player.toString() + " player has connected!");
            ClientSocketHandler clientSocketHandler = new ClientSocketHandler(socket, player);
            clientSocketHandlers.add(clientSocketHandler);
            executorService.execute(clientSocketHandler);
        }
    }

    private boolean clientsAreUpdated() {
        boolean clientsAreUpdated = true;
        for (ClientSocketHandler ch : clientSocketHandlers) {
            clientsAreUpdated &= ch.isClientUpdated();
        }
        return  clientsAreUpdated;
    }

    private void waitForUpdateClients() {
        try {
            while(!clientsAreUpdated()) {
                Thread.sleep(40);
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    private void updateStatusOfPlayers() {
        if(!clientSocketHandlers.isEmpty()) {
            LinkedList<Player> updatedPlayers = new LinkedList<>();
            for (ClientSocketHandler ch : clientSocketHandlers) {
                updatedPlayers.add(ch.getPlayer());
            }
            players.clear();
            players.addAll(updatedPlayers);
        }
    }

    private void updateClientHandlers(boolean ready) {
        for (ClientSocketHandler ch : clientSocketHandlers) {
            serverMessage = new ServerMessage(new ConcurrentLinkedQueue<>(players), ready, nextPlayerID, turn);
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
            }
            while(true) {
                for(ClientSocketHandler ch : clientSocketHandlers) {
                    serverSocketHandlerLogger.log(Level.INFO, () -> String.valueOf(ch.isClientReady()));
                }
                waitForUpdateClients();
                updateStatusOfPlayers();
                nextTurnReady = isNextTurnReady();
                updateClientHandlers(this.nextTurnReady);
                if (nextTurnReady) setNextTurn();
                clientSocketHandlers.removeIf(ClientSocketHandler::isLostConnection);
            }
        } catch(IOException e) {
            serverSocketHandlerLogger.log(Level.SEVERE, e::getMessage);
        }
    }

    private void setNextTurn() {
        this.nextTurnReady = false;
        turn++;
        nextPlayerID = turn % clientSocketHandlers.size();
    }
}