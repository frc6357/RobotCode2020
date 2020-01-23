package frc.robot.subsystems;

import com.analog.adis16448.frc.ADIS16448_IMU;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;
import frc.robot.subsystems.base.SuperClasses.BaseDrive;
import frc.robot.subsystems.base.SuperClasses.ShiftPolarity;
import frc.robot.utils.ScaledEncoder;
import frc.robot.commands.DefaultDriveCommand;

public class SK20Drive extends SubsystemBase {

    private final WPI_VictorSPX frontLeft = new WPI_VictorSPX(Ports.frontLeftDrive);
    private final WPI_VictorSPX backLeft = new WPI_VictorSPX(Ports.backLeftDrive);
    private final WPI_VictorSPX frontRight = new WPI_VictorSPX(Ports.frontRightDrive);
    private final WPI_VictorSPX backRight = new WPI_VictorSPX(Ports.backRightDrive);
    private final SpeedControllerGroup motorGroupLeft = new SpeedControllerGroup(frontLeft, backLeft);
    private final SpeedControllerGroup motorGroupRight = new SpeedControllerGroup(frontRight, backRight);
    private final ScaledEncoder encoderLeft = null;
    private final ScaledEncoder encoderRight = null;
    private final Solenoid gearShiftSolenoid = null;
    private final ShiftPolarity shiftPolarity = ShiftPolarity.PRESSURE_IS_LOW;
    private final BaseDrive drive = new BaseDrive(motorGroupLeft, motorGroupRight, encoderLeft, encoderRight,
            gearShiftSolenoid, shiftPolarity);
    private final SmoothDrive smoothDrive = new SmoothDrive(drive);
    private final DefaultDriveCommand driveCommand;

    private final ADIS16448_IMU imu = new ADIS16448_IMU();
    private double angleTarget;
    private double angleCurrent;

    /**
     * This constructor of the SK20Drive sets up the BaseDrive object and passes it
     * into the SmoothDrive object to set it up.
     */
    public SK20Drive() {
        driveCommand = new DefaultDriveCommand(this);
        setDefaultCommand(driveCommand);
    }

    /**
     * This method sets both speeds for the robot
     * 
     * @param speedLeft  The desired for the left side of drivetrain
     * @param speedRight The desired for the right side of drivetrain
     */
    public void setSpeeds(double speedLeft, double speedRight) {
        smoothDrive.setSpeeds(speedLeft, speedRight);
    }
    
    /*
    public void setTargetAngle(double degrees) {
        angleTarget = degrees;
        angleCurrent = imu.getGyroAngleZ();
        double deltaAngle = angleTarget - angleCurrent;
        
    };
    */

    //TEST FUNCTION
    //TODO: This is a test. Remove in production
    public void resetGyro() {
        imu.reset();
    }

    //TEST FUNCTION
    //TODO: This is a test. Remove in production
    public double getAngle() {
        return imu.getGyroAngleZ();
    }

    // TODO: Create all the methods we need!!

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        // TODO: Set up this method!!
        smoothDrive.SmoothDrivePeriodic();
    }
    
}