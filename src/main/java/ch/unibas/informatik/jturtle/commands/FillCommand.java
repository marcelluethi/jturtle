package ch.unibas.informatik.jturtle.commands;

import ch.unibas.informatik.jturtle.graphics.TurtleInterpreter;

public class FillCommand implements TurtleCommand {
  @Override
  public void interpret(TurtleInterpreter interpreter) {
    interpreter.interpretFill(this);
  }
}
