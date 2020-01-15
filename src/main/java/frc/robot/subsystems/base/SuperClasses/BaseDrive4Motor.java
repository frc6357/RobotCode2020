package frc.robot.subsystems.base.SuperClasses;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

/**
 * The base drive that is used for any 4 motor total standard tank drive train
 * Stores the speed controllers that it uses, and passes them in the form of SpeedControllerGroups
 * to the super class.
 * @author David Pieper
 */
public class BaseDrive4Motor extends BaseDrive
{
    private SpeedController leftFront, rightFront, leftBack, rightBack;
    private SpeedControllerGroup leftMotors, rightMotors;
    
    /**
     * Takes in the required motor controllers, then merges them into a group and passes to the 
     * super class.
     * @param leftFront <br>
     * - Type SpeedController <br>
     * - Front left motor controller for drive
     * @param rightFront <br>
     * - Type SpeedController <br>
     * - Front right motor controller for drive
     * @param leftBack <br>
     * - Type SpeedController <br>
     * - Back left motor controller for drive
     * @param rightBack <br>
     * - Type SpeedController <br>
     * - Back right motor controller for drive
     */
    public BaseDrive4Motor (SpeedController leftFront, SpeedController rightFront, SpeedController leftBack, SpeedController rightBack)
    {
        this.leftFront = leftFront;
        this.leftBack = leftBack;
        this.rightFront = rightFront;
        this.rightBack = rightBack;
        this.leftMotors = new SpeedControllerGroup(this.leftFront, this.leftBack);
        this.rightMotors = new SpeedControllerGroup(this.rightFront, this.rightBack);
        
        super.setMotorGroups(leftMotors, rightMotors);
    }
}