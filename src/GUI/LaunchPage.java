package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LaunchPage extends Component implements ActionListener {

    //FRAME
    JFrame launchFrame = new JFrame();

    //TEXTFIELDS
    CustomTextField usernameTextField;
    CustomTextField ipAddressTextField;
    private boolean clientWantsToConnect = false;

    public CustomTextField getUsernameTextField() {
        return usernameTextField;
    }

    public CustomTextField getIpAddressTextField() { return ipAddressTextField; }

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
        ipAddressTextField = new CustomTextField(50, 100, 250, 40, "IP cím");
        launchFrame.add(submitButton);
        launchFrame.add(usernameTextField);
        launchFrame.add(ipAddressTextField);
        launchFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submitButton){
            if(ipAddressTextField.getText().compareTo("127.0.0.1") == 0) {
                launchFrame.dispose();        //close frame
                clientWantsToConnect = true;
            } else {
                JOptionPane.showMessageDialog(this, "Helytelen IP cím, próbáld újra!", "Hiba", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

