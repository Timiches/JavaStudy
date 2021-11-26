package Laba5.Battleships;


public class Ship {
    static boolean isAlive;
    Dot dot1, dot2, dot3, dot4;
    String shipType;
    static int x, y, xCount, yCount;

    //Под каждий вид корабля своя реализация

    Ship(Dot dot1){
        this.dot1 = dot1;
        this.isAlive = true;

        dot1.alive = 1;

        this.shipType = "Schnellboot";
    }

    Ship(Dot dot1, Dot dot2){
        this.dot1 = dot1;
        this.dot2 = dot2;
        this.isAlive = true;

        dot1.alive = 1;
        dot2.alive = 1;

        this.shipType = "Destroyer";

    }

    Ship(Dot dot1, Dot dot2, Dot dot3){
        this.dot1 = dot1;
        this.dot2 = dot2;
        this.dot3 = dot3;
        this.isAlive = true;

        dot1.alive = 1;
        dot2.alive = 1;
        dot3.alive = 1;

        this.shipType = "Cruiser";
    }

    Ship(Dot dot1, Dot dot2, Dot dot3, Dot dot4){
        this.dot1 = dot1;
        this.dot2 = dot2;
        this.dot3 = dot3;
        this.dot4 = dot4;
        this.isAlive = true;

        dot1.alive = 1;
        dot2.alive = 1;
        dot3.alive = 1;
        dot4.alive = 1;

        this.shipType = "Battleship";
    }

    private static void checkAlive(Dot dot1, Ship ship, int playerID){
        if(dot1.alive == 0){
            isAlive = false;
            System.out.println("Schnellboot has ben drowned");
            Server.removeShip(ship, playerID); // обратно в Server. Это для того, чтоб работать со списком в том класе
            blockAroundOneDot(dot1, playerID);// вызов метода для блокировки клеток вокруг убитого корабля
        }
    }

    private static void checkAlive(Dot dot1, Dot dot2, Ship ship, int playerID){
        if(dot1.alive == 0 && dot2.alive == 0){
            isAlive = false;
            System.out.println("Destroyer has ben drowned");
            Server.removeShip(ship, playerID);
            blockAroundOneDot(dot1, playerID);
            blockAroundOneDot(dot2, playerID);
        }
    }

    private static void checkAlive(Dot dot1, Dot dot2, Dot dot3, Ship ship, int playerID){
        if(dot1.alive == 0 && dot2.alive == 0 && dot3.alive == 0){
            isAlive = false;
            System.out.println("Cruiser has ben drowned");
            Server.removeShip(ship, playerID);
            blockAroundOneDot(dot1, playerID);
            blockAroundOneDot(dot2, playerID);
            blockAroundOneDot(dot3, playerID);
        }
    }

    private static void checkAlive(Dot dot1, Dot dot2, Dot dot3, Dot dot4, Ship ship, int playerID){
        if(dot1.alive == 0 && dot2.alive == 0 && dot3.alive == 0 && dot4.alive == 0){
            isAlive = false;
            System.out.println("Battleship has ben drowned");
            Server.removeShip(ship, playerID);
            blockAroundOneDot(dot1, playerID);
            blockAroundOneDot(dot2, playerID);
            blockAroundOneDot(dot3, playerID);
            blockAroundOneDot(dot4, playerID);
        }
    }

    public static void checkAlive(Ship ship, int playerID){ //Проверка кораблей на наличие "живых" клеток. Пришлось реализовать именно так
                                                            // + изза этого приходилось делать бесполезный if для оптимизации; приходится передавать playerID по всему кругу, потому что требуется статическая
                                                            //переменная в одном из методов, что приводит к некорректной работе
        switch (ship.shipType) { // вызов вышеописаных методов
            case "Schnellboot":
                checkAlive(ship.dot1, ship, playerID);
                break;
            case "Destroyer":
                checkAlive(ship.dot1, ship.dot2, ship, playerID);
                break;
            case "Cruiser":
                checkAlive(ship.dot1, ship.dot2, ship.dot3, ship, playerID);
                break;
            case "Battleship":
                checkAlive(ship.dot1, ship.dot2, ship.dot3, ship.dot4, ship, playerID);
                break;
            default:break;
        }
    }

    static void blockAroundOneDot(Dot dot, int playerID){
        x = dot.x - 1;
        y = dot.y - 1;
        xCount = 3;
        yCount = 3;

        if(x < 0) {
            x++;
            xCount = 2;
        }else if(y < 0) {
            y++;
            yCount = 2;
        } else if(x < 0 && y < 0){
            x++;
            y++;
            xCount = 2;
            yCount = 2;
        } else if (x+2 > 9){
            xCount = 2;
        } else if (y+2 > 9){
            yCount = 2;
        } else if(x+2 > 9 && y+2 > 9){
            xCount = 2;
            yCount = 2;
        }

        for (int i = 0; i < xCount; i++){
            for(int j = 0; j < yCount; j++){
                Server.blockButton(x, y, playerID); //вызываем метод в серваке, для блокировки клеток
                y++;
            }
            y = y - yCount;
            x++;
        }
    }
}
