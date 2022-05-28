package GUI;

import game_elements.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MonopolyGUI extends JFrame {

    CustomButton payButton;
    CustomButton readyButton;
    private Player player;
    private String userName;
    private LinkedList<Player> players;
    //Images
    //board
    ImageIcon boardThousandsIcon;
    //pawns
    ImageIcon shoeIcon;
    ImageIcon hatIcon;
    ImageIcon boatIcon;
    ImageIcon carIcon;
    //cards
    ImageIcon meglepetes1Icon;
    ImageIcon almagyarIcon;
    //dices
    ImageIcon dice1Icon;
    ImageIcon dice2Icon;
    //logos
    ImageIcon dollarLogoIcon;
    //dice
    CustomLabel diceTitleLabel;
    CustomLabel dice1Label;
    CustomLabel dice2Label;
    CustomPanel dicePanel;

    ArrayList<CustomLabel> playerNameLabels;
    ArrayList<CustomLabel> playerMoneyLabels;
    CustomLabel player1Namelabel;
    CustomLabel player1Moneylabel;
    CustomPanel player1Panel;
    CustomLabel player2Namelabel;
    CustomLabel player2Moneylabel;
    CustomPanel player2Panel;
    CustomLabel player3Namelabel;
    CustomLabel player3Moneylabel;
    CustomPanel player3Panel;
    CustomLabel player4Namelabel;
    CustomLabel player4Moneylabel;
    CustomPanel player4Panel;

    CustomLabel pawnshoeLabel;
    CustomLabel pawnhatLabel;
    CustomLabel pawnboatLabel;
    CustomLabel pawncarLabel;
    CustomPanel pawnPanel;

    public MonopolyGUI(String userName){

        this.userName = userName;
        setImageIcons();
        this.players = new LinkedList<>();
        this.playerNameLabels = new ArrayList<>();
        this.playerMoneyLabels = new ArrayList<>();
        //board
        CustomLabel boardLabel = new CustomLabel(0, 0, 1000, 1000, boardThousandsIcon);
        CustomPanel boardPanel = new CustomPanel(0, 0, 1000, 1100, Color.WHITE);
        boardPanel.add(boardLabel);

        //pawns
        setPawns();
        //players title
        CustomLabel playerTitleLabel = new CustomLabel("Játékosok", 60, 260, 10, 400, 90);
        CustomPanel playerTitlePanel = new CustomPanel(1000, 0, 800, 100, Color.PINK);

        playerTitlePanel.add(playerTitleLabel);
        //players
        initPlayers();

        //cards
        CustomLabel cardTitleLabel = new CustomLabel("Kártyák", 40, 10, 10, 200, 50);
        CustomLabel card1Label = new CustomLabel(110, 60, 600, 350, meglepetes1Icon);
        CustomLabel card2Label = new CustomLabel(110, 60, 600, 350, almagyarIcon);
        CustomPanel cardsPanel = new CustomPanel(1000, 400, 600, 400, Color.WHITE);

        cardsPanel.add(cardTitleLabel);
        cardsPanel.add(card1Label);
        //cardsPanel.add(card2Label);
        setDicePanels(Color.BLUE, "Kockák");
        CustomLabel actionTitleLabel = new CustomLabel("Akciók", 40, 10, 10, 200, 50);

        this.payButton = new CustomButton(50, 80, 250, 100, "Fizetés", dollarLogoIcon);
        this.readyButton = new CustomButton(320, 80, 250, 100, "Ready", dollarLogoIcon);
        CustomPanel actionPanel = new CustomPanel(1000, 800, 800, 300, Color.CYAN);
        setActionListeners();

        actionPanel.add(actionTitleLabel);
        actionPanel.add(this.payButton);
        actionPanel.add(this.readyButton);

        //LAYERED PANE
        //layers: default - 0 (JLayeredPane.DEFAULT_LAYER), palette - 1, modal - 2, popup - 3, drag - 4
        JLayeredPane layeredPaneBoard = new JLayeredPane();
        layeredPaneBoard.setBounds(0, 0, 1000, 1000);
        layeredPaneBoard.add(boardPanel, Integer.valueOf(1));
        layeredPaneBoard.add(pawnPanel, Integer.valueOf(0));

        this.setTitle("Monopoly");
        this.add(layeredPaneBoard);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);   //full screen with window
        this.setLayout(null);
        this.setVisible(true);
        this.add(boardPanel);
        this.add(playerTitlePanel);
        this.add(player1Panel);
        this.add(player2Panel);
        this.add(player3Panel);
        this.add(player4Panel);
        this.add(cardsPanel);
        this.add(dicePanel);
        this.add(actionPanel);
    }

    public String getUserName() {
        return userName;
    }

    private void initPlayers() {
        player1Namelabel = new CustomLabel("Offline", 20, 10, 0, 400, 30);
        player1Moneylabel = new CustomLabel("", 20, 10, 30, 400, 30);
        player1Panel = new CustomPanel(1000, 100, 400, 150, Color.GREEN);
        player1Panel.add(player1Namelabel);
        player1Panel.add(player1Moneylabel);
        player2Namelabel = new CustomLabel("Offline", 20, 10, 0, 400, 30);
        player2Moneylabel = new CustomLabel("", 20, 10, 30, 400, 30);
        player2Panel = new CustomPanel(1400, 100, 400, 150, Color.RED);
        player2Panel.add(player2Namelabel);
        player2Panel.add(player2Moneylabel);
        player3Namelabel = new CustomLabel("Offline", 20, 10, 0, 400, 30);
        player3Moneylabel = new CustomLabel("", 20, 10, 30, 400, 30);
        player3Panel = new CustomPanel(1000, 250, 400, 150, Color.MAGENTA);
        player3Panel.add(player3Namelabel);
        player3Panel.add(player3Moneylabel);
        player4Namelabel = new CustomLabel("Offline", 20, 10, 0, 400, 30);
        player4Moneylabel = new CustomLabel("", 20, 10, 30, 400, 30);
        player4Panel = new CustomPanel(1400, 250, 400, 150, Color.YELLOW);
        player4Panel.add(player4Namelabel);
        player4Panel.add(player4Moneylabel);
        playerNameLabels.add(player1Namelabel);
        playerNameLabels.add(player2Namelabel);
        playerNameLabels.add(player3Namelabel);
        playerNameLabels.add(player4Namelabel);
        playerMoneyLabels.add(player1Moneylabel);
        playerMoneyLabels.add(player2Moneylabel);
        playerMoneyLabels.add(player3Moneylabel);
        playerMoneyLabels.add(player4Moneylabel);
    }

    private void setActionListeners() {
        this.payButton.addActionListener(e -> {
            System.out.println("Fizettél nekem");
        });
        this.readyButton.addActionListener(e -> {
            System.out.println("Kész vagyok");
        });
    }

    private void setPawns() {
        pawnshoeLabel = new CustomLabel(25, 25, 25, 25, shoeIcon);
        pawnhatLabel = new CustomLabel(25, 50, 25, 25, hatIcon);
        pawnboatLabel = new CustomLabel(50, 25, 25, 25, boatIcon);
        pawncarLabel = new CustomLabel(50, 50, 25, 25, carIcon);
        pawnPanel = new CustomPanel(0, 0, 1000, 1000);
        pawnPanel.add(pawnshoeLabel);
        pawnPanel.add(pawnhatLabel);
        pawnPanel.add(pawnboatLabel);
        pawnPanel.add(pawncarLabel);
    }

    private void setPlayers() {
        int i = 0;
        for (Player player : this.players) {
            CustomLabel label = playerNameLabels.get(i);
            label.setText(player.getPlayerName());
            label = playerMoneyLabels.get(i);
            label.setText(String.valueOf(player.getMoney()));
        }
    }

    private void setDicePanels(Color blueBackground, String diceTitle) {
        diceTitleLabel = new CustomLabel(diceTitle, 40, 10, 10, 200, 50);
        dice1Label = new CustomLabel(25, 70, 200, 150, dice1Icon);
        dice2Label = new CustomLabel(25, 230, 200, 150, dice2Icon);
        dicePanel = new CustomPanel(1600, 400, 200, 400, blueBackground);
        dicePanel.add(diceTitleLabel);
        dicePanel.add(dice1Label);
        dicePanel.add(dice2Label);
    }

    public void updateGameBoard(Queue<Player> players) {
        this.players.clear();
        this.players.addAll(players);
        setPlayers();
    }

    private void setImageIcons() {
        boardThousandsIcon = new ImageIcon("src//Images//boardThousands.jpg");
        //pawns
        shoeIcon = new ImageIcon("src//Images//pawns//shoe.jpg");
        hatIcon = new ImageIcon("src//Images//pawns//hat.png");
        boatIcon = new ImageIcon("src//Images//pawns//boat.png");
        carIcon = new ImageIcon("src//Images//pawns//car.jpg");
        //cards
        meglepetes1Icon = new ImageIcon("src//Images//cards//meglepetes1.jpg");
        almagyarIcon = new ImageIcon("src//Images//cards//Almagyar_utca_ar.jpg");
        //dices
        dice1Icon = new ImageIcon("src//Images//dices//dice1.png");
        dice2Icon = new ImageIcon("src//Images//dices//dice2.png");
        //logos
        dollarLogoIcon = new ImageIcon("src//Images//logos//dollar.png");
    }
}
