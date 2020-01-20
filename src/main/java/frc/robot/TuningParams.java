package frc.robot;

import edu.wpi.first.wpilibj.util.Color;

public final class TuningParams {

    public static final double ACCEL_MAX_TOWARDS_FORWARD  = 0.01;
    public static final double ACCEL_MAX_TOWARDS_BACKWARD = -0.01;
    public static final double DECEL_MAX_TOWARDS_FORWARD  = 0.01;
    public static final double DECEL_MAX_TOWARDS_BACKWARD = -0.01;


    public static final Color RGB_CYAN   = new Color(0.1799, 0.4451, 0.3799);
    public static final Color RGB_GREEN  = new Color(0.2199, 0.5200, 0.2300);
    public static final Color RGB_RED    = new Color(0.4199, 0.3899, 0.1699);
    public static final Color RGB_YELLOW = new Color(0.3100, 0.5300, 0.1499);

    //////////////////////////////
    // Launcher Params
    //////////////////////////////
    public static final int MAX_RPM                     = 5700;
    public static final int P_VALUE                     = 1;
    public static final int I_VALUE                     = 1;
    public static final int D_VALUE                     = 1;

}