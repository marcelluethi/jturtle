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
  public static void main(String[] args) throws Exception {

    Turtle t = new Turtle();
    t.penDown();
    t.penColor(Color.BLUE);
    t.penSize(1);

    int length = 100;

    t.forward(length / 2); ;
    t.penColor(Color.RED);
    t.turn(90);
    t.forward(length / 2);
    t.penDown();
    t.turn(90);
    t.forward(length);
    t.turn(90);
    t.forward(length);
    t.turn(90);
    t.forward(length);
    t.turn(90);
    t.forward(length);

    // second square
    t.penDown();
    t.penColor(Color.BLACK);
    t.turn(90);
    t.forward(length / 2);
    t.turn(-90);
    long diag = Math.round(Math.sqrt(length * length + length * length));
    t.forward((int) (diag - length) / 2);

    t.penDown();
    t.turn(135);
    t.forward(length);
    t.turn(90);
    t.forward(length);
    t.turn(90);
    t.forward(length);
    t.turn(90);
    t.forward(length);


    t.home();
    t.penDown();
    t.penColor(Color.BLACK);

    int radius = 10;
      for (int i = 0; i < 360; i++) {
        double dist =  (2.0 * 3.14159 * radius / 360.0);
        System.out.println(dist);
        t.forward( dist );
        t.turn(1);
      }


    t.penDown();

    //
//    for (int i = 0; i < 700; i++) {
//      t.forward(1);
//      t.turn(0.5);
//    }

    BufferedImage image = t.toImage();
    ImageIO.write(image, "png", new File("image.png"));
  }
}
