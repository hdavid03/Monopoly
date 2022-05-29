package GUI;

import game_elements.Player;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MonopolyGUI extends JFrame {

    private CustomButton payButton;
    private CustomButton readyButton;
    private CustomButton throwButton;
    private Player player;
    private final String userName;
    private boolean ready = false;
    private LinkedList<Player> players = new LinkedList<>();
    private ArrayList<CustomLabel> ownedPropertyIndicator = new ArrayList<>();
    private ArrayList<ImageIcon> surpriseCardIcons = new ArrayList<>();
    private ArrayList<ImageIcon> chanceCardIcons = new ArrayList<>();
    private ArrayList<ImageIcon> propertyCardIcons = new ArrayList<>();
    private ArrayList<CustomLabel> playerNameLabels = new ArrayList<>();
    private ArrayList<CustomLabel> playerMoneyLabels = new ArrayList<>();
    private ArrayList<ImageIcon> dieIcons = new ArrayList<>();

    private CustomLabel die1Label;
    private CustomLabel die2Label;
    private CustomPanel dicePanel;
    private CustomPanel pawnPanel;

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

    public MonopolyGUI(String userName){
        setPawns();
        initGameBoard();
        initPlayers();
        CustomPanel cardsPanel = getCustomCardsPanel();
        setDicePanels();
        CustomPanel actionPanel = getCustomActionPanel();
        setActionListeners();
        setImageIcons(chanceCardIcons, "src//resources//szerencse.txt");
        setImageIcons(surpriseCardIcons, "src//resources//meglepetes.txt");
        setImageIcons(dieIcons, "src//resources//dice.txt");
        this.userName = userName;
        this.setTitle("Monopoly");
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);   //full screen with window
        this.setLayout(null);
        this.setVisible(true);
        this.add(cardsPanel);
        this.add(dicePanel);
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
        cardsPanel.add(cardTitleLabel);
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

    public boolean isReady() {
        return ready;
    }

    public String getUserName() {
        return userName;
    }

    private void initPlayers() {
        CustomLabel playerTitleLabel = new CustomLabel("Játékosok", 60, 260, 10, 400, 90);
        CustomPanel playerTitlePanel = new CustomPanel(1000, 0, 800, 100, Color.PINK);
        playerTitlePanel.add(playerTitleLabel);
        this.add(playerTitlePanel);
        initDefaultPlayer(1000, 100, Color.GREEN);
        initDefaultPlayer(1400, 100, Color.RED);
        initDefaultPlayer(1000, 250, Color.MAGENTA);
        initDefaultPlayer(1400, 250, Color.YELLOW);
    }

    private void initDefaultPlayer(int x, int y, Color color) {
        CustomLabel playerNameLabel = new CustomLabel("Offline", 20, 10, 0, 400, 30);
        CustomLabel playerMoneyLabel = new CustomLabel("", 20, 10, 30, 400, 30);
        CustomPanel playerPanel = new CustomPanel(x, y, 400, 150, color);
        playerPanel.add(playerNameLabel);
        playerPanel.add(playerMoneyLabel);
        playerNameLabels.add(playerNameLabel);
        playerMoneyLabels.add(playerMoneyLabel);
        this.add(playerPanel);
    }

    private void setActionListeners() {
        this.payButton.addActionListener(e -> System.out.println("Fizettél nekem"));

        this.readyButton.addActionListener(e -> {
            this.ready = true;
            this.readyButton.setVisible(false);
            this.throwButton.setVisible(true);
            //this.throwButton.setEnabled(false);
        });

        this.throwButton.addActionListener(e -> {
            SecureRandom random = new SecureRandom();
            die1Label.setIcon(dieIcons.get(random.nextInt(6)));
            die2Label.setIcon(dieIcons.get(random.nextInt(6)));
            dicePanel.repaint();
        });
    }

    private void setPawns() {
        ImageIcon shoeIcon = new ImageIcon("src//Images//pawns//shoe.jpg");
        ImageIcon hatIcon = new ImageIcon("src//Images//pawns//hat.png");
        ImageIcon boatIcon = new ImageIcon("src//Images//pawns//boat.png");
        ImageIcon carIcon = new ImageIcon("src//Images//pawns//car.jpg");
        CustomLabel pawnshoeLabel = new CustomLabel(25, 25, 25, 25, shoeIcon);
        CustomLabel pawnhatLabel = new CustomLabel(25, 50, 25, 25, hatIcon);
        CustomLabel pawnboatLabel = new CustomLabel(50, 25, 25, 25, boatIcon);
        CustomLabel pawncarLabel = new CustomLabel(50, 50, 25, 25, carIcon);
        pawnPanel = new CustomPanel(0, 0, 1000, 1000);
        pawnPanel.add(pawnshoeLabel);
        pawnPanel.add(pawnhatLabel);
        pawnPanel.add(pawnboatLabel);
        pawnPanel.add(pawncarLabel);
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

    private void setPlayers() {
        int i = 0;
        for (Player player : this.players) {
            CustomLabel label = playerNameLabels.get(i);
            label.setText(player.getPlayerName());
            label = playerMoneyLabels.get(i);
            label.setText(String.valueOf(player.getMoney()));
            i++;
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
    }

    public void updateGameBoard(Queue<Player> players) {
        this.players.clear();
        this.players.addAll(players);
        setPlayers();
    }

    private void initGameBoard() {
        ImageIcon boardIcon = new ImageIcon("src//Images//board.png");
        CustomLabel boardLabel = new CustomLabel(0, 0, 1000, 1000, boardIcon);
        CustomPanel boardPanel = new CustomPanel(0, 0, 1000, 1100, Color.WHITE);
        boardPanel.add(boardLabel);
        JLayeredPane layeredPaneBoard = new JLayeredPane();
        layeredPaneBoard.setBounds(0, 0, 1000, 1000);
        layeredPaneBoard.add(boardPanel, Integer.valueOf(1));
        layeredPaneBoard.add(pawnPanel, Integer.valueOf(0));
        this.add(boardPanel);
        this.add(layeredPaneBoard);
    }

    private void setOwnedPropertyIndicator() {
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
    }

    private void setPropertyFieldIcons {
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

