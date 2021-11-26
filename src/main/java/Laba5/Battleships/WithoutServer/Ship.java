package Laba5.Battleships.WithoutServer;

import java.util.ArrayList;

public class Ship {
    static boolean isAlive;
    Dot dot1, dot2,dot3,dot4;
    String shipType;
    static int x, y, xCount, yCount;
    //Dot[] blockAround;

    Ship(Dot dot1){
        this.dot1 = dot1;
        this.isAlive = true;

        dot1.alive = 1;

        this.shipType = "Schnellboot";

//        int[][] blockAroundX = new int[3][3];
//        int[][] blockAroundY = new int[3][3];
//        int x = dot1.x;
//        int y = dot1.y;

//        blockAroundX[1][1] = x;
//        blockAroundY[1][1] = y;

//        blockAround = new Dot[9];
//        blockAround[0] = dot1;
//        blockAround[1] = new Dot(x-1,y-1);
//        blockAround[2] = new Dot(x-1,y);
//        blockAround[3] = new Dot(x-1,y+1);
//        blockAround[4] = new Dot(x,y-1);
//        blockAround[5] = new Dot(x,y+1);
//        blockAround[6] = new Dot(x+1,y-1);
//        blockAround[7] = new Dot(x+1,y);
//        blockAround[8] = new Dot(x+1,y+1);
    }

    Ship(Dot dot1, Dot dot2){
        this.dot1 = dot1;
        this.dot2 = dot2;
        this.isAlive = true;

        dot1.alive = 1;
        dot2.alive = 1;

        this.shipType = "Destroyer";

//        for(int i = 0; i<=dotList.size(); i++){
//            if(dotList.get(i).alive == false){
//                this.killPoint++;
//                dotList.get(i).alive = null;
//            }
//        }

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
        //checkAlive(this);
    }

    private static void checkAlive(Dot dot1, Ship ship){
        if(dot1.alive == 0){
            isAlive = false;
            System.out.println("Schnellboot has ben drowned");
            PlayBoard.removeShip(ship);
            blockAroundOneDot(dot1);
        }
    }

    private static void checkAlive(Dot dot1, Dot dot2, Ship ship){
        if(dot1.alive == 0 && dot2.alive == 0){
            isAlive = false;
            System.out.println("Destroyer has ben drowned");
            PlayBoard.removeShip(ship);
            blockAroundOneDot(dot1);
            blockAroundOneDot(dot2);
        }
    }

    private static void checkAlive(Dot dot1, Dot dot2, Dot dot3, Ship ship){
        if(dot1.alive == 0 && dot2.alive == 0 && dot3.alive == 0){
            isAlive = false;
            System.out.println("Cruiser has ben drowned");
            PlayBoard.removeShip(ship);
            blockAroundOneDot(dot1);
            blockAroundOneDot(dot2);
            blockAroundOneDot(dot3);
        }
    }

    private static void checkAlive(Dot dot1, Dot dot2, Dot dot3, Dot dot4, Ship ship){
        if(dot1.alive == 0 && dot2.alive == 0 && dot3.alive == 0 && dot4.alive == 0){
            isAlive = false;
            System.out.println("Battleship has ben drowned");
            PlayBoard.removeShip(ship);
            blockAroundOneDot(dot1);
            blockAroundOneDot(dot2);
            blockAroundOneDot(dot3);
            blockAroundOneDot(dot4);
        }
    }

    public static void checkAlive(Ship ship){
        switch (ship.shipType) {
            case "Schnellboot":
                checkAlive(ship.dot1, ship);
                break;
            case "Destroyer":
                checkAlive(ship.dot1, ship.dot2, ship);
                break;
            case "Cruiser":
                checkAlive(ship.dot1, ship.dot2, ship.dot3, ship);
                break;
            case "Battleship":
                checkAlive(ship.dot1, ship.dot2, ship.dot3, ship.dot4, ship);
                break;
            default:break;
        }
    }

    static void blockAroundOneDot(Dot dot){
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
                PlayBoard.blockButton(x, y);
                y++;
            }
            y = y - yCount;
            x++;
        }
    }

//    int ReturnBlockedAxisX(int i){
//        int x = blockAround[i].x;
//        return x;
//    }
//
//    int ReturnBlockedAxisY(int i){
//        int y = blockAround[i].y;
//        return y;
//    }
}
