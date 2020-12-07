/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.ui_example.simple;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author evgeniy.kozinov
 */
public class SimpleLable {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    JLabel lable = new JLabel();
    frame.setSize(300, 200);
    frame.setLocation(200, 100);
    frame.setTitle("Hello!");
    lable.setText("Hello, JLable!");
    frame.add(lable);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
