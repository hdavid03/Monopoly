package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MonopolyGUI extends JFrame implements ActionListener {

    custom_Button button1;

    MonopolyGUI(){

        //Images
        //board
        ImageIcon boardThousandsIcon = new ImageIcon("src//Images//boardThousands.jpg");
        ImageIcon boardIcon = new ImageIcon("src//Images//board.png");
        //pawns
        ImageIcon shoeIcon = new ImageIcon("src//Images//pawns//shoe.jpg");
        ImageIcon hatIcon = new ImageIcon("src//Images//pawns//hat.png");
        ImageIcon boatIcon = new ImageIcon("src//Images//pawns//boat.png");
        ImageIcon carIcon = new ImageIcon("src//Images//pawns//car.jpg");
        //cards
        ImageIcon meglepetes1Icon = new ImageIcon("src//Images//cards//meglepetes1.jpg");
        ImageIcon almagyarIcon = new ImageIcon("src//Images//cards//Almagyar_utca_ar.jpg");
        //dices
        ImageIcon dice1Icon = new ImageIcon("src//Images//dices//dice1.png");
        ImageIcon dice2Icon = new ImageIcon("src//Images//dices//dice2.png");
        //logos
        ImageIcon dollarLogoIcon = new ImageIcon("src//Images//logos//dollar.png");

        //colors
        Color redBackground = Color.RED;
        Color blueBackground = Color.BLUE;
        Color greenBackground = Color.GREEN;
        Color yellowBackground = Color.YELLOW;
        Color whiteBackground = Color.WHITE;
        Color pinkBackground = Color.PINK;
        Color grayBackground = Color.GRAY;
        Color magentaBackground = Color.MAGENTA;
        Color cyanBackground = Color.CYAN;
/*
        Integer top = JLabel.TOP;
        Integer left = JLabel.LEFT;
        Integer center = JLabel.CENTER;
*/
        //players
        String playerTitle =    "Játékosok";
        String playerName =     "név:   ";
        String playerMoney =    "pénz:  ";
        //player1
        String player1Name = "Valaki";
        String player1Money = "500 Forint";
        //player2
        String player2Name = "Valaki2";
        String player2Money = "1000 Forint";
        //player3
        String player3Name = "Valaki3";
        String player3Money = "1500 Forint";
        //player4
        String player4Name = "Valaki4";
        String player4Money = "2000 Forint";
        //cards
        String cardTitle = "Kártyák";
        //dice
        String diceTitle = "Kockák";
        //action
        String actionTitle = "Akciók";
        //buttons
        String payButton = "  Fizetés";


        //board
        custom_Label boardLabel = new custom_Label(0, 0, 1000, 1000, boardIcon);
        custom_Panel boardPanel = new custom_Panel(0, 0, 1000, 1100, whiteBackground);

        boardPanel.add(boardLabel);

        //pawns
        custom_Label pawnshoeLabel = new custom_Label(25, 25, 25, 25, shoeIcon);
        custom_Label pawnhatLabel = new custom_Label(25, 50, 25, 25, hatIcon);
        custom_Label pawnboatLabel = new custom_Label(50, 25, 25, 25, boatIcon);
        custom_Label pawncarLabel = new custom_Label(50, 50, 25, 25, carIcon);
        custom_Panel pawnPanel = new custom_Panel(0, 0, 1000, 1000);

        pawnPanel.add(pawnshoeLabel);
        pawnPanel.add(pawnhatLabel);
        pawnPanel.add(pawnboatLabel);
        pawnPanel.add(pawncarLabel);


        //players title
        custom_Label playerTitleLabel = new custom_Label(playerTitle, 60, 260, 10, 400, 90);
        custom_Panel playerTitlePanel = new custom_Panel(1000, 0, 800, 100, pinkBackground);

        playerTitlePanel.add(playerTitleLabel);


        //players
        custom_Label player1Namelabel = new custom_Label(playerName + player1Name, 20, 10, 0, 400, 30);
        custom_Label player1Moneylabel = new custom_Label(playerMoney + player1Money, 20, 10, 30, 400, 30);
        custom_Panel player1Panel = new custom_Panel(1000, 100, 400, 150, greenBackground);

        player1Panel.add(player1Namelabel);
        player1Panel.add(player1Moneylabel);

        custom_Label player2Namelabel = new custom_Label(playerName + player2Name, 20, 10, 0, 400, 30);
        custom_Label player2Moneylabel = new custom_Label(playerMoney + player2Money, 20, 10, 30, 400, 30);
        custom_Panel player2Panel = new custom_Panel(1400, 100, 400, 150, redBackground);

        player2Panel.add(player2Namelabel);
        player2Panel.add(player2Moneylabel);

        custom_Label player3Namelabel = new custom_Label(playerName + player3Name, 20, 10, 0, 400, 30);
        custom_Label player3Moneylabel = new custom_Label(playerMoney + player3Money, 20, 10, 30, 400, 30);
        custom_Panel player3Panel = new custom_Panel(1000, 250, 400, 150, magentaBackground);

        player3Panel.add(player3Namelabel);
        player3Panel.add(player3Moneylabel);

        custom_Label player4Namelabel = new custom_Label(playerName + player4Name, 20, 10, 0, 400, 30);
        custom_Label player4Moneylabel = new custom_Label(playerMoney + player4Money, 20, 10, 30, 400, 30);
        custom_Panel player4Panel = new custom_Panel(1400, 250, 400, 150, yellowBackground);

        player4Panel.add(player4Namelabel);
        player4Panel.add(player4Moneylabel);

        //cards
        custom_Label cardTitleLabel = new custom_Label(cardTitle, 40, 10, 10, 200, 50);
        custom_Label card1Label = new custom_Label(110, 60, 600, 350, meglepetes1Icon);
        custom_Label card2Label = new custom_Label(110, 60, 600, 350, almagyarIcon);
        custom_Panel cardsPanel = new custom_Panel(1000, 400, 600, 400, whiteBackground);

        cardsPanel.add(cardTitleLabel);
        cardsPanel.add(card1Label);
        //cardsPanel.add(card2Label);



        //dice
        custom_Label diceTitleLabel = new custom_Label(diceTitle, 40, 10, 10, 200, 50);
        custom_Label dice1Label = new custom_Label(25, 70, 200, 150, dice1Icon);
        custom_Label dice2Label = new custom_Label(25, 230, 200, 150, dice2Icon);
        custom_Panel dicePanel = new custom_Panel(1600, 400, 200, 400, blueBackground);

        dicePanel.add(diceTitleLabel);
        dicePanel.add(dice1Label);
        dicePanel.add(dice2Label);

        //action
        custom_Label actionTitleLabel = new custom_Label(actionTitle, 40, 10, 10, 200, 50);

        button1 = new custom_Button(50, 80, 250, 100, payButton, dollarLogoIcon);
        custom_Panel actionPanel = new custom_Panel(1000, 800, 800, 300, cyanBackground);
        button1.addActionListener(this);

        actionPanel.add(actionTitleLabel);
        actionPanel.add(button1);



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


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button1){
            System.out.println("Fizettél nekem, köszi!");
            // legyen egy Ready button, ami megnyomás után eltűnik
            // button1.setVisible(false);
        }

    }
}




