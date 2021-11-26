package Trash;
import java.text.DecimalFormat;
import java.util.Scanner;

import static java.lang.System.*;

public class Ref {


   /*

    Задача № 3.  Вариант 2

   Написать программу, моделирующую поведение сборщика артефактов (Stalker) путём создания соответствующего класса.
Все сталкеры имеют одинаковую скорость v0 без груза. Каждый сталкер имеет свой псевдоним и свою грузоподъёмность m0.
Сталкер добывает (accrue) артефакты массы m, затем пытается их вынести (carry), двигаясь со скоростью v.
Скорость сталкера определяется следующим образом:


    v = v0 * ( 1 / ( 1 + ( m^2 / m0^2) ) )

    В  методе Main провести тестирование работоспособности разработанного класса.
    Создать несколько объектов класса, промоделировать их работу, результаты выводить на экран.
    */

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        out.print("Enter count of stalkers: ");

        int stalkerCount = Integer.parseInt(in.nextLine());

        out.print("Enter stalker`s default speed: ");

        Stalker.SetV0(Double.parseDouble(in.nextLine()));

        Stalker[] stalkersArr = new Stalker[stalkerCount]; // массив сталкеров

        out.println("Foreach stalker enter: ");

        for (int i = 0; i < stalkerCount; i++)
        {

            out.print(i + ".Name: ");
            String stalkerName = in.nextLine();

            out.print(i + ".Carrying capacity: ");
            int carryingCapacity = Integer.parseInt(in.nextLine());

            stalkersArr[i] = new Stalker(stalkerName, carryingCapacity);
        }

        boolean isEveryoneCanMove = true;// флаг, что никто не остановился при зборке артефактов

        while (isEveryoneCanMove)
        {
            for (int i = 0; i < stalkerCount; i++)
            {
                stalkersArr[i].Accrue(2); //сталкер[i] собирает артефакт весом 2 кг

                if( !stalkersArr[i].Carry())  //сталкер[i] пытается унести артефакт
                {
                    out.println("Stalker " +  stalkersArr[i].GetName() +" has stopped."); //сталкер[i] останавливается, если v = 0 км/ч
                    isEveryoneCanMove = false;
                }

                out.println();
            }
        }
    }

    public static class Stalker
    {
        private String name; //псевдоним
        private static double v0; // скорость персонажа без груза (неизменяемая величина)  [км/ч]
        private double v; //скорость с грузом [км/ч] (изменяемая величина)
        private double m0; // грузоподъёмность персонажа (неизменяемая величина) [кг]
        private double m1; // вес рюкзака [кг] (изменяемая величина)
        private int countOfArtifacts;

        public Stalker()
        {
            this.name = "DefaultName";
            this.v = 0;
            this.v0 = 5;
            this.m1 = this.m0 = 0;
            this.countOfArtifacts = 0;
        }

        public Stalker (String name, int carryingCapacity)
        {
            this.name = name;
            this.m1 = 0;
            this.m0 = carryingCapacity;
            this.v = 0;
            this.countOfArtifacts = 0;
        }

        public String GetName()
        {
            return this.name;
        }

        public static void SetV0(double speed)
        {
            Stalker.v0 = speed;
        }


        public void GetCarryingCapacity()
        {
            out.println("Stalker " +  this.name + " carrying capacity is " + this.m0);
        }

        public void GetCountOfArtifacts()
        {
            out.println("Stalker " +  this.name + " has " + this.countOfArtifacts + " artifacts");
        }

        public boolean Accrue (double m)// собирания артефактов
        {
            if ( m >= 0)
            {
                this.m1 =  this.m1 + m;// m1 вес рюкзака
                this.v =  this.v0 * ( 1 / ( 1 + Math.pow(this.m1,2) / Math.pow(this.m0,2)));
                this.countOfArtifacts++;


                out.println("Stalker " +  this.name + " gathered this artifact (weight " + m + " kg) and has "+ this.countOfArtifacts + " in backpack. His carrying capacity is " + this.m0
                        + "kg. His backpack weight " + this.m1 + " kg.");

                return true;
            }

            return false;
        }

        public boolean Carry() //движения сталкера
        {
            if( this.v > 0.1)
            {
                out.printf("Stalker %s moving with speed %.2f km\\h. \n", this.name, this.v);
                return true; // сталкер может двигаться
            }
            return false; //сталкер не может двигаться
        }
    }
}

