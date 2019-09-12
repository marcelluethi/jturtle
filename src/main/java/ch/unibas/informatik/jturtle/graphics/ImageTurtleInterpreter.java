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
import java.util.LinkedList;
import java.util.List;

public class ImageTurtleInterpreter implements TurtleInterpreter {

  private static final int WIDTH = 1024;
  private static final int HEIGHT = 1024 ;


  private boolean isPenDown = false;
  private Point currentPosition = new Point(0, 0);
  private double angleDegree = 0.0;
  private Color penColor = Color.BLACK;
  private int penSize = 1;


  private BufferedImage image =  null;
  private Graphics2D graphics = null;


  public ImageTurtleInterpreter()  {
    this.initialize();
  }

  public BufferedImage runTurtle(Turtle turtle, List<TurtleCommand> commands) {
    for (TurtleCommand command : commands) {
      command.interpret(this);
    }
    return image;
  }


  @Override
  public void interpretClear(ClearCommand clearDommand) {
    this.initialize();
  }

  @Override
  public void interpretMove(MoveCommand moveCommand) {

    // computing new point position
    double newX = currentPosition.getX() + Math.sin(degreeToRad(angleDegree)) * moveCommand.getDistance();
    double newY = currentPosition.getY() - Math.cos(degreeToRad(angleDegree)) * moveCommand.getDistance();
    Point newPosition = new Point(newX, newY);

    ScreenPoint startPosInWindow = turtleToImageCoordinate(currentPosition);
    ScreenPoint endPosInWindow = turtleToImageCoordinate(newPosition);

    if (isPenDown) {
      graphics.setColor(this.penColor);
      graphics.setStroke(new BasicStroke(this.penSize));
      graphics.drawLine(startPosInWindow.getX(), startPosInWindow.getY(), endPosInWindow.getX(), endPosInWindow.getY());
    }

    currentPosition = newPosition;
  }

  @Override
  public void interpretPenColor(PenColorCommand penColorCommand) {
    this.penColor = penColorCommand.getPenColor();
  }

  @Override
  public void interpretPenState(PenStateCommand penStateCommand) {
    isPenDown = penStateCommand.isPenDown();
  }


  @Override
  public void interpretPenSize(PenSizeCommand penSizeCommand) {
    this.penSize = penSizeCommand.getSize();
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
  public void interpretFill(FillCommand fillCommand) {
    ScreenPoint screenPoint = turtleToImageCoordinate(this.currentPosition);

    int colorToFill = this.image.getRGB(screenPoint.x, screenPoint.y);

    // in case the current color corresponds to the one we stand at, there is nothing to do,
    // as filling would only color all pixels which anyway have this color.
    if (colorToFill == this.penColor.getRGB()) { return; }

    LinkedList<ScreenPoint> q = new LinkedList<ScreenPoint>();
    q.addLast(screenPoint);
    while (!q.isEmpty()) {
      ScreenPoint position = q.removeFirst();

      if ((position.x >= 0 && position.x < WIDTH && position.y >= 0 && position.y < HEIGHT) &&
          this.image.getRGB(position.x, position.y) == colorToFill) {

        this.image.setRGB(position.x, position.y, this.penColor.getRGB());

        for (int i = -1; i <= 1; i++) {
          for (int j = -1; j <= 1; j++) {
            if (i != 0 || j != 0) {
              q.addLast(new ScreenPoint(position.x + i, position.y + j));
            }
          }
        }

      }
    }
  }


  @Override
  public void interpretTurn(TurnCommand turnCommand) {
    // turn clockwise
    this.angleDegree = normalizeAngle(this.angleDegree + turnCommand.getAngle());
  }

  private static double degreeToRad(double degree) {
    return degree / 360.0 * Math.PI * 2.0;
  }

  private ScreenPoint pointInCanvas(ScreenPoint point) {

    int x = Math.max(0, Math.min(WIDTH, point.getX()));
    int y = Math.max(0, Math.min(HEIGHT, point.getY()));
    return new ScreenPoint(x, y);
  }

  private void initialize() {
    this.image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    this.graphics = image.createGraphics();
    graphics.setStroke(new BasicStroke(1));
    this.penColor = Color.BLACK;
    this.penSize = 1;
    this.currentPosition = new Point(0 ,0);
    this.angleDegree = 0;
    // set the background to white
    this.graphics.setColor(Color.WHITE);
    this.graphics.fillRect(0, 0, WIDTH, HEIGHT);
  }

  private ScreenPoint turtleToImageCoordinate(Point point) {
    double scaleFactorX = WIDTH / Turtle.CANVAS_SIZE_X;
    double scaleFactorY = HEIGHT / Turtle.CANVAS_SIZE_Y;
    return new ScreenPoint(
        (int) Math.round(point.getX() * scaleFactorX + WIDTH / 2) ,
        (int) Math.round(point.getY() * scaleFactorY + HEIGHT / 2) );
  }

  private double normalizeAngle(double angle) {
    double normalizedAngle = ((angle % 360) + 360) % 360 ;

    if (normalizedAngle > 180)
      return normalizedAngle;
    else
      return normalizedAngle - 360;
  }
}
