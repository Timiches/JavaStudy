import javax.swing.*;
public class HelloWorld{
  public static void main(String[] args){
     String name;
      name = JOptionPane.showInputDialog("whats your name?");
      JOptionPane.showMessageDialog(null, "Hello, "+ name +"!");
  }
}