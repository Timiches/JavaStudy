package Laba5.TicTacToe.Prog.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends JFrame implements ActionListener, INetwork {
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Player();
            }
        });
    }

    ArrayList<JButton> buttonList = new ArrayList<>();

    private Network network;
    int playerType = 1; //1 - X, 2 - O, 0 - empty
    JFrame winnerWindow = new JFrame();
    JTextField winText = new JTextField();

    Player(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450,450);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(3,3));
        for(int x = 0; x < 9; x++){
            JButton button = new JButton();
            buttonList.add(button);
            add(button);
            button.addActionListener(this);
        }

        setVisible(true);
        try {
            network = new Network(this, "localhost", 8030);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        if (playerType == 0){
            button.setText(" ");
        } else if (playerType == 1){
            button.setText("X");
        } else if (playerType == 2){
            button.setText("Y");//на самом деле все, что происходит в actionPerformed не имеет смысла, т.к. оно не отображается. Дело в том, что мы стави на поле эти значения, отправляем данные (1, 2 итд., не 'Х' или еще что-то там) на сервер, а тот, в свою очередь, рассылает значение всем клиентам, включая и этот самый, и поэтому высвечивается на экран только то, что происходит в IResponse или, как его можно назвать, "получателю" данных.
        }
        button.setEnabled(false);

        for(int i = 0; i<=buttonList.size(); i++){
            if (buttonList.get(i).equals(button)){
                int x = i;
                network.SendValues(x);
                network.SendValues(playerType);
                network.SendValues(3);
            }
        }
    }

    @Override
    public void IConnection(Network network) {
        System.out.println("Connection exception");
    }

    @Override
    public void IDisconnection(Network network) {
        System.out.println("Connection closed");
    }

    @Override
    public void IResponse(Network network, int x, int sign, int winnerID) {
        if (sign == 0){
            buttonList.get(x).setText(" ");
        } else if (sign == 1){
            buttonList.get(x).setText("X");
        } else if (sign == 2){
            buttonList.get(x).setText("O");
        }
        buttonList.get(x).setEnabled(false);

        if(winnerID == 1){
            winText.setText("X win!");
            changeToWinWindow();
        } else if(winnerID == 2){
            winText.setText("O win!");
            changeToWinWindow();
        } else if(winnerID == 0){
            winText.setText("Draw!");
            changeToWinWindow();
        }
    }


    @Override
    public void IException(Network network, Exception e) {
        System.out.println("Connection exception" + e);
    }


    private void changeToWinWindow(){
        winText.setHorizontalAlignment(JTextField.CENTER);
        winnerWindow.add(winText, BorderLayout.CENTER);
        winnerWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
        winnerWindow.setSize(200,100);
        winnerWindow.setLocationRelativeTo(null);
        winnerWindow.setAlwaysOnTop(true);
        setVisible(false);
        winnerWindow.setVisible(true);
    }

}

