package ch.unibas.informatik.jturtle.interpreters;

import ch.unibas.informatik.jturtle.common.PenState;
import ch.unibas.informatik.jturtle.common.Point;
import ch.unibas.informatik.jturtle.common.Utils;
import ch.unibas.informatik.jturtle.common.Vector;

import java.awt.*;
import java.util.LinkedList;
import java.util.function.Consumer;

public class DefaultTurtle implements Turtle {

  private Point currentPosition = new Point(0, 0);
  private double angleInDegree = 0.0;
  private Color penColor = null;
  private int penSize = 0;
  private PenState penState = PenState.PEN_DOWN;

  private LinkedList<TurtleEventListener> listeners = new LinkedList<>();

  public DefaultTurtle() {
    reset();
  }

  public void addListener(TurtleEventListener listener) {
    listeners.add(listener);
  }

  @Override
  public Point getPosition() {
    return currentPosition;
  }

  @Override
  public Color getPenColor() {
    return penColor;
  }

  @Override
  public PenState getPenState() {
    return penState;
  }

  @Override
  public double getHeading() {
    return angleInDegree;
  }

  @Override
  public int getPenSize() {
    return penSize;
  }


  @Override
  public void reset() {
    this.penColor = Color.BLACK;
    this.penSize = 2;
    this.penState = PenState.PEN_DOWN;
    publish(listener -> listener.penColorChanged());
    publish(listener -> listener.penSizeChanged());
    publish(listener -> listener.penStateChanged());
    home();
  }

  @Override
  public void home() {
    PenState backupState = penState;
    setPenState(PenState.PEN_UP);
    this.currentPosition = new Point(0,0);
    this.angleInDegree = 0.0;
    publish(listener -> listener.positionChanged());
    publish(listener -> listener.headingChanged());
    setPenState(backupState);
  }

  @Override
  public void move(double distance) {

    double degreeInRad = Utils.degreeToRad(angleInDegree);

    int sign = distance >= 0 ? 1 : -1;

    // step of one unit in the direction, adjusted by direction
    Vector v = new Vector(Math.sin(degreeInRad), Math.cos(degreeInRad)).times(sign);

    for (int i = 0; i < Math.abs(distance); i++) {
        this.currentPosition = this.currentPosition.plus(v);
        publish(listener -> listener.positionChanged());

    }
    double remainingDistance =  Math.abs(distance - Math.floor(distance));
    this.currentPosition = this.currentPosition.plus(v.times(remainingDistance));
  }

  @Override
  public void goTo(Point point) {
    this.currentPosition = point;
    publish(listener -> listener.positionChanged());
  }


  @Override
  public void setPenColor(Color color) {
    this.penColor = color;
    publish(listener -> listener.penColorChanged());
  }

  @Override
  public void setPenState(PenState penState) {
    this.penState = penState;
    publish(listener -> listener.penStateChanged());
  }


  @Override
  public void setPenSize(int size) {
    this.penSize = size;
    publish(listener -> listener.penSizeChanged());
  }

  @Override
  public void setPosition(Point pos) {

    this.currentPosition = pos;
  }

  @Override
  public void setHeading(double angleInDegree) {
    this.angleInDegree = angleInDegree;
    publish(listener -> listener.headingChanged());
  }


  @Override
  public void turn(double angleInDegree) {
    // turn clockwise
    this.angleInDegree = Utils.normalizeAngle(this.angleInDegree + angleInDegree);
    publish(listener -> listener.penSizeChanged());
  }

  private void publish(Consumer<TurtleEventListener> listenerCall) {
    for (TurtleEventListener listener : listeners) {
      listenerCall.accept(listener);
    }

  }

}
