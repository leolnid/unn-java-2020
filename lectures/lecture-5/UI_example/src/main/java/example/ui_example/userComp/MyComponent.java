/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.ui_example.userComp;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author evgeniy.kozinov
 */
public class MyComponent extends JPanel{
  String msg;
  JLabel l;
  JButton b;
  public MyComponent(String msg) {
    this.msg = msg;
    this.setLayout(null);
    b = new JButton();
    b.setText("clik!");
    b.setSize(100, 20);
    b.setLocation(5, 5);
    b.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if(l!=null)l.setText("Msg + " + msg);
      }
    });
    this.add(b);
    this.setBackground(Color.white);
    this.setSize(110, 30);
  }
  public void init(JLabel l) {
    this.l = l;
  }
}
