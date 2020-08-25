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
package ch.unibas.informatik.jturtle.interpreters;

import ch.unibas.informatik.jturtle.common.PenState;
import ch.unibas.informatik.jturtle.common.Point;

import java.awt.*;

public interface Turtle {

  void reset();
  void home();
  void move(double distance);
  void goTo(Point point);
  void setPenColor(Color color);
  void setPenState(PenState penState);
  void turn(double angleInDegree);
  void setPenSize(int size);
  void setPosition(Point point);
  void setHeading(double angleInDegrees);

  void addListener(TurtleEventListener listener);

  Point getPosition();
  Color getPenColor();
  PenState getPenState();
  double getHeading();
  int getPenSize();

}
