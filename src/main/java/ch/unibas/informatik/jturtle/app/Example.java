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
    //t.penDown();
    t.penColor(Color.BLUE);
    t.penSize(1);

    int length = 200;

    t.move(length / 2); ;
    t.penColor(Color.RED);
    t.turn(90);
    t.move(length / 2);
    t.penDown();
    t.turn(90);
    t.move(length);
    t.turn(90);
    t.move(length);
    t.turn(90);
    t.move(length);
    t.turn(90);
    t.move(length);

    // second square
    t.penDown();
    t.penColor(Color.BLACK);
    t.turn(90);
    t.move(length / 2);
    t.turn(-90);
    long diag = Math.round(Math.sqrt(length * length + length * length));
    t.move((diag - length) / 2);

    t.penDown();
    t.turn(135);
    t.move(length);
    t.turn(90);
    t.move(length);
    t.turn(90);
    t.move(length);
    t.turn(90);
    t.move(length);

    BufferedImage image = t.toImage();
    ImageIO.write(image, "png", new File("image.png"));
  }
}
