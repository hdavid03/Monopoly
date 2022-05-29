package GUI;

//import client.ClientApplication;

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



        Launchframe.add(submitButton);
        Launchframe.add(username_tF);


        Launchframe.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submitButton){
            // a felhasználónév megadásnál nem kell a portot megadni, mert azt majd a kliens tudni fogja
            String userName = username_tF.getText();
            //ClientApplication.connectToServer(userName);
            System.out.println("Üdvözlégy "+ username_tF.getText()+ "!");
            Launchframe.dispose();        //close frame
            try {
                MonopolyGUI gameWindow = new MonopolyGUI();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}

