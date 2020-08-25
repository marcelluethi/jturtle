package ch.unibas.informatik.jturtle.common;

public class Utils {

  public static double degreeToRad(double degree) {
    return degree / 360.0 * Math.PI * 2.0;
  }

  public static double normalizeAngle(double angle) {
    double normalizedAngle = ((angle % 360) + 360) % 360 ;

    if (normalizedAngle > 180)
      return normalizedAngle;
    else
      return normalizedAngle - 360;
  }

}
