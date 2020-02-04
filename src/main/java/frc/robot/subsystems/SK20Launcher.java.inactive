package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;
import frc.robot.TuningParams;
import frc.robot.subsystems.base.BaseRoller;

/**
 * This is the launcher subsystem that controls everything that has to do with the launcher including the transfer from the handling system to the launcher
 * as well the speed of the launcher and the position of the hood to change the set angle
 */
public class SK20Launcher extends SubsystemBase
{
    private final CANSparkMax launcherMotor = new CANSparkMax(Ports.ballLauncherMotor, MotorType.kBrushless);
    private final CANPIDController PIDControl = launcherMotor.getPIDController();

    private final CANSparkMax transferMotor = new CANSparkMax(Ports.ballLoaderMotor, MotorType.kBrushless);   
    private final BaseRoller transferRoller = new BaseRoller(transferMotor, TuningParams.LOADER_MAX_SPEED);

    private final CANEncoder launcherMotorEncoder = new CANEncoder(launcherMotor);
    private final CANEncoder transferMotorEncoder = new CANEncoder(transferMotor);

    private final DoubleSolenoid hoodMover = new DoubleSolenoid(Ports.launcherHoodExtend, Ports.launcherHoodRetract);

    /**
     * This does nothing as everything is intialized inside of the class before the constructor is even called so that 
     * we can guarantee 
     */
    public SK20Launcher()
    {
        
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
    public void setPIDValues(int pVal, int iVal, int dVal)
    {
        PIDControl.setP(pVal);
        PIDControl.setI(iVal);
        PIDControl.setD(dVal);
    }

    /**
     * Sets the setpoint of the PID controller
     * @param value The set double value
     */
    public void setSetpoint(double value)
    {
        PIDControl.setReference(value, ControlType.kVelocity);
    }

    /**
     * It sets the hood according to whatever position it is passed
     */
    public void setHoodPosition(DoubleSolenoid.Value value)
    {
        hoodMover.set(value);
    }

    /**
     * Checks to see what position the hood is in
     * @return The value of the double solenoid
     */
    public DoubleSolenoid.Value getHoodPosition()
    {
        return hoodMover.get();
    }

    /**
     * This sets the speed of the motor using the PID control loop
     * TODO: Check to see if this is actually setting the speed using the PID control loop rather than just setting the speed manually
     * @param speed The speed that we want the motor to be runinng at
     */
    public void setLauncherSpeed(double speed)
    {
        launcherMotor.set(speed);
    }
}