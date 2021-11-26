package Laba5.TicTacToe.Prog.Console;

import java.io.*;
import java.net.Socket;

public class Network extends Thread {
    private BufferedWriter writer;
    private BufferedReader reader;
    private Socket socket;

    public Network(Socket UnoSocket) throws IOException{
        //int UserPort = socket.getPort();
        System.out.println("User connected!");
        writer = new BufferedWriter(new OutputStreamWriter(UnoSocket.getOutputStream()));//Инициализация канала ввода(кидаем)
        reader = new BufferedReader(new InputStreamReader(UnoSocket.getInputStream()));//Инициализация канала вывода(принимаем)
        this.socket = UnoSocket;
        start(); // вызываем run()
    }

    @Override
    public void run(){
        try {
            while (true) {
                int x = reader.read();
                int y = reader.read();
                int Sign = reader.read();

                try{
                    for (Network vr : Server.connections){
                        vr.writer.write(x);
                        vr.writer.flush();
                    }
                    Thread.sleep(200);
                    for (Network vr : Server.connections){
                        vr.writer.write(y);
                        vr.writer.flush();
                    }
                    Thread.sleep(200);
                    for (Network vr : Server.connections){
                        vr.writer.write(Sign);
                        vr.writer.flush();
                    }
                    Thread.sleep(200);
                }catch (InterruptedException e){
                    System.out.println("Error while Thread.sleep command:" + e );
                }
            }
        } catch (IOException e) {
            System.out.println("Error while reading/writing message:" + e );
            downService();
        }
    }

    private void downService() {
        try {
            if(!socket.isClosed()) {
                socket.close();
                reader.close();
                writer.close();
                for (Network vr : Server.connections) {
                    if(vr.equals(this)) vr.interrupt();
                    Server.connections.remove(this);
                }
            }
        } catch (IOException ignored) {}
    }

}
