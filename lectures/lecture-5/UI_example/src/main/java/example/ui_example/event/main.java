/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.ui_example.event;

/**
 *
 * @author evgeniy.kozinov
 */
public class main {
  public static void main(String[] args) {
    Model m = new Model();
    m.addOblerver(new MyObserver(m, "Observer 1 :"));
    m.addOblerver(new IObserver() {
      @Override
      public void event() {
        System.out.println("Observer 2 : " + m.getText());
      }
    });
    
    m.setText("Hello 1!");
    m.setText("Hello 2!");
  }
}
