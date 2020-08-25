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
package ch.unibas.informatik.jturtle.views;

import ch.unibas.informatik.jturtle.TurtleCommands;
import ch.unibas.informatik.jturtle.common.PenState;
import ch.unibas.informatik.jturtle.common.Point;

import ch.unibas.informatik.jturtle.common.Utils;
import ch.unibas.informatik.jturtle.interpreters.TurtleEventListener;
import ch.unibas.informatik.jturtle.interpreters.Turtle;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.LinkedList;


public class ImageTurtleView implements TurtleView, TurtleEventListener {

  private static final int WIDTH = 1024;
  private static final int HEIGHT = 1024 ;


  private BufferedImage image =  null;
  private Graphics2D graphics = null;
  private BufferedImage turtleLogo = null;
  private Turtle turtle = null;

  Point lastTurtleSighting; // where the turtle was last time

  public ImageTurtleView(Turtle turtle) {

    this.turtle = turtle;
    this.lastTurtleSighting = turtle.getPosition();

    this.initialize();

    URL url = getClass().getResource("/turtle.png");
    try {
      turtleLogo = ImageIO.read(url);

    } catch (Exception e) {
      System.err.println("could not read turtle logo");
      System.err.println(e.getMessage());
    }
  }

  public BufferedImage getImage() {
      image.flush();
      return image;
  }



  @Override
  public void positionChanged() {
    ScreenPoint startPosInWindow = turtleToImageCoordinate(lastTurtleSighting);
    ScreenPoint endPosInWindow = turtleToImageCoordinate(turtle.getPosition());

    if (turtle.getPenState() == PenState.PEN_DOWN) {
      graphics.setColor(turtle.getPenColor());
      graphics.setStroke(new BasicStroke(turtle.getPenSize()));
      graphics.drawLine(startPosInWindow.getX(), startPosInWindow.getY(), endPosInWindow.getX(), endPosInWindow.getY());
    }
    this.lastTurtleSighting = turtle.getPosition();
  }

  @Override
  public void headingChanged() {
    // no action required for this view
  }

  @Override
  public void penStateChanged() {
    // no action required for this view
  }


  @Override
  public void penColorChanged() {
    // no action needed
  }

  @Override
  public void penSizeChanged() {
    // no action needed
  }

  @Override
  public void clear() {
    this.image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    this.graphics = image.createGraphics();
  }

  @Override
  public void fill() {
    ScreenPoint screenPoint = turtleToImageCoordinate(turtle.getPosition());

    int colorToFill = this.image.getRGB(screenPoint.x, screenPoint.y);

    // in case the current color corresponds to the one we stand at, there is nothing to do,
    // as filling would only color all pixels which anyway have this color.
    if (colorToFill == turtle.getPenColor().getRGB()) { return; }

    LinkedList<ScreenPoint> q = new LinkedList<ScreenPoint>();
    q.addLast(screenPoint);
    while (!q.isEmpty()) {
      ScreenPoint position = q.removeFirst();

      if ((position.x >= 0 && position.x < WIDTH && position.y >= 0 && position.y < HEIGHT) &&
          this.image.getRGB(position.x, position.y) == colorToFill) {

        this.image.setRGB(position.x, position.y, turtle.getPenColor().getRGB());

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
  public void writeText(String text, int fontSize) {
    ScreenPoint sp = turtleToImageCoordinate(turtle.getPosition());

    graphics.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
    graphics.setColor(turtle.getPenColor());
    graphics.drawString(text, sp.getX(), sp.getY());
  }

  public void drawTurtle() {
    ScreenPoint sp = turtleToImageCoordinate(turtle.getPosition());
    if (turtleLogo != null) {
      int xPos = sp.getX()- turtleLogo.getWidth() / 2;
      int yPos = sp.getY() - turtleLogo.getHeight() / 2;
      AffineTransform tx = AffineTransform.getRotateInstance(Utils.degreeToRad(turtle.getHeading()), turtleLogo.getWidth() / 2, turtleLogo.getHeight() / 2);
      AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

      graphics.drawImage(op.filter(turtleLogo, null), xPos, yPos, null);
    }
  }

  private ScreenPoint pointInCanvas(ScreenPoint point) {

    int x = Math.max(0, Math.min(WIDTH, point.getX()));
    int y = Math.max(0, Math.min(HEIGHT, point.getY()));
    return new ScreenPoint(x, y);
  }

  private void initialize() {
    this.image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    this.graphics = image.createGraphics();
    graphics.setStroke(new BasicStroke(turtle.getPenSize()));

    // set the background to white
    this.graphics.setColor(turtle.getPenColor());
    this.graphics.fillRect(0, 0, WIDTH, HEIGHT);
  }

  private ScreenPoint turtleToImageCoordinate(Point point) {
    double scaleFactorX = WIDTH / TurtleCommands.CANVAS_SIZE_X;
    double scaleFactorY = - HEIGHT / TurtleCommands.CANVAS_SIZE_Y;
    return new ScreenPoint(
        (int) Math.round(point.getX() * scaleFactorX + WIDTH / 2) ,
        (int) Math.round(point.getY() * scaleFactorY + HEIGHT / 2) );
  }


}
