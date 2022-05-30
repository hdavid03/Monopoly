package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MonopolyGUI extends JFrame implements ActionListener {

    static custom_Button button1;
    custom_Button button2;
    public static ArrayList<custom_Label> pawns = new ArrayList<>();
    public static custom_Label card1Label;
    public static custom_Label HouseHotelMonitor;
    public static ArrayList<ImageIcon> propertycardsIcon = new ArrayList<>();
    public static ArrayList<custom_Label> ownedpropertyIndicator = new ArrayList<>();
    public static Integer tablefieldsID_local = 0;
    public static Integer playerID_local = 0;
    public static Integer getHouseCounter = 0;
    public static String isHotel = "Nincs";
    public static String HouseHotelString = "Ház: " + getHouseCounter + " || Szálloda: " + isHotel;
    public static custom_Label playerTitleLabel;
    public static JComboBox comboBox;

    public static String[] options = {"Reset", "Veszek 1 Házat", "Veszek 2 Házat", "Veszek 3 Házat", "Veszek 4 Házat", "Veszek Szállodát"};
    //arrayxy
    public static int[][] arrayXY = {{875, 925}, {800, 925}, {725, 925}, {635, 925}, {550, 920},          //0
            {475, 925}, {390, 925}, {310, 925}, {225, 925}, {150, 925},          //5
            {25, 925}, {25, 800}, {25, 720}, {25, 640}, {25, 550},           //10
            {25, 475}, {25, 390}, {25, 310}, {25, 235}, {25, 150},           //15
            {25, 25}, {150, 25}, {230, 25}, {310, 25}, {390, 25},           //20
            {475, 25}, {550, 25}, {635, 25}, {715, 25}, {795, 25},           //25
            {890, 25}, {925, 150}, {925, 230}, {925, 315}, {925, 395},          //30
            {925, 480}, {925, 550}, {925, 635}, {925, 715}, {925, 800}           //35
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


        //dices
        ImageIcon dice1Icon = new ImageIcon("src//Images//dices//dice1.png");
        ImageIcon dice2Icon = new ImageIcon("src//Images//dices//dice2.png");
        ImageIcon dice3Icon = new ImageIcon("src//Images//dices//dice3.png");
        ImageIcon dice4Icon = new ImageIcon("src//Images//dices//dice4.png");
        ImageIcon dice5Icon = new ImageIcon("src//Images//dices//dice5.png");
        ImageIcon dice6Icon = new ImageIcon("src//Images//dices//dice6.png");
        //logos
        ImageIcon dollarLogoIcon = new ImageIcon("src//Images//logos//dollar.png");
        ImageIcon readyLogoIcon = new ImageIcon("src//Images//logos//ready.png");

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
        String readyButton = " Készen állok!";


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

        //ownedpropertyIndicator
        custom_Label ownedpiacLabel = new custom_Label(786, 860, 30, 30, null);
        custom_Label ownedtorokLabel = new custom_Label(625, 860, 30, 30, null);
        custom_Label ownedeszakLabel = new custom_Label(463, 860, 30, 30, null);
        custom_Label ownednagykorosLabel = new custom_Label(384, 860, 30, 30, null);
        custom_Label ownedlestarLabel = new custom_Label(220, 860, 30, 30, null);
        custom_Label ownedkisfaludyLabel = new custom_Label(144, 860, 30, 30, null);
        custom_Label ownedegyetemLabel = new custom_Label(110, 785, 30, 30, null);
        custom_Label ownedeketromosLabel = new custom_Label(110, 700, 30, 30, null);
        custom_Label ownedszinhazLabel = new custom_Label(110, 620, 30, 30, null);
        custom_Label ownedjanusLabel = new custom_Label(110, 544, 30, 30, null);
        custom_Label ownedkeletiLabel = new custom_Label(110, 457, 30, 30, null);
        custom_Label ownedpetofiLabel = new custom_Label(110, 379, 30, 30, null);
        custom_Label ownednagyerdoLabel = new custom_Label(110, 217, 30, 30, null);
        custom_Label ownedbethlenLabel = new custom_Label(110, 137, 30, 30, null);
        custom_Label ownedmoraLabel = new custom_Label(143, 107, 30, 30, null);
        custom_Label ownedoskolaLabel = new custom_Label(303, 107, 30, 30, null);
        custom_Label owneddomLabel = new custom_Label(385, 107, 30, 30, null);
        custom_Label owneddeliLabel = new custom_Label(460, 107, 30, 30, null);
        custom_Label owneddoboLabel = new custom_Label(543, 107, 30, 30, null);
        custom_Label ownedalmagyarLabel = new custom_Label(627, 107, 30, 30, null);
        custom_Label ownedvizmuLabel = new custom_Label(703, 107, 30, 30, null);
        custom_Label ownedgardonyiLabel = new custom_Label(787, 107, 30, 30, null);
        custom_Label ownedkofaragoLabel = new custom_Label(865, 136, 30, 30, null);
        custom_Label ownedovarosLabel = new custom_Label(865, 217, 30, 30, null);
        custom_Label ownedotvosLabel = new custom_Label(865, 380, 30, 30, null);
        custom_Label ownednyugatiLabel = new custom_Label(865, 460, 30, 30, null);
        custom_Label ownedvorosmartyLabel = new custom_Label(865, 620, 30, 30, null);
        custom_Label owneddunakorzoLabel = new custom_Label(865, 782, 30, 30, null);
        custom_Panel ownedPanel = new custom_Panel(0, 0, 1000, 1000);

        ownedpropertyIndicator.add(null);
        ownedpropertyIndicator.add(ownedpiacLabel);
        ownedpropertyIndicator.add(null);
        ownedpropertyIndicator.add(ownedtorokLabel);
        ownedpropertyIndicator.add(null);
        ownedpropertyIndicator.add(ownedeszakLabel);
        ownedpropertyIndicator.add(ownednagykorosLabel);
        ownedpropertyIndicator.add(null);
        ownedpropertyIndicator.add(ownedlestarLabel);
        ownedpropertyIndicator.add(ownedkisfaludyLabel);
        ownedpropertyIndicator.add(null);
        ownedpropertyIndicator.add(ownedegyetemLabel);
        ownedpropertyIndicator.add(ownedeketromosLabel);
        ownedpropertyIndicator.add(ownedszinhazLabel);
        ownedpropertyIndicator.add(ownedjanusLabel);
        ownedpropertyIndicator.add(ownedkeletiLabel);
        ownedpropertyIndicator.add(ownedpetofiLabel);
        ownedpropertyIndicator.add(null);
        ownedpropertyIndicator.add(ownednagyerdoLabel);
        ownedpropertyIndicator.add(ownedbethlenLabel);
        ownedpropertyIndicator.add(null);
        ownedpropertyIndicator.add(ownedmoraLabel);
        ownedpropertyIndicator.add(null);
        ownedpropertyIndicator.add(ownedoskolaLabel);
        ownedpropertyIndicator.add(owneddomLabel);
        ownedpropertyIndicator.add(owneddeliLabel);
        ownedpropertyIndicator.add(owneddoboLabel);
        ownedpropertyIndicator.add(ownedalmagyarLabel);
        ownedpropertyIndicator.add(ownedvizmuLabel);
        ownedpropertyIndicator.add(ownedgardonyiLabel);
        ownedpropertyIndicator.add(null);
        ownedpropertyIndicator.add(ownedkofaragoLabel);
        ownedpropertyIndicator.add(ownedovarosLabel);
        ownedpropertyIndicator.add(null);
        ownedpropertyIndicator.add(ownedotvosLabel);
        ownedpropertyIndicator.add(ownednyugatiLabel);
        ownedpropertyIndicator.add(null);
        ownedpropertyIndicator.add(ownedvorosmartyLabel);
        ownedpropertyIndicator.add(null);
        ownedpropertyIndicator.add(owneddunakorzoLabel);


        for (int i = 0; i < 40; i++) {
            if (ownedpropertyIndicator.get(i) != null) {
                ownedPanel.add(ownedpropertyIndicator.get(i));
            }
        }

        /*
        ownedPanel.add(ownedpiacLabel);
        ownedPanel.add(ownedtorokLabel);
        ownedPanel.add(ownedeszakLabel);
        ownedPanel.add(ownednagykorosLabel);
        ownedPanel.add(ownedlestarLabel);
        ownedPanel.add(ownedkisfaludyLabel);
        ownedPanel.add(ownedegyetemLabel);
        ownedPanel.add(ownedeketromosLabel);
        ownedPanel.add(ownedszinhazLabel);
        ownedPanel.add(ownedjanusLabel);
        ownedPanel.add(ownedkeletiLabel);
        ownedPanel.add(ownedpetofiLabel);
        ownedPanel.add(ownednagyerdoLabel);
        ownedPanel.add(ownedbethlenLabel);
        ownedPanel.add(ownedmoraLabel);
        ownedPanel.add(ownedoskolaLabel);
        ownedPanel.add(owneddomLabel);
        ownedPanel.add(owneddeliLabel);
        ownedPanel.add(owneddoboLabel);
        ownedPanel.add(ownedalmagyarLabel);
        ownedPanel.add(ownedvizmuLabel);
        ownedPanel.add(ownedgardonyiLabel);
        ownedPanel.add(ownedkofaragoLabel);
        ownedPanel.add(ownedovarosLabel);
        ownedPanel.add(ownedotvosLabel);
        ownedPanel.add(ownednyugatiLabel);
        ownedPanel.add(ownedvorosmartyLabel);
        ownedPanel.add(owneddunakorzoLabel);

*/


        //players title
        playerTitleLabel = new custom_Label(playerTitle, 60, 260, 10, 400, 90);
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


        custom_Label cardTitleLabel = new custom_Label(cardTitle, 40, 10, 10, 190, 50);
        HouseHotelMonitor = new custom_Label(HouseHotelString, 30, 200, 10, 400, 50);
        HouseHotelMonitor.setVisible(false);
        card1Label = new custom_Label(110, 60, 600, 350, null);


        custom_Panel cardsPanel = new custom_Panel(1000, 400, 600, 400, whiteBackground);

        cardsPanel.add(cardTitleLabel);
        cardsPanel.add(card1Label);
        cardsPanel.add(HouseHotelMonitor);


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


        comboBox = new JComboBox(options);
        comboBox.addActionListener(this);
        comboBox.setBounds(50, 70, 250, 30);
        //comboBox.insertItemAt("pig", 0);
        comboBox.setSelectedIndex(0);
        //comboBox.removeItem("Ház");
        //comboBox.removeItemAt(0);
        //comboBox.removeAllItems();
        comboBox.setVisible(false);

        button1 = new custom_Button(50, 120, 250, 100, payButton, dollarLogoIcon);
        button1.addActionListener(this);

        button1.setEnabled(false);

        button2 = new custom_Button(350, 120, 250, 100, readyButton, readyLogoIcon);
        button2.addActionListener(this);

        custom_Panel actionPanel = new custom_Panel(1000, 800, 800, 300, cyanBackground);

        actionPanel.add(actionTitleLabel);
        actionPanel.add(comboBox);
        actionPanel.add(button1);
        actionPanel.add(button2);


        //LAYERED PANE
        //layers: default - 0 (JLayeredPane.DEFAULT_LAYER), palette - 1, modal - 2, popup - 3, drag - 4
        JLayeredPane layeredPaneBoard = new JLayeredPane();
        layeredPaneBoard.setBounds(0, 0, 1000, 1000);
        layeredPaneBoard.add(boardPanel, Integer.valueOf(1));
        layeredPaneBoard.add(pawnPanel, Integer.valueOf(0));
        layeredPaneBoard.add(ownedPanel, Integer.valueOf(0));


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

        propertycardsIcon.add(null);
        propertycardsIcon.add(PiacIcon);
        propertycardsIcon.add(null);
        propertycardsIcon.add(TorokIcon);
        propertycardsIcon.add(null);
        propertycardsIcon.add(EszakiVasutvonalIcon);
        propertycardsIcon.add(NagykorosiIcon);
        propertycardsIcon.add(null);
        propertycardsIcon.add(LestarIcon);
        propertycardsIcon.add(KisfaludyIcon);
        propertycardsIcon.add(null);
        propertycardsIcon.add(EgyetemIcon);
        propertycardsIcon.add(ElektromosTarsasagIcon);
        propertycardsIcon.add(SzinhazIcon);
        propertycardsIcon.add(JanuspannoniusIcon);
        propertycardsIcon.add(KeletiVasutvonalIcon);
        propertycardsIcon.add(PetofiIcon);
        propertycardsIcon.add(null);
        propertycardsIcon.add(NagyerdoIcon);
        propertycardsIcon.add(BethlenIcon);
        propertycardsIcon.add(null);
        propertycardsIcon.add(MoraIcon);
        propertycardsIcon.add(null);
        propertycardsIcon.add(OskolaIcon);
        propertycardsIcon.add(DomIcon);
        propertycardsIcon.add(DeliVasutvonalIcon);
        propertycardsIcon.add(DoboIcon);
        propertycardsIcon.add(AlmagyarIcon);
        propertycardsIcon.add(VizmuTarsasagIcon);
        propertycardsIcon.add(GardonyiIcon);
        propertycardsIcon.add(null);
        propertycardsIcon.add(KofaragoIcon);
        propertycardsIcon.add(OvarosIcon);
        propertycardsIcon.add(null);
        propertycardsIcon.add(OtvosIcon);
        propertycardsIcon.add(NyugatiVasutvonalIcon);
        propertycardsIcon.add(null);
        propertycardsIcon.add(VorosmartyIcon);
        propertycardsIcon.add(null);
        propertycardsIcon.add(DunakorzoIcon);


    }
/*
        pawns.get(playerID).setLocation(arrayXY[1][0], arrayXY[1][1]);
        Integer playercountX = pawns.get(playerID).getX();
        Integer playercountY = pawns.get(playerID).getY();
        pawns.get(playerID).setLocation(playercountX, playercountY + 25);
   */


    public static void goingonfields(Integer tablefieldsID, Integer playerCount, Integer playerID) {
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

    public static void fieldImage(Integer tablefieldsID) {
        card1Label.setIcon(propertycardsIcon.get(tablefieldsID));


    }

    public static void setisHotel(Boolean whetherHotel) {
        if (whetherHotel) {
            isHotel = "Van";
        } else {
            isHotel = "Nincs";
        }
    }

    public static void HouseHotelMonitoring(Integer tablefieldsID) {
        if (propertycardsIcon.get(tablefieldsID) == null) {
            HouseHotelMonitor.setVisible(false);
            button1.setEnabled(false);          //kiszürkíti
            comboBox.setVisible(false);
        } else {
            //setisHotel
            HouseHotelString = "Ház: " + getHouseCounter + " || Szálloda: " + isHotel;
            HouseHotelMonitor.setText(HouseHotelString);
            HouseHotelMonitor.setVisible(true);
            button1.setEnabled(true);
            comboBox.setVisible(true);



/*
            comboBox.removeAllItems();

            if(ownedpropertyIndicator.get(tablefieldsID).getBackground() != null){
                button1.setEnabled(true);
                comboBox.setVisible(true);
                if()
                if(isHotel == "Van"){           //Bool = true
                    if(playerID == )
                }
            }
            else{
                button1.setEnabled(false);
                comboBox.setVisible(false);
            }
*/


        }


    }

    public static void getTablefieldsID_local(Integer tablefieldsID) {
        tablefieldsID_local = tablefieldsID;
    }

    public static void getPlayerID_local(Integer playerID) {
        playerID_local = playerID;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {


            Color colour = null;
            switch (playerID_local) {
                case 0:
                    colour = Color.GREEN;
                    break;
                case 1:
                    colour = Color.RED;
                    break;
                case 2:
                    colour = Color.MAGENTA;
                    break;
                case 3:
                    colour = Color.YELLOW;
                    break;
                default:
                    System.out.println("Hiba a playerID switch szerkezetben!!");
            }


            if (ownedpropertyIndicator.get(tablefieldsID_local) != null) {
                System.out.println(colour);
                System.out.println(tablefieldsID_local);

                //playerTitleLabel.setBackground(Color.GREEN);
                //playerTitleLabel.setOpaque(true);
                ownedpropertyIndicator.get(tablefieldsID_local).setBackground(colour);
                ownedpropertyIndicator.get(tablefieldsID_local).setOpaque(true);
                System.out.println("Fizettél nekem, köszi!");

                //String[] options = {"Reset","Veszek 1 Házat","Veszek 2 Házat","Veszek 3 Házat","Veszek 4 Házat", "Veszek Szállodát"};

                Integer removeCounter = 1;

                switch(comboBox.getSelectedItem().toString()){
                    case "Reset":
                        System.out.println("Reset");

                        if(getHouseCounter < 4){
                            for(int i = 1; i < (4 - getHouseCounter + 1); i++){
                                String removingItem = "Veszek " + i + " Házat";
                                System.out.println("Removing: \"" + removingItem + "\"");
                                comboBox.removeItem(removingItem);
                            }
                        }

                        for (int i = 1; i < 5; i++) {
                            comboBox.insertItemAt(options[i], i); //0.-ra (i - 1)
                        }

                        if(isHotel == "Van" | getHouseCounter != 0){
                            comboBox.insertItemAt("Veszek Szállodát", 5);
                        }

                        //Boolean false
                        isHotel = "Nincs";
                        getHouseCounter = 0;
                        break;
                    case "Veszek 1 Házat":
                        System.out.println("Veszek egy házat");
                        comboBox.removeItem("Veszek Szállodát");
                        getHouseCounter++;
                        removeCounter++;
                        break;
                    case "Veszek 2 Házat":
                        System.out.println("Veszek két házat");
                        comboBox.removeItem("Veszek Szállodát");
                        getHouseCounter = getHouseCounter + 2;
                        removeCounter = removeCounter + 2;
                        break;
                    case "Veszek 3 Házat":
                        System.out.println("Veszek három házat");
                        comboBox.removeItem("Veszek Szállodát");
                        getHouseCounter = getHouseCounter + 3;
                        removeCounter = removeCounter + 3;
                        break;
                    case "Veszek 4 Házat":
                        System.out.println("Veszek négy házat");
                        comboBox.removeItem("Veszek Szállodát");
                        getHouseCounter = getHouseCounter + 4;
                        removeCounter = removeCounter + 4;
                        break;
                    case "Veszek Szállodát":
                        System.out.println("Veszek Szállodát");
                        comboBox.removeItem("Veszek Szállodát");
                        for(int i = 1; i < 5; i++){
                            String removingItem = "Veszek " + i + " Házat";
                            System.out.println("Removing: \"" + removingItem + "\"");
                            comboBox.removeItem(removingItem);
                        }
                        isHotel = "Van";
                        break;
                    default:
                        System.out.println("Hiba történt ház/szálloda jelzésnél");
                        System.out.println(comboBox.getSelectedItem().toString());

                }

                System.out.println(removeCounter);
                if(removeCounter != 1){
                    for(int i = 1; i < removeCounter; i++){
                        String removingItem = "Veszek " + (4 - getHouseCounter + i) + " Házat";
                        System.out.println("Removing: \"" + removingItem + "\"");
                        comboBox.removeItem(removingItem);
                    }
                }

                //isHotel
                HouseHotelString = "Ház: " + getHouseCounter + " || Szálloda: " + isHotel;
                HouseHotelMonitor.setText(HouseHotelString);

            }
            else {

                System.out.println("Ezt te nem veheted meg!");

            }

                // legyen egy Ready button, ami megnyomás után eltűnik
                // button1.setVisible(false);
            }
            if (e.getSource() == comboBox) {
                //System.out.println(comboBox.getSelectedItem());
                System.out.println(comboBox.getSelectedIndex());
            }
            if (e.getSource() == button2) {
                button2.setEnabled(false);          //kiszürkíti
                //button2.setVisible(false);        //ha mindenki készen áll
                System.out.println("ready");
            }
        }
    }


/*

 */