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
package ch.unibas.informatik.jturtle.graphics;

import ch.unibas.informatik.jturtle.commands.*;

public interface TurtleInterpreter {
  void interpretClear(ClearCommand clearDommand);
  void interpretMove(MoveCommand moveCommand);
  void interpretPenColor(PenColorCommand penColorCommand);
  void interpretPenState(PenStateCommand penStateCommand);
  void interpretTurn(TurnCommand turnCommand);
  void interpretPenSize(PenSizeCommand size);
  void interpretSetPosition(SetPositionCommand setxy);
  void interpretSetHeading(SetHeadingCommand heading);
  void interpretFill(FillCommand fillCommand);
}
