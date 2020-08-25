package ch.unibas.informatik.jturtle.common;

/**
  * Representation of a (Geometric-) Vector
  */
public class Vector {
  double x;
  double y;

  public Vector(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public Vector times(double s) {
    return new Vector(x * s, y * s);
  }
}