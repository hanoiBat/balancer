package frc.robot;

public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static class ids {
    public static final int ID_LEFT_PRIMARY = 1;
    public static final int ID_LEFT_SECONDARY = 2;
    public static final int ID_RIGHT_PRIMARY = 3;
    public static final int ID_RIGHT_SECONDARY = 4;
  }

  public static class wheel {
    public static final double WHEEL_RADIUS = 3;
    public static final double GEARRATIO = 15.5; 
  }

  public static class balance {
    public static final double BEAM_BALANACED_DRIVE_KP = 0.015;
    public static final double BEAM_BALANCED_GOAL_DEGREES = 0;
    public static final double BEAM_BALANCED_ANGLE_TRESHOLD_DEGREES = 1;
    public static final double BACKWARDS_BALANCING_EXTRA_POWER_MULTIPLIER = 1.35;
  }
}
