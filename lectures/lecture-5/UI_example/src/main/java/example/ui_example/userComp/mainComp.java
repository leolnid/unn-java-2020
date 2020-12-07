/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.ui_example.userComp;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author evgeniy.kozinov
 */
public class mainComp {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setSize(300, 200);
    frame.setLocation(200, 100);
    frame.setTitle("Hello!");
    frame.setLayout(null);
 
    JLabel lable = new JLabel();
    lable.setSize(100, 20);
    lable.setLocation(0, 0);
    frame.add(lable);
    
    MyComponent c1 = new MyComponent("Btn1");
    c1.init(lable);
    c1.setLocation(0,20);
    frame.add(c1);
    
    MyComponent c2 = new MyComponent("Btn2");
    c2.init(lable);
    c2.setLocation(0,60);
    frame.add(c2);
    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  } 
}
