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

    public static final int leftEncoderA        = 0;    // DIO
    public static final int leftEncoderB        = 1;    // DIO
    public static final int rightEncoderA       = 2;    // DIO
    public static final int rightEncoderB       = 3;    // DIO


    //////////////////////////////
    // Intake Addresses
    //////////////////////////////
    public static final int intakeMotor         = 20;   // CAN ID 20

    public static final int intakeMoverExtend   = 0;    // PCM 1, Output 0
    public static final int intakeMoverRetract  = 1;    // PCM 1, Output 1

    public static final int intakeOpenCheck     = 4;    // DIO
    public static final int intakeSpeedCheckA   = 5;    // DIO
    public static final int intakeSpeedCheckB   = 6;    // DIO
    public static final int intakeBallCheck     = 7;    // DIO

    ///////////////////////////////
    // Ball Handling Addresses
    ///////////////////////////////
    public static final int ballHandlingBelt    = 21;   // CAN ID 21

    public static final int ballGateOpen        = 2;    // PCM 1, Output 2
    public static final int ballGateDown        = 3;    // PCM 1, Output 3

    public static final int ballSensor1         = 8;    // DIO
    public static final int ballSensor2         = 9;    // DIO
    public static final int ballSensor3         = 10;   // DIO
    public static final int ballSensor4         = 11;   // DIO
    public static final int ballSensor5         = 12;   // DIO

    public static final int gateStateOpen       = 13;   // DIO

    ///////////////////////////////
    // Ball Launcher Addresses
    ///////////////////////////////
    public static final int ballLauncherMotor   = 22;   // CAN ID 22
    public static final int ballLoaderMotor     = 23;   // CAN ID 23
    
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

    ///////////////////////////////
    // Operator Interface
    ///////////////////////////////
    public static final int OIDriverJoystick    = 0;

    public static final int OIDriverLeftDrive   = 1;    // Left Joystick Y
    public static final int OIDriverRightDrive  = 5;    // Right Joystick Y
}