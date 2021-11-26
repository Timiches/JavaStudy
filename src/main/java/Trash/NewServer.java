package Trash;

import java.net.*;
import java.io.*;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
/**
 *
 *
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
public class NewServer {

    public static void main(String[] args)throws IOException, InterruptedException{
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));//Канал прослушки консоли
        ServerSocket server = new ServerSocket(8030);//Инициализация сервера с портом 8030
        Semaphore semaphore = new Semaphore(2);//(1) Создаем семафор с 2 разрешениями
        ExecutorService execServ = Executors.newFixedThreadPool(2);//(2) Задаем фиксированое количесво подключений(потоков)


        while(true) {
            semaphore.acquire();
            Socket socket = server.accept();//Прослушка канала подключения

            //execServ.execute(() -> { //Запускаем поток

                try {
                    connections.add(new ServerSomething(socket));
                } catch (Exception e) {
                    System.out.println("Exception" + e);
                } finally {
                    connections.remove(socket);
                    semaphore.release();//После окончания работы нужно освободить место, или, грубо говоря, выделить новое разрешение, занятое предыдущим соеденением
                }
            //});
        }

    }

    public static LinkedList<ServerSomething> connections = new LinkedList<>();

    private void Server()throws IOException, InterruptedException{

    }

    private static void WorkWithUser(Socket UnoSocket) throws IOException, InterruptedException{//Метод для работы с каждым пользователем

    }


}
