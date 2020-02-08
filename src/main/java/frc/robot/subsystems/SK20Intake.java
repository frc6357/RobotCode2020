package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Ports;
import frc.robot.TuningParams;
import frc.robot.subsystems.base.BaseRoller;
import frc.robot.utils.ScaledEncoder;
import frc.robot.subsystems.base.LimitSensor;

/**
 * The SK20Intake class is the subsystem that interacts with the intake to both set its speed and deploy or retract it and get its status.
 */
public class SK20Intake extends SubsystemBase
{
    private BaseRoller intakeRoller;
    private DoubleSolenoid intakeMover;
    // TODO: Switch to CAN Spark Max and Neo integrated encoder.
    private WPI_VictorSPX intakeRollerMotor;
    private ScaledEncoder intakeRollerEncoder;
    private LimitSensor intakeBallDetector;

    /**
     * Sets up the intake control such that it takes the values that are declared for it in Ports and assigns them to a BaseRoller and a double solenoid.
     */
    public SK20Intake ()
    {
        // TODO: The final intake uses a Neo motor controller with integrated encoder. Update this.
        intakeRollerMotor = new WPI_VictorSPX(Ports.intakeMotor);
        intakeRoller = new BaseRoller(intakeRollerMotor, TuningParams.INTAKE_MAX_SPEED);
        intakeRollerEncoder = new ScaledEncoder(Ports.intakeSpeedCheckA, Ports.intakeSpeedCheckB, TuningParams.INTAKE_ENCODER_PULSES, TuningParams.INTAKE_WHEEL_DIAMETER);
        intakeBallDetector = new LimitSensor(Ports.intakeBallCheck, TuningParams.INTAKE_BALL_CHECK_INVERT);

        intakeMover = new DoubleSolenoid(Ports.intakeMoverExtend, Ports.intakeMoverRetract);
    }

    /**
     * When extend intake is called the solenoid will activate and it will push it out to be able to turn on the motors.
     */
    public void extendIntake()
    {
        intakeMover.set(DoubleSolenoid.Value.kForward);
    }

    /**
     * When retract intake is called the solenoid will retract and pull the intake mechanism back inside of the frame perimeter
     */
    public void retractIntake()
    {
        intakeMover.set(DoubleSolenoid.Value.kReverse);
    }   

    /**
     * When activate intake is called the motor on the intake turns on up to the set speed until it is deactivated
     */
    public void startIntakeRoller()
    {
        intakeRoller.setForwards();
    }

    /**
     * When deactivate intake is called on the motor the intake is turned completely off
     */
    public void stopIntakeRoller()
    {
        intakeRoller.setStop();
    }

    /**
     * Checks whether the intake is open or closed using the limit switch that's installed
     * @return The intake position. kForward for out, kReverse for in
     */
    public boolean isIntakeExtended()
    {
        DoubleSolenoid.Value currentState = intakeMover.get();
        return (currentState == Value.kForward) ? true : false;
    }

    /**
     * Checks that the intake speed is using the encoder that's there
     * @return The speed of the intake.
     */
    public double getIntakeRollerSpeed()
    {
        return intakeRollerEncoder.getRate();
    }

    /**
     * Checks if there is currently a ball in the intake mechanism
     * @return If there's a ball in the indicator. True if there is, and false if there isn't.
     */
    public boolean isBallInIntake()
    {
        return intakeBallDetector.get();
    }
}