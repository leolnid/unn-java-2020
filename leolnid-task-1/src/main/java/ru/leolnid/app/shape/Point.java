package ru.leolnid.app.shape;

public class Point {
  protected float x;
  protected float y;
  protected float z;

  Point(float x, float y, float z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  Point() {
    this(0f, 0f, 0f);
  }

  public Point add(Point other) {
    return new Point(this.x + other.x, this.y + other.y, this.z + other.z);
  }

  public Point sub(Point other) {
    return new Point(this.x - other.x, this.y - other.y, this.z - other.z);
  }

  public Point mul(float n) {
    return new Point(this.x * n, this.y * n, this.z * n);
  }

  public Point reverse() {
    return new Point(-this.x, -this.y, -this.z);
  }

}
