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
package ch.unibas.informatik.jturtle.app;

import ch.unibas.informatik.jturtle.Turtle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Example {

  static void drawPixel(Turtle t, boolean filled) {
    int pixelWidth = 2;
    t.penDown();
    t.forward(pixelWidth);
    t.turnRight(90);
    t.forward(pixelWidth);
    t.turnRight(90);
    t.forward(pixelWidth);
    t.turnRight(90);
    t.forward(pixelWidth);
    t.turnRight(90);
    t.penUp();

    if (filled) {
      t.forward(pixelWidth / 2);
      t.turnRight(90);
      t.forward(pixelWidth / 2);
      t.fill();
      t.backward(pixelWidth / 2);
      t.turnLeft(90);
      t.backward(pixelWidth / 2);
    }
    t.forward(2);
    t.penDown();

  }


  public static void drawImage(Turtle t, boolean[][] image) {
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image.length; j++) {
        drawPixel(t, image[i][j] );
      }
      t.penUp();
      t.backward(image.length * 2);
      t.turnRight(90); t.forward(2);
      t.turnLeft(90);
      t.penDown();

    }
  }

  public static void main(String[] args) throws Exception {

    Turtle t = new Turtle();

    boolean[][] twoDArray = new boolean[40][40];

    for (int i = 0; i < twoDArray.length; i++) {
      double x = (i - twoDArray.length / 2.0) / 15.0;
      for (int j = 0; j < twoDArray[i].length; j++) {
        double y = (j - twoDArray[i].length / 2.0) / 15.0 ;
        boolean res = (x * x + y * y - 1) * (x * x + y * y - 1) * (x * x + y * y - 1) - (x * x * y * y * y) <= 0;
        if (res ) {
          twoDArray[i][j] = true;
        } else {
          twoDArray[i][j] = false;
        }
      }
    }

    drawImage(t, twoDArray);

    BufferedImage image = t.toImage();
    ImageIO.write(image, "png", new File("image.png"));
  }
}
