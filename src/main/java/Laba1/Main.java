package Laba1;
import java.awt.*;
import java.awt.geom.Point2D;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class Main {
    static Scanner in = new Scanner(System.in);
    static String x;
    public static void main(String[] args) {
        System.out.println("==============================================");
        task1();
        System.out.println("End of Task");
        x = in.nextLine();
        System.out.println("==============================================");
        task2();
        System.out.println("End of Task");
        x = in.nextLine();
        System.out.println("==============================================");
        task3(false);
        System.out.println("End of Task");
        x = in.nextLine();
        System.out.println("==============================================");
        task4();
        System.out.println("End of Task");
        x = in.nextLine();
        System.out.println("==============================================");
        task5();
        System.out.println("End of Task");
        x = in.nextLine();
        System.out.println("==============================================");
        task6();
        System.out.println("End of Task");
        x = in.nextLine();
        System.out.println("==============================================");
        task7();
        System.out.println("End of Task");
        x = in.nextLine();
        System.out.println("==============================================");
        task8();
        System.out.println("End of Task");
        x = in.nextLine();
        System.out.println("==============================================");
        task9();
        System.out.println("End of Task");
        x = in.nextLine();
        System.out.println("==============================================");
        task10();
        System.out.println("End of Task");
        x = in.nextLine();
        System.out.println("==============================================");
        task11();
        System.out.println("End of Task");
        x = in.nextLine();
        System.out.println("==============================================");
        task12();
        System.out.println("End of Task");
        x = in.nextLine();
    }

    /**
     * Увести n рядків з консолі, знайти самий короткий рядок. Вивести цей рядок і її довжину.
     */
    public static void task1(){
        System.out.println("Input words");
        ArrayList<String> Arr = input(false, false);
        Arr.sort(Comparator.comparing(String::length));
        printStr(Arr.get(1));
        for(int i = 2; i<Arr.size(); i++) {
            if (Arr.get(1).length() == Arr.get(i).length())
                printStr(Arr.get(i));
            else
                break;
        }
    }

    /**
     * Увести n рядків з консолі. Упорядкувати й вивести рядки в порядку зростання їх довжин, а також (другий пріоритет) значень цих їхніх довжин.
     */

    public static void task2(){
        System.out.println("Input words");
        ArrayList<String> Arr = input(false, true);
        Arr.sort(Comparator.comparing(String::length));
        printArr(Arr);
    }

    /**
     * Увести n рядків з консолі. Вивести на консоль ті рядки, довжина яких менше середньої, також їх довжини.
     */

    public static void task3(boolean info){
        System.out.println("Input words");
        ArrayList<String> Arr = input(false, true);
        float average = 0;

        for(String line : Arr){
            average += line.length();
        }

        average /= (Arr.size()-1);// Последняя строка пустая, поэтому -1

        for(String line : Arr)
        {
            if(line.length() <= average)
                printStr(line);
        }

        if(info == true) {
            System.out.print("Average - ");
            System.out.println(average);
            System.out.print("Size of array - ");
            System.out.println(Arr.size()-1);
        }
    }

    /**
     * У кожному слові тексту k-ю букву замінити заданим символом. Якщо k більше довжини слова, коректування не виконувати.
     */

    public static void task4(){
        System.out.println("Input words");
        ArrayList<String> Arr = input(true, false);


        Scanner in = new Scanner(System.in);
        System.out.print("Input a number: ");


        int num = in.nextInt();
        System.out.print("Input a symbol: ");
        in.nextLine();
        String symbol = in.nextLine();



        String line = Arr.get(0);
        String[] words = line.split("\\s");
        String result = "";
        for(String str : words)
        {
            if(str.length() >= num) {
                StringBuffer buf = new StringBuffer(str);
                result += buf.replace(num-1,num,symbol) + " ";
            }else
            result += str + " ";
        }
        System.out.println(result);
    }

    /**
     * У російському тексті кожну букву замінити її номером в алфавіті. В одному рядку друкувати текст із двома пробілами між буквами, у наступному рядку внизу під кожною буквою друкувати її номер.
     */

    public static void task5(){
        System.out.println("Input words");
        ArrayList<String> Arr = input(true, true);
        String line = Arr.get(0);


        line = line.replaceAll(" ", ""); // убираем пробелы
        line.toLowerCase();
        for (char a : line.toCharArray()) {
            if (Character.UnicodeBlock.of(a) != Character.UnicodeBlock.CYRILLIC) {
                return;
            }
        }


        for (char c: line.toCharArray()) {
            System.out.print( c + "  ");
        }
        System.out.println();


        for (char c: line.toCharArray()) {

            if((int)c - 1071 == 34)
                System.out.print( 7 + "  ");

            else if((int)c - 1071 <= 6 )
                System.out.print( (int)c - 1071 + "  ");

            else if((int)c - 1071 < 9 )
                System.out.print( (int)c - 1070 + "  ");

            else
                System.out.print( (int)c - 1070 + " ");
        }


    }

    /**
     * З невеликого тексту вилучити всі символи, крім пробілів, що не є буквами. Між послідовностями букв, що підряд ідуть, залишити хоча б один пробіл.
     */

    public static void task6(){
        System.out.println("Input words");
        ArrayList<String> Arr = input(true, true);
        String line = Arr.get(0);

        line = line.replaceAll("[^a-zA-Z\\s?]","");
        for (char sym : line.toCharArray()) {
           System.out.print(sym);
        }
    }

    /**
     * З тексту вилучити всі слова заданої довжини, що починаються на згодну букву.
     */

    public static void task7() {
        System.out.println("Input words");
        ArrayList<String> Arr = input(true, true);

        System.out.print("Input length");
        Scanner in = new Scanner(System.in);
        int wLength = in.nextInt();
        String line = Arr.get(0);

        String[] words = line.split("\\s");
        String result = "";

        for (String word : words) {
            if( isConsonant(word.charAt(0)) && word.length() == wLength){ }else
                result = result.concat(word + ' ');
        }

        System.out.print(result);
    }

    /**
     * Знайти й надрукувати, скільки раз повторюється в тексті кожне слово.
     */

    public static void task8(){
        System.out.println("Input words");
        ArrayList<String> Arr = input(true, true);
        String line = Arr.get(0);

        String[] words = line.split("\\s");


        for(int i = 0; i < words.length; i++){


            String temp =  words[i];
            int amount = 1;
            words[i] = "0";
            for(int j = 0; j < words.length; j++){
                    if(words[j].equals(temp)){
                        if(!words[j].equals("0")) {
                            words[j] = "0";
                            amount += 1;
                        }
                    }
            }

            if(!temp.equals("0")) {
                System.out.print(temp + " - ");
                System.out.println(amount);
            }
        }
    }

    /**
     * Знайти, яких букв, голосних або згодних, більше в кожній пропозиції тексту.
     */

    public static void task9(){
        System.out.println("Determine the number of vowels and consonants");
        ArrayList<String> Arr = input(true, true);
        String line = Arr.get(0);
        line = line.replaceAll("[^a-zA-Z?]","");

        int ConsonantCounter = 0;
        int VowelCounter = 0;
        for(char sym : line.toCharArray()){
            if(isConsonant(sym))
                ConsonantCounter++;
            else
                VowelCounter++;

        }

        System.out.println("Consolas - " + ConsonantCounter);
        System.out.println("Vowel - " + VowelCounter);

    }

    /**
     * Вибрати три різні точки заданого на площині множини крапок, що становлять трикутник найбільшого периметра.
     */

    public static void task10(){
        System.out.println("Choose three points that define the triangle of the largest perimeter.");
        Point[] pArr = getPArr(true, 10);
        Point a = new Point(0,0);
        Point b = new Point(0,0);
        Point c = new Point(0,0);
        Double perimetr = 0.0;

        for(int i = 0; i<pArr.length; i++){


            for (int j = 0; j < pArr.length; j++){
                if(i != j){

                    for (int k = 0; k < pArr.length; k++){
                        if(k != j && k != i){

                            double temp =  getPerimetr(pArr[i], pArr[j], pArr[k]);

                            if(temp > perimetr && Math.round(getCos_vectors(pArr[i], pArr[j], pArr[k])) == 1){

                                a = pArr[i];
                                b = pArr[j];
                                c = pArr[k];

                                perimetr = temp;
                            }


                        }
                    }
                }
            }
        }
        System.out.println("Triangle (max perimetr):");

        System.out.println("{ " + a.getX() + ", " + a.getY() + "}");
        System.out.println("{ " + b.getX() + ", " + b.getY() + "}");
        System.out.println("{ " + c.getX() + ", " + c.getY() + "}");
        System.out.println("perimetr = " + perimetr);
    }

    /**
     * Знайти таку крапку заданого на площині множини крапок, сума відстаней від якої до інших мінімальна.
     */

    public static void task11(){
        System.out.println("Find the point the sum of the distances from which to others is the minimum");
        Point[] pArr = getPArr(true, 10);
        Point point = new Point(0,0);

        Double sum = Double.MAX_VALUE;

        for(int i = 0; i < pArr.length; i++){

            double temp = Double.MIN_VALUE;
            for (int j = 0; j <pArr.length; j++){

                 temp += getDistance(pArr[i],pArr[j]);

            }
            if(temp < sum){
                point = pArr[i];
                sum = temp;
            }
        }

        System.out.println("Point {" + point.x + ", " + point.y + "}   sum = " + sum);

    }

    /**
     * Опуклий багатокутник заданий на площині перерахуванням   координат вершин у порядку обходу його границі. Визначити площа багатокутника.
     */

    public static void task12(){
        System.out.println("Determine the area of the polygon");
        Point[] pArr = new Point[5];
        pArr[0] = new Point(10,1);
        pArr[1] = new Point(10,5);
        pArr[2] = new Point(5,5);
        pArr[3] = new Point(5,1);
        pArr[4] = new Point(10,1);

        double area = 0.0;
        for(int i = 0; i < pArr.length - 1; i++){
            area += pArr[i].getX() * pArr[i+1].getY();
            area -= pArr[i].y * pArr[i+1].getX();
        }
        area /= 2;
        System.out.println(area);
    }



    private static ArrayList<String> input(boolean once, boolean print){

        ArrayList<String> Arr = new ArrayList<String>();


        Scanner in = new Scanner(System.in);
        if(once == false) {
            System.out.println("enter \"[empty]\" to exit");

            while (true) {
                String line = in.nextLine();
                Arr.add(line);


                if (line.isEmpty()) {
                    break;
                }
            }
        }else
        {
            String line = in.nextLine();
            Arr.add(line);
        }
        //in.close();


        if(print == true) {
            for (String str : Arr) {
                System.out.println(str);
            }
        }

        return Arr;
    }

    private static void printStr(String line){
        if(line.isEmpty())
            return;

        System.out.print(line);
        System.out.print(" - ");
        System.out.println(line.length());
    }

    private static void printArr(ArrayList<String> Arr) {
        for(String line : Arr)
        {
           if (!line.isEmpty())
           printStr(line);
        }
    }

    private static boolean isConsonant(char symbol){
        symbol = Character.toLowerCase(symbol);
        if (symbol == 'a' || symbol == 'e' || symbol == 'i' || symbol == 'o' || symbol == 'u')
            return false;

        return true;
    }

    private static Point[] getPArr(boolean print, int amount){
        Point[] pArr = new Point[amount];
        for(int i = 0; i < pArr.length; i++){
            //pArr[i] = new Point(1+i, 2+i);

           pArr[i] = new Point((int)(Math.random() * ((10 - 0) + 1)) + 0,
                                (int)(Math.random() * ((10 - 0) + 1)) + 0);
        }

        if (print == true){
            for (Point p : pArr) {
                System.out.print("{" + p.x +", "+ p.y + "},  ");
            }
            System.out.println();
        }
        return pArr;
    }

    private static double getDistance(Point a, Point b){
        return Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2));
    }

    private static double getArea(Point a, Point b, Point c){
         double perimetr = (getDistance(a,b) +
                getDistance(a,c) +
                getDistance(b,c)) / 2;
         double area = Math.sqrt(
                 perimetr *
                 (perimetr - getDistance(a,b)) *
                 (perimetr - getDistance(a,c)) *
                 (perimetr - getDistance(b,c))
         );
        return area;
    }

    private static double getPerimetr(Point a, Point b, Point c){

        return getDistance(a, b) + getDistance(a, c) + getDistance(b, c);
    }

    private static double getCos_vectors(Point a, Point b, Point c){


        Point v1 = new Point(b.x - a.x, b.y - a.y);
        Point v2 = new Point(c.x - a.x, c.y - a.y);

        return  (v1.getX()*v2.getX() + v1.getY()*v2.getY()) /
                     ( Math.sqrt(Math.pow(v1.getX(),2) + Math.pow(v1.getY(),2)) *
                     Math.sqrt(Math.pow(v2.getX(),2) + Math.pow(v2.getY(),2)) );

    }

}
