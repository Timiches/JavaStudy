package Laba3;

import java.util.*;
import java.io.*;

public class Laba3 {
    public static void main(String[] args) throws Exception {
        
        //question1();
        //question2();
        //question3();
        //question4();
        //question5();
        //question6();
        question7();
        
       
    }
    
    static void question1() throws Exception {
        FileReader fr = new FileReader("C:\\Users\\111\\Documents\\NetBeansProjects\\FirstProject\\src\\main\\java\\Laba3\\Text1.txt");
        Scanner scan= new Scanner(fr);
        
        FileWriter fw = new FileWriter("C:\\Users\\111\\Documents\\NetBeansProjects\\FirstProject\\src\\main\\java\\Laba3\\Text2.txt");
        
        Stack stack = new Stack();
        if (stack.empty()){
            System.out.println("Stack is clear");
        } else stack.clear();
        
        while(scan.hasNextLine()){
            stack.push(scan.nextLine());
        }
        
        System.out.println("wrote to stack");
        
        while(!stack.empty()){
            fw.write((String) stack.pop());
            fw.write(" \n");
        }
        
        fr.close();
        fw.close();
    }
    
    static void question2() {
        Stack stack = new Stack();
        
        String numbers = "1234567890";
        char[] array = numbers.toCharArray();
        for(int i = 0; i < array.length; i++){
            stack.push(array[i]);
        }
        
        while(!stack.empty()){
            System.out.print(stack.pop());            
        }
    }
    
    static void question3(){
        Map<Integer, Integer> hashmap1 = new HashMap<>();
        Map<Integer, Integer> hashmap2 = new HashMap<>();
        Scanner in = new Scanner(System.in);
        
        System.out.println("Write a max degree of X");
        int FirDegree = in.nextInt();
        
        for (int i=0;i<=FirDegree;i++){
            System.out.println("X^"+i);
            hashmap1.put(i,in.nextInt());
        }
        
//        Проверка
//        System.out.println(hashmap1.get(0)+"X^0\r");
//        for (int i=1;i<=FirDegree;i++){
//            System.out.println(" + "+hashmap1.get(i)+"X^"+i+"\r");
//        }
//        System.out.println(hashmap1.get(8)+"X^8\r");
        
        System.out.println("Write a max degree of X of second polinom");
        int SecDegree = in.nextInt();
        
        for (int i=0;i<=SecDegree;i++){
            System.out.println("X^"+i);
            hashmap2.put(i,in.nextInt());
        }
        
        int degree;
        if(FirDegree>=SecDegree) degree =  FirDegree;
        else degree = SecDegree;
        
        int sum;
        sum = hashmap1.get(0)+hashmap2.get(0);
        System.out.println(sum+"X^0");
        for (int i=0;i<=degree;i++){
            
            if(hashmap1.get(i)==null) hashmap1.put(i,0);
            else if(hashmap2.get(i)==null) hashmap2.put(i,0);
            
            sum = hashmap1.get(i)+hashmap2.get(i);
            System.out.println(" + "+sum+"X^"+i+"");
        }
        
        in.close();
    }
    
    static void question4(){
        Stack stack = new Stack();
        // определяем объект для каталога
        File dir = new File("C:\\Users\\111\\Documents\\NetBeansProjects\\FirstProject\\src\\main\\java");
        // если объект представляет каталог
        if(dir.isDirectory())
        {
            // получаем все вложенные объекты в каталоге
            for(File item : dir.listFiles()){
                stack.push(item.getName());      
            }
//            while(!stack.empty()){
//                System.out.println(stack.pop());
//            }    
        }     
    }
    
    static void question5(){
        ArrayList<Integer> arrayList = new ArrayList();
        arrayList.add(5);
        arrayList.add(-10);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(-4);
        
        int temp;
       
//        Первый вариант
//                
//        for(int i = 0; i<=arrayList.size()-1; i++){
//            if(arrayList.get(i)<0){
//                temp = arrayList.get(i);
//                arrayList.remove(i);
//                arrayList.add(0, temp);
//            } else {
//                temp = arrayList.get(i);
//                arrayList.remove(i);
//                arrayList.add(arrayList.size(), temp);
//            }
//        }

//        Второй вариант(удивительно, но работает)
        
        for(int i = 0; i<=arrayList.size()-1; i++){
            if(arrayList.get(i)>=0){
                temp = arrayList.get(i);
                arrayList.remove(i);
                arrayList.add(arrayList.size(), temp);
            } else {i--;i--;};
        }
        
        //Check
        for (int i = 0; i < arrayList.size(); i++)
            System.out.println(arrayList.get(i));
    }
    
//    static void question6(){
//        Scanner scan = new Scanner(System.in);
//        Stack stack = new Stack();
//        
//        System.out.println("Write first number");
//        stack.push(scan.next());
//        
//        System.out.println("Write second number");
//        stack.push(scan.next());
//        
//        System.out.println("Write an operator");
//        stack.push(scan.next());
//
//        
//        scan.close();
//        int fir = Integer.parseInt(stack.pop());
//        //char oper = stack.pop().charAt(1);
//        int sec = Integer.parseInt(stack.pop());
//        int sum = 0;
//        char oper = 1;
//        
//        
//        switch(stack.pop()){
//            case "+":
//                sum = fir + sec;
//                
//                break;
//            case "-":
//                sum = fir - sec;
//                break;
//            case "*":
//                sum = fir * sec;
//                break;
//            case "/":
//                sum = fir / sec;
//                break;
//            default:
//                System.out.println("I dont know this operator");
//                System.exit(0);
//        }
//        stack.push(Integer.toString(sum));
//        stack.push("=");
//        stack.push(Integer.toString(sec));
//        stack.push(Character.toString(oper));
//        stack.push(Integer.toString(fir));
//        
////        Проверка
//        while(!stack.empty()){
//            System.out.println(stack.pop());            
//        }
//    }
    
    
    static void question7(){
        ArrayList<Integer> arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        
        int sum = 0;
        
        ListIterator<Integer> listIter = arrayList.listIterator();
         
        while(listIter.hasNext()){
            sum = sum + listIter.next();
        }
        
        System.out.print(sum);
    }
    
}
