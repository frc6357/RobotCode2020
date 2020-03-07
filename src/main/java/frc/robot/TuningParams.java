package frc.robot;

import edu.wpi.first.wpilibj.util.Color;

public final class TuningParams 
{
    //////////////////////////////
    // Drive Params
    //////////////////////////////
    public static final double  ACCEL_MAX_TOWARDS_FORWARD       =  0.075;
    public static final double  ACCEL_MAX_TOWARDS_BACKWARD      = -0.075;
    public static final double  DECEL_MAX_TOWARDS_FORWARD       =  0.075;
    public static final double  DECEL_MAX_TOWARDS_BACKWARD      = -0.075;

    public static final double  CONTROLLED_TURN_SPEED           = 0.5;
    public static final double  SLOW_CONTROLLED_TURN_SPEED      = 0.2;
    public static final double  ANGLE_TURN_TOLERANCE            = 1;
    public static final double  AUTONOMOUS_SLOW_START_ANGLE     = 30;       // In Degrees

    public static final boolean ENCODER_LEFT_REVERSED           = false;
    public static final boolean ENCODER_RIGHT_REVERSED          = true;
    public static final int     ENCODER_PULSES                  = 480;
    public static final double  WHEEL_DIAMETER                  = 18.375;   // 7.5" In Centimeters

    public static final double  STRAIGHT_DRIVE_OFFSET_TOLERANCE = 1.0;      // In Centimeters
    public static final double  OFFSET_SPEED_INCREMENT          = 0.01;
    public static final double  AUTONOMOUS_DRIVE_SPEED          = 0.5;
    public static final double  AUTONOMOUS_LOW_SPEED_LEVEL      = 0.2;
    public static final double  AUTONOMOUS_SLOW_DISTANCE_AREA   = 40;       // In Centimeters

    public static final double  SLOWMODE_MULTIPLIER             = 0.25;

    //////////////////////////////
    // Color Wheel Params
    //////////////////////////////
    public static final Color RGB_CYAN                         = new Color(0.1799, 0.4451, 0.3799);
    public static final Color RGB_GREEN                        = new Color(0.2199, 0.5200, 0.2300);
    public static final Color RGB_RED                          = new Color(0.4199, 0.3899, 0.1699);
    public static final Color RGB_YELLOW                       = new Color(0.3100, 0.5300, 0.1499);

    public static final int COLOR_WHEEL_PROXIMITY_THRESHOLD    = 30;

    public static final double COLOR_WHEEL_SPEED               = 0.5; 

    public static final int COLOR_WHEEL_TRANSITIONS            = 26; // Slightly more than three revs

    public static final int COLOR_WHEEL_ARRAY_SIZE             = 3;

    //////////////////////////////
    // Launcher Params
    //////////////////////////////
    public static final double LOADER_MAX_SPEED                = 1.0;
    public static final double RELEASE_MOTOR_SPEED             = 1.0;
    public static final double LAUNCHER_SET_PERCENTAGE_LOW     = -0.56; // TODO: IMPORTANT! Tune this
    public static final double LAUNCHER_SET_PERCENTAGE_HIGH    = -0.8;
    public static final int RELEASE_MOTOR_RUNTIME              = 300; // In Milliseconds
    public static final double LAUNCHER_P_VALUE                = 0.005; //TODO: IMPORTANT! Tune these PID values later
    public static final double LAUNCHER_I_VALUE                = 0.005;
    public static final double LAUNCHER_D_VALUE                = 0.005;

    //////////////////////////////
    // Intake Params
    //////////////////////////////
    public static final double INTAKE_MAX_SPEED                = 1.0;
    public static final int INTAKE_ENCODER_PULSES              = 2048;
    public static final double INTAKE_WHEEL_DIAMETER           = 5.08; // 2.0 Inches in centimetres
    public static final boolean INTAKE_BALL_CHECK_INVERT       = false;

    //////////////////////////////
    // Ball Handling Params
    //////////////////////////////
    public static final double BALL_OUTER_SPEED             = 1.0;
    public static final double BALL_INNER_SPEED             = -1.0;
    public static final boolean BALL_SENSOR_1_INVERT        = false;
    public static final boolean BALL_SENSOR_2_INVERT        = false;
    public static final boolean BALL_SENSOR_3_INVERT        = false;
    public static final boolean BALL_SENSOR_4_INVERT        = false;
    public static final boolean BALL_SENSOR_5_INVERT        = false;
    public static final double TRIGGER_THRESHOLD            = 0.9;


    ///////////////////////////////
    // Climb Params
    ///////////////////////////////
    public static final double WINCH_MOTOR_SPEED            = 0.5;
    public static final boolean DEPLOY_CLIMB_ARM            = true; //assumes that the solenoid needs to be energized be extended

    ///////////////////////////////
    // Auto Params
    ///////////////////////////////
    public static final double AUTO_STRAIGHTSTMOVE_DRIVE_DISTANCE       = 50.0;
    public static final double AUTO_OFFSETSTMOVE_DRIVE_DISTANCE         = -1000.0;
    public static final double LAUNCHER_START_UP_TIME                   = 3.0;

    public static final int AUTO_STRAIGHTSTTRENCH_TURN_ANGLE            = 90;
    public static final double AUTO_STRAIGHTSTTRENCH_DRIVE_DISTANCE_1   = 192.024;

    public static final double AUTO_TRENCH_DRIVE_DISTANCE               = 670.56;

    public static final int AUTO_OFFSETSTTRENCH_TURN_ANGLE_1            = -148;

    ///////////////////////////////
    // Test Params
    ///////////////////////////////
    public static final double TEST_DRIVE_STRAIGHT_1        = 100;    //Amount in centimeters
    public static final double TEST_DRIVE_STRAIGHT_2        = 200;    //Amount in centimeters
    public static final double TEST_DRIVE_STRAIGHT_3        = 500;    //Amount in centimeters

    public static final double TEST_TURN_1                  = 10;    //Amount of degrees
    public static final double TEST_TURN_2                  = 30;    //Amount of degrees
    public static final double TEST_TURN_3                  = 50;    //Amount of degrees
    public static final double TEST_TURN_4                  = 90;    //Amount of degrees
    public static final double TEST_TURN_5                  = 180;    //Amount of degrees

}