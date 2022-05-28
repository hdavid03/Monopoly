package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class custom_Label extends JLabel {

    custom_Label(int x, int y, int w, int h, Icon picture){

        this.setIcon(picture);
        //this.setVerticalAlignment(vertical);
        //this.setHorizontalAlignment(horizontal);
/*
        if(isborder){
            Border border = BorderFactory.createLineBorder(Color.BLUE, 3);
            this.setBorder(border);
        }
*/
        this.setBounds(x, y, w, h);

    }


    custom_Label(String text, int size, int x, int y, int w, int h){

        this.setText(text);
        this.setFont(new Font("Times New Roman", Font.BOLD, size));

        this.setBounds(x, y, w, h);


    }

    custom_Label(int x, int y, int w, int h, Icon picture, String text){

    }
/*
    public void setLocation(Integer x, Integer y){
        this.setLocation(x, y);
    }*/

}
