package GUI;

import javax.swing.*;
import java.awt.*;

public class custom_Button extends JButton {

    custom_Button(int x, int y, int w, int h, String text, Icon icon){
        this.setText(text);
        this.setFont(new Font("Times New Roman", Font.BOLD, 25));
        this.setIcon(icon);
        this.setBackground(Color.WHITE);
        this.setForeground(Color.BLACK);

        this.setBounds(x, y, w, h);
        this.setFocusable(false);
    }

    custom_Button(int x, int y, int w, int h, String text){
        this.setText(text);
        this.setFont(new Font("Times New Roman", Font.BOLD, 25));

        this.setBounds(x, y, w, h);
        this.setFocusable(false);
    }
}

