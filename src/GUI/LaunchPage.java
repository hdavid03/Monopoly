package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LaunchPage implements ActionListener {

    //FRAME
    JFrame launchFrame = new JFrame();

    //TEXTFIELDS
    CustomTextField usernameTextField;
    CustomTextField ipaddressTextField;
    private boolean clientWantsToConnect = false;

    public CustomTextField getUsernameTextField() {
        return usernameTextField;
    }

    public boolean clientWantToConnect() {
        return clientWantsToConnect;
    }

    //BUTTON
    JButton submitButton;

    public LaunchPage(){
        launchFrame.setTitle("Launch Page");
        launchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        launchFrame.setSize(420, 420);
        launchFrame.setLayout(null);

        submitButton = new CustomButton(75, 160, 200, 40, "Csatlakozás");
        submitButton.addActionListener(this);

        usernameTextField = new CustomTextField(50, 50, 250, 40, "Felhasználónév");
        ipaddressTextField = new CustomTextField(50, 100, 250, 40, "IP cím");
        launchFrame.add(submitButton);
        launchFrame.add(usernameTextField);
        launchFrame.add(ipaddressTextField);
        launchFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submitButton){
            // a felhasználónév megadásnál nem kell a portot megadni, mert azt majd a kliens tudni fogja
            System.out.println("Üdvözlégy "+ usernameTextField.getText()+ "!");
            launchFrame.dispose();        //close frame
            clientWantsToConnect = true;
        }
    }
}

