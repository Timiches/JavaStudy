package Laba5.Gomoku;

import java.io.*;
import java.net.Socket;

public class Connection {
    private final Socket socket;
    private final Thread thread;//Поток, который слушает входящее соединение
    private final BufferedReader reader;
    private final BufferedWriter writer;
    private final IConnection eventListener;

    public Connection(IConnection eventListener, String ipAddress, int port) throws IOException{
        this(eventListener, new Socket(ipAddress, port));
    }

    public Connection(IConnection eventListener, Socket socket) throws IOException {
        this.eventListener = eventListener;
        this.socket = socket;
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        thread = new Thread(new Runnable() {
            @Override
            public void run() {//старнанули поток и он готов к использованию
                try {
                    eventListener.UserConnection(Connection.this);
                    while (!thread.isInterrupted()){//пока поток не прерван...
                        String string = reader.readLine();
                        eventListener.UserResponse(Connection.this, string);
                    }
                } catch (IOException e) {
                    eventListener.UserException(Connection.this, e);
                } finally {
                    eventListener.UserDisconnection(Connection.this);
                }
            }
        });
        thread.start();
    }

    public synchronized void SendMsg(String msg){
        try {
            writer.write(msg + "\r\n");
            writer.flush();
        } catch (IOException e) {
            eventListener.UserException(Connection.this, e);
            disconnect();
        }
    }

    public synchronized void disconnect(){
        thread.interrupt();
        try {
            socket.close();
        } catch (IOException e) {
            eventListener.UserException(Connection.this, e);
        }
    }

    @Override
    public String toString() {
        return "connection: " + socket.getInetAddress() +":" + socket.getPort();
    }
}
