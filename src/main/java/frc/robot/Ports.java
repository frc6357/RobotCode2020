/** 
 * Definitions of all hardware connections and hardware-related
 * IDs on the robot. This class should be included in any other
 * class which needs to interact with the robot hardware. The
 * values here form part of the robot's control system configuration
 * specification.
 */ 

package frc.robot;

public class Ports
{

    //////////////////////////////
    // Robot Infrastructure
    //////////////////////////////
    public static final int pcm1                = 1;    // CAN ID 1

    //////////////////////////////
    // I2C device addresses
    //////////////////////////////
    public static final int i2cColorSensor      = 0x52;

    //////////////////////////////
    // Drivetrain Addresses
    //////////////////////////////
    public static final int frontLeftDrive      = 10;   // CAN ID 10
    public static final int frontRightDrive     = 11;   // CAN ID 11
    public static final int backLeftDrive       = 12;   // CAN ID 12
    public static final int backRightDrive      = 13;   // CAN ID 13

    public static final int leftEncoder         = 0;    // DIO 0
    public static final int rightEncoder        = 1;    // DIO 1

    //////////////////////////////
    // Intake Addresses
    //////////////////////////////
    public static final int intakeMotor         = 20;   // CAN ID 20

    public static final int intakeMoverExtend   = 0;    // PCM 1, Output 0
    public static final int intakeMoverRetract  = 1;    // PCM 1, Output 1

    public static final int intakeOpenCheck     = 2;    // DIO 2
    public static final int intakeSpeedCheck    = 3;    // DIO 3
    public static final int intakeBallCheck     = 4;    // DIO 4

    ///////////////////////////////
    // Ball Handling Addresses
    ///////////////////////////////
    public static final int ballHandlingBelt    = 21;   // CAN ID 21

    public static final int ballGateOpen        = 2;    // PCM 1, Output 2
    public static final int ballGateDown        = 3;    // PCM 1, Output 3

    public static final int ballSensor1         = 5;    // DIO 5
    public static final int ballSensor2         = 6;    // DIO 6
    public static final int ballSensor3         = 7;    // DIO 7
    public static final int ballSensor4         = 8;    // DIO 8
    public static final int ballSensor5         = 9;    // DIO 9

    public static final int gateStateOpen       = 10;   // DIO 10

    public static final int beltSpeed           = 11;   // DIO 11

    ///////////////////////////////
    // Ball Launcher Addresses
    ///////////////////////////////
    public static final int ballLauncherMotor   = 22;   // CAN ID 22

    public static final int launcherHoodExtend  = 4;    // PCM 1, Output 4
    public static final int launcherHoodRetract = 5;    // PCM 1, Output 5

    ///////////////////////////////
    // Climb Addresses
    ///////////////////////////////
    public static final int winchClimbLeft      = 30;   // CAN ID 30
    public static final int winchClimbRight     = 31;   // CAN ID 31

    public static final int armLockDown         = 6;    // PCM 1, Output 6

    ///////////////////////////////
    // Control Wheel Addresses
    ///////////////////////////////
    public static final int colorWheelSpinner   = 40;   // CAN ID 40

    public static final int colorSpinnerLifter  = 7;    // PCM 1, Output 7
}