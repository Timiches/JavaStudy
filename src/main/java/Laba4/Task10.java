package Laba4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;

public class Task10 {
    public static void main(String[] args) {

        ArrayList<Font> fontArray = new ArrayList<>();
        fontArray.add(new Font("TimesRoman", Font.PLAIN, 24));
        fontArray.add(new Font("TimesRoman", Font.BOLD, 24));
        fontArray.add(new Font("Roboto", Font.BOLD, 24));
        fontArray.add(new Font("Arial", Font.ITALIC, 24));
        fontArray.add(new Font("Calibri", Font.PLAIN, 24));
        fontArray.add(new Font("HelveticaNeueCyr", Font.BOLD, 24));

        JFrame jFrame = newFrame();

        JPanel jPanel = new JPanel();
        jFrame.add(jPanel);

        Random random = new Random();

        Timer timer = new Timer(100, new ActionListener() {
            int rand = 0;
            int tempRand = 0;
            int x0 = 0, y0 = 0, x1 = 1280, y1 = 720, i = 1, x = 0, y = 0, dif = 100;
            String str = "Hello";
            char[] chars = str.toCharArray();
            @Override
            public void actionPerformed(ActionEvent e) {

                Graphics2D gr=(Graphics2D)jPanel.getRootPane().getGraphics();
                jPanel.update(gr);

                if (i < dif) {
                    x = x + x1 / dif;
                    y = y + y1 / dif;
                }else if (i>= dif){
                    x = x - x1 / dif;
                    y = y - y1 / dif;
                }

                if(i == dif || i == dif*2){
                    while (true) {
                        if (rand != tempRand) {
                            rand = tempRand;
                            break;
                        }else
                            tempRand = random.nextInt(6);
                    }

                    for(int i = 0; i < chars.length; i++){
                        int temp = random.nextInt(2);
                        if(temp == 0){
                            chars[i] = Character.toUpperCase(chars[i]);
                        } else if(temp == 1){
                            chars[i] = Character.toLowerCase(chars[i]);
                        } else
                            chars[i] = Character.toLowerCase(chars[i]);
                    }
                    str = new String(chars);
                }

                AffineTransform tranforms = AffineTransform.getTranslateInstance(x, y);
                gr.transform(tranforms);

                gr.setFont(fontArray.get(rand));
                gr.drawString(str, x0, y0);

                if(i == dif*2)
                    i = 1;

                i++;
            }
        });
        timer.start();
    }


    public static JFrame newFrame(){
        JFrame fr = new JFrame("Random words movement");
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();

        int wWidth = 1280;
        int wHeight = 720;
        fr.setBounds(dimension.width/2 - wWidth/2, dimension.height/2 - wHeight/2, wWidth, wHeight);


        return fr;
    }
}
