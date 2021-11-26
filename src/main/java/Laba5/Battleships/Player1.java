package Laba5.Battleships;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Player1 extends JFrame implements ActionListener, INetwork {
    public static void main(String args[]){
        new Player1();
    }

    static JButton enemyButtonList[][] = new JButton[10][10];
    static JButton allyButtonList[][] = new JButton[10][10];

    JPanel enemyBoard = new JPanel();
    JPanel allyBoard = new JPanel();

    int playerID = 1;

    private Network network;

    JFrame winnerWindow = new JFrame(); //окно, выскакивающее при попеде
    JTextField winText = new JTextField(); // его текст

    Player1(){
        //стары добрый корявый swing
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1010,500);
        setLocationRelativeTo(null);

        enemyBoard.setSize(500,500); //правая панель
        enemyBoard.setLayout(new GridLayout(10,10));

        for(int x = 0; x < 10; x++){ // создание кнопок
            for(int y = 0; y < 10; y++) {
                JButton button = new JButton();
                enemyBoard.add(button);

                enemyButtonList[x][y] = button;
                button.addActionListener(this); // добавление слушателя событий к кнопке
            }
        }

        allyBoard.setSize(500,500); // левая панель
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

        //опять же, АСУЖДАЮ такую реализация
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
            network = new Network(this, "localhost", 8030); // подключени к серверу с портом 8030
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) { //Проще говоря, что будет делать кнопка при нажатии
        JButton button = (JButton) e.getSource(); // находим виновника торжества
        for(int x = 0; x < 10; x++){
            for(int y = 0; y < 10; y++) {
                if (button.equals(enemyButtonList[x][y])){ // ищем его в спииске, а точнее его кординаты
                    button.setEnabled(false);

                    //кидаем все серваку
                    network.SendValues(x);
                    network.SendValues(y);
                    network.SendValues(3);
                    network.SendValues(playerID);
                    network.SendValues(3);
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
    public void IResponse(Network network, int x, int y, int mark, int playerID, int winnerID) { // реакция на пришедшие данные
        if(this.playerID == playerID){ // если пришли свои же данные...
            if(mark == 0){ // и пустая клетка
                enemyButtonList[x][y].setText("X");
                enemyButtonList[x][y].setEnabled(false);
            }else if(mark == 1){ // а если здесь корабль...
                enemyButtonList[x][y].setBackground(Color.RED);
                enemyButtonList[x][y].setText("X");
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

    private void changeToWinWindow(){//ну тут понятно
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
