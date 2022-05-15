package GUI;

import javax.swing.*;

public class custom_Button extends JButton {

    custom_Button(int x, int y, int w, int h, String text){
        this.setText(text);
        this.setBounds(x, y, w, h);
        this.setFocusable(false);
    }
}

