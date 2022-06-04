package GUI;

import client.ClientApplication;
import game_elements.*;
import game_elements.table_fields.*;
import game_elements.table_fields.property_fields.PropertyFieldColor;
import game_elements.table_fields.property_fields.RailRoadField;
import game_elements.table_fields.property_fields.StreetField;
import game_elements.table_fields.property_fields.UtilityField;
import networking.ServerMessage;
import networking.Transaction;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;

public class MonopolyGUI extends JFrame {

    private CustomButton payButton;
    private CustomButton readyButton;
    private CustomButton throwButton;
    private int playerID;
    private Player player;
    private final String userName;
    private boolean ready = false;
    private boolean userInterAction = false;
    private boolean gameStarted = false;
    private transient Field[] fields;
    private final ArrayList<Player> players = new ArrayList<>();
    private final ArrayList<CustomLabel> ownedPropertyIndicator = new ArrayList<>();
    private final ArrayList<CustomLabel> playerNameLabels = new ArrayList<>();
    private final ArrayList<CustomLabel> playerMoneyLabels = new ArrayList<>();
    private final ArrayList<CustomLabel> playerPropertyLabels = new ArrayList<>();
    private final ArrayList<CustomLabel> playerJailLabels = new ArrayList<>();
    private final ArrayList<CustomLabel> playerExtraLabels = new ArrayList<>();
    private final ArrayList<CustomLabel> pawns = new ArrayList<>();
    private final ArrayList<ImageIcon> propertyCardIcons = new ArrayList<>();
    private final ArrayList<ImageIcon> surpriseCardIcons = new ArrayList<>();
    private final ArrayList<ImageIcon> chanceCardIcons = new ArrayList<>();
    private final ArrayList<ImageIcon> dieIcons = new ArrayList<>();
    private transient SurpriseCard community = new SurpriseCard();
    private transient ChanceCard chance = new ChanceCard();

    private CustomLabel cardLabel;
    private CustomLabel househotelLabel;
    private CustomLabel die1Label;
    private CustomLabel die2Label;
    private CustomPanel dicePanel;

    //arrayxy
    private final int[][] arrayXY = {{875,925},  {800,925},  {725,925},  {635,925},  {550,920},          //0
                                     {475,925},  {390,925},  {310,925},  {225,925},  {150,925},          //5
                                     {25,925},   {25,800},   {25,720},   {25,640},   {25,550},           //10
                                     {25,475},   {25,390},   {25,310},   {25,235},   {25,150},           //15
                                     {25,25},    {150,25},   {230,25},   {310,25},   {390,25},           //20
                                     {475,25},   {550,25},   {635,25},   {715,25},   {795,25},           //25
                                     {890,25},   {925,150},  {925,230},  {925,315},  {925,395},          //30
                                     {925,480},  {925,550},  {925,635},  {925,715},  {925,800}           //35
                                    };
    private final int[][] defaultPlayerPanelPostions = {{1000, 100}, {1400, 100}, {1000, 250}, {1400, 250}};
    private final Color[] playerColors = {Color.GREEN, Color.RED, Color.MAGENTA, Color.YELLOW};

    public MonopolyGUI(String userName){
        initPawns();
        initGameBoard();
        initPlayers();
        CustomPanel cardsPanel = getCustomCardsPanel();
        setDicePanels();
        CustomPanel actionPanel = getCustomActionPanel();
        setActionListeners();
        setImageIcons(chanceCardIcons, "src//resources//szerencse.txt");
        setImageIcons(surpriseCardIcons, "src//resources//meglepetes.txt");
        setImageIcons(dieIcons, "src//resources//dice.txt");
        setPropertyFieldIcons();
        initFields();
        this.userName = userName;
        this.setTitle("Monopoly");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setExtendedState(this.getExtendedState() | Frame.MAXIMIZED_BOTH);   //full screen with window
        this.setLayout(null);
        this.setVisible(true);
        this.add(cardsPanel);
        this.add(actionPanel);
    }

    private CustomPanel getCustomActionPanel() {
        CustomLabel actionTitleLabel = new CustomLabel("Akciók", 40, 10, 10, 200, 50);
        CustomPanel actionPanel = new CustomPanel(1000, 800, 800, 300, Color.CYAN);
        setButtons(actionTitleLabel, actionPanel);
        return actionPanel;
    }

    private CustomPanel getCustomCardsPanel() {
        CustomLabel cardTitleLabel = new CustomLabel("Kártyák", 40, 10, 10, 200, 50);
        househotelLabel = new CustomLabel("", 30, 200, 10, 400, 50);
        CustomPanel cardsPanel = new CustomPanel(1000, 400, 600, 400, Color.WHITE);
        cardLabel = new CustomLabel(110, 60, 600, 350, null);
        cardsPanel.add(cardTitleLabel);
        cardsPanel.add(cardLabel);
        cardsPanel.add(househotelLabel);
        return cardsPanel;
    }

    private void setButtons(CustomLabel actionTitleLabel, CustomPanel actionPanel) {
        ImageIcon dollarLogoIcon = new ImageIcon("src//Images//logos//dollar.png");
        ImageIcon readyLogoIcon = new ImageIcon("src//Images//logos//ready.png");
        ImageIcon diceLogoIcon = new ImageIcon("src//Images//logos//dice.png");
        this.payButton = new CustomButton(50, 110, 250, 80, "Fizetés", dollarLogoIcon);
        this.readyButton = new CustomButton(320, 110, 250, 80, "Kész", readyLogoIcon);
        this.throwButton = new CustomButton(590, 110, 200, 80, "Dobás", diceLogoIcon);
        this.throwButton.setVisible(false);
        this.payButton.setEnabled(false);
        actionPanel.add(actionTitleLabel);
        actionPanel.add(this.payButton);
        actionPanel.add(this.readyButton);
        actionPanel.add(this.throwButton);
    }

    public boolean isUserInterAction() {
        return userInterAction;
    }

    public void setUserInterAction(boolean userInterAction) {
        this.userInterAction = userInterAction;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) { this.ready = ready; }

    public String getUserName() {
        return userName;
    }

    public void fieldImage(Integer tableFieldsID) {
        Field field = this.fields[tableFieldsID];
        househotelLabel.setText("");
        if (field instanceof PropertyField) {
            cardLabel.setIcon(propertyCardIcons.get(tableFieldsID));
            if(field instanceof StreetField streetField){
                String isHotel = streetField.isThereHotel() ? "Van" : "Nincs";
                househotelLabel.setText("Ház: " + streetField.getHouseCounter() + " || Szálloda: " + isHotel);
            }
        }
        else if (field instanceof CommunityChestField) {
            cardAction(community, surpriseCardIcons, "Húztál egy meglepetés kártyát!");
        }
        else if (field instanceof ChanceField) {
            cardAction(chance, chanceCardIcons, "Húztál egy szerencse kártyát!");
        }
    }

    private void cardAction(Card card, ArrayList<ImageIcon> cardIcons, String message) {
        SecureRandom random = new SecureRandom();
        int cardID = random.nextInt(16);
        cardLabel.setIcon(cardIcons.get(cardID));
        popUpMessage(message, JOptionPane.INFORMATION_MESSAGE);
        card.action(this.player, cardID, this.players, this.fields, this);
    }

    private void updatePlayerPosition(Player player) {
        int pID = player.getPlayerID();
        int fieldID = player.getFieldID();
        if(pID != this.playerID) {
            pawns.get(pID).setLocation(arrayXY[fieldID][0], arrayXY[fieldID][1]);
            pawns.get(pID).repaint();
            setOnFieldPlayerPosition(pID);
        }
    }

    public void goingOnFields(int setNewfieldID){
        goingOnFields(setNewfieldID, false);
    }

    public void goingOnFields(int result, boolean resultOfThrowing){
        int newFieldID = result;
        if(resultOfThrowing){
            newFieldID = (this.player.getFieldID() + result) % 40;
        }
        if (newFieldID == 30) {
            player.setInJailTimer(3);
            player.setInJail(true);
            newFieldID = 10;
        }
        else if (newFieldID == 4 || newFieldID == 38){
            this.player.changeBalance(-1 * 100);
        }
        else {
            player.startPassCheck(newFieldID);
        }
        pawns.get(this.playerID).setLocation(arrayXY[newFieldID][0], arrayXY[newFieldID][1]);
        setOnFieldPlayerPosition(this.playerID);
        this.player.setFieldID(newFieldID);
        fieldImage(newFieldID);
    }

    private void setOnFieldPlayerPosition(int pID) {
        int x = pawns.get(pID).getX();
        int y = pawns.get(pID).getY();
        switch (pID) {
            case 0:
                break;
            case 1:
                pawns.get(pID).setLocation(x, y + 25);
                break;
            case 2:
                pawns.get(pID).setLocation(x + 25, y);
                break;
            case 3:
                pawns.get(pID).setLocation(x + 25, y + 25);
                break;
            default:
                ClientApplication.clientApplicationLogger.log(Level.SEVERE,"Error in the setOnFieldPlayerPosition(int pID) method.");
        }
    }

    private void initPlayers() {
        CustomLabel playerTitleLabel = new CustomLabel("Játékosok", 60, 260, 10, 400, 90);
        CustomPanel playerTitlePanel = new CustomPanel(1000, 0, 800, 100, Color.PINK);
        playerTitlePanel.add(playerTitleLabel);
        this.add(playerTitlePanel);
        for(int i = 0; i < 4; i++) {
            initDefaultPlayer(defaultPlayerPanelPostions[i][0], defaultPlayerPanelPostions[i][1], playerColors[i]);
        }
    }

    private void initDefaultPlayer(int x, int y, Color color) {
        CustomLabel playerNameLabel = new CustomLabel("Offline", 20, 10, 0, 400, 30);
        CustomLabel playerMoneyLabel = new CustomLabel("Pénz: 0", 20, 10, 30, 400, 30);
        CustomPanel playerPanel = new CustomPanel(x, y, 400, 150, color);
        CustomLabel playerPropertyLabel = new CustomLabel("Vasút: 0 db || Közmű: 0 db", 20, 10, 60, 400, 30);
        CustomLabel playerJailLabel = new CustomLabel("Börtön: Nincs - 0 kör", 20, 10, 90, 400, 30);
        CustomLabel playerExtraLabel = new CustomLabel("Extra: ", 20, 10, 120, 400, 30);
        playerPanel.add(playerNameLabel);
        playerPanel.add(playerMoneyLabel);
        playerPanel.add(playerPropertyLabel);
        playerPanel.add(playerJailLabel);
        playerPanel.add(playerExtraLabel);
        playerNameLabels.add(playerNameLabel);
        playerMoneyLabels.add(playerMoneyLabel);
        playerPropertyLabels.add(playerPropertyLabel);
        playerJailLabels.add(playerJailLabel);
        playerExtraLabels.add(playerExtraLabel);
        this.add(playerPanel);
    }

    private void showOwnershipOnField(Player p) {
        int pID = p.getPlayerID();
        Field field = this.fields[p.getFieldID()];
        if (field instanceof PropertyField propertyField && propertyField.getOwnership()) {
            Color colour = null;
            switch (pID) {
                case 0 -> colour = Color.GREEN;
                case 1 -> colour = Color.RED;
                case 2 -> colour = Color.MAGENTA;
                case 3 -> colour = Color.YELLOW;
                default ->
                        ClientApplication.clientApplicationLogger.log(Level.SEVERE,"Error in the showOwnershipOnField(Player p) method.");
                }
                if (ownedPropertyIndicator.get(p.getFieldID()) != null) {
                    ownedPropertyIndicator.get(p.getFieldID()).setBackground(colour);
                    ownedPropertyIndicator.get(p.getFieldID()).setOpaque(true);
                }
            }
    }

    private void setActionListeners() {
        this.payButton.addActionListener(e -> {
            int fieldID = this.player.getFieldID();
            Field field = this.fields[fieldID];
            if(field instanceof PropertyField propertyField){
                if(!propertyField.getOwnership()) {
                    buyPropertyField(propertyField);
                }
                else if(propertyField.getOwnerID() == this.player.getPlayerID()){
                    propertyFieldTypeOptions(propertyField);
                }
            }
            this.payButton.setEnabled(false);
        });

        this.readyButton.addActionListener(e -> {
            this.ready = true;
            this.readyButton.setEnabled(false);
            this.throwButton.setVisible(true);
            this.throwButton.setEnabled(false);
            this.payButton.setEnabled(false);
        });

        this.throwButton.addActionListener(e -> {
                SecureRandom random = new SecureRandom();
                int result1 = random.nextInt(6);
                int result2 = random.nextInt(6);
                this.die1Label.setIcon(dieIcons.get(result1));
                this.die2Label.setIcon(dieIcons.get(result2));
                this.dicePanel.repaint();
            if(!userInterAction) {
                goingOnFields(result1 + result2 + 2, true);
                checkPropertyField();
            } else {
                userInterAction = false;
                int value = (result1 + result2) * 10;
                this.player.changeBalance(-1 * value);
                int ownerID = ((UtilityField)fields[player.getFieldID()]).getOwnerID();
                this.player.setTransaction(new Transaction(value, ownerID));
            }
            this.readyButton.setEnabled(true);
            this.throwButton.setEnabled(false);
        });
    }

    private void checkPropertyField() {
        if (fields[player.getFieldID()] instanceof PropertyField propertyField) {
            this.payButton.setEnabled(true);
            int ownerID = propertyField.getOwnerID();
            if(ownerID != playerID && propertyField.getOwnership()) {
                this.payButton.setEnabled(false);
                int value = propertyField.rent();
                popUpMessage(String.format("Fizetned kell %dM bérleti díjat %s játékosnak!",
                        value, this.players.get(ownerID).getPlayerName()), JOptionPane.INFORMATION_MESSAGE);
                this.player.changeBalance(-1 * value);
                this.player.setTransaction(new Transaction(value, ownerID));
            }
        }
    }

    private void propertyFieldTypeOptions(PropertyField propertyField) {
        if(propertyField instanceof StreetField streetField){
            if(streetField.getHouseCounter() < 4 && !streetField.isThereHotel() && streetField.getHouseBuildCost() < this.player.getMoney()) {
                streetField.setHouseCounter(streetField.getHouseCounter() + 1);
                this.player.changeBalance(-1 * streetField.getHouseBuildCost());
                this.player.setHouseCounter(this.player.getHouseCounter()+1);
            }
            else if(streetField.getHouseCounter() == 4 && !streetField.isThereHotel() && streetField.getHotelBuildCost() < this.player.getMoney()){
                streetField.setHotel(true);
                streetField.setHouseCounter(0);
                this.player.setHouseCounter(this.player.getHouseCounter()-4);
                this.player.changeBalance(-1 * streetField.getHotelBuildCost());
                this.player.setHotelCounter(this.player.getHotelCounter()+1);
            } else {
                String message = null;
                if(streetField.isThereHotel()) {
                    message = "A telken már van egy hotel, nem vásárolhatsz több épületet!";
                } else {
                    message = "Nincs elég pénzed az épületre!";
                } popUpMessage(message, JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void buyPropertyField(PropertyField propertyField) {
        if(propertyField.getValue() < this.player.getMoney()) {
            int fieldID = propertyField.getFieldID();
            propertyField.setOwnership(true);
            propertyField.setOwnerID(this.player.getPlayerID());
            this.player.addOwnedFieldID(fieldID);
            ClientApplication.clientApplicationLogger.log(Level.INFO, () -> ("Player ID: " + this.player.getPlayerID() + " bougth fleid ID: " + fieldID));
            this.player.changeBalance(-1 * propertyField.getValue());
            showOwnershipOnField(this.player);
            if (propertyField instanceof UtilityField) {
                this.player.setUtilityCounter(this.player.getUtilityCounter() + 1);
            } else if (propertyField instanceof RailRoadField) {
                this.player.setRailRoadCounter(this.player.getRailRoadCounter() + 1);
            }
        } else popUpMessage("Nincs elég pénzed, hogy megvásárold a mezőt!", JOptionPane.INFORMATION_MESSAGE);
    }

    public void popUpMessage(String message, int messageType) {
        switch (messageType) {
            case JOptionPane.WARNING_MESSAGE ->
                JOptionPane.showMessageDialog(this, message, "Figyelmeztetés",
                        JOptionPane.WARNING_MESSAGE);

            case JOptionPane.ERROR_MESSAGE ->
                JOptionPane.showMessageDialog(this, message, "Hiba",
                        JOptionPane.ERROR_MESSAGE);

            case JOptionPane.INFORMATION_MESSAGE ->
                JOptionPane.showMessageDialog(this, message, "Információ",
                        JOptionPane.INFORMATION_MESSAGE);
            default ->
                JOptionPane.showMessageDialog(this, "Valami nincs rendben", "Hiba",
                        JOptionPane.ERROR_MESSAGE);
        }
    }

    private CustomPanel initPawns() {
        ImageIcon shoeIcon = new ImageIcon("src//Images//pawns//shoe.jpg");
        ImageIcon hatIcon = new ImageIcon("src//Images//pawns//hat.png");
        ImageIcon boatIcon = new ImageIcon("src//Images//pawns//boat.png");
        ImageIcon carIcon = new ImageIcon("src//Images//pawns//car.jpg");
        CustomLabel pawnShoeLabel = new CustomLabel(875, 925, 25, 25, shoeIcon);
        CustomLabel pawnHatLabel = new CustomLabel(875, 950, 25, 25, hatIcon);
        CustomLabel pawnBoatLabel = new CustomLabel(900, 925, 25, 25, boatIcon);
        CustomLabel pawnCarLabel = new CustomLabel(900, 950, 25, 25, carIcon);
        CustomPanel pawnPanel = new CustomPanel(0, 0, 1000, 1000);
        pawnPanel.add(pawnShoeLabel);
        pawnPanel.add(pawnHatLabel);
        pawnPanel.add(pawnBoatLabel);
        pawnPanel.add(pawnCarLabel);
        pawnBoatLabel.setVisible(false);
        pawnCarLabel.setVisible(false);
        pawnShoeLabel.setVisible(false);
        pawnHatLabel.setVisible(false);
        return pawnPanel;
    }

    private void setImageIcons(ArrayList<ImageIcon> icons, String source) {
        try(FileInputStream fis = new FileInputStream(source)) {
            Scanner scanner = new Scanner(fis);
            while(scanner.hasNextLine()){
                String path = scanner.nextLine();
                icons.add(new ImageIcon(path));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setThrowButton() {
        this.throwButton.setEnabled(true);
    }

    private void setDicePanels() {
        CustomLabel diceTitleLabel = new CustomLabel("Kockák", 40, 10, 10, 200, 50);
        die1Label = new CustomLabel(25, 70, 200, 150, null);
        die2Label = new CustomLabel(25, 230, 200, 150, null);
        dicePanel = new CustomPanel(1600, 400, 200, 400, Color.BLUE);
        dicePanel.add(diceTitleLabel);
        dicePanel.add(die1Label);
        dicePanel.add(die2Label);
        this.add(dicePanel);
    }

    private void updateBankruptedPlayers() {
       for (Player p : this.players) {
           if(p.isInsolvency()) {
               setBankruptcy(p);
           }
        }
    }

    public void updateGameBoard(ServerMessage message) {
        this.players.clear();
        this.players.addAll(message.getPlayers());
        updateBankruptedPlayers();
        updateOwnedFieldIDs();
        int nextPlayerID = message.getNextPlayerID();
        boolean gameIsReady = message.isGameIsReady();
        startCheck(message, gameIsReady);
        if(nextPlayerID == this.playerID && gameIsReady) {
            initTurn();
        }
        updateStatusOfPlayers();
        winCheck();
    }

    private void winCheck() {
        if(this.players.size() == 1 && gameStarted) {
            popUpMessage("Gratulálunk! Megnyerted a játékot", JOptionPane.INFORMATION_MESSAGE);
            this.player.setOnline(false);
            this.readyButton.setEnabled(false);
            this.payButton.setEnabled(false);
            this.throwButton.setEnabled(false);
        }
    }

    private void updateOwnedFieldIDs() {
        for(Player p : this.players) {
            if(p.getPlayerID() != this.playerID) {
                ArrayList<Integer> ownedFieldIDs = p.getOwnedFieldIDs();
                for (Integer ownedFieldID : ownedFieldIDs) {
                    if (this.fields[ownedFieldID] instanceof PropertyField propertyField) {
                        propertyField.setOwnerID(p.getPlayerID());
                        propertyField.setOwnership(true);
                        showOwnershipOnField(p);
                    }
                }
            }
        }
    }

    private void initTurn() {
        if(this.player.isInJail()) {
            int inJailTimer = this.player.getInJailTimer();
            this.player.setInJailTimer(inJailTimer - 1);
            this.readyButton.setEnabled(true);
            if((inJailTimer - 1) == 0) {
                this.player.setInJail(false);
            }
        } else {
            this.throwButton.setEnabled(true);
            this.ready = false;
        }
    }

    private void updateStatusOfPlayers() {
        for (Player p : this.players) {
            int pID = p.getPlayerID();
            if(pID != playerID) {
                executeTransaction(p);
                updatePlayerPosition(p);
                updatePlayerLabels(p);
            }
            else {
                updatePlayerLabels(this.player);
            }
        }
    }

    private void executeTransaction(Player p) {
        Transaction transaction = p.getTransaction();
        if(transaction.isActive()) {
            if(transaction.isAll() || transaction.getPlayerID() == this.playerID) {
                this.player.changeBalance(transaction.getValue());
            }
        }
    }

    private void startCheck(ServerMessage message, boolean gameIsReady) {
        if(message.getLap() == 0) {
            setPawnsVisible();
            if (gameIsReady) this.gameStarted = true;
        }
    }

    private void setPawnsVisible() {
        for(Player p : players) {
            pawns.get(p.getPlayerID()).setVisible(true);
        }
    }

    private void updatePlayerLabels(Player p) {
        int pID = p.getPlayerID();
        if(!p.isInsolvency()) {
            CustomLabel label = playerNameLabels.get(pID);
            label.setText("Név: " + p.getPlayerName());
            label = playerMoneyLabels.get(pID);
            label.setText("Pénz: " + p.getMoney());
            label = playerPropertyLabels.get(pID);
            label.setText(String.format("Vasút: %d db || Közmű: %d db", p.getRailRoadCounter(), p.getUtilityCounter()));
            label = playerJailLabels.get(pID);
            label.setText(String.format("Börtön: %d kör", p.getInJailTimer()));
            label = playerExtraLabels.get(pID);
            label.setText("Extrák: " + p.getExtras());
        }
    }

    //fingom sincs hova kell rakni/leellenőrizni
    private void setBankruptcy(Player p){
        //kiszedni a playerek között, hogy ne kapjon újabb kört
        //still megkapja az adatokat, tudja a többieket nézni
        int pID = p.getPlayerID();
        updateBankruptcyLabels(p);
        pawns.get(pID).setVisible(false);
        if(pID == this.playerID) {
            popUpMessage("Kiestél a játékból!", JOptionPane.INFORMATION_MESSAGE);
            this.readyButton.setEnabled(false);
            this.player.setOnline(false);
            this.ready = true;
        }
        //gui rendesen pakolja a bábukat helyére, kiszedem a fieldid-t is
        p.setFieldID(0);
        p.setUtilityCounter(0);
        p.setRailRoadCounter(0);
        p.setExtras("");
        p.setOnline(false);
        for (Integer i : p.getOwnedFieldIDs()) {
            if (fields[i] instanceof PropertyField propertyField) {
                propertyField.setOwnership(false);
                if (propertyField instanceof StreetField streetField && streetField.getOwnerID() == p.getPlayerID()) {
                        if (streetField.isThereHotel()) {
                            streetField.setHotel(false);
                        } else if (streetField.getHouseCounter() > 0) {
                            streetField.setHouseCounter(0);
                        }
                }
            }
            if( fields[i] instanceof PropertyField propertyField) {
                propertyField.setOwnership(false);
            }
        }
    }

    private void updateBankruptcyLabels(Player p){
        int pID = p.getPlayerID();
        CustomLabel label = playerNameLabels.get(pID);
        label.setText("Név: " + p.getPlayerName());
        label = playerMoneyLabels.get(pID);
        label.setText("Pénz: " + p.getMoney() + " -> CSŐD");
        label = playerPropertyLabels.get(pID);
        label.setText("");
        label = playerJailLabels.get(pID);
        label.setText("");
        label = playerExtraLabels.get(pID);
        label.setText("");
    }

    private void initGameBoard() {
        ImageIcon boardIcon = new ImageIcon("src//Images//board.png");
        CustomLabel boardLabel = new CustomLabel(0, 0, 1000, 1000, boardIcon);
        CustomPanel boardPanel = new CustomPanel(0, 0, 1000, 1100, Color.WHITE);
        CustomPanel pawnPanel = initPawns();
        CustomPanel ownedPanel = setOwnedPropertyIndicator();
        boardPanel.add(boardLabel);
        JLayeredPane layeredPaneBoard = new JLayeredPane();
        layeredPaneBoard.setBounds(0, 0, 1000, 1000);
        layeredPaneBoard.add(boardPanel, Integer.valueOf(1));
        layeredPaneBoard.add(pawnPanel, Integer.valueOf(0));
        layeredPaneBoard.add(ownedPanel, Integer.valueOf(0));
        for(int i = 0; i < 4; i++) {
            pawns.add((CustomLabel) pawnPanel.getComponent(i));
        }
        this.add(layeredPaneBoard);
        this.add(boardPanel);
    }

    private CustomPanel setOwnedPropertyIndicator() {
        CustomLabel ownedPiacLabel       = new CustomLabel(786, 860, 30, 30, null);
        CustomLabel ownedTorokLabel      = new CustomLabel(625, 860, 30, 30, null);
        CustomLabel ownedEszakLabel      = new CustomLabel(463, 860, 30, 30, null);
        CustomLabel ownedNagykorosLabel  = new CustomLabel(384, 860, 30, 30, null);
        CustomLabel ownedLestarLabel     = new CustomLabel(220, 860, 30, 30, null);
        CustomLabel ownedKisfaludyLabel  = new CustomLabel(144, 860, 30, 30, null);
        CustomLabel ownedEgyetemLabel    = new CustomLabel(110, 785, 30, 30, null);
        CustomLabel ownedEketromosLabel  = new CustomLabel(110, 700, 30, 30, null);
        CustomLabel ownedSzinhazLabel    = new CustomLabel(110, 620, 30, 30, null);
        CustomLabel ownedJanusLabel      = new CustomLabel(110, 544, 30, 30, null);
        CustomLabel ownedKeletiLabel     = new CustomLabel(110, 457, 30, 30, null);
        CustomLabel ownedPetofiLabel     = new CustomLabel(110, 379, 30, 30, null);
        CustomLabel ownedNagyerdoLabel   = new CustomLabel(110, 217, 30, 30, null);
        CustomLabel ownedBethlenLabel    = new CustomLabel(110, 137, 30, 30, null);
        CustomLabel ownedMoraLabel       = new CustomLabel(143, 107, 30, 30, null);
        CustomLabel ownedOskolaLabel     = new CustomLabel(303, 107, 30, 30, null);
        CustomLabel ownedDomLabel        = new CustomLabel(385, 107, 30, 30, null);
        CustomLabel ownedDeliLabel       = new CustomLabel(460, 107, 30, 30, null);
        CustomLabel ownedDoboLabel       = new CustomLabel(543, 107, 30, 30, null);
        CustomLabel ownedAlmagyarLabel   = new CustomLabel(627, 107, 30, 30, null);
        CustomLabel ownedVizmuLabel      = new CustomLabel(703, 107, 30, 30, null);
        CustomLabel ownedGardonyiLabel   = new CustomLabel(787,107 , 30, 30, null);
        CustomLabel ownedKofaragoLabel   = new CustomLabel(865, 136, 30, 30, null);
        CustomLabel ownedOvarosLabel     = new CustomLabel(865, 217, 30, 30, null);
        CustomLabel ownedOtvosLabel      = new CustomLabel(865, 380, 30, 30, null);
        CustomLabel ownedNyugatiLabel    = new CustomLabel(865, 460, 30, 30, null);
        CustomLabel ownedVorosmartyLabel = new CustomLabel(865, 620, 30, 30, null);
        CustomLabel ownedDunakorzoLabel  = new CustomLabel(865, 782, 30, 30, null);
        CustomPanel ownedPanel           = new CustomPanel(0, 0, 1000, 1000);
        ownedPropertyIndicator.add(null);
        ownedPropertyIndicator.add(ownedPiacLabel);
        ownedPropertyIndicator.add(null);
        ownedPropertyIndicator.add(ownedTorokLabel);
        ownedPropertyIndicator.add(null);
        ownedPropertyIndicator.add(ownedEszakLabel);
        ownedPropertyIndicator.add(ownedNagykorosLabel);
        ownedPropertyIndicator.add(null);
        ownedPropertyIndicator.add(ownedLestarLabel);
        ownedPropertyIndicator.add(ownedKisfaludyLabel);
        ownedPropertyIndicator.add(null);
        ownedPropertyIndicator.add(ownedEgyetemLabel);
        ownedPropertyIndicator.add(ownedEketromosLabel);
        ownedPropertyIndicator.add(ownedSzinhazLabel);
        ownedPropertyIndicator.add(ownedJanusLabel);
        ownedPropertyIndicator.add(ownedKeletiLabel);
        ownedPropertyIndicator.add(ownedPetofiLabel);
        ownedPropertyIndicator.add(null);
        ownedPropertyIndicator.add(ownedNagyerdoLabel);
        ownedPropertyIndicator.add(ownedBethlenLabel);
        ownedPropertyIndicator.add(null);
        ownedPropertyIndicator.add(ownedMoraLabel);
        ownedPropertyIndicator.add(null);
        ownedPropertyIndicator.add(ownedOskolaLabel);
        ownedPropertyIndicator.add(ownedDomLabel);
        ownedPropertyIndicator.add(ownedDeliLabel);
        ownedPropertyIndicator.add(ownedDoboLabel);
        ownedPropertyIndicator.add(ownedAlmagyarLabel);
        ownedPropertyIndicator.add(ownedVizmuLabel);
        ownedPropertyIndicator.add(ownedGardonyiLabel);
        ownedPropertyIndicator.add(null);
        ownedPropertyIndicator.add(ownedKofaragoLabel);
        ownedPropertyIndicator.add(ownedOvarosLabel);
        ownedPropertyIndicator.add(null);
        ownedPropertyIndicator.add(ownedOtvosLabel);
        ownedPropertyIndicator.add(ownedNyugatiLabel);
        ownedPropertyIndicator.add(null);
        ownedPropertyIndicator.add(ownedVorosmartyLabel);
        ownedPropertyIndicator.add(null);
        ownedPropertyIndicator.add(ownedDunakorzoLabel);
        for(int i=0; i < 40; i++){
            if(ownedPropertyIndicator.get(i) != null){
                ownedPanel.add(ownedPropertyIndicator.get(i));
            }
        }
        return ownedPanel;
    }

    private void setPropertyFieldIcons() {
        ImageIcon AlmagyarIcon = new ImageIcon("src//Images//cards//ar//Almagyar_utca_ar.jpg");
        ImageIcon BethlenIcon = new ImageIcon("src//Images//cards//ar//Bethlen_utca_ar.jpg");
        ImageIcon DeliVasutvonalIcon = new ImageIcon("src//Images//cards//ar//Deli_vasutvonal_ar.jpg");
        ImageIcon DoboIcon = new ImageIcon("src//Images//cards//ar//Dobo_ter_ar.jpg");
        ImageIcon DomIcon = new ImageIcon("src//Images//cards//ar//Dom_ter_ar.jpg");
        ImageIcon DunakorzoIcon = new ImageIcon("src//Images//cards//ar//Dunakorzo_ar.jpg");
        ImageIcon EgyetemIcon = new ImageIcon("src//Images//cards//ar//Egyetem_utca_ar.jpg");
        ImageIcon ElektromosTarsasagIcon = new ImageIcon("src//Images//cards//ar//Elektromos_tarsasag_ar.jpg");
        ImageIcon EszakiVasutvonalIcon = new ImageIcon("src//Images//cards//ar//Eszaki_vasutvonal_ar.jpg");
        ImageIcon GardonyiIcon = new ImageIcon("src//Images//cards//ar//Gardonyi_ut_ar.jpg");
        ImageIcon JanuspannoniusIcon = new ImageIcon("src//Images//cards//ar//Janus_Pannonius_ar.jpg");
        ImageIcon KeletiVasutvonalIcon = new ImageIcon("src//Images//cards//ar//Keleti_vasutvonal_ar.jpg");
        ImageIcon KisfaludyIcon = new ImageIcon("src//Images//cards//ar//Kisfaludy_ut_ar.jpg");
        ImageIcon KofaragoIcon = new ImageIcon("src//Images//cards//ar//Kofarago_ter_ar.jpg");
        ImageIcon LestarIcon = new ImageIcon("src//Images//cards//ar//Lestar_ar.jpg");
        ImageIcon MoraIcon = new ImageIcon("src//Images//cards//ar//Mora_park_ar.jpg");
        ImageIcon NagyerdoIcon = new ImageIcon("src//Images//cards//ar//Nagyerdo_ar.jpg");
        ImageIcon NagykorosiIcon = new ImageIcon("src//Images//cards//ar//Nagykorosi_ut_ar.jpg");
        ImageIcon NyugatiVasutvonalIcon = new ImageIcon("src//Images//cards//ar//Nyugati_vasutvonal_ar.jpg");
        ImageIcon OskolaIcon = new ImageIcon("src//Images//cards//ar//Oskola_utca_ar.jpg");
        ImageIcon OtvosIcon = new ImageIcon("src//Images//cards//ar//Otvos_utca_ar.jpg");
        ImageIcon OvarosIcon = new ImageIcon("src//Images//cards//ar//Ovaros_ar.jpg");
        ImageIcon PetofiIcon = new ImageIcon("src//Images//cards//ar//Petofi_ter_ar.jpg");
        ImageIcon PiacIcon = new ImageIcon("src//Images//cards//ar//Piac_ter_ar.jpg");
        ImageIcon SzinhazIcon = new ImageIcon("src//Images//cards//ar//Szinhaz_ter_ar.jpg");
        ImageIcon TorokIcon = new ImageIcon("src//Images//cards//ar//Torok_udvar_ar.jpg");
        ImageIcon VizmuTarsasagIcon = new ImageIcon("src//Images//cards//ar//Vizmu_tarsasag_ar.jpg");
        ImageIcon VorosmartyIcon = new ImageIcon("src//Images//cards//ar//Vorosmarty_ter_ar.jpg");
        propertyCardIcons.add(null);
        propertyCardIcons.add(PiacIcon);
        propertyCardIcons.add(null);
        propertyCardIcons.add(TorokIcon);
        propertyCardIcons.add(null);
        propertyCardIcons.add(EszakiVasutvonalIcon);
        propertyCardIcons.add(NagykorosiIcon);
        propertyCardIcons.add(null);
        propertyCardIcons.add(LestarIcon);
        propertyCardIcons.add(KisfaludyIcon);
        propertyCardIcons.add(null);
        propertyCardIcons.add(EgyetemIcon);
        propertyCardIcons.add(ElektromosTarsasagIcon);
        propertyCardIcons.add(SzinhazIcon);
        propertyCardIcons.add(JanuspannoniusIcon);
        propertyCardIcons.add(KeletiVasutvonalIcon);
        propertyCardIcons.add(PetofiIcon);
        propertyCardIcons.add(null);
        propertyCardIcons.add(NagyerdoIcon);
        propertyCardIcons.add(BethlenIcon);
        propertyCardIcons.add(null);
        propertyCardIcons.add(MoraIcon);
        propertyCardIcons.add(null);
        propertyCardIcons.add(OskolaIcon);
        propertyCardIcons.add(DomIcon);
        propertyCardIcons.add(DeliVasutvonalIcon);
        propertyCardIcons.add(DoboIcon);
        propertyCardIcons.add(AlmagyarIcon);
        propertyCardIcons.add(VizmuTarsasagIcon);
        propertyCardIcons.add(GardonyiIcon);
        propertyCardIcons.add(null);
        propertyCardIcons.add(KofaragoIcon);
        propertyCardIcons.add(OvarosIcon);
        propertyCardIcons.add(null);
        propertyCardIcons.add(OtvosIcon);
        propertyCardIcons.add(NyugatiVasutvonalIcon);
        propertyCardIcons.add(null);
        propertyCardIcons.add(VorosmartyIcon);
        propertyCardIcons.add(null);
        propertyCardIcons.add(DunakorzoIcon);
    }

    private void initFields() {
        this.fields = new Field[40];
            // Go Field
            this.fields[0] = new GoField(0);
            // Jail Field
            this.fields[10] = new JailField(10);
            // Free Parking Field
            this.fields[20] = new FreeParkingField(20);
            // GoToJail Field
            this.fields[30] = new GoToJailField(30);
            // CC Field
            this.fields[2] = new CommunityChestField(2);
            this.fields[33] = new CommunityChestField(33);
            this.fields[17] = new CommunityChestField(17);
            // Chance Field
            this.fields[7] = new ChanceField(7);
            this.fields[22] = new ChanceField(22);
            this.fields[36] = new ChanceField(36);
            // Tax Field
            this.fields[4] = new TaxField(4,100);
            this.fields[38] = new TaxField(38,100);

            // Utilities Field
            this.fields[12] = new UtilityField(12,150);
            this.fields[28] = new UtilityField(28,150);
            // Railroad Field
            this.fields[5] = new RailRoadField(5,200,25);
            this.fields[15] = new RailRoadField(15,200,25);
            this.fields[25] = new RailRoadField(25,200,25);
            this.fields[35] = new RailRoadField(35,200,25);

            //Streets
            this.fields[1] = new StreetField(1, PropertyFieldColor.BROWN, 30,50,50);
            this.fields[3] = new StreetField(3, PropertyFieldColor.BROWN_EXTRA, 30,50,50);
            this.fields[6] = new StreetField(6, PropertyFieldColor.WHITE, 50,50,50);
            this.fields[8] = new StreetField(8, PropertyFieldColor.WHITE, 50,50,50);
            this.fields[9] = new StreetField(9, PropertyFieldColor.WHITE_EXTRA, 60,50,50);
            this.fields[11] = new StreetField(11, PropertyFieldColor.PURPLE, 70,100,100);
            this.fields[13] = new StreetField(13, PropertyFieldColor.PURPLE, 70,100,100);
            this.fields[14] = new StreetField(14, PropertyFieldColor.PURPLE_EXTRA, 80,100,100);
            this.fields[16] = new StreetField(16, PropertyFieldColor.ORANGE,90, 100,100);
            this.fields[18] = new StreetField(18, PropertyFieldColor.ORANGE,90, 100,100);
            this.fields[19] = new StreetField(19, PropertyFieldColor.ORANGE_EXTRA,100, 100,100);
            this.fields[21] = new StreetField(21, PropertyFieldColor.RED, 110, 150,150);
            this.fields[23] = new StreetField(23, PropertyFieldColor.RED, 110, 150,150);
            this.fields[24] = new StreetField(24, PropertyFieldColor.RED_EXTRA, 120, 150,150);
            this.fields[26] = new StreetField(26, PropertyFieldColor.YELLOW, 130,150,150);
            this.fields[27] = new StreetField(27, PropertyFieldColor.YELLOW, 130,150,150);
            this.fields[29] = new StreetField(29, PropertyFieldColor.YELLOW_EXTRA, 140,150,150);
            this.fields[31] = new StreetField(31, PropertyFieldColor.GREEN, 150,200,200);
            this.fields[32] = new StreetField(32, PropertyFieldColor.GREEN, 150,200,200);
            this.fields[34] = new StreetField(34, PropertyFieldColor.GREEN_EXTRA, 160,200,200);
            this.fields[37] = new StreetField(37, PropertyFieldColor.BLUE, 175,200,200);
            this.fields[39] = new StreetField(39, PropertyFieldColor.BLUE_EXTRA, 200,200,200);
    }
}