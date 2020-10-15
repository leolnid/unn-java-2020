package ru.leolnid.app.shape;

public class Point {
  protected double x;
  protected double y;

  Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  Point() {
    this(0f, 0f);
  }

  public Point add(Point other) {
    return new Point(this.x + other.x, this.y + other.y);
  }

  public Point sub(Point other) {
    return new Point(this.x - other.x, this.y - other.y);
  }

  public Point mul(double n) {
    return new Point(this.x * n, this.y * n);
  }

  public Point reverse() {
    return new Point(-this.x, -this.y);
  }

  public double getDistance(Point other) {
    return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
  }
}
