package GUI;

import javax.swing.*;
import java.awt.*;

public class CustomPanel extends JPanel {

    CustomPanel(int x, int y, int w, int h, Color background){
        this.setBackground(background);
        this.setBounds(x, y, w, h);
        //this.setLayout(new BorderLayout());       //using the whole panel
        this.setLayout(null);


    }

    CustomPanel(int x, int y, int w, int h){
        this.setBounds(x, y, w, h);
        //this.setLayout(new BorderLayout());       //using the whole panel
        this.setLayout(null);
        this.setOpaque(false);


    }
}
