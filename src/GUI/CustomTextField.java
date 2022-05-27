package GUI;

import javax.swing.*;
import java.awt.*;

public class CustomTextField extends JTextField {

    CustomTextField(int x, int y, int w, int h, String text){

        this.setBounds(x, y, w, h);
        this.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        this.setForeground(Color.green);
        this.setBackground(Color.black);
        this.setCaretColor(Color.white);
        this.setText(text);
    }
}

