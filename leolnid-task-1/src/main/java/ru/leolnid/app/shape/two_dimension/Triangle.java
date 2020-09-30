package ru.leolnid.app.shape.two_dimension;

import ru.leolnid.app.shape.BaseShape;
import ru.leolnid.app.shape.Point;

public class Triangle extends BaseShape {
  Triangle(Point a, Point b, Point c) {
    super(new Point[] { a, b, c });
  }
}
