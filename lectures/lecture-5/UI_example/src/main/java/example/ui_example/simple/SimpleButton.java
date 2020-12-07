/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.ui_example.simple;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author evgeniy.kozinov
 */
public class SimpleButton {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    JLabel lable = new JLabel();
    JButton button = new JButton();
    frame.setSize(300, 200);
    frame.setLocation(200, 100);
    frame.setTitle("Hello!");
    frame.setLayout(null);
    frame.add(button);
    frame.add(lable);
    button.setText("clik!");
    lable.setSize(100, 20);
    button.setSize(100, 20);
    lable.setLocation(0, 20);
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        lable.setText("Button cliked!");
      }
    });
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
