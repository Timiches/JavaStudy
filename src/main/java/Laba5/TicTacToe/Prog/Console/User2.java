package Laba5.TicTacToe.Prog.Console;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class User2 extends Rules{

    public static void main(String[] str){
        System.out.println("Connecting...");
        new User2();
    }

    User2(){
        Scanner in = new Scanner(System.in);
        char [][] table = new char[3][3];
        final int UserSign = 0;
        final char Sign_X = 'x';
        final char Sign_O = 'o';
        int Sign;
        final char Sign_Empty = '.';
        int x,y;

        try(
                Socket socket = new Socket("localhost", 8030);
                BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ){
            super.initTable(table, Sign_Empty);
            for (int row = 0; row < 3; row++)
                for (int col = 0; col < 3; col++)
                    table[row][col] = Sign_Empty;
            System.out.println("Connected!");
            System.out.println("Press Enter to write");
            while(true) {

                if(reader.ready()){
                    x = reader.read();
                    y = reader.read();
                    Sign = reader.read();

                    if(Sign==1){
                        table[y][x] = Sign_X;
                    } else if(Sign==0){
                        table[y][x] = Sign_O;
                    }

                    for (int row = 0; row < 3; row++) {
                        for (int col = 0; col < 3; col++)
                            System.out.print(table[row][col] + " ");
                        System.out.println();
                    }

                    if (super.checkWin(Sign_X, table)) {
                        if(UserSign == Sign){
                            System.out.println("You win");
                        }
                        System.out.println("X Win!");
                        break;
                    }
                    if (super.checkWin(Sign_O, table)) {
                        if(UserSign == Sign){
                            System.out.println("You win");
                        }
                        System.out.println("O Win!");
                        break;
                    }
                    if (super.isTableFull(table, Sign_Empty)) {
                        System.out.println("Draw");
                        break;
                    }
                    System.out.println("Press Enter to write");

                } else if(console.ready()) {
                    while(true){
                        System.out.println("Write X");
                        x = in.nextInt();
                        x -= 1;
                        System.out.println("Write Y");
                        y = in.nextInt();
                        y -= 1;
                        if(x < 0 || y < 0 || x > 3|| y > 3)
                            System.out.println("This position is out of game board");
                        else if(table[y][x]!=Sign_Empty)
                            System.out.println("This position already marked");
                        else break;
                    }

                    writer.write(x);
                    writer.flush();

                    writer.write(y);
                    writer.flush();

                    writer.write(UserSign);
                    writer.flush();

                }


            }
        }catch(IOException e){
            System.out.print("Error = " + e);
        }
    }

}
