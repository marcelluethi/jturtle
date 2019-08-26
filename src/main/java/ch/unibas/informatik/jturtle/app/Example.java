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

  static void circle(Turtle t, int radius, int x, int y) {
    // go to center
    t.home();
    t.penDown();

    //
    for (int i = 0; i < 360; i++) {
      double dist = Math.round (2.0 * 3.14159 * radius / 360.0);
      t.forward( dist );
      t.turnRight(1);
    }
  }

  public static void main(String[] args) throws Exception {

    Turtle t = new Turtle();
    t.penDown();
    int radius = 10;
      for (int i = 0; i < 360; i++) {
        double dist =  (2.0 * 3.14159 * radius / 360.0);
        System.out.println(dist);
        t.forward( dist );
        t.turnRight(1);
      }


    t.clear();
      t.penDown();
      t.forward(100);

    //
//    for (int i = 0; i < 700; i++) {
//      t.forward(1);
//      t.turn(0.5);
//    }

    BufferedImage image = t.toImage();
    ImageIO.write(image, "png", new File("image.png"));
  }
}
