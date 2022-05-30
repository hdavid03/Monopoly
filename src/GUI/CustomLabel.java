package GUI;

import javax.swing.*;
import java.awt.*;

public class CustomLabel extends JLabel {

    CustomLabel(int x, int y, int w, int h, Icon picture){

        this.setIcon(picture);
        //this.setVerticalAlignment(vertical);
        //this.setHorizontalAlignment(horizontal);
/*
        if(isborder){
            Border border = BorderFactory.createLineBorder(PropertyFieldColor.BLUE, 3);
            this.setBorder(border);
        }
*/
        this.setBounds(x, y, w, h);

    }


    CustomLabel(String text, int size, int x, int y, int w, int h){

        this.setText(text);
        this.setFont(new Font("Times New Roman", Font.BOLD, size));

        this.setBounds(x, y, w, h);


    }

    CustomLabel(int x, int y, int w, int h, Icon picture, String text){

    }

}
