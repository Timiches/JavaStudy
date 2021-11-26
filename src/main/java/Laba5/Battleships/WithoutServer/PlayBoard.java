package Laba5.Battleships.WithoutServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlayBoard extends JFrame implements ActionListener {
    public static void main(String args[]){
        new PlayBoard();
    }

    static String board[][] = new String[10][10];
    static JButton buttonList[][] = new JButton[10][10];
    public Dot dotList[][];
    static ArrayList<Ship> ships = new ArrayList<>();

    JPanel enemyBoard = new JPanel();
    JPanel allyBoard = new JPanel();

    PlayBoard(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1010,500);
        setLocationRelativeTo(null);

        enemyBoard.setSize(500,500);
        enemyBoard.setLayout(new GridLayout(10,10));
        dotList = new Dot[10][10];

        for(int x = 0; x < 10; x++){
            for(int y = 0; y < 10; y++) {
                JButton button = new JButton();
                enemyBoard.add(button);

                board[x][y] = "0";

                buttonList[x][y] = button;
                button.addActionListener(this);

                dotList[x][y] = new Dot(x,y);
//                dotList[x][y].x = x;
//                dotList[x][y].y = y;
//                dotList[x][y].alive = 2;

            }
        }

        allyBoard.setSize(500,500);
        allyBoard.setLayout(new GridLayout(10,10));
        for(int x = 0; x < 10; x++){
            for(int y = 0; y < 10; y++) {
                JButton button = new JButton();
                allyBoard.add(button);
            }
        }
        //растановка кораблей

        board[0][1] = "1";
        ships.add(new Ship(dotList[0][1]));
        proverka();
        dotList[0][1].alive = 1;
        board[1][3] = "1";
        ships.add(new Ship(dotList[1][3]));
        proverka();
        dotList[1][3].alive = 1;
        board[1][5] = "1";
        ships.add(new Ship(dotList[1][5]));
        proverka();
        dotList[1][5].alive = 1;
        board[1][7] = "1";
        ships.add(new Ship(dotList[1][7]));
        dotList[1][7].alive = 1;
        proverka();

        board[3][3] = "2";
        board[3][4] = "2";
        ships.add(new Ship(dotList[3][3],dotList[3][4]));
        dotList[3][3].alive = 1;
        dotList[3][4].alive = 1;
        proverka();
        board[5][3] = "2";
        board[5][4] = "2";
        ships.add(new Ship(dotList[5][3],dotList[5][4]));
        dotList[5][3].alive = 1;
        dotList[5][4].alive = 1;

        board[1][9] = "2";
        board[2][9] = "2";
        ships.add(new Ship(dotList[1][9],dotList[2][9]));
        dotList[1][9].alive = 1;
        dotList[2][9].alive = 1;

        board[5][6] = "3";
        board[6][6] = "3";
        board[7][6] = "3";
        ships.add(new Ship(dotList[5][6],dotList[6][6],dotList[7][6]));
        dotList[5][6].alive = 1;
        dotList[6][6].alive = 1;
        dotList[7][6].alive = 1;

        board[7][1] = "3";
        board[7][2] = "3";
        board[7][3] = "3";
        ships.add(new Ship(dotList[7][1],dotList[7][2],dotList[7][3]));
        dotList[7][1].alive = 1;
        dotList[7][2].alive = 1;
        dotList[7][3].alive = 1;

        board[9][9] = "4";
        board[9][8] = "4";
        board[9][7] = "4";
        board[9][6] = "4";
        ships.add(new Ship(dotList[9][6],dotList[9][7],dotList[9][8],dotList[9][9]));
        dotList[9][9].alive = 1;
        dotList[9][8].alive = 1;
        dotList[9][7].alive = 1;
        dotList[9][6].alive = 1;

        setLayout(new GridLayout(1,2));

        add(allyBoard);
        add(enemyBoard);

        setVisible(true);

        printBoard();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        for(int x = 0; x < 10; x++){
            for(int y = 0; y < 10; y++) {

                if (button.equals(buttonList[x][y])){
                    button.setEnabled(false);

                    if (board[x][y] != "0"){
                        button.setText("0");
                        board[x][y] = "/";
                        dotList[x][y].alive = 0;
                        //Dot(x,y).alive;
                        for(int i = 0; i<ships.size(); i++){

                            Ship.checkAlive(ships.get(i));

                            if(ships.get(i).isAlive == false){
                                ships.get(i).isAlive = true;
                                break;
                            }
                        }
                    } else if (board[x][y] == "0"){
                        button.setText("X");
                        board[x][y] = "X";
                    }
                }
            }
        }
        printBoard();

    }

    public void printBoard(){
        System.out.println("==============================");
        for(int x = 0; x < 10; x++){
            for(int y = 0; y < 10; y++) {
                System.out.print(" " + board[x][y] + " ");
            }
            System.out.print("\n");
        }
        System.out.println("==============================");
    }


    void proverka(){
        for(int i = 0; i<ships.size(); i++){
            System.out.println("Ship #" + i);
            System.out.println("===========");
            System.out.println(ships.get(i).shipType);
            System.out.println(ships.get(i).dot1);
            System.out.println(ships.get(i).dot2);
            System.out.println("===========");
        }
    }

    static void  removeShip(Ship ship){
        for(int i = 0; i<ships.size(); i++){
            if(ships.get(i) == ship){
                ships.remove(i);
                if(ships.size() == 0) // если корабли закончились, то конец
                    System.exit(0);
                break;
            }
        }
    }

    static void blockButton(int x, int y){
        buttonList[x][y].setEnabled(false);
        if(!board[x][y].equals("/"))
            buttonList[x][y].setText("X");
        if (board[x][y].equals("0"))
            board[x][y] = "X";
    }
}
