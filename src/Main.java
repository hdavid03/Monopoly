import game_elements.GameElement;
import game_elements.Player;
import game_elements.Table;
import game_elements.table_fields.property_fields.Color;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dsig.XMLObject;
import java.awt.image.BufferedImage;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Main {

    final static String str = "MMSMSM";
    final static String ssj = "skskks";

    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        Table table = new Table();
        Player player = new Player(1, 2, 200);
        XMLEncoder encoder = new XMLEncoder(System.out);

        encoder.writeObject(player);
        encoder.writeObject("Miska");
        encoder.flush();
        encoder.close();


    }

}
