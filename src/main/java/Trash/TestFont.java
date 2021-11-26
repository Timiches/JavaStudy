package Trash;

import java.awt.*;

public class TestFont {
    public static void main(String args[]){
        GraphicsEnvironment ge = GraphicsEnvironment
                .getLocalGraphicsEnvironment();

        Font[] allFonts = ge.getAllFonts();

        for (Font font : allFonts) {
            System.out.println(font.getFontName());
        }
    }
}
