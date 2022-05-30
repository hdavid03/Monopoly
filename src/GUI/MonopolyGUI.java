package GUI;

import game_elements.Player;
import networking.ServerMessage;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Scanner;

public class MonopolyGUI extends JFrame {

    private CustomButton payButton;
    private CustomButton readyButton;
    private CustomButton throwButton;
    private int playerID;
    private int playerCount = 0;
    private Player player;
    private final String userName;
    private boolean ready = false;
    private ArrayList<Player> players = new ArrayList<>();
    private final ArrayList<CustomLabel> ownedPropertyIndicator = new ArrayList<>();
    private final ArrayList<ImageIcon> surpriseCardIcons = new ArrayList<>();
    private final ArrayList<ImageIcon> chanceCardIcons = new ArrayList<>();
    private final ArrayList<ImageIcon> propertyCardIcons = new ArrayList<>();
    private final ArrayList<CustomLabel> playerNameLabels = new ArrayList<>();
    private final ArrayList<CustomLabel> playerMoneyLabels = new ArrayList<>();
    private final ArrayList<CustomLabel> playerPropertyLabels = new ArrayList<>();
    private final ArrayList<CustomLabel> playerJailLabels = new ArrayList<>();
    private final ArrayList<CustomLabel> playerExtraLabels = new ArrayList<>();
    private final ArrayList<CustomLabel> pawns = new ArrayList<>();
    private final ArrayList<ImageIcon> dieIcons = new ArrayList<>();

    private CustomLabel card1Label;
    private CustomLabel die1Label;
    private CustomLabel die2Label;
    private CustomPanel dicePanel;

    JComboBox comboBox;
    //arrayxy
    public static int[][] arrayXY = {{875,925},  {800,925},  {725,925},  {635,925},  {550,920},          //0
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
        CustomPanel cardsPanel = new CustomPanel(1000, 400, 600, 400, Color.WHITE);
        card1Label = new CustomLabel(110, 60, 600, 350, null);
        cardsPanel.add(cardTitleLabel);
        cardsPanel.add(card1Label);
        return cardsPanel;
    }

    private void setButtons(CustomLabel actionTitleLabel, CustomPanel actionPanel) {
        ImageIcon dollarLogoIcon = new ImageIcon("src//Images//logos//dollar.png");
        ImageIcon readyLogoIcon = new ImageIcon("src//Images//logos//ready.png");
        this.payButton = new CustomButton(50, 80, 250, 100, "Fizetés", dollarLogoIcon);
        this.readyButton = new CustomButton(320, 80, 250, 100, "Kész", readyLogoIcon);
        this.throwButton = new CustomButton(590, 80, 200, 100, "Dobás", dollarLogoIcon);
        this.throwButton.setVisible(false);
        actionPanel.add(actionTitleLabel);
        actionPanel.add(this.payButton);
        actionPanel.add(this.readyButton);
        actionPanel.add(this.throwButton);
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

    public String getUserName() {
        return userName;
    }

    public void fieldImage(Integer tablefieldsID){
        card1Label.setIcon(propertyCardIcons.get(tablefieldsID));
    }

    public void goingOnFields(int resultOfThrowing){
        int newFieldID = (0 + resultOfThrowing) % 40;
        System.out.println(newFieldID);
        pawns.get(0).setLocation(arrayXY[newFieldID][0], arrayXY[newFieldID][1]);
        System.out.println("ok");

        //playercount switch

        Integer playercountX = pawns.get(playerID).getX();
        Integer playercountY = pawns.get(playerID).getY();
        switch (playerCount) {
            case 0:
                break;
            case 1:
                pawns.get(playerID).setLocation(playercountX, playercountY + 25);
                break;
            case 2:
                pawns.get(playerID).setLocation(playercountX + 25, playercountY);
                break;
            case 3:
                pawns.get(playerID).setLocation(playercountX + 25, playercountY + 25);
                break;
            default:
                System.out.println("Hiba a playerCount switch szerkezetben!!");
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

    private void setActionListeners() {
        this.payButton.addActionListener(e -> System.out.println("Fizettél nekem"));

        this.readyButton.addActionListener(e -> {
            this.ready = true;
            this.readyButton.setVisible(false);
            this.throwButton.setVisible(true);
            pawns.get(0).setVisible(true);
            //this.throwButton.setEnabled(false);
        });

        this.throwButton.addActionListener(e -> {
            SecureRandom random = new SecureRandom();
            int result1 = random.nextInt(6);
            int result2 = random.nextInt(6);
            die1Label.setIcon(dieIcons.get(result1));
            die2Label.setIcon(dieIcons.get(result2));
            dicePanel.repaint();
            goingOnFields(result1 + result2);
        });
    }

    private CustomPanel initPawns() {
        ImageIcon shoeIcon = new ImageIcon("src//Images//pawns//shoe.jpg");
        ImageIcon hatIcon = new ImageIcon("src//Images//pawns//hat.png");
        ImageIcon boatIcon = new ImageIcon("src//Images//pawns//boat.png");
        ImageIcon carIcon = new ImageIcon("src//Images//pawns//car.jpg");
        CustomLabel pawnShoeLabel = new CustomLabel(875, 900, 25, 25, shoeIcon);
        CustomLabel pawnHatLabel = new CustomLabel(875, 925, 25, 25, hatIcon);
        CustomLabel pawnBoatLabel = new CustomLabel(900, 900, 25, 25, boatIcon);
        CustomLabel pawnCarLabel = new CustomLabel(900, 925, 25, 25, carIcon);
        CustomPanel pawnPanel = new CustomPanel(0, 0, 1000, 1000);
        pawnPanel.add(pawnShoeLabel);
        pawnPanel.add(pawnHatLabel);
        pawnPanel.add(pawnBoatLabel);
        pawnPanel.add(pawnCarLabel);
        //pawnBoatLabel.setVisible(false);
        //pawnCarLabel.setVisible(false);
        //pawnShoeLabel.setVisible(false);
        //pawnHatLabel.setVisible(false);
        pawns.add(pawnShoeLabel);
        pawns.add(pawnHatLabel);
        pawns.add(pawnBoatLabel);
        pawns.add(pawnCarLabel);
        return pawnPanel;
    }

    private void setImageIcons(ArrayList<ImageIcon> cardIcons, String source) {
        try(FileInputStream fis = new FileInputStream(source)) {
            Scanner scanner = new Scanner(fis);
            while(scanner.hasNextLine()){
                String path = scanner.nextLine();
                cardIcons.add(new ImageIcon(path));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void updateGameBoard(ServerMessage message) {
        ArrayList<Player> oldPlayerList = new ArrayList<>(this.players);
        this.players.clear();
        this.players.addAll(message.getPlayers());
        this.player = this.players.get(playerID);
        int updatedPlayerCount = this.players.size();
        boolean anyPlayerDisconnected = playerCount > updatedPlayerCount;
        playerCount = updatedPlayerCount;
        for (Player p : this.players) {
            int pID = p.getPlayerID();
            if(message.getLap() == 0 && message.isGameIsReady()) pawns.get(pID).setVisible(true);
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
        if(anyPlayerDisconnected) {
            deleteDisconnectedPlayer(oldPlayerList);
        }
    }

    private void deleteDisconnectedPlayer(ArrayList<Player> oldPlayerList) {
        boolean IDfound = false;
        for(int i = 0; i < oldPlayerList.size(); i++) {
            int ID = oldPlayerList.get(i).getPlayerID();
            for(int j = 0; i < playerCount; j++) {
                if (ID == this.players.get(j).getPlayerID()) {
                    IDfound = true;
                    break;
                }
            }
            if(IDfound) {
                IDfound = false;
            } else {
                initDefaultPlayer(defaultPlayerPanelPostions[ID][0], defaultPlayerPanelPostions[ID][1], playerColors[ID]);
                pawns.get(ID).setVisible(false);
            }
        }
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
        ImageIcon VizmuTarsasagIcon = new ImageIcon("src//Images//cards//ar//Vizmu_tarsasag.jpg");
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
}