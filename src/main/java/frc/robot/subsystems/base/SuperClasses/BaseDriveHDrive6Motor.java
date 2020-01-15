package frc.robot.subsystems.base.SuperClasses;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

/**
 * This is the base drive for the 6 motor drivetrain with either 1, or 2 H-Drive motors
 */
public class BaseDriveHDrive6Motor extends BaseDriveHDrive
{
    private SpeedController leftFront, leftMiddle, leftBack, rightFront, rightMiddle, rightBack, HDrive1, HDrive2;
    private SpeedControllerGroup leftGroup, rightGroup, HDriveGroup;

    /**
     * The constructor 
     * @param leftFront
     * @param leftMiddle
     * @param leftBack
     * @param rightFront
     * @param rightMiddle
     * @param rightBack
     * @param HDrive1
     * @param HDrive2
     */
    public BaseDriveHDrive6Motor (SpeedController leftFront, SpeedController leftMiddle, SpeedController leftBack, SpeedController rightFront, SpeedController rightMiddle, SpeedController rightBack, SpeedController HDrive1, SpeedController HDrive2)
    {
        this.leftFront = leftFront;
        this.leftMiddle = leftMiddle;
        this.leftBack = leftBack;
        this.leftGroup = new SpeedControllerGroup(this.leftFront, this.leftMiddle, this.leftBack);

        this.rightFront = rightFront;
        this.rightMiddle = rightMiddle;
        this.rightBack = rightBack;
        this.rightGroup = new SpeedControllerGroup(this.rightFront, this.rightMiddle, this.rightBack);

        this.HDrive1 = HDrive1;
        this.HDrive2 = HDrive2;
        this.HDriveGroup = new SpeedControllerGroup(this.HDrive1, this.HDrive2);

        super.setHMotorGroups(this.leftGroup, this.rightGroup, this.HDriveGroup);
    }
}