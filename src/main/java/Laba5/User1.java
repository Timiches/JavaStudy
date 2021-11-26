package Laba5;

import Laba4.Main_lab4;

import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class User1 {

    static public void main(String[] str)throws InterruptedException{
        System.out.println("Connecting...");
        Scanner in = new Scanner(System.in);
        
        try(
                Socket socket = new Socket("localhost", 8030);
                BufferedReader console = new BufferedReader(new InputStreamReader(System.in));//Канал прослушки консоли
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ){
            System.out.println("Connected!");
            while(true) {
                if(reader.ready()){
                    String response = reader.readLine();
                    System.out.println(response);
                } else if(console.ready()){
                    String string = in.nextLine();
                    writer.write(string);
                    writer.newLine();
                    writer.flush();

                    if(string.equals("quit")){

                        System.out.println("Client kill connections");
                        Thread.sleep(2000);
                        //                            if(reader.read() > -1)     {
                        //                                System.out.println("reading...");
                        String request = reader.readLine();
                        System.out.println(request);
                        //                            }
                        break;
                    }
                }
            }
        }catch(IOException e){
            System.out.print("Error = " + e);
        }
        
    }

    private void UserWindow(){
        JFrame jFrame = Main_lab4.newFrame();
    }

}
