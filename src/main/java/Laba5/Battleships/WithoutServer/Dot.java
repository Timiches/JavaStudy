package Laba5.Battleships.WithoutServer;

public class Dot {
    int x, y, alive; //alive = 1 - true; = 0 - false; = 2 - used or undefined

    Dot(int x, int y){
        this.x = x;
        this.y = y;
        this.alive = 2;
    }

}
