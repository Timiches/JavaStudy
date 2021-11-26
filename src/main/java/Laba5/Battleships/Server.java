package Laba5.Battleships;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;


public class Server implements INetwork {
    public static void main(String args[]){
        new Server();
    }

    private static ArrayList<Network> connections = new ArrayList<>(); //список подключенных пользователей

    //для первого клиента
    static String board1[][] = new String[10][10];//игровая доска, отображаемая в консоли
    public Dot dotList1[][]; //список всех кнопок (клеток) первого пользователя
    static ArrayList<Ship> ships1 = new ArrayList<>(); // список всех кораблей

    //для второго клиента
    static String board2[][] = new String[10][10];
    public Dot dotList2[][];
    static ArrayList<Ship> ships2 = new ArrayList<>();

    static int mark, playerID, winnerID;
   // int x, y;

    private Server(){
        // вызываем конструкторы всех нужных элементов
        dotList1 = new Dot[10][10];
        dotList2 = new Dot[10][10];
        System.out.println("Running server...");

        for(int x = 0; x < 10; x++){
            for(int y = 0; y < 10; y++) {
                board1[x][y] = "0";
                dotList1[x][y] = new Dot(x,y);

                board2[x][y] = "0";
                dotList2[x][y] = new Dot(x,y);
            }
        }
        addShipsKostyl(board1, ships1, dotList1);//Добавление кораблей(Просто мега костыль, но извените меня, куда деватся...)
        addShipsKostyl(board2, ships2, dotList2);//Попытался сделать нормальную реализацию добавления корадлей через юзера, но очень неочевидно, как это сделать

        printTables();//напечатать игровое поле в консоле

        try(ServerSocket server = new ServerSocket(8030)){//Создание сервера на порте 8030
            while(true){
                try{
                    new Network(this, server.accept()); //добавление нового подключения
                }catch (IOException e){
                    System.out.println(e);
                }
            }

        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }



    @Override
    public synchronized void IConnection(Network network) { // добавление в список подключенных
        connections.add(network);
    }

    @Override
    public synchronized void IDisconnection(Network network) { // удаление из списока подключенных
        connections.remove(network);
    }

    @Override
    public synchronized void IResponse(Network network, int x, int y, int mark, int playerID, int winnerID) { //Реаккци на пришедшие данные
         winnerID = 0;

        if(playerID == 1) { //если запрос пришел из Player1
            if (board1[x][y] != "0") { // и если на этой клетке есть корабль

                board1[x][y] = "/"; //Обозначаем в консоле убитую клетку
                dotList1[x][y].alive = 0; //убиваем клетку
                mark = 1; //метка того, что мы убили клетку, которую отправим юзерам (нужно для того, что б просто поставило крестик в нажатую клетку, если, конечно, там есть корабль)

                for (int i = 0; i < ships1.size(); i++) { // Если есть, ищем убитый корабль
                    Ship.checkAlive(ships1.get(i), playerID); // Вызов метода в классе Ship
                    if (ships1.get(i).isAlive == false) { // пропуск ненужных элементов, которые остались (ОПТИМИЗАЦИЯ)
                        ships1.get(i).isAlive = true;
                        break;
                    }
                }
            } else if (board1[x][y] == "0") { // а если на этой клетке пусто
                mark = 0; // то метка "пустая"
                board1[x][y] = "X";
            }
        } else if(playerID == 2){ //Аналогично с первым юзером
            if (board2[x][y] != "0") {

                board2[x][y] = "/";
                dotList2[x][y].alive = 0;
                mark = 1;

                for (int i = 0; i < ships2.size(); i++) {
                    Ship.checkAlive(ships2.get(i), playerID);
                    if (ships2.get(i).isAlive == false) {
                        ships2.get(i).isAlive = true;
                        break;
                    }
                }
            } else if (board2[x][y] == "0") {
                mark = 0;
                board2[x][y] = "X";
            }
        }

        if (ships1.size() == 0) {
            System.out.println("Player 1 win");
            winnerID = 1;
        }
        if (ships2.size() == 0) {
            System.out.println("Player 2 win");
            winnerID = 2;
        }

        printTables();

        sendToAnotherPlayer(x);
        sendToAnotherPlayer(y);
        sendToAnotherPlayer(mark);
        sendToAnotherPlayer(playerID);
        sendToAnotherPlayer(winnerID);

        if(ships1.size() == 0 || ships2.size() == 0) // если корабли закончились, то конец
            System.exit(0); // "Моя остановочка"
    }

    @Override
    public synchronized void IException(Network network, Exception e) { //реакция на ошибку
        System.out.println("Connection failure: " + e);
    }

    private static void sendToAnotherPlayer(int val){
        for(int i = 0; i < connections.size(); i++){
                connections.get(i).SendValues(val); //вызов метода в Network
        }
    }

    void printTables(){
        System.out.println("============================== ++ ==============================");
        for(int x = 0; x < 10; x++){
            for(int y = 0; y < 10; y++) {
                System.out.print(" " + board1[x][y] + " ");
            }
            System.out.print(" || ");
            for(int y = 0; y < 10; y++) {
                System.out.print(" " + board2[x][y] + " ");
            }
            System.out.print("\n");
        }
        System.out.println("============================== ++ ==============================");
    }

    static void  removeShip(Ship ship, int playerID){ // удаление корабля из нужного списка
        if(playerID == 1) {
            for (int i = 0; i < ships1.size(); i++) {
                if (ships1.get(i) == ship) {
                    ships1.remove(i);
                    break;
                }
            }
        } else if (playerID == 2){
            for (int i = 0; i < ships2.size(); i++) {
                if (ships2.get(i) == ship) {
                    ships2.remove(i);
                    break;
                }
            }
        }
    }

    static void blockButton(int x, int y, int playerID){
        if (board1[x][y].equals("0"))//"если клетка пустая", тобиш просто чтоб не поставило крестик на клетку с кораблем
            board1[x][y] = "X";

        // Отправка юзерам блокированых клеток
        sendToAnotherPlayer(x);
        sendToAnotherPlayer(y);
        sendToAnotherPlayer(mark);
        sendToAnotherPlayer(playerID); // из-за этой чертовой строчки пришлось сделать крюк и таскать playerID
        sendToAnotherPlayer(winnerID);
    }

    void addShipsKostyl(String board[][], ArrayList<Ship> ships, Dot dotList[][]){ // ну тут я ничего писать не буду...
        board[0][1] = "1";
        ships.add(new Ship(dotList[0][1]));
        dotList[0][1].alive = 1;
        board[1][3] = "1";
        ships.add(new Ship(dotList[1][3]));
        dotList[1][3].alive = 1;
        board[1][5] = "1";
        ships.add(new Ship(dotList[1][5]));
        dotList[1][5].alive = 1;
        board[1][7] = "1";
        ships.add(new Ship(dotList[1][7]));
        dotList[1][7].alive = 1;

        board[3][3] = "2";
        board[3][4] = "2";
        ships.add(new Ship(dotList[3][3],dotList[3][4]));
        dotList[3][3].alive = 1;
        dotList[3][4].alive = 1;
        board[5][3] = "2";
        board[5][4] = "2";
        ships.add(new Ship(dotList[5][3],dotList[5][4]));
        dotList[5][3].alive = 1;
        dotList[5][4].alive = 1;

        board[1][9] = "2";
        board[2][9] = "2";
        ships.add(new Ship(dotList[1][9],dotList[2][9]));
        dotList[1][9].alive = 1;
        dotList[2][9].alive = 1;

        board[5][6] = "3";
        board[6][6] = "3";
        board[7][6] = "3";
        ships.add(new Ship(dotList[5][6],dotList[6][6],dotList[7][6]));
        dotList[5][6].alive = 1;
        dotList[6][6].alive = 1;
        dotList[7][6].alive = 1;

        board[7][1] = "3";
        board[7][2] = "3";
        board[7][3] = "3";
        ships.add(new Ship(dotList[7][1],dotList[7][2],dotList[7][3]));
        dotList[7][1].alive = 1;
        dotList[7][2].alive = 1;
        dotList[7][3].alive = 1;

        board[9][8] = "4";
        board[9][7] = "4";
        board[9][6] = "4";
        board[9][5] = "4";
        ships.add(new Ship(dotList[9][6],dotList[9][7],dotList[9][8],dotList[9][9]));
        dotList[9][8].alive = 1;
        dotList[9][7].alive = 1;
        dotList[9][6].alive = 1;
        dotList[9][5].alive = 1;
    }
}
