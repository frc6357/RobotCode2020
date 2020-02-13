package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;
import frc.robot.TuningParams;
import frc.robot.subsystems.base.BaseRoller;

// TODO: I don't see anything here that relates to determining the number of balls
//       currently stored in the mechanism. Is this an oversight?
/**
 * This is the launcher subsystem that controls everything that has to do with 
 * the launcher including the transfer from the handling system to the launcher
 * as well the speed of the launcher and the position of the hood to change the set angle
 */
public class SK20Launcher extends SubsystemBase
{
    private final CANSparkMax launcherMotor = new CANSparkMax(Ports.ballLauncherMotor, MotorType.kBrushless);
    private final CANPIDController PIDControl = launcherMotor.getPIDController();

    private final CANSparkMax transferMotor = new CANSparkMax(Ports.ballLoaderMotor, MotorType.kBrushless);   
    private final BaseRoller transferRoller = new BaseRoller(transferMotor, TuningParams.LOADER_MAX_SPEED);

    private final CANSparkMax releaseMotor = new CANSparkMax(Ports.ballReleaseMotor, MotorType.kBrushless);
    private final BaseRoller releaseRoller = new BaseRoller(releaseMotor, TuningParams.RELEASE_MOTOR_SPEED);

    private final CANEncoder launcherMotorEncoder = new CANEncoder(launcherMotor);
    private final CANEncoder transferMotorEncoder = new CANEncoder(transferMotor);

    private final DoubleSolenoid hoodMover = new DoubleSolenoid(Ports.launcherHoodExtend, Ports.launcherHoodRetract);

    /**
     * This does nothing as everything is intialized inside of the class before the constructor is even called so that 
     * we can guarantee everything is set up the way that we need it to be set up at.
     */
    public SK20Launcher()
    {
        // Set the launcher motor into coast mode. If we don't do this and we stop the motor quickly,
        // there's a very good chance we'll damage something since there's a large flywheel attached 
        // to this subsystem!
        launcherMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
        setPIDValues();
    }

    /**
     * Since this motor doesn't run on a PID, and it just needs to run at a set speed there just needs to be a turn on and off method
     */
    public void activateTransfer()
    {
        transferRoller.setForwards();
    }

    /**
     * Since this motor doesn't run on a PID, and it just needs to run at a set speed there just needs to be a turn on and off method
     */
    public void deactivateTransfer()
    {
        transferRoller.setStop();
    }

    /**
     * This returns the current encoder speed of the launcher
     * TODO: We need to test this method to see if it actually works the way that we think it does.
     * @return The current encoder speed of the launcher
     */
    public double getLauncherSpeed()
    {
        return launcherMotorEncoder.getVelocity();
    }

    /**
     * This will return the encoder speed of the transfer motors
     * @return The current speed of the transfer motors
     */
    public double getTransferSpeed()
    {
        return transferMotorEncoder.getVelocity();
    }

    /**
     * Sets the PID values for the controller
     * @param pVal The proportianal value
     * @param iVal The integral value
     * @param dVal The derivative value
     */
    private void setPIDValues()
    {
        PIDControl.setP(TuningParams.LAUNCHER_P_VALUE);
        PIDControl.setI(TuningParams.LAUNCHER_I_VALUE);
        PIDControl.setD(TuningParams.LAUNCHER_D_VALUE);
    }

    /**
     * Sets the setpoint of the PID controller
     * @param value The set double value
     */
    private void setSetpoint(double value)
    {
        PIDControl.setReference(value, ControlType.kVelocity);
    }

    /**
     * It sets the hood according to whatever position it is passed
     */
    public void setHoodUp(boolean value)
    {
        DoubleSolenoid.Value sendVal = value ? Value.kForward: Value.kReverse;
        hoodMover.set(sendVal);
    }

    /**
     * Checks to see what position the hood is in
     * @return The value of the double solenoid
     */
    public boolean isHoodPositionHigh()
    {
        DoubleSolenoid.Value value = hoodMover.get();

        return (value == DoubleSolenoid.Value.kForward) ? true : false;
    }

    /**
     * This sets the speed of the motor using the PID control loop
     * @param speed The speed that we want the motor to be runinng at
     */
    public void setLauncherSpeed(double speed)
    {
        setSetpoint(speed);
    }

    /**
     * This starts the launcher's release roller motor. Turning it on causes the shooter to
     * fire balls. It is likely that a command to shoot balls will turn his on for some period,
     * possibly using one of the ball detectors to determine when to turn it back off again.
     */
    public void startLaunchReleaseMotor()
    {
        releaseRoller.setForwards();
    }

    /**
     * This stops the launcher's release roller motor.
     */
    public void stopLaunchReleaseMotor()
    {
        releaseRoller.setStop();
    }

    /**
     * Don't quite know how this is going to work yet, it could just end up firing one ball,
     * or it could end up firing many balls. This is not implemented yet because the complete
     * action of the ball handling to launcher transfer mechanism is not known yet. However,
     * this method will still operate in the same way.
     * TODO: This method needs to actually be coded, watiting on final operation of mechanism to be able to know for sure.
     * @param numBalls How many balls you want to launch at a time, the deafault is zero
     */
    public void fireBall(int numBalls)
    {

    }
}