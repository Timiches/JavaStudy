package Laba5.Battleships;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Player2 extends JFrame implements ActionListener, INetwork {
    public static void main(String args[]){
        new Player2();
    }

    static JButton enemyButtonList[][] = new JButton[10][10];
    static JButton allyButtonList[][] = new JButton[10][10];

    JPanel enemyBoard = new JPanel();
    JPanel allyBoard = new JPanel();

    int playerID = 2;

    private Network network;

    JFrame winnerWindow = new JFrame();
    JTextField winText = new JTextField();

    Player2(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1010,500);
        setLocationRelativeTo(null);

        enemyBoard.setSize(500,500);
        enemyBoard.setLayout(new GridLayout(10,10));

        for(int x = 0; x < 10; x++){
            for(int y = 0; y < 10; y++) {
                JButton button = new JButton();
                enemyBoard.add(button);

                enemyButtonList[x][y] = button;
                button.addActionListener(this);
            }
        }

        allyBoard.setSize(500,500);
        allyBoard.setLayout(new GridLayout(10,10));
        for(int x = 0; x < 10; x++){
            for(int y = 0; y < 10; y++) {
                JButton button = new JButton();
                allyBoard.add(button);

                allyButtonList[x][y] = button;
                allyButtonList[x][y].setEnabled(false);
            }
        }

        setLayout(new GridLayout(1,2));

        add(allyBoard);
        add(enemyBoard);

        setVisible(true);

        allyButtonList[0][1].setBackground(Color.RED);

        allyButtonList[1][3].setBackground(Color.RED);

        allyButtonList[1][5].setBackground(Color.RED);

        allyButtonList[1][7].setBackground(Color.RED);


        allyButtonList[3][3].setBackground(Color.RED);
        allyButtonList[3][4].setBackground(Color.RED);

        allyButtonList[5][3].setBackground(Color.RED);
        allyButtonList[5][4].setBackground(Color.RED);


        allyButtonList[1][9].setBackground(Color.RED);
        allyButtonList[2][9].setBackground(Color.RED);


        allyButtonList[5][6].setBackground(Color.RED);
        allyButtonList[6][6].setBackground(Color.RED);
        allyButtonList[7][6].setBackground(Color.RED);


        allyButtonList[7][1].setBackground(Color.RED);
        allyButtonList[7][2].setBackground(Color.RED);
        allyButtonList[7][3].setBackground(Color.RED);


        allyButtonList[9][8].setBackground(Color.RED);
        allyButtonList[9][7].setBackground(Color.RED);
        allyButtonList[9][6].setBackground(Color.RED);
        allyButtonList[9][5].setBackground(Color.RED);


        try {
            network = new Network(this, "localhost", 8030);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        for(int x = 0; x < 10; x++){
            for(int y = 0; y < 10; y++) {
                if (button.equals(enemyButtonList[x][y])){
                    button.setEnabled(false);

//                    if (board[x][y] != "0"){
//                        button.setText("0");
//                        board[x][y] = "/";
//                        dotList[x][y].alive = 0;
//                        //Dot(x,y).alive;
//                        for(int i = 0; i<ships.size(); i++){
//
//                            Ship.checkAlive(ships.get(i));
//
//                            if(ships.get(i).isAlive == false){
//                                ships.get(i).isAlive = true;
//                                break;
//                            }
//                        }
//                    } else if (board[x][y] == "0"){
//                        button.setText("X");
//                        board[x][y] = "X";
//                    }

                    network.SendValues(x);
                    network.SendValues(y);
                    network.SendValues(4);
                    network.SendValues(2);
                    network.SendValues(4);
                }
            }
        }
    }

    @Override
    public void IConnection(Network network) {
        System.out.println("Connection accepted");
    }

    @Override
    public void IDisconnection(Network network) {
        System.out.println("Connection closed");
    }

    @Override
    public void IResponse(Network network, int x, int y, int mark, int playerID, int winnerID) {
        if(this.playerID == playerID){
            if(mark == 0){
                enemyButtonList[x][y].setText("X");
                enemyButtonList[x][y].setEnabled(false);
            }else if(mark == 1){
                enemyButtonList[x][y].setText("X");
                enemyButtonList[x][y].setBackground(Color.RED);
                enemyButtonList[x][y].setEnabled(false);
            }
        }else if(this.playerID != playerID){
            allyButtonList[x][y].setText("X");
            allyButtonList[x][y].setEnabled(false);
        }

        if(winnerID == 1){
            winText.setText("X win!");
            changeToWinWindow();
        } else if(winnerID == 2){
            winText.setText("O win!");
            changeToWinWindow();
        } else if(winnerID == 3){
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