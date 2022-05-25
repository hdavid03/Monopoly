package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LaunchPage implements ActionListener {

    //FRAME
    JFrame Launchframe = new JFrame();

    //TEXTFIELDS
    custom_textField username_tF;
    custom_textField port_tF;
    custom_textField others_tF;


    //BUTTON
    JButton submitButton;

    public LaunchPage(){

        Launchframe.setTitle("Launch Page");
        Launchframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Launchframe.setSize(420, 420);
        Launchframe.setLayout(null);

        submitButton = new custom_Button(75, 160, 200, 40, "Csatlakozás");
        submitButton.addActionListener(this);

        username_tF = new custom_textField(50, 50, 250, 40, "Felhasználónév");
        port_tF = new custom_textField(50, 100, 250, 40, "port");


        Launchframe.add(submitButton);
        Launchframe.add(username_tF);
        Launchframe.add(port_tF);


        Launchframe.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submitButton){
            System.out.println("Üdvözlégy "+ username_tF.getText()+ "!");
            Launchframe.dispose();        //close frame
            MonopolyGUI gameWindow = new MonopolyGUI();
        }
    }
}

