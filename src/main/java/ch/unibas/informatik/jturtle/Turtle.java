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
    this.commands.addLast(new PenDown());
  }

  public void penUp() { this.commands.addLast(new PenUp());}

  public void move(long distance) {
    this.commands.addLast(new Move(distance));
  }

  public void turn(double angle) {
    this.commands.addLast(new Turn(angle));
  }

  public void penColor(Color color) {this.commands.addLast(new PenColor(color));}

  public void penSize(int size) {this.commands.addLast(new PenSize(size)); }

  public BufferedImage toImage() {
    ImageTurtleInterpreter interpreter = new ImageTurtleInterpreter();
    return interpreter.runTurtle(this, commands);
  }


}
