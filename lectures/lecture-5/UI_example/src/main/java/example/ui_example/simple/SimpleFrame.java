/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.ui_example.simple;

import javax.swing.JFrame;

/**
 *
 * @author evgeniy.kozinov
 */
public class SimpleFrame {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setSize(300, 200);
    frame.setLocation(200, 100);
    frame.setTitle("Hello!");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
