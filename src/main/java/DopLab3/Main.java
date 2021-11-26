package DopLab3;

import java.util.Scanner;


/**
 * Задача № 3.  Вариант 2
 * 	Написать программу, моделирующую поведение сборщика артефактов (Stalker) путём создания соответствующего класса.
 * Все сталкеры имеют одинаковую скорость v0 без груза. Каждый сталкер имеет свой псевдоним и свою грузоподъёмность m0.
 * Сталкер добывает (accrue) артефакты массы m, затем пытается их вынести (carry), двигаясь со скоростью v.
 * В  методе Main провести тестирование работоспособности разработанного класса.
 * Создать несколько объектов класса, промоделировать их работу, результаты выводить на экран.
 */

public class Main {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        System.out.print("Enter stalkers` count: ");
        int stalkerCount = in.nextInt();

        Stalker[] stalkersArr = new Stalker[stalkerCount];
        System.out.println("Enter some information about each stalker: ");
        in.nextLine();
        for (int i = 0; i < stalkerCount; i++) {

            System.out.print(i+1 + ". Name: ");
            String stalkerName = in.nextLine();

            System.out.print(i+1 + ". Carrying capacity: ");
            int carryingCapacity = in.nextInt();
            in.nextLine();

            stalkersArr[i] = new Stalker(stalkerName, carryingCapacity);
        }
        boolean movable = true;

        while(movable){
            for (int i = 0; i < stalkerCount; i++) {
                stalkersArr[i].Accrue();

                if(!stalkersArr[i].Carry()) {
                    System.out.println("Stalker " +  stalkersArr[i].GetName() +" has stopped.");
                    movable = false;
                }
                System.out.println();
            }
        }
    }
}
