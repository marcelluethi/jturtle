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
package ch.unibas.informatik.jturtle.graphics;

import ch.unibas.informatik.jturtle.Turtle;
import ch.unibas.informatik.jturtle.commands.*;
import ch.unibas.informatik.jturtle.common.Point;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class ImageTurtleInterpreter implements TurtleInterpreter {

  private static final int WIDTH = 512;
  private static final int HEIGHT = 512;

  private boolean isPenDown = false;
  private Point currentPosition = new Point(WIDTH / 2, HEIGHT / 2);
  private Color penColor = Color.BLACK;
  private double angleRad = 0.0;
  private int penSize = 1;


  private BufferedImage image =  new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
  private Graphics2D graphics = image.createGraphics();


  public BufferedImage runTurtle(Turtle turtle, List<TurtleCommand> commands) {
    for (TurtleCommand command : commands) {
      command.interpret(this);
    }
    return image;
  }


  @Override
  public void interpretMove(Move move) {

    // computing new point position
    long newX = Math.round(currentPosition.getX() + Math.sin(angleRad) * move.getDistance());
    long newY = Math.round(currentPosition.getY() - Math.cos(angleRad) * move.getDistance());

    Point newPosition = new Point((int) newX, (int) newY);
    Point newDrawingEndPoint = pointInCanvas(newPosition);
    graphics.setColor(penColor);
    graphics.setStroke(new BasicStroke(penSize));

    if (isPenDown) {
      graphics.drawLine(currentPosition.getX(), currentPosition.getY(), newDrawingEndPoint.getX(), newDrawingEndPoint.getY());
    }

    currentPosition = newPosition;
  }

  @Override
  public void interpretPenColor(PenColor pencolor) {penColor = pencolor.getPenColor();}

  @Override
  public void interpretPenDown(PenDown penDown) {
    isPenDown = true;
  }

  @Override
  public void interpretPenUp(PenUp penUp) {
    isPenDown = false;
  }

  @Override
  public void interpretPenSize(PenSize penSize) {
    this.penSize = penSize.getSize();
  }

  @Override
  public void interpretTurn(Turn turn) {
    // turn clockwise
    this.angleRad = (this.angleRad + degreeToRad(turn.getAngle())) % (2.0 * Math.PI);
  }



  private static double degreeToRad(double degree) {
    return degree / 360.0 * Math.PI * 2.0;
  }

  private Point pointInCanvas(Point point) {

    int x = Math.max(0, Math.min(WIDTH, point.getX()));
    int y = Math.max(0, Math.min(HEIGHT, point.getY()));
    return new Point(x, y);
  }

}
