package Laba5;

import java.io.*;
import java.net.Socket;

public class Network extends Thread {
    private BufferedWriter writer;
    private BufferedReader reader;
    Socket socket;

    public Network(Socket UnoSocket) throws IOException {
        System.out.println("Connected!");
        writer = new BufferedWriter(new OutputStreamWriter(UnoSocket.getOutputStream()));//Инициализация канала ввода(кидаем)
        reader = new BufferedReader(new InputStreamReader(UnoSocket.getInputStream()));//Инициализация канала вывода(принимаем)
        this.socket = UnoSocket;
        start(); // вызываем run()
    }

    @Override
    public void run() {
        int UserPort = socket.getPort();//узнаем порт пользователя
        String request; //поле для запросов
        try {
            while (true) {
                System.out.println("Waiting response...");
                request = reader.readLine(); //считываем данные, полученые от польователя
                if(request.equals("quit")) { //если ключевое слово quit,
                    System.out.println("User " + UserPort + " disconnected");
                    this.downService(); // то запуск закрывающего метода
                    break; // и выходим из цикла прослушки
                }
                System.out.println("User " + UserPort +": " + request);
                for (Network vr : Server.connections) {                 //До сих пор не понимаю как эти две строчки работают, но
                    vr.send("User " + UserPort + ": " + request); //тут отсылаем сообщение каждому пользователю
                }
            }
        } catch (IOException e) {
            System.out.println("Error while reading/writing message:" + e );
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

    private void send(String msg) {
        try {
            writer.write(msg + "\n");
            writer.flush();
        } catch (IOException ignored) {}

    }
}
