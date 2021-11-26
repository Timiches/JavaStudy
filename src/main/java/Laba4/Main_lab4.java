// Рандомное движение рандомных слоп по апплету

package Laba4;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.awt.geom.*;



public class Main_lab4{
   static String[] words = {"aaaa","ssss","dddd","wwwww","qqqq","xxxx","hhhh","kkkk","ffff"};
  
    public static void main(String[] args) {
        //===========================Создание окна=======================
        JFrame jFrame = newFrame();
        //jFrame.add(new Component());
        
        //===========================Создание и подключение панели =======================
        JPanel jPanel = new JPanel();
        jFrame.add(jPanel);
//        jPanel.add(new Component());
        
//        JButton jButton = new JButton("11111");
//        jPanel.add(jButton);
        
        
        Random random = new Random();
        
        //===========================Таймер с задержкой в 20 млс =======================
        Timer timer = new Timer(20, new ActionListener() {
            int i = 1, w = 0, x = 50, y = 50, cof1 = 0, cof2 = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                
                Graphics2D gr=(Graphics2D)jPanel.getRootPane().getGraphics();
                jPanel.update(gr);


                
                i++;
                AffineTransform tranforms = AffineTransform.getTranslateInstance(cof1, cof2);
                gr.transform(tranforms);
                
                cof1 = cof1 + x/50;
                cof2 = cof2 + y/50;
                
                if (i == 51){
                    w = random.nextInt(9);
                    x = random.nextInt(400)-200;
                    y = random.nextInt(400)-200;
                    i = 1;
                }
                
                gr.drawString(words[w], 640, 360);
                

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
        
        //===========================Размеры и положение=======================
        int wWidth = 1280;
        int wHeight = 720;
        fr.setBounds(dimension.width/2 - wWidth/2, dimension.height/2 - wHeight/2, wWidth, wHeight);
        
        //=========================Свойства (Цвет, шрифт итд.)//не работает почему-то =================
//        fr.setFont(new Font("Verdana", Font.BOLD, 20));
//        fr.setBackground(Color.black);
//        fr.setForeground(Color.white);
        
        return fr;
    }
    

}



