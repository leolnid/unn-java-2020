/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.ui_example.event;

import java.util.ArrayList;

/**
 *
 * @author evgeniy.kozinov
 */
public class Model {
  String s = "";
  ArrayList<IObserver> arr_observer = new ArrayList<>();
  public void addOblerver(IObserver e)
  {
    arr_observer.add(e);
  }
  void eventNotification()
  {
    for (IObserver iObserver : arr_observer) {
      iObserver.event();
    }
  }

  public String getText() {
    return s;
  }
  
  public void setText(String s) {
    this.s = s;
    eventNotification();
  }  
}
