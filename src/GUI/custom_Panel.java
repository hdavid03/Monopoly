package GUI;

import javax.swing.*;
import java.awt.*;

public class custom_Panel extends JPanel {

    custom_Panel (int x, int y,int w, int h, Color background){
        this.setBackground(background);
        this.setBounds(x, y, w, h);
        //this.setLayout(new BorderLayout());       //using the whole panel
        this.setLayout(null);


    }

    custom_Panel (int x, int y,int w, int h){
        this.setBounds(x, y, w, h);
        //this.setLayout(new BorderLayout());       //using the whole panel
        this.setLayout(null);
        this.setOpaque(false);


    }
}
