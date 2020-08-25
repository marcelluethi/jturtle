package ch.unibas.informatik.jturtle.interpreters;

import ch.unibas.informatik.jturtle.common.PenState;
import ch.unibas.informatik.jturtle.common.Point;

import java.awt.*;

public interface TurtleEventListener {
  void positionChanged();
  void headingChanged();
  void penStateChanged();
  void penColorChanged();
  void penSizeChanged();
}
