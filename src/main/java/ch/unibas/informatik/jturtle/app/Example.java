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

public class Example {
  public static void main(String[] args) throws IOException {

    clear();
    home();
    penColor(GREEN);
    forward(100);
    turnRight(90);
    penColor(color(128, 128, 128));
    forward(100);
    turnRight(90);
    penSize(10);
    forward(100);
    //display(drawing());

    //clear();
    ImageIO.write(drawing(), "png", new java.io.File("image.png"));
  }
}