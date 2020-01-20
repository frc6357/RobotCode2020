package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Ports;
import frc.robot.TuningParams;
import frc.robot.subsystems.base.BaseRoller;
import frc.robot.utils.ScaledEncoder;

// TODO: Need to write this class.
/**
 * The SK20Intake class is the subsystem that interacts with the intake to both set its speed and deploy or retract it and get its status.
 */
public class SK20Intake extends SubsystemBase
{
    private BaseRoller intakeRoller;
    private DoubleSolenoid intakeMover;
    private WPI_VictorSPX intakeRollerMotor;
    private ScaledEncoder intakeRollerEncoder;

    /**
     * Sets up the intake control such that it takes the values that are declared for it in Ports and assigns them to a BaseRoller and a double solenoid.
     */
    public SK20Intake ()
    {
        intakeRollerMotor = new WPI_VictorSPX(Ports.intakeMotor);
        intakeRoller = new BaseRoller(intakeRollerMotor, TuningParams.INTAKE_MAX_SPEED);
        intakeRollerEncoder = new ScaledEncoder(Ports.intakeSpeedCheckA, Ports.intakeSpeedCheckB, TuningParams.INTAKE_ENCODER_PULSES, TuningParams.INTAKE_WHEEL_DIAMETER);

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
    public void activateIntakeRoller()
    {
        intakeRoller.setForwards();
    }

    /**
     * When deactivate intake is called on the motor the intake is turned completely off
     */
    public void deactivateIntakeRoller()
    {
        intakeRoller.setStop();
    }

    /**
     * Checks whether the intake is open or closed using the limit switch that's installed
     * @return The intake position. True for out, false for closed.
     */
    public boolean getIsIntakeOut()
    {

        return true;
    }

    /**
     * Checks that the intake speed is using the encoder that's there
     * @return The speed of the intake.
     */
    public double getIntakeRollerSpeed()
    {
        return intakeRollerEncoder.getRaw();
    }

    /**
     * Checks if there is currently a ball in the intake mechanism
     * @return If there's a ball in the indicator. True if there is, and false if there isn't.
     */
    public boolean isBallInIntake()
    {

        return true;
    }
}