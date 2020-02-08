package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;
import frc.robot.TuningParams;
import frc.robot.commands.DefaultBallHandlingCommand;
import frc.robot.subsystems.base.BaseRoller;
import frc.robot.subsystems.base.LimitSensor;

/**
 * Sets the methods that are used to hold and control the balls inside of the
 * robot.
 */
public class SK20BallHandling extends SubsystemBase {
    private CANSparkMax beltMotor;
    private CANEncoder beltEncoder;
    private BaseRoller ballBelt;
    private final DefaultBallHandlingCommand ballHandling;

    private LimitSensor[] ballSensors = {new LimitSensor(Ports.ballSensor1, TuningParams.BALL_SENSOR_1_INVERT), 
                                            new LimitSensor(Ports.ballSensor2, TuningParams.BALL_SENSOR_2_INVERT),
                                            new LimitSensor(Ports.ballSensor3, TuningParams.BALL_SENSOR_3_INVERT), 
                                            new LimitSensor(Ports.ballSensor4, TuningParams.BALL_SENSOR_4_INVERT),
                                            new LimitSensor(Ports.ballSensor5, TuningParams.BALL_SENSOR_5_INVERT)};

    /**
     * Activates the roller that is used for the main ballBelt
     */
    public SK20BallHandling()
    {
        beltMotor = new CANSparkMax(Ports.ballHandlingBelt, MotorType.kBrushless);
        beltEncoder = new CANEncoder(beltMotor);
        ballBelt = new BaseRoller(beltMotor, TuningParams.BALL_HANDLING_MAX_SPEED);
        ballHandling = new DefaultBallHandlingCommand(this);
        setDefaultCommand(ballHandling);
    }

    /**
     * When activated the rollers are set to run at whatever speed that they're set to run at
     */
    public void startRoller()
    {
        ballBelt.setForwards();
    }

    /**
     * When activated the rollers are set to run at 0 speed
     */
    public void stopRoller()
    {
        ballBelt.setStop();
    }

    /**
     * Returns the speed that the belts are currently running off
     * @return The speed of the belt
     */
    public double getRollerSpeed()
    {
        return beltEncoder.getVelocity();
    }

    /**
     * This counts the number of activated sensors and counts the number of balls that are there
     * (Could have issues if there are balls that aren't tripping sensors)
     * @return Number of sensors that are activated
     */
    public int getBallCount()
    {
        int count = 0;

        for(int cnt = 0; cnt < ballSensors.length; cnt++)
        {
            count += ballSensors[cnt].get() ? 1: 0;
        }

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
        // TODO: This also needs to check the intake ball sensor, but the intake class has not been implemented into robot.

        return count >= 5;
    }
}