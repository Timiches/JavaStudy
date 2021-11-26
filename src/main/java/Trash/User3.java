package Trash;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class User3 extends JFrame/*Наследование от класа JFrame*/ implements ActionListener /* и реализует интерфейс ActionListener*/ {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new User3();
            }
        });
        //Грубо говоря, вся эта конструкция вокруг new User3(); нужна, чтоб графический интерфейс работал в потке EDT(Event Dispatch Thread).
    }

    private final JTextArea frameLog = new JTextArea();//Экземпляр текстового поля
    private  final JTextField frameInputField = new JTextField();//Экземпляр поля ввода

    private User3(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Завершение процесса по нажатию на крестик

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();//Берем размер экрана

        //===========================Размеры и положение=======================
        int wWidth = 1280;
        int wHeight = 720;
        setBounds(dimension.width/2 - wWidth/2, dimension.height/2 - wHeight/2, wWidth, wHeight);//Установка положения окна, должно быть по центру.
        setAlwaysOnTop(true);



        frameLog.setEditable(false);//В текстовом поле нелья ничего редактировать
        add(frameLog, BorderLayout.CENTER);//Добавляем это поле наверх
        add(frameInputField, BorderLayout.SOUTH);//А поле ввода снизу

        setVisible(true);
//        try(
//                Socket socket = new Socket("localhost", 8030);
//                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
//        ){
//            printMsg("Connected!");
//            while(true) {
//                if(reader.ready()){
//                    String response = reader.readLine();
//                    printMsg(response);
//                } else{
//                    frameInputField.addActionListener(this);
//                }
//                /*else if(frameInputField.co){
//                    String string = frameInputField.getText();
//                    writer.write(string);
//                    writer.newLine();
//                    writer.flush();
//
//                    if(string.equals("quit")){
//                        System.out.println("Client kill connections");
//                        String request = reader.readLine();
//                        System.out.println(request);
//                        break;
//                    }
//                }*/
//            }
//        }catch(IOException e){
//            System.out.print("Error = " + e);
//        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = frameInputField.getText();
        if(msg.equals(""))return;
        frameInputField.setText(null);

    }

    private synchronized void printMsg(String msg){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frameLog.append(msg +"\n");
                frameLog.setCaretPosition(frameLog.getDocument().getLength());
            }
        });
    }
}
