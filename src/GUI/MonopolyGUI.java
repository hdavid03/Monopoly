package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MonopolyGUI extends JFrame implements ActionListener {

    custom_Button button1;
    public static ArrayList<custom_Label> pawns = new ArrayList<>();
    //arrayxy
    public static int[][] arrayXY = {  {875,925},  {800,925},  {725,925},  {635,925},  {550,925},          //0
                                {475,925},  {390,925},  {310,925},  {225,925},  {150,925},          //5
                                {25,925},   {25,800},   {25,720},   {25,640},   {25,550},           //10
                                {25,475},   {25,390},   {25,310},   {25,235},   {25,150},           //15
                                {25,25},    {25,150},   {25,230},   {25,310},   {25,390},           //20
                                {25,475},   {25,550},   {25,635},   {25,715},   {25,795},           //25
                                {25,890},   {925,150},  {925,230},  {925,315},  {925,395},          //30
                                {925,480},  {925,550},  {925,635},  {925,715},  {925,800}           //35
                            };

    MonopolyGUI() throws InterruptedException {


        //Images
        //board
        ImageIcon boardIcon = new ImageIcon("src//Images//board.png");
        //pawns
        ImageIcon shoeIcon = new ImageIcon("src//Images//pawns//shoe.jpg");
        ImageIcon hatIcon = new ImageIcon("src//Images//pawns//hat.png");
        ImageIcon boatIcon = new ImageIcon("src//Images//pawns//boat.png");
        ImageIcon carIcon = new ImageIcon("src//Images//pawns//car.jpg");
        //cards
        //meglepetes
        ImageIcon meglepetes1Icon = new ImageIcon("src//Images//cards//meglepetes//meglepetes1.jpg");
        ImageIcon meglepetes2Icon = new ImageIcon("src//Images//cards//meglepetes//meglepetes2.jpg");
        ImageIcon meglepetes3Icon = new ImageIcon("src//Images//cards//meglepetes//meglepetes3.jpg");
        ImageIcon meglepetes4Icon = new ImageIcon("src//Images//cards//meglepetes//meglepetes4.jpg");
        ImageIcon meglepetes5Icon = new ImageIcon("src//Images//cards//meglepetes//meglepetes5.jpg");
        ImageIcon meglepetes6Icon = new ImageIcon("src//Images//cards//meglepetes//meglepetes6.jpg");
        ImageIcon meglepetes7Icon = new ImageIcon("src//Images//cards//meglepetes//meglepetes7.jpg");
        ImageIcon meglepetes8Icon = new ImageIcon("src//Images//cards//meglepetes//meglepetes8.jpg");
        ImageIcon meglepetes9Icon = new ImageIcon("src//Images//cards//meglepetes//meglepetes9.jpg");
        ImageIcon meglepetes10Icon = new ImageIcon("src//Images//cards//meglepetes//meglepetes10.jpg");
        ImageIcon meglepetes11Icon = new ImageIcon("src//Images//cards//meglepetes//meglepetes11.jpg");
        ImageIcon meglepetes12Icon = new ImageIcon("src//Images//cards//meglepetes//meglepetes12.jpg");
        ImageIcon meglepetes13Icon = new ImageIcon("src//Images//cards//meglepetes//meglepetes13.jpg");
        ImageIcon meglepetes14Icon = new ImageIcon("src//Images//cards//meglepetes//meglepetes14.jpg");
        ImageIcon meglepetes15Icon = new ImageIcon("src//Images//cards//meglepetes//meglepetes15.jpg");
        ImageIcon meglepetes16Icon = new ImageIcon("src//Images//cards//meglepetes//meglepetes16.jpg");
        //szerencse
        ImageIcon szerencse1Icon = new ImageIcon("src//Images//cards//szerencse//szerencse1.jpg");
        ImageIcon szerencse2Icon = new ImageIcon("src//Images//cards//szerencse//szerencse2.jpg");
        ImageIcon szerencse3Icon = new ImageIcon("src//Images//cards//szerencse//szerencse3.jpg");
        ImageIcon szerencse4Icon = new ImageIcon("src//Images//cards//szerencse//szerencse4.jpg");
        ImageIcon szerencse5Icon = new ImageIcon("src//Images//cards//szerencse//szerencse5.jpg");
        ImageIcon szerencse6Icon = new ImageIcon("src//Images//cards//szerencse//szerencse6.jpg");
        ImageIcon szerencse7Icon = new ImageIcon("src//Images//cards//szerencse//szerencse7.jpg");
        ImageIcon szerencse8Icon = new ImageIcon("src//Images//cards//szerencse//szerencse8.jpg");
        ImageIcon szerencse9Icon = new ImageIcon("src//Images//cards//szerencse//szerencse9.jpg");
        ImageIcon szerencse10Icon = new ImageIcon("src//Images//cards//szerencse//szerencse10.jpg");
        ImageIcon szerencse11Icon = new ImageIcon("src//Images//cards//szerencse//szerencse11.jpg");
        ImageIcon szerencse12Icon = new ImageIcon("src//Images//cards//szerencse//szerencse12.jpg");
        ImageIcon szerencse13Icon = new ImageIcon("src//Images//cards//szerencse//szerencse13.jpg");
        ImageIcon szerencse14Icon = new ImageIcon("src//Images//cards//szerencse//szerencse14.jpg");
        ImageIcon szerencse15Icon = new ImageIcon("src//Images//cards//szerencse//szerencse15.jpg");
        ImageIcon szerencse16Icon = new ImageIcon("src//Images//cards//szerencse//szerencse16.jpg");

        //ar
        ImageIcon almagyarIcon = new ImageIcon("src//Images//cards//ar//Almagyar_utca_ar.jpg");
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
        ImageIcon OvorosIcon = new ImageIcon("src//Images//cards//ar//Ovoros_ar.jpg");
        ImageIcon PetofiIcon = new ImageIcon("src//Images//cards//ar//Petofi_ter_ar.jpg");
        ImageIcon PiacIcon = new ImageIcon("src//Images//cards//ar//Piac_ter_ar_utca_ar.jpg");
        ImageIcon SzinhazIcon = new ImageIcon("src//Images//cards//ar//Szinhaz_ter_ar_utca_ar.jpg");
        ImageIcon TorokIcon = new ImageIcon("src//Images//cards//ar//Torok_udvar_ar_utca_ar.jpg");
        ImageIcon VizmuTarsasagIcon = new ImageIcon("src//Images//cards//ar//Vizmu_tarsasag_ar_utca_ar.jpg");
        ImageIcon VorosmartyIcon = new ImageIcon("src//Images//cards//ar//Vorosmarty_ter_ar_utca_ar.jpg");


        //dices
        ImageIcon dice1Icon = new ImageIcon("src//Images//dices//dice1.png");
        ImageIcon dice2Icon = new ImageIcon("src//Images//dices//dice2.png");
        ImageIcon dice3Icon = new ImageIcon("src//Images//dices//dice3.png");
        ImageIcon dice4Icon = new ImageIcon("src//Images//dices//dice4.png");
        ImageIcon dice5Icon = new ImageIcon("src//Images//dices//dice5.png");
        ImageIcon dice6Icon = new ImageIcon("src//Images//dices//dice6.png");
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
        String playerTitle = "Játékosok";
        String playerName = "név:   ";
        String playerMoney = "pénz:  ";
        String playerRr = "Vasút: ";
        String playerUt = " || Közmű: ";
        String playerJail = "Börtön: ";
        String playerJailTimeplus = " kör";
        String playerExtra = "Extra: ";
        String playerDb = " db";
        //player1
        String player1Name = "Valaki";
        String player1Money = "500 Forint";
        Integer player1Rr = 1;
        Integer player1Ut = 1;
        String player1BoolJail = "van";
        Integer player1JailTime = 4;
        String player1freejail = "Szabadulás, ";
        String player1passgo = "Pénzfelvétel";
        //player2
        String player2Name = "Valaki2";
        String player2Money = "1000 Forint";
        Integer player2Rr = 0;
        Integer player2Ut = 1;
        String player2BoolJail = "Nincs";
        Integer player2JailTime = 0;
        String player2freejail = " ";
        String player2passgo = "";
        //player3
        String player3Name = "Valaki3";
        String player3Money = "1500 Forint";
        Integer player3Rr = 1;
        Integer player3Ut = 0;
        String player3BoolJail = "Nincs";
        Integer player3JailTime = 0;
        String player3freejail = " ";
        String player3passgo = "";
        //player4
        String player4Name = "Valaki4";
        String player4Money = "2000 Forint";
        Integer player4Rr = 0;
        Integer player4Ut = 0;
        String player4BoolJail = "Nincs";
        Integer player4JailTime = 0;
        String player4freejail = " ";
        String player4passgo = "";
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
        custom_Label pawnshoeLabel = new custom_Label(875, 900, 25, 25, shoeIcon);
        custom_Label pawnhatLabel = new custom_Label(875, 925, 25, 25, hatIcon);
        custom_Label pawnboatLabel = new custom_Label(900, 900, 25, 25, boatIcon);
        custom_Label pawncarLabel = new custom_Label(900, 925, 25, 25, carIcon);
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
        //player1
        custom_Label player1Namelabel = new custom_Label(playerName + player1Name, 20, 10, 0, 400, 30);
        custom_Label player1Moneylabel = new custom_Label(playerMoney + player1Money, 20, 10, 30, 400, 30);
        custom_Label player1RrUtlabel = new custom_Label(playerRr + player1Rr + playerDb + playerUt + player1Ut + playerDb, 20, 10, 60, 400, 30);
        custom_Label player1Jaillabel = new custom_Label(playerJail + player1BoolJail + " - " + player1JailTime + playerJailTimeplus, 20, 10, 90, 400, 30);
        custom_Label player1Extralabel = new custom_Label(playerExtra + player1freejail + player1passgo, 20, 10, 120, 400, 30);
        custom_Panel player1Panel = new custom_Panel(1000, 100, 400, 150, greenBackground);

        player1Panel.add(player1Namelabel);
        player1Panel.add(player1Moneylabel);
        player1Panel.add(player1RrUtlabel);
        player1Panel.add(player1Jaillabel);
        player1Panel.add(player1Extralabel);

        //player2
        custom_Label player2Namelabel = new custom_Label(playerName + player2Name, 20, 10, 0, 400, 30);
        custom_Label player2Moneylabel = new custom_Label(playerMoney + player2Money, 20, 10, 30, 400, 30);
        custom_Label player2RrUtlabel = new custom_Label(playerRr + player2Rr + playerDb + playerUt + player2Ut + playerDb, 20, 10, 60, 400, 30);
        custom_Label player2Jaillabel = new custom_Label(playerJail + player2BoolJail + " - " + player2JailTime + playerJailTimeplus, 20, 10, 90, 400, 30);
        custom_Label player2Extralabel = new custom_Label(playerExtra + player2freejail + player2passgo, 20, 10, 120, 400, 30);
        custom_Panel player2Panel = new custom_Panel(1400, 100, 400, 150, redBackground);

        player2Panel.add(player2Namelabel);
        player2Panel.add(player2Moneylabel);
        player2Panel.add(player2RrUtlabel);
        player2Panel.add(player2Jaillabel);
        player2Panel.add(player2Extralabel);


        //player3
        custom_Label player3Namelabel = new custom_Label(playerName + player3Name, 20, 10, 0, 400, 30);
        custom_Label player3Moneylabel = new custom_Label(playerMoney + player3Money, 20, 10, 30, 400, 30);
        custom_Label player3RrUtlabel = new custom_Label(playerRr + player3Rr + playerDb + playerUt + player3Ut + playerDb, 20, 10, 60, 400, 30);
        custom_Label player3Jaillabel = new custom_Label(playerJail + player3BoolJail + " - " + player3JailTime + playerJailTimeplus, 20, 10, 90, 400, 30);
        custom_Label player3Extralabel = new custom_Label(playerExtra + player3freejail + player3passgo, 20, 10, 120, 400, 30);
        custom_Panel player3Panel = new custom_Panel(1000, 250, 400, 150, magentaBackground);

        player3Panel.add(player3Namelabel);
        player3Panel.add(player3Moneylabel);
        player3Panel.add(player3RrUtlabel);
        player3Panel.add(player3Jaillabel);
        player3Panel.add(player3Extralabel);


        //player4
        custom_Label player4Namelabel = new custom_Label(playerName + player4Name, 20, 10, 0, 400, 30);
        custom_Label player4Moneylabel = new custom_Label(playerMoney + player4Money, 20, 10, 30, 400, 30);
        custom_Label player4RrUtlabel = new custom_Label(playerRr + player4Rr + playerDb + playerUt + player4Ut + playerDb, 20, 10, 60, 400, 30);
        custom_Label player4Jaillabel = new custom_Label(playerJail + player4BoolJail + " - " + player4JailTime + playerJailTimeplus, 20, 10, 90, 400, 30);
        custom_Label player4Extralabel = new custom_Label(playerExtra + player4freejail + player4passgo, 20, 10, 120, 400, 30);
        custom_Panel player4Panel = new custom_Panel(1400, 250, 400, 150, yellowBackground);

        player4Panel.add(player4Namelabel);
        player4Panel.add(player4Moneylabel);
        player4Panel.add(player4RrUtlabel);
        player4Panel.add(player4Jaillabel);
        player4Panel.add(player4Extralabel);

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


        //pawns arraylist

        pawns.add(pawnshoeLabel);
        pawns.add(pawnhatLabel);
        pawns.add(pawnboatLabel);
        pawns.add(pawncarLabel);


        //switch
        Integer tablefieldsID = 0;
        Integer playerCount = 0;
        Integer playerID = 0;

        System.out.println(arrayXY[0][0]);
        System.out.println(arrayXY[0][1]);
        System.out.println(arrayXY[1][0]);
        System.out.println(arrayXY[1][1]);
    }
/*
        pawns.get(playerID).setLocation(arrayXY[1][0], arrayXY[1][1]);
        Integer playercountX = pawns.get(playerID).getX();
        Integer playercountY = pawns.get(playerID).getY();
        pawns.get(playerID).setLocation(playercountX, playercountY + 25);
   */



    public static void goingonfields(Integer tablefieldsID, Integer playerCount, Integer playerID){
        pawns.get(playerID).setLocation(arrayXY[tablefieldsID][0], arrayXY[tablefieldsID][1]);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            System.out.println("Fizettél nekem, köszi!");
            // legyen egy Ready button, ami megnyomás után eltűnik
            // button1.setVisible(false);
        }
    }
}

