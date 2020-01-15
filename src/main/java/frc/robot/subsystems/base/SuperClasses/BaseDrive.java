package frc.robot.subsystems.base.SuperClasses;

import edu.wpi.first.wpilibj.SpeedControllerGroup;

/**
 * The base class for any 2 or 3 motor sided drive train that has multiple subclasses.
 * There is no constructor because what would be constructors are implemented by methods.
 * @author David Pieper
 */
public abstract class BaseDrive
{
    private SpeedControllerGroup leftMotors, rightMotors;

    /**
     * Sets the speed for the left side of the drivetrain
     * @author David Pieper
     * @param input <br>
     * - Type double <br>
     * - The speed that the motor controller is going to be set to, 1 for full forwards and -1 for
     * full back
     */
    public void setLeftSpeed(double input)
    {
        leftMotors.set(input);
    }

    /**
     * Sets the speed for the right side of the drivetrain
     * @author David Pieper
     * @param input <br>
     * - Type double <br>
     * - The speed that the motor controller is going to be set to, 1 for full forwards and -1 for
     * full back
     */
    public void setRightSpeed(double input)
    {
        rightMotors.set(input);
    }

    /**
     * Returns the current set speed for the left side of the drivetrain
     * @author David Pieper
     * @return
     * - Type double <br>
     * - Current set speed of motor controller, 1 for full forwards, -1 for full back
     */
    public double getLeftSpeed()
    {
        return leftMotors.get();
    }

    /**
     * Returns the current set speed for the right side of the drivetrain
     * @author David Pieper
     * @return
     * - Type double <br>
     * - Current set speed of motor controller, 1 for full forwards, -1 for full back
     */
    public double getRightSpeed()
    {
        return rightMotors.get();
    }

    /**
     * Sets the motor groups that are required for a tank drive and is how the set speed methods are used.
     * @author David Pieper
     * @param leftMotorGroup <br>
     * - Type SpeedControllerGroup <br>
     * - The group that is used to interface with the left side motor controllers
     * @param rightMotorGroup <br>
     * - Type SpeedControllerGroup <br>
     * - The group that is used to interface with the right side motor controllers
     */
    public void setMotorGroups (SpeedControllerGroup leftMotorGroup, SpeedControllerGroup rightMotorGroup)
    {
        leftMotors = leftMotorGroup;
        rightMotors = rightMotorGroup;
    }
}