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
package ch.unibas.informatik.jturtle.common;

public class Point {
  double x = 0;
  double y = 0;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public Point plus(Vector v) {
    return new Point(this.x + v.x, this.y + v.y);
  }

  public double getX() { return x; }
  public double getY() { return y; }


  /**
   * Return a string representation of the point. Useful for printing
   * it out onto the console.
   */
  public String toString() {
    return "(" + x + ", " + y + ")";
  }
}

