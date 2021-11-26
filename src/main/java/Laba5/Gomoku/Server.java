package Laba5.Gomoku;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server implements IConnection{
    public static void main(String args[]){
        new Server();
    }

    private ArrayList<Connection> connections = new ArrayList<>();

    private  char[][] table = new char[15][15];


    private Server(){
        System.out.println("Running server...");
        try(ServerSocket server = new ServerSocket(8030)){
            while(true){
                try{
                    new Connection(this, server.accept());
                }catch (IOException e){
                    System.out.println(e);
                }
            }

        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private void sendToAllConnections(String msg){
        System.out.println(msg);
        for(int i = 0; i < connections.size(); i++){
            connections.get(i).SendMsg(msg);
        }
    }

    private void gameRules(){

    }

    @Override
    public synchronized void UserConnection(Connection connection) {
        connections.add(connection);
        sendToAllConnections("Client connected " + connection);
    }

    @Override
    public synchronized void UserDisconnection(Connection connection) {
        connections.remove(connection);
        sendToAllConnections("Client disconnected " + connection);
    }

    @Override
    public synchronized void UserResponse(Connection connection, String request) {
        sendToAllConnections(request);
    }

    @Override
    public synchronized void UserException(Connection connection, Exception e) {
        System.out.println("Connection failure: " + e);
    }
}
