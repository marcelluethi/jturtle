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

import ch.unibas.informatik.jturtle.common.PenState;
import ch.unibas.informatik.jturtle.common.Point;
import ch.unibas.informatik.jturtle.interpreters.DefaultTurtle;
import ch.unibas.informatik.jturtle.interpreters.Turtle;
import ch.unibas.informatik.jturtle.views.ImageTurtleView;
import ch.unibas.informatik.jturtle.views.TurtleView;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TurtleCommands {

  public static final int CANVAS_SIZE_X = 200;
  public static final int CANVAS_SIZE_Y = 200;

  private static Turtle turtle = new DefaultTurtle();
  private static TurtleView view = new ImageTurtleView(turtle);


  public static Color BLACK = Color.BLACK;
  public static Color BLUE = Color.BLUE;
  public static Color GREEN = Color.GREEN;
  public static Color RED = Color.RED;
  public static Color GREY = Color.GRAY;
  public static Color YELLOW = Color.YELLOW;
  public static Color ORANGE = Color.ORANGE;
  public static Color WHITE = Color.WHITE;

  public static Color color(int r, int g, int b) {
    return new Color(r, g, b);
  }

  static {
    turtle.addListener(view);
  }


  public static void clear() {
    view.clear();
  }

  public static void reset() { turtle.reset();}

  public static void penDown() {
    turtle.setPenState(PenState.PEN_DOWN);
  }

  public static void penUp() {
    turtle.setPenState(PenState.PEN_UP);
  }

  public static void forward(double distance) {
    turtle.move(distance);
  }

  public static void backward(double distance) {
    turtle.move(-distance);
  }

  public static void home() {
    turtle.home();
  }

  public static void goTo(double x, double y) {
    turtle.goTo(new Point(x, y));
  }

  public static void turnRight(double angle) {
    turtle.turn(angle);
  }

  public static void turnLeft(double angle) {
    turtle.turn(-angle);
  }

  public static void penColor(Color color) {
    turtle.setPenColor(color);
  }

  public static void penSize(int size) {
    turtle.setPenSize(size);
  }

  public static void write(String text, int fontSize) {
    view.writeText(text, fontSize);
  }

  public static void drawTurtle() {
    if (view instanceof ImageTurtleView) {
      ((ImageTurtleView) view).drawTurtle();
    }
  }
  public static void fill() { view.fill();}

  public static BufferedImage drawing() {

    return view.getImage();

  }


}
