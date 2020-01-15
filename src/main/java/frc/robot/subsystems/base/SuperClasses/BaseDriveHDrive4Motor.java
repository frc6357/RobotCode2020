package frc.robot.subsystems.base.SuperClasses;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

/**
 * This is the base class for a 4 motor H-Drive with the options of both a 1 and 2 motor drivetrain
 * @author David Pieper
 */
public class BaseDriveHDrive4Motor extends BaseDriveHDrive
{
    private SpeedController leftFront, rightFront, leftBack, rightBack, HDrive1;
    private SpeedControllerGroup leftDrive, rightDrive, HDriveGroup;

    /**
     * The constructor for a 4 motor, 2 H-Drive motor drivetrain utilizing a tank
     * drive setup
     * @author David Pieper
     * 
     * @param leftFront  <br>
     *                   - Type SpeedController <br>
     *                   - Front left motor controller
     * @param rightFront <br>
     *                   - Type SpeedController <br>
     *                   - Front right motor controller
     * @param leftBack   <br>
     *                   - Type SpeedController <br>
     *                   - Back left motor controller
     * @param rightBack  <br>
     *                   - Type SpeedController <br>
     *                   - Back right motor controller
     * @param HDrive1    <br>
     *                   - Type SpeedController <br>
     *                   - H-Drive motor 1
     * @param HDrive2    <br>
     *                   - Type SpeedController <br>
     *                   - H-Drive motor 2
     */
    public BaseDriveHDrive4Motor(SpeedController leftFront, SpeedController rightFront, SpeedController leftBack,
            SpeedController rightBack, SpeedController HDrive1, SpeedController HDrive2) {
        this.leftFront = leftFront;
        this.leftBack = leftBack;
        leftDrive = new SpeedControllerGroup(this.leftFront, this.leftBack);

        this.rightFront = rightFront;
        this.rightBack = rightBack;
        rightDrive = new SpeedControllerGroup(this.rightFront, this.rightBack);

        this.HDrive1 = HDrive1;
        HDriveGroup = new SpeedControllerGroup(this.HDrive1);

        super.setHMotorGroups(leftDrive, rightDrive, HDriveGroup);
    }

    /**
     * Constructor for a 4 motor tank drive with a 1 motor h-drive systemc
     * @author David Pieper
     * 
     * @param leftFront <br>
     * - Type SpeedController <br>
     * - Front left motor controller
     * @param rightFront <br>
     * - Type SpeedController <br>
     * - Front right motor controller
     * @param leftBack <br>
     * - Type SpeedController <br>
     * - Back left motor controller
     * @param rightBack <br> 
     * - Type SpeedController <br>
     * - Back right motor controller
     * @param HDrive <br>
     * - Type SpeedController <br>
     * - H-Drive motor controller
     */
    public BaseDriveHDrive4Motor (SpeedController leftFront, SpeedController rightFront, SpeedController leftBack, SpeedController rightBack, SpeedController HDrive)
    {
        this.leftFront = leftFront;
        this.leftBack = leftBack;
        leftDrive = new SpeedControllerGroup (this.leftFront, this.leftBack);

        this.rightFront = rightFront;
        this.rightBack = rightBack;
        rightDrive = new SpeedControllerGroup(this.rightFront, this.rightBack);

        this.HDrive1 = HDrive;
        HDriveGroup = new SpeedControllerGroup(this.HDrive1);

        super.setHMotorGroups(leftDrive, rightDrive, HDriveGroup);
    }
}