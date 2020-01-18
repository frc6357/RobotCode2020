package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Sets the methods that are used to hold and control the balls inside of the robot.
 */
public class SK20BallHandling extends Subsystem
{

    // private BaseRoller ballBelt;
    /**
     * Activates the roller that is used for the main ballBelt
     */
    public SK20BallHandling()
    {

    }

    /**
     * When activated the rollers are set to run at whatever speed that they're set to run at
     */
    public void activateRollers()
    {

    }

    /**
     * When activated the rollers are set to run at 0 speed
     */
    public void shutoffRollers()
    {

    }

    /**
     * Returns the speed that the belts are currently running off
     * @return The speed of the belt
     */
    public double getRollerSpeed()
    {

        return 1.0;
    }

    /**
     * This counts the number of activated sensors and counts the number of balls that are there
     * (Could have issues if there are balls that aren't tripping sensors)
     * @return Number of sensors that are activated
     */
    public int getBallCount()
    {

        return 1;
    }

    /**
     * Checks to see if all 5 of the ball sensors are activated OR if 4 of the ball sensors are activated and the intake sensor is as well
     * @return The result of the check if true - true and vice versa.
     */
    public boolean isFull()
    {
        
        return true;
    }

    @Override
    protected void initDefaultCommand() 
    {

    }
}