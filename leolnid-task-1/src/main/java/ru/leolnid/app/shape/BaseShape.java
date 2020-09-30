package ru.leolnid.app.shape;

import ru.leolnid.app.interfaces.Shape;

public abstract class BaseShape extends Object implements Shape {
  private Point[] points;

  protected BaseShape(Point[] points) {
    this.points = points;
  }

  @Override
  public float getVolume() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public float getPerimetr() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public float getAcreage() {
    // TODO Auto-generated method stub
    return 0;
  }
}
