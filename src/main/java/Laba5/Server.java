package Laba5;


import java.net.*;
import java.io.*;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * Это сервер. Устанавливаем его на прослушку порта 8030. При подключении соединение добавляется в список с типом даных Network - класс,
 * созданый для обработки подключения.
 *
 * Ниже будут пояснения на ссылки с кода
 *
 * (1) Semaphore - средство синхронизации для доступа к ресурсу, который располагается в пакете java.util.concurrent.
 * Для управления доступом к ресурсу семафор использует счетчик, представляющий количество разрешений. Если значение
 * счетчика больше нуля, то поток получает доступ к ресурсу, при этом счетчик уменьшается на единицу. После окончания
 * работы с ресурсом поток освобождает семафор, и счетчик увеличивается на единицу. Если же счетчик равен нулю, то поток
 * блокируется и ждет, пока не получит разрешение от семафора. Для получения разрешения у семафора надо вызвать метод acquire().
 * После окончания работы с ресурсом полученное ранее разрешение надо освободить с помощью метода release().
 *
 * (2) ExecutorService - интерфейс, который служит альтернативой классу Thread, предназначенному для управления потоками.
 * В основу сервиса исполнения положен интерфейс Executor, в котором определен один метод:
 * void execute(Runnable thread);
 * При вызове метода execute исполняется поток thread. То есть, метод execute запускает указанный поток на исполнение.
 * Интерфейс ExecutorService расширяет свойства Executor, дополняя его методами управления исполнением и контроля.
 * */

public class Server {


    public static LinkedList<Network> connections = new LinkedList<>();

    public static void main(String[] args)throws IOException, InterruptedException{

        ServerSocket server = new ServerSocket(8030);//Инициализация сервера с портом 8030
        Semaphore semaphore = new Semaphore(2);//(1) Создаем семафор с 2 разрешениями
        //ExecutorService execServ = Executors.newFixedThreadPool(2);//(2) Задаем фиксированое количесво подключений(потоков)(Не используется, см ниже)


        while(true) {
            semaphore.acquire();
            Socket socket = server.accept();//Прослушка канала подключения


            //execServ.execute(() -> { //Запускаем поток (На самом деле, т.к. потоки создаются по ходу нижу и сразу записываются
                                       // в список, то смысла в этой строчке нет, в отличии от семафора, который ставит не
                                       // влезающие потоки на удержание)
                try {
                    Network network = new Network(socket);
                    connections.add(network);
                    toFile(socket);

                } catch (Exception e) {
                    System.out.println("Exception" + e);

                } finally {
                    connections.remove(socket);
                    semaphore.release();//После окончания работы нужно освободить место, или, грубо говоря, выделить новое разрешение, занятое предыдущим соеденением

                }

            //});

        }
    }

    static void toFile(Socket socket) throws Exception{
        FileWriter fileOfConnections = new FileWriter("C:\\Users\\111\\Documents\\NetBeansProjects\\FirstProject\\src\\main\\java\\Laba5\\Connections.txt");

        for(int i = 0; i < connections.size(); i++){
            String str = socket.toString();
            fileOfConnections.write(str);

            fileOfConnections.close();
        }
    }
}