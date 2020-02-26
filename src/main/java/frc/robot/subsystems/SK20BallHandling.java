package frc.robot.subsystems;

//import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;
import frc.robot.TuningParams;
import frc.robot.commands.DefaultBallHandlingCommand;
import frc.robot.subsystems.base.BaseRoller;
//import frc.robot.subsystems.base.LimitSensor;

/**
 * Sets the methods that are used to hold and control the balls inside of the
 * robot.
 */
public class SK20BallHandling extends SubsystemBase {
    private CANSparkMax beltMotor1;
    private CANSparkMax beltMotor2;
    //private CANEncoder beltEncoder1;
    private BaseRoller ballBelt1;
    //private CANEncoder beltEncoder2;
    private BaseRoller ballBelt2;
    private final DefaultBallHandlingCommand ballHandling;

    // private LimitSensor[] ballSensors = {new LimitSensor(Ports.ballSensor1, TuningParams.BALL_SENSOR_1_INVERT), 
                                            // new LimitSensor(Ports.ballSensor2, TuningParams.BALL_SENSOR_2_INVERT),
                                            // new LimitSensor(Ports.ballSensor3, TuningParams.BALL_SENSOR_3_INVERT), 
                                            // new LimitSensor(Ports.ballSensor4, TuningParams.BALL_SENSOR_4_INVERT),
                                            // new LimitSensor(Ports.ballSensor5, TuningParams.BALL_SENSOR_5_INVERT)};

    /**
     * Activates the roller that is used for the main ballBelt
     */
    public SK20BallHandling()
    {
        beltMotor1 = new CANSparkMax(Ports.ballHandlingBelt, MotorType.kBrushless);
        beltMotor2 = new CANSparkMax(Ports.ballHandlingBelt2, MotorType.kBrushless);
        // TODO: Encoders commented out since no-one currently uses getRollerSpeed().
        //beltEncoder1 = new CANEncoder(beltMotor1);
        //beltEncoder2 = new CANEncoder(beltMotor2);
        ballBelt1 = new BaseRoller(beltMotor1, TuningParams.BALL_HANDLING_MAX_SPEED);
        ballBelt2 = new BaseRoller(beltMotor2, TuningParams.BALL_HANDLING_MAX_SPEED);
        ballHandling = new DefaultBallHandlingCommand(this);
        setDefaultCommand(ballHandling);
    }

    /**
     * When activated the rollers are set to run at whatever speed that they're set to run at
     */
    public void startRoller()
    {
        ballBelt1.setForwards();
        ballBelt2.setForwards();
    }

    /**
     * When activated the rollers are set to run at 0 speed
     */
    public void stopRoller()
    {
        ballBelt1.setStop();
        ballBelt2.setStop();
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