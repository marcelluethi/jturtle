package ch.unibas.informatik.jturtle.commands;

import ch.unibas.informatik.jturtle.graphics.TurtleInterpreter;

public class ClearCommand implements TurtleCommand {
  @Override
  public void interpret(TurtleInterpreter interpreter) {
    interpreter.interpretClear(this);
  }
}
