package ch.unibas.informatik.jturtle.views;

import ch.unibas.informatik.jturtle.interpreters.TurtleEventListener;

import java.awt.image.BufferedImage;

public interface TurtleView extends TurtleEventListener {
  void clear();
  void fill();
  void writeText(String text, int fontSize);

  BufferedImage getImage();
}
