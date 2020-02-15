package frc.robot.subsystems;

import com.analog.adis16448.frc.ADIS16448_IMU;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;
import frc.robot.TuningParams;
import frc.robot.commands.DefaultDriveCommand;
import frc.robot.subsystems.base.SuperClasses.BaseDrive;
import frc.robot.subsystems.base.SuperClasses.Gear;
import frc.robot.subsystems.base.SuperClasses.ShiftPolarity;
import frc.robot.utils.ScaledEncoder;

public class SK20Drive extends SubsystemBase {

    private final WPI_VictorSPX frontLeft = new WPI_VictorSPX(Ports.frontLeftDrive);
    private final WPI_VictorSPX backLeft = new WPI_VictorSPX(Ports.backLeftDrive);
    private final WPI_VictorSPX frontRight = new WPI_VictorSPX(Ports.frontRightDrive);
    private final WPI_VictorSPX backRight = new WPI_VictorSPX(Ports.backRightDrive);

    private final SpeedControllerGroup motorGroupLeft = new SpeedControllerGroup(frontLeft, backLeft);
    private final SpeedControllerGroup motorGroupRight = new SpeedControllerGroup(frontRight, backRight);

    private final ScaledEncoder encoderLeft = new ScaledEncoder(Ports.leftEncoderA, Ports.leftEncoderB,
            TuningParams.ENCODER_LEFT_REVERSED, TuningParams.ENCODER_PULSES, TuningParams.WHEEL_DIAMETER);
    private final ScaledEncoder encoderRight = new ScaledEncoder(Ports.rightEncoderA, Ports.rightEncoderB,
            TuningParams.ENCODER_RIGHT_REVERSED, TuningParams.ENCODER_PULSES, TuningParams.WHEEL_DIAMETER);

    private final Solenoid gearShiftSolenoid = null;
    private final ShiftPolarity shiftPolarity = ShiftPolarity.PRESSURE_IS_LOW;

    private final BaseDrive drive = new BaseDrive(motorGroupLeft, motorGroupRight, encoderLeft, encoderRight,
            gearShiftSolenoid, shiftPolarity);
    private final SmoothDrive smoothDrive = new SmoothDrive(drive);
    private final DefaultDriveCommand driveCommand;

    private final ADIS16448_IMU imu = new ADIS16448_IMU();

    private double speedMultiplier = 1.0;

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
        smoothDrive.setSpeeds(speedLeft * speedMultiplier, speedRight * speedMultiplier);
    }

    /**
     * This method sets the motors directly to 0 without any deceleration value.
     */
    public void emergencyStop() {
        smoothDrive.setSpeeds(0, 0);
        drive.SetSpeed(0, 0);
    }

    public void setSlowmode(boolean enabled) {
        speedMultiplier = enabled ? TuningParams.SLOWMODE_MULTIPLIER : 1.0;
    }

    /**
     * This method is used to set the gear on the drivetrain.
     * 
     * @param gearTarget The target gear we want to reach.
     */
    public void setGear(Gear gearTarget) {
        drive.setGear(gearTarget);
    }

    /**
     * This method is used to find which gear the robot is currently on.
     * 
     * @return Gear - The gear we are on (HIGH or LOW).
     */
    public Gear getGear() {
        return drive.getGear();
    }

    /**
     * This method is used to query the distance the left encoder has recorded since
     * the last time it was reset.
     *
     * @return Returns the number of centimeters the left encoder has measured.
     */
    public double getLeftEncoderDistance() {
        return drive.getLeftEncoderDistance();
    }

    /**
     * This method is used to query the distance the right encoder has recorded
     * since the last time it was reset.
     *
     * @return Returns the number of centimeters the right encoder has measured.
     */
    public double getRightEncoderDistance() {
        return drive.getRightEncoderDistance();
    }

    /**
     * Resets the gyro value
     */
    public void resetGyro() {
        imu.reset();
    }

    /**
     * Finds the angle of the robot(in the plane parallel to the field) relative to
     * the last reset
     * 
     * @return double - The value the gyro says we are turned
     */
    public double getAngle() {
        return imu.getGyroAngleZ();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        smoothDrive.SmoothDrivePeriodic();
    }

}