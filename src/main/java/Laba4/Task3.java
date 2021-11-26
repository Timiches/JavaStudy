package Laba4;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Task3 {
    public static void main(String[] args) {

        JFrame jFrame = newFrame();

        JPanel jPanel = new JPanel();
        jFrame.add(jPanel);

        Timer timer = new Timer(50, new ActionListener() {
            int w = 220, h = 220, i = 0, x = 530, y = 250;
            @Override
            public void actionPerformed(ActionEvent e) {

                Graphics2D gr=(Graphics2D)jPanel.getRootPane().getGraphics();
                jPanel.update(gr);

                if(i < 30) {
                    w += 10;
                    h += 10;
                    x -= 5;
                    y -= 5;
                } else if (i>=30){
                    w -= 10;
                    h -= 10;
                    x += 5;
                    y += 5;
                }

                i++;
                if(i == 60){
                    i = 0;
                }

                Shape circle = new Ellipse2D.Double(x, y, w, h);
                gr.draw(circle);
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