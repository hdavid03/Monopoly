package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MonopolyGUI extends JFrame implements ActionListener {

    MonopolyGUI(){

        ImageIcon boardIcon = new ImageIcon("src//Images//board.jpg");
        ImageIcon boardBigIcon = new ImageIcon("src//Images//boardBig.jpg");
        ImageIcon boardThousandsIcon = new ImageIcon("src//Images//boardThousands.jpg");


        JLabel boardLabel = new JLabel();
        boardLabel.setIcon(boardThousandsIcon);

        boardLabel.setVerticalAlignment(JLabel.TOP);
        boardLabel.setHorizontalAlignment(JLabel.LEFT);
        boardLabel.setBounds(0, 0, 1000, 1000);




        this.setTitle("Monopoly");
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);   //full screen with window
        this.setLayout(null);
        this.setVisible(true);
        this.add(boardLabel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}




