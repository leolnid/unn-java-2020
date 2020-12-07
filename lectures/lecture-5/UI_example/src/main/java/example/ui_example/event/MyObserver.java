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
public class MyObserver implements IObserver{
  String s;
  Model m;
  public MyObserver(Model m, String name) {
    this.m = m;
    this.s = name;
  }

  @Override
  public void event() {
    System.out.println(s + " " + m.getText());
  }
}
