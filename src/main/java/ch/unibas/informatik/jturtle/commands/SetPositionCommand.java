package ch.unibas.informatik.jturtle.commands;

import ch.unibas.informatik.jturtle.common.Point;
import ch.unibas.informatik.jturtle.graphics.TurtleInterpreter;

public class SetPositionCommand implements TurtleCommand {

  Point point = null;

  public SetPositionCommand(int x, int y) {
    this.point = new Point(x, y);
  }

  public Point getPosition() {
    return this.point;
  }

  @Override
  public void interpret(TurtleInterpreter interpreter) {
    interpreter.interpretSetPosition(this);
  }
}
