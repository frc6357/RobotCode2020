package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;
import frc.robot.TuningParams;
import frc.robot.subsystems.base.BaseRoller;
import frc.robot.subsystems.base.LimitSensor;

/**
 * The SK20Intake class is the subsystem that interacts with the intake to both set its speed and deploy or retract it and get its status.
 */
public class SK20Intake extends SubsystemBase
{
    private BaseRoller intakeRoller;
    private DoubleSolenoid intakeMover;
    private CANSparkMax intakeRollerMotor;
    private CANEncoder intakeRollerEncoder;
    private LimitSensor intakeBallDetector;
    private boolean forward;

    /**
     * Sets up the intake control such that it takes the values that are declared for it in Ports and assigns them to a BaseRoller and a double solenoid.
     */
    public SK20Intake ()
    {
        intakeRollerMotor = new CANSparkMax(Ports.intakeMotor, MotorType.kBrushless);
        intakeRoller = new BaseRoller(intakeRollerMotor, TuningParams.INTAKE_MAX_SPEED);
        intakeRollerEncoder = new CANEncoder(intakeRollerMotor);
        intakeBallDetector = new LimitSensor(Ports.intakeBallCheck, TuningParams.INTAKE_BALL_CHECK_INVERT);

        intakeMover = new DoubleSolenoid(Ports.pcm, Ports.intakeMoverExtend, Ports.intakeMoverRetract);

        forward = true;

        SmartDashboard.putBoolean("Intake Roller Forwards", forward);
        SmartDashboard.putBoolean("Intake Roller Running", false);
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
        forward = true;
        intakeRoller.setForwards();
        SmartDashboard.putBoolean("Intake Roller Forwards", true);
        SmartDashboard.putBoolean("Intake Roller Running", true);
    }

    /**
     * This method sets the intake motor to run in the reverse direction.
     */
    public void reverseIntakeRoller()
    {
        forward = false;
        intakeRoller.setBackwards();
        SmartDashboard.putBoolean("Intake Roller Forwards", false);
        SmartDashboard.putBoolean("Intake Roller Running", true);
    }

    /**
     * Return true if the intake roller is set to run in the forward direction,
     * false if set to run in reverse.
     */
    public boolean IsIntakeRollerDirectionForwards()
    {
        return forward;
    }

    /**
     * Return true if the intake roller is currently running, false if stopped.
     */
    public boolean IsIntakeRollerRunning()
    {
        return ((intakeRoller.returnSpeed() == 0.0) ? false : true);
    }
    
    /**
     * When deactivate intake is called on the motor the intake is turned completely off
     */
    public void stopIntakeRoller()
    {
        intakeRoller.setStop();
        SmartDashboard.putBoolean("Intake Roller Running", false);
    }

    /**
     * Checks whether the intake is open or closed using the limit switch that's installed
     * @return The intake position. kForward for out, kReverse for in
     */
    public boolean isIntakeExtended()
    {
        DoubleSolenoid.Value currentState = intakeMover.get();
        return (currentState.equals(Value.kForward)) ? true : false;
    }

    /**
     * Checks that the intake speed is using the encoder that's there
     * @return The speed of the intake.
     */
    public double getIntakeRollerSpeed()
    {
        return intakeRollerEncoder.getVelocity();
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