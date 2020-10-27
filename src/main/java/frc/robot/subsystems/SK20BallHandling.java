package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;
import frc.robot.TuningParams;
import frc.robot.subsystems.base.BaseRoller;
import frc.robot.commands.DefaultBallHandlingCommand;

/**
 * Sets the methods that are used to hold and control the balls inside of the
 * robot.
 */
public class SK20BallHandling extends SubsystemBase {
    private CANSparkMax beltMotor1;
    private CANSparkMax beltMotor2;
    private BaseRoller ballBelt1;
    private BaseRoller ballBelt2;
    private final DefaultBallHandlingCommand ballHandling;
    private boolean systemMotorsAreEnabled = false;

    /**
     * Activates the roller that is used for the main ballBelt
     */
    public SK20BallHandling()
    {
        beltMotor1 = new CANSparkMax(Ports.ballHandlingBelt, MotorType.kBrushless);
        beltMotor2 = new CANSparkMax(Ports.ballHandlingBelt2, MotorType.kBrushless);

        ballBelt1 = new BaseRoller(beltMotor1, TuningParams.BALL_INNER_SPEED);
        ballBelt2 = new BaseRoller(beltMotor2, TuningParams.BALL_OUTER_SPEED);

        ballHandling = new DefaultBallHandlingCommand(this, false);
        setDefaultCommand(ballHandling);

        SmartDashboard.putBoolean("Ball Handler Active", systemMotorsAreEnabled);
    }

    /**
     * When activated the rollers are set to run at whatever speed that they're set to run at
     */
    public void startRoller()
    {
        ballBelt1.setForwards();
        ballBelt2.setForwards();
        systemMotorsAreEnabled = true;
        SmartDashboard.putBoolean("Ball Handler Active", systemMotorsAreEnabled);
    }

    /**
     * When activated the rollers are set to run at 0 speed
     */
    public void stopRoller()
    {
        ballBelt1.setStop();
        ballBelt2.setStop();
        systemMotorsAreEnabled = false;
        SmartDashboard.putBoolean("Ball Handler Active", systemMotorsAreEnabled);
    }


    public boolean motorsAreEnabled()
    {
        return systemMotorsAreEnabled;
    }

    /**
     * Returns the speed that the belts are currently running off
     * @return The speed of the belt
     */
    //public double getRollerSpeed()
    //{
    //    return beltEncoder1.getVelocity();
    //}

    /**
     * This counts the number of activated sensors and counts the number of balls that are there
     * (Could have issues if there are balls that aren't tripping sensors)
     * @return Number of sensors that are activated
     */
    public int getBallCount()
    {
        int count = 0;

        // for(int cnt = 0; cnt < ballSensors.length; cnt++)
        // {
            // count += ballSensors[cnt].get() ? 1: 0;
        // }

        return count;
    }

    /**
     * Checks to see if all 5 of the ball sensors are activated OR if 4 of the ball sensors are activated and the intake sensor is as well
     * @return The result of the check if true - true and vice versa.
     */
    public boolean isFull()
    {
        int count = 0;

        count += getBallCount();

        return count >= 5;
    }
}