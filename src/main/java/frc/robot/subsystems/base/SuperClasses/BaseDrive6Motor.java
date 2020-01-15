package frc.robot.subsystems.base.SuperClasses;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

/**
 * The base class for a base 6 motor tank drive including speed controlller groups
 * The speed controllers are taken in, and then merged into groups and passed to the super classes
 */
public class BaseDrive6Motor extends BaseDrive
{
    private SpeedController leftFront, rightFront, leftMiddle, rightMiddle, leftBack, rightBack;
    private SpeedControllerGroup leftMotors, rightMotors;

    /**
     * Takes in 6 motor controllers and then folds them into speed controller groups and sends it
     * up the the super class.
     * @param leftFront <br>
     * - Type SpeedController <br>
     * - Front left Speed Controller
     * @param leftMiddle <br>
     * - Type SpeedController <br>
     * - Middle left speed controller
     * @param leftBack <br>
     * - Type SpeedController <br>
     * - Back left speed controller
     * @param rightFront <br>
     * - Type SpeedController <br>
     * - Front right speed controller
     * @param rightMiddle <br>
     * - Type SpeedController <br>
     * - Middle right speed controller 
     * @param rightBack <br>
     * - Type SpeedController <br>
     * - Back right speed controller
     */
    public BaseDrive6Motor (SpeedController leftFront, SpeedController leftMiddle, SpeedController leftBack, SpeedController rightFront, SpeedController rightMiddle, SpeedController rightBack)
    {
        this.leftFront = leftFront;
        this.leftMiddle = leftMiddle;
        this.leftBack = leftBack;
        this.rightFront = rightFront;
        this.rightMiddle = rightMiddle;
        this.rightBack = rightBack;
        this.leftMotors = new SpeedControllerGroup(this.leftFront, this.leftMiddle, this.leftBack);
        this.rightMotors = new SpeedControllerGroup(this.rightFront, this.rightMiddle, this.rightBack);

        super.setMotorGroups(leftMotors, rightMotors);
    }
}