package Laba5.TicTacToe.Prog.Console;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Server {

    public static LinkedList<Network> connections = new LinkedList<>();

    public static void main(String[] args)throws IOException, InterruptedException{
        ServerSocket server = new ServerSocket(8030);
        Semaphore semaphore = new Semaphore(2);

        while(true) {
            semaphore.acquire();
            Socket socket = server.accept();
            try {
                connections.add(new Network(socket));
            } catch (Exception e) {
                System.out.println("Exception" + e);
            } finally {
                connections.remove(socket);
                semaphore.release();
            }
        }
    }
}