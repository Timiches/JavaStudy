package Laba5.TicTacToe.Prog.GUI;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;


public class Server implements INetwork{
    public static void main(String args[]){
        new Server();
    }

    private ArrayList<Network> connections = new ArrayList<>();
    char [][] table = new char[3][3];
    final char Sign_X = 'x';
    final char Sign_O = 'o';
    final char Sign_Empty = '.';

    private Server(){
        System.out.println("Running server...");

        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                table[row][col] = Sign_Empty;

        try(ServerSocket server = new ServerSocket(8030)){
            while(true){
                try{
                    new Network(this, server.accept());
                }catch (IOException e){
                    System.out.println(e);
                }
            }

        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private void sendToAllConnections(int val){
        //System.out.println(val);
        for(int i = 0; i < connections.size(); i++){
            connections.get(i).SendValues(val);
        }
    }

    @Override
    public synchronized void IConnection(Network network) {
        connections.add(network);
    }

    @Override
    public synchronized void IDisconnection(Network network) {
        connections.remove(network);
    }

    @Override
    public synchronized void IResponse(Network network, int i, int sign, int winnerID) {
        int x = 0, y = 0;

        if(i == 0){ //Самая тупая вещь, которую я когда либо писал))
            x = 0;
            y = 0;
        }else if(i == 1){
            x = 1;
            y = 0;
        }else if(i == 2){
            x = 2;
            y = 0;
        }else if(i == 3){
            x = 0;
            y = 1;
        }else if(i == 4){
            x = 1;
            y = 1;
        }else if(i == 5){
            x = 2;
            y = 1;
        }else if(i == 6){
            x = 0;
            y = 2;
        }else if(i == 7){
            x = 1;
            y = 2;
        }else if(i == 8){
            x = 2;
            y = 2;
        }

        if(sign==1){
            table[y][x] = Sign_X;
        } else if(sign==2){
            table[y][x] = Sign_O;
        }

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++)
                System.out.print(table[row][col] + " ");
            System.out.println();
        }

        winnerID = 3;

        if (checkWin(Sign_X, table)) {
            System.out.println("X Win!");
            winnerID = 1;
        }
        if (checkWin(Sign_O, table)) {
            System.out.println("O Win!");
            winnerID = 2;
        }
        if (isTableFull(table, Sign_Empty)) {
            System.out.println("Draw");
            winnerID = 0;
        }

        sendToAllConnections(i);
        sendToAllConnections(sign);
        sendToAllConnections(winnerID);

    }

    @Override
    public synchronized void IException(Network network, Exception e) {
        System.out.println("Connection failure: " + e);
    }

    boolean checkWin(char dot, char[][] table) {
        for (int i = 0; i < 3; i++)
            if ((table[i][0] == dot && table[i][1] == dot &&
                    table[i][2] == dot) ||
                    (table[0][i] == dot && table[1][i] == dot &&
                            table[2][i] == dot))
                return true;
        if ((table[0][0] == dot && table[1][1] == dot &&
                table[2][2] == dot) ||
                (table[2][0] == dot && table[1][1] == dot &&
                        table[0][2] == dot))
            return true;
        return false;
    }

    boolean isTableFull(char[][] table, char Sign_Empty) {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                if (table[row][col] == Sign_Empty)
                    return false;
        return true;
    }

}
