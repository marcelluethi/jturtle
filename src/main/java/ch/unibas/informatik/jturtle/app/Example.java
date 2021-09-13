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

import static ch.unibas.informatik.jturtle.TurtleCommands.*;

import javax.imageio.ImageIO;

import java.io.IOException;
import java.util.Random;

public class Example {


   static Random random = new Random(18);

  static void randomTree(double length, double minLength, int thickness, int minThickness, double minAngle, double maxAngle, double minShrink, double maxShrink) {
    if (length < minLength || thickness < minThickness) {
      return;
    } else {
      double angle1 = minAngle + random.nextDouble() * (maxAngle - minAngle);
      double angle2 = minAngle + random.nextDouble() * (maxAngle - minAngle);
      double shrink1 = minShrink + random.nextDouble() * (maxShrink - minShrink);
      double shrink2 = minShrink + random.nextDouble() * (maxShrink - minShrink);
      penSize(thickness);
      forward(length);
      turnRight(angle1);
      randomTree(length*shrink1, minLength, (int) (thickness * shrink1),  minThickness, minAngle, maxAngle, minShrink, maxShrink);
      turnLeft(angle1 + angle2);
      randomTree(length*shrink2, minLength, (int) (thickness * shrink2), minThickness, minAngle, maxAngle, minShrink, maxShrink);
      turnRight(angle2);
      penSize(thickness);
      backward(length);
    }
  }

    static void tree(int levels, double trunkLen, double angle, double shrinkFactor) {

      if (levels > 0) {
        // Draw the trunk.
        forward(trunkLen);
        // Turn and draw the right subtree.
        turnRight(angle);
        tree(levels-1, trunkLen*shrinkFactor, angle, shrinkFactor);
        // Turn and draw the left subtree.
        turnLeft(angle * 2);
        tree(levels-1, trunkLen*shrinkFactor, angle, shrinkFactor);
        // Turn back and back up to root without drawing.
        turnRight(angle);
        penUp();
        backward(trunkLen);
        penDown();
      }
    }

  public static void main(String[] args) throws IOException {

    clear();
    home();
    randomTree(20, 2, 12, 1, 10, 30, 0.75, 1.0);
    //tree(10, 50, 30, 0.5);

    //clear();
    ImageIO.write(drawing(), "png", new java.io.File("image.png"));
  }
}