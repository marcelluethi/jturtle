package ch.unibas.informatik.jturtle.commands;

import ch.unibas.informatik.jturtle.graphics.TurtleInterpreter;

public class CircleCommand implements TurtleCommand {

  private int radius = 0;

  public CircleCommand(int radius) {
    this.radius = radius;
  }

  public int getRadius() { return this.radius; }

  @Override
  public void interpret(TurtleInterpreter interpreter) {
    interpreter.interpretCircle(this);
  }
}
