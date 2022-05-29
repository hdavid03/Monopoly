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
    private ArrayList<ImageIcon> propertyCardIcons = new ArrayList<>();
    private ArrayList<ImageIcon> surpriseCardIcons = new ArrayList<>();
    private ArrayList<ImageIcon> chanceCardIcons = new ArrayList<>();
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
        setImageIcons(propertyCardIcons, "src//resources//ter_kozmu.txt");
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
        CustomLabel ownedpiacLabel       = new CustomLabel(786, 860, 30, 30, null);
        CustomLabel ownedtorokLabel      = new CustomLabel(625, 860, 30, 30, null);
        CustomLabel ownedeszakLabel      = new CustomLabel(463, 860, 30, 30, null);
        CustomLabel ownednagykorosLabel  = new CustomLabel(384, 860, 30, 30, null);
        CustomLabel ownedlestarLabel     = new CustomLabel(220, 860, 30, 30, null);
        CustomLabel ownedkisfaludyLabel  = new CustomLabel(144, 860, 30, 30, null);
        CustomLabel ownedegyetemLabel    = new CustomLabel(110, 785, 30, 30, null);
        CustomLabel ownedeketromosLabel  = new CustomLabel(110, 700, 30, 30, null);
        CustomLabel ownedszinhazLabel    = new CustomLabel(110, 620, 30, 30, null);
        CustomLabel ownedjanusLabel      = new CustomLabel(110, 544, 30, 30, null);
        CustomLabel ownedkeletiLabel     = new CustomLabel(110, 457, 30, 30, null);
        CustomLabel ownedpetofiLabel     = new CustomLabel(110, 379, 30, 30, null);
        CustomLabel ownednagyerdoLabel   = new CustomLabel(110, 217, 30, 30, null);
        CustomLabel ownedbethlenLabel    = new CustomLabel(110, 137, 30, 30, null);
        CustomLabel ownedmoraLabel       = new CustomLabel(143, 107, 30, 30, null);
        CustomLabel ownedoskolaLabel     = new CustomLabel(303, 107, 30, 30, null);
        CustomLabel owneddomLabel        = new CustomLabel(385, 107, 30, 30, null);
        CustomLabel owneddeliLabel       = new CustomLabel(460, 107, 30, 30, null);
        CustomLabel owneddoboLabel       = new CustomLabel(543, 107, 30, 30, null);
        CustomLabel ownedalmagyarLabel   = new CustomLabel(627, 107, 30, 30, null);
        CustomLabel ownedvizmuLabel      = new CustomLabel(703, 107, 30, 30, null);
        CustomLabel ownedgardonyiLabel   = new CustomLabel(787,107 , 30, 30, null);
        CustomLabel ownedkofaragoLabel   = new CustomLabel(865, 136, 30, 30, null);
        CustomLabel ownedovarosLabel     = new CustomLabel(865, 217, 30, 30, null);
        CustomLabel ownedotvosLabel      = new CustomLabel(865, 380, 30, 30, null);
        CustomLabel ownednyugatiLabel    = new CustomLabel(865, 460, 30, 30, null);
        CustomLabel ownedvorosmartyLabel = new CustomLabel(865, 620, 30, 30, null);
        CustomLabel owneddunakorzoLabel  = new CustomLabel(865, 782, 30, 30, null);
        CustomPanel ownedPanel = new CustomPanel(0, 0, 1000, 1000);

        ownedPropertyIndicator.add(null);
        ownedPropertyIndicator.add(ownedpiacLabel);
        ownedPropertyIndicator.add(null);
        ownedPropertyIndicator.add(ownedtorokLabel);
        ownedPropertyIndicator.add(null);
        ownedPropertyIndicator.add(ownedeszakLabel);
        ownedPropertyIndicator.add(ownednagykorosLabel);
        ownedPropertyIndicator.add(null);
        ownedPropertyIndicator.add(ownedlestarLabel);
        ownedPropertyIndicator.add(ownedkisfaludyLabel);
        ownedPropertyIndicator.add(null);
        ownedPropertyIndicator.add(ownedegyetemLabel);
        ownedPropertyIndicator.add(ownedeketromosLabel);
        ownedPropertyIndicator.add(ownedszinhazLabel);
        ownedPropertyIndicator.add(ownedjanusLabel);
        ownedPropertyIndicator.add(ownedkeletiLabel);
        ownedPropertyIndicator.add(ownedpetofiLabel);
        ownedPropertyIndicator.add(null);
        ownedPropertyIndicator.add(ownednagyerdoLabel);
        ownedPropertyIndicator.add(ownedbethlenLabel);
        ownedPropertyIndicator.add(null);
        ownedPropertyIndicator.add(ownedmoraLabel);
        ownedPropertyIndicator.add(null);
        ownedPropertyIndicator.add(ownedoskolaLabel);
        ownedPropertyIndicator.add(owneddomLabel);
        ownedPropertyIndicator.add(owneddeliLabel);
        ownedPropertyIndicator.add(owneddoboLabel);
        ownedPropertyIndicator.add(ownedalmagyarLabel);
        ownedPropertyIndicator.add(ownedvizmuLabel);
        ownedPropertyIndicator.add(ownedgardonyiLabel);
        ownedPropertyIndicator.add(null);
        ownedPropertyIndicator.add(ownedkofaragoLabel);
        ownedPropertyIndicator.add(ownedovarosLabel);
        ownedPropertyIndicator.add(null);
        ownedPropertyIndicator.add(ownedotvosLabel);
        ownedPropertyIndicator.add(ownednyugatiLabel);
        ownedPropertyIndicator.add(null);
        ownedPropertyIndicator.add(ownedvorosmartyLabel);
        ownedPropertyIndicator.add(null);
        ownedPropertyIndicator.add(owneddunakorzoLabel);
    }
}

