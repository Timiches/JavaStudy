package Laba5.TicTacToe.Prog.GUI;

import java.io.*;
import java.net.Socket;

public class Network {
    private final Socket socket;
    private final Thread thread;//Поток, который слушает входящее соединение
    private final BufferedReader reader;
    private final BufferedWriter writer;
    private final INetwork eventListener;//Подкидываем объекту нужную в данный момент реалицацию(подключится, отключится, итд.)

    public Network(INetwork eventListener, String ipAddress, int port) throws IOException{
        this(eventListener, new Socket(ipAddress, port));
    }

    public Network(INetwork eventListener, Socket socket) throws IOException {
        this.eventListener = eventListener;
        this.socket = socket;
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        thread = new Thread(new Runnable() {
            @Override
            public void run() {//старнанули поток и он готов к использованию
                try {
                    eventListener.IConnection(Network.this);
                    while (!thread.isInterrupted()){//пока поток не прерван...
                        int x = reader.read();
                        int sign = reader.read();
                        int winnerID = reader.read();
                        eventListener.IResponse(Network.this, x, sign, winnerID);
                    }
                } catch (IOException e) {
                    eventListener.IException(Network.this, e);
                } finally {
                    eventListener.IDisconnection(Network.this);
                }
            }
        });
        thread.start();
    }

    public synchronized void SendValues(int val){
        try {
            writer.write(val);
            writer.flush();
        } catch (IOException e) {
            eventListener.IException(Network.this, e);
            disconnect();
        }
    }

    public synchronized void disconnect(){
        thread.interrupt();
        try {
            socket.close();
        } catch (IOException e) {
            eventListener.IException(Network.this, e);
        }
    }

}