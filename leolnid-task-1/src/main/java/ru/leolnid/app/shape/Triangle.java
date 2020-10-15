package ru.leolnid.app.shape;

public class Triangle {
  public enum Type {
    ACUTE, OBTUSE, STRAIGHT, INVALID
  }

  protected Point a;
  protected Point b;
  protected Point c;

  protected double ab;
  protected double ac;
  protected double bc;

  protected Type type;

  public Triangle() throws Exception {
    this(new Point(), new Point(), new Point());
  }

  public Triangle(Point a, Point b, Point c) throws Exception {
    this.a = a;
    this.b = b;
    this.c = c;

    this.ab = a.getDistance(b);
    this.ac = a.getDistance(c);
    this.bc = b.getDistance(c);

    this.type = this.checkTriangle();
  }

  public Triangle(double ab, double bc, double ac) throws Exception {
    this.a = new Point();
    this.b = new Point(0, ab);

    double cosCAB = (ab * ab + ac * ac - bc * bc) / (2 * ab * ac);
    double sinCAB = 1 - cosCAB * cosCAB;

    this.c = new Point(ac * cosCAB, ac * sinCAB);

    this.ab = ab;
    this.ac = ac;
    this.bc = bc;

    this.type = this.checkTriangle();
  }

  public Type getType() {
    return type;
  }

  private interface Operation {
    boolean apply(double a, double b, double c);
  }

  private boolean checkTriangleI(double a, double b, double c, Operation op) {
    return op.apply(a, b, c) || op.apply(a, c, b) || op.apply(b, c, a);
  }

  private Type checkTriangle() throws Exception {
    if (a.equals(b) || b.equals(c) || c.equals(a))
      throw new Exception("Invalid sides length");

    if (checkTriangleI(ab, bc, ac, (a1, a2, a3) -> a1 + a2 <= a3))
      throw new Exception("Invalid sides length");

    double ab2 = this.ab * this.ab;
    double bc2 = this.bc * this.bc;
    double ac2 = this.ac * this.ac;

    if (checkTriangleI(ab2, bc2, ac2, (a1, a2, a3) -> a1 + a2 == a3))
      return Type.STRAIGHT;

    if (checkTriangleI(ab2, bc2, ac2, (a1, a2, a3) -> a1 + a2 < a3))
      return Type.OBTUSE;

    return Type.ACUTE;
  }
}
