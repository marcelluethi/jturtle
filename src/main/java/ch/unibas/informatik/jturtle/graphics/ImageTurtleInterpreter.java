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
  private Point currentPosition = new Point(0, 0);
  private double angleDegree = 0.0;



  private BufferedImage image =  new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
  private Graphics2D graphics = image.createGraphics();


  public BufferedImage runTurtle(Turtle turtle, List<TurtleCommand> commands) {
    for (TurtleCommand command : commands) {
      command.interpret(this);
    }
    return image;
  }


  @Override
  public void interpretMove(MoveCommand moveCommand) {

    // computing new point position
    long newX = Math.round(currentPosition.getX() + Math.sin(degreeToRad(angleDegree)) * moveCommand.getDistance());
    long newY = Math.round(currentPosition.getY() - Math.cos(degreeToRad(angleDegree)) * moveCommand.getDistance());
    Point newPosition = new Point((int) newX, (int) newY);

    Point startPosInWindow = turtleToImageCoordinate(currentPosition);
    Point endPosInWindow = turtleToImageCoordinate(newPosition);

    Point newDrawingEndPoint = pointInCanvas(newPosition);

    if (isPenDown) {
      graphics.drawLine(startPosInWindow.getX(), startPosInWindow.getY(), endPosInWindow.getX(), endPosInWindow.getY());
    }

    currentPosition = newPosition;
  }

  @Override
  public void interpretPenColor(PenColorCommand pencolor) {
    graphics.setColor(pencolor.getPenColor());
  }

  @Override
  public void interpretPenState(PenStateCommand penStateCommand) {
    isPenDown = penStateCommand.isPenDown();
  }


  @Override
  public void interpretPenSize(PenSizeCommand penSizeCommand) {
    graphics.setStroke(new BasicStroke(penSizeCommand.getSize()));
  }

  @Override
  public void interpretSetPosition(SetPositionCommand pos) {

    this.currentPosition = pos.getPosition();
  }

  @Override
  public void interpretSetHeading(SetHeadingCommand heading) {
    this.angleDegree = heading.getAngle();
  }

  @Override
  public void interpretTurn(TurnCommand turnCommand) {
    // turn clockwise
    this.angleDegree = clampAngle(this.angleDegree + turnCommand.getAngle());
  }

  @Override
  public void interpretCircle(CircleCommand cmd) {
    if (isPenDown) {
      Point posInImageCoordinates = turtleToImageCoordinate(this.currentPosition);
      graphics.drawOval(
          posInImageCoordinates.getX() - cmd.getRadius() ,
          posInImageCoordinates.getY() - cmd.getRadius() ,
          cmd.getRadius() * 2,
          cmd.getRadius() * 2
      );
    }
  }

  private static double degreeToRad(double degree) {
    return degree / 360.0 * Math.PI * 2.0;
  }

  private Point pointInCanvas(Point point) {

    int x = Math.max(0, Math.min(WIDTH, point.getX()));
    int y = Math.max(0, Math.min(HEIGHT, point.getY()));
    return new Point(x, y);
  }

  private Point turtleToImageCoordinate(Point point) {
    return new Point(point.getX() + WIDTH / 2, point.getY() + HEIGHT / 2);
  }

  private double clampAngle(double angle) {
    double sanitizedAngle = ((angle % 360) + 360) % 360 ;

    if (sanitizedAngle > 180)
      return sanitizedAngle;
    else
      return sanitizedAngle - 360;
  }
}
