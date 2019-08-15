package ch.unibas.informatik.jturtle.commands;

import ch.unibas.informatik.jturtle.graphics.TurtleInterpreter;

public class SetHeadingCommand implements TurtleCommand {

  int angle = 0;
  public SetHeadingCommand(int angle) {
    this.angle = angle;
  }

  public int getAngle() {
    return this.angle;
  }

  @Override
  public void interpret(TurtleInterpreter interpreter) {
    interpreter.interpretSetHeading(this);
  }
}
