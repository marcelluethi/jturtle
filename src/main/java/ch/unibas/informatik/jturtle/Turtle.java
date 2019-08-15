/*
 * Copyright 2019 University of Basel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.unibas.informatik.jturtle;

import ch.unibas.informatik.jturtle.commands.*;
import ch.unibas.informatik.jturtle.graphics.ImageTurtleInterpreter;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.awt.*;

public class Turtle {
  private LinkedList<TurtleCommand> commands = null;

  public Turtle() {
    this.commands = new LinkedList<>();
  }

  public void penDown() {
    this.commands.addLast(new PenStateCommand(true));
  }

  public void penUp() { this.commands.addLast(new PenStateCommand(false));}

  public void forward(int distance) {
    this.commands.addLast(new MoveCommand(distance));
  }

  public void backward(int distance) {
    this.commands.addLast(new MoveCommand(-distance));
  }

  public void home() {
    this.commands.addLast(new SetPositionCommand(0, 0));
    this.commands.addLast(new SetHeadingCommand(0));
  }

  public void turn(double angle) {
    this.commands.addLast(new TurnCommand(angle));
  }

  public void circle(int radius) {
    this.commands.addLast(new CircleCommand(radius));
  }

  public void penColor(Color color) {this.commands.addLast(new PenColorCommand(color));}

  public void penSize(int size) {this.commands.addLast(new PenSizeCommand(size)); }

  public BufferedImage toImage() {
    ImageTurtleInterpreter interpreter = new ImageTurtleInterpreter();
    return interpreter.runTurtle(this, commands);
  }


}
