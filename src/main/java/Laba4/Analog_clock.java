package Laba4;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.PI;

import java.awt.geom.Ellipse2D;
import java.util.Date;

public class Analog_clock {
    
    static int sec = 0;
    static int min = 0;
    static int hour = 0;
    
    static Date d = new Date();
    static int isec =d.getSeconds();
    static int imin =d.getMinutes();
    static int ihour = d.getHours();
            
    public static void main(String[] args) {
        JFrame jFrame = Main_lab4.newFrame();

        JPanel jPanel = new JPanel();
        jFrame.add(jPanel);

        JButton jButtonAddMin = new JButton("Add minute");
        jPanel.add(jButtonAddMin);
        jButtonAddMin.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                imin += 1;
            }    
        });  

        JButton jButtonDelMin = new JButton("Dell minute");
        jPanel.add(jButtonDelMin);
        jButtonDelMin.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                imin -= 1;
            }    
        });

        JButton jButtonAddHour = new JButton("Add hour");
        jPanel.add(jButtonAddHour);
        jButtonAddHour.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                ihour += 1;
            }    
        });

        JButton jButtonDelHour = new JButton("Dell hour");
        jPanel.add(jButtonDelHour);
        jButtonDelHour.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                ihour -= 1;
            }    
        });
        
        Timer timer = new Timer(1000, new ActionListener() {             
            @Override
            public void actionPerformed(ActionEvent e) {
                Graphics2D gr=(Graphics2D)jPanel.getRootPane().getGraphics();
                jPanel.update(gr);

                Shape circle = new Ellipse2D.Double(530, 250, 220, 220);
                gr.draw(circle);

                isec++;
                sec =+ isec*6;
                min = 6*(imin + (1/60)*isec);
                hour = 30*(ihour + (1/60)*imin);
                
                //gr.drawLine(640, 360, (int) (100*cos(i))+640, (int) (100*sin(i))+360);
                gr.drawLine(640, 360, (int) (640+100*cos(PI/2-sec*(PI/180))), (int) (360-100*sin(PI/2-sec*(PI/180))));
                gr.drawLine(640, 360, (int) (640+100*cos(PI/2-min*(PI/180))), (int) (360-100*sin(PI/2-min*(PI/180))));
                gr.drawLine(640, 360, (int) (640+50*cos(PI/2-hour*(PI/180))), (int) (360-50*sin(PI/2-hour*(PI/180))));
                
                if(isec == 60){
                    imin = imin + 1;
                    isec=0;
                }
                if (imin == 60){
                    ihour = ihour + 1;
                    imin=0;
                }
                if (ihour == 60){
                    ihour = 0;
                }
            }
        });
        timer.start();
    }
}
