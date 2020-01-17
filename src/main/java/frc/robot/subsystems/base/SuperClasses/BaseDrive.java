package frc.robot.subsystems.base.SuperClasses;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.utils.ScaledEncoder;

/**
 * The base class for any 2 or 3 motor sided drive train that has multiple
 * subclasses. There is no constructor because what would be constructors are
 * implemented by methods.
 * 
 * @author David Pieper
 */
public class BaseDrive {
    private final SpeedControllerGroup motorGroupLeft, motorGroupRight;
    private final DifferentialDrive driveDiff;
    private final ScaledEncoder encoderLeft, encoderRight;
    private final Solenoid gearShiftSolenoid;
    private final ShiftPolarity shiftPolarity;

    /**
     * Constructor of the BaseDrive
     * 
     * @param motorGroupLeft  <br>
     *                        - Type SpeedControllerGroup <br>
     *                        - A group containing all the motors on the left side
     *                        of the drivetrain.
     * @param motorGroupRight <br>
     *                        - Type SpeedControllerGroup <br>
     *                        - A group containing all the motors on the right side
     *                        of the drivetrain.
     */
    public BaseDrive(SpeedControllerGroup motorGroupLeft, SpeedControllerGroup motorGroupRight,
            ScaledEncoder encoderLeft, ScaledEncoder encoderRight, Solenoid gearShiftSolenoid,
            ShiftPolarity shiftPolarity) {
        this.motorGroupLeft = motorGroupLeft;
        this.motorGroupRight = motorGroupRight;
        this.encoderLeft = encoderLeft;
        this.encoderRight = encoderRight;
        this.gearShiftSolenoid = gearShiftSolenoid;
        this.shiftPolarity = shiftPolarity;
        driveDiff = new DifferentialDrive(motorGroupLeft, motorGroupRight);
    }

    /**
     * Sets the speed for the left and right side of the drivetrain
     * 
     * @param speedLeft  <br>
     *                   - A number between -1.0 and 1.0 to set speed of the left
     *                   side of the drivetrain
     * @param speedRight <br>
     *                   - A number between -1.0 and 1.0 to set speed of the right
     *                   side of the drivetrain - The speed that the motor
     *                   controller is going to be set to, 1 for full forwards and
     *                   -1 for full back
     */
    public void SetSpeed(double speedLeft, double speedRight) {
        driveDiff.tankDrive(speedLeft, speedRight);
    }

    /**
     * Grabs the raw values from the left encoder
     */
    public int getLeftEncoderRaw() {
        return encoderLeft.getRaw();
    }

    /**
     * Grabs the raw values from the right encoder
     */
    public int getRightEncoderRaw() {
        return encoderRight.getRaw();
    }

    /**
     * Sets the speed for the left side of the drivetrain
     * 
     * @param newGear - The speed that the motor controller is going to be set to, 1
     *                for full forwards and -1 for full back
     */
    public void setGear(Gear newGear) {
        switch (shiftPolarity) {
        case PRESSURE_IS_LOW:
            gearShiftSolenoid.set(newGear == Gear.LOW);
            break;
        case PRESSURE_IS_HIGH:
            gearShiftSolenoid.set(newGear == Gear.HIGH);
            break;
        }
    }

    /**
     * Returns if the gear is HIGH or LOW
     * @return
     */
    public Gear getGear() {
        Gear toReturn = Gear.LOW;
        switch (shiftPolarity) {
        case PRESSURE_IS_LOW:
            toReturn = gearShiftSolenoid.get() ? Gear.LOW : Gear.HIGH;
            break;
        case PRESSURE_IS_HIGH:
            toReturn = gearShiftSolenoid.get() ? Gear.HIGH : Gear.LOW;
            break;
        }
        return toReturn;

    }

    /**
     * Returns the current set speed for the left side of the drivetrain
     * 
     * @author David Pieper
     * @return - Type double <br>
     *         - Current set speed of motor controller, 1 for full forwards, -1 for
     *         full back
     */
    public double getLeftSpeed() {
        return motorGroupLeft.get();
    }

    /**
     * Returns the current set speed for the right side of the drivetrain
     * 
     * @author David Pieper
     * @return - Type double <br>
     *         - Current set speed of motor controller, 1 for full forwards, -1 for
     *         full back
     */
    public double getRightSpeed() {
        return motorGroupRight.get();
    }

}