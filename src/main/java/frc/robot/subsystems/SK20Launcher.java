package frc.robot.subsystems;

// import com.revrobotics.CANEncoder;
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

public class SK20Launcher extends SubsystemBase
{
    private CANSparkMax launcherMotor = new CANSparkMax(Ports.ballLauncherMotor, MotorType.kBrushless);
    private CANPIDController PIDControl = launcherMotor.getPIDController();

    private CANSparkMax transferMotor = new CANSparkMax(Ports.ballLoaderMotor, MotorType.kBrushless);
    private BaseRoller transferRoller = new BaseRoller(transferMotor, TuningParams.LOADER_MAX_SPEED);
    // private CANEncoder launcherMotorEncoder = new CANEncoder(launcherMotor);

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

    }

    /**
     * This returns the current encoder speed of the launcher
     * @return The current encoder speed of the launcher
     */
    public double getLauncherSpeed()
    {
        return 1.0;
    }

    /**
     * This will return the encoder speed of the transfer motors
     * @return The current speed of the transfer motors
     */
    public double getTransferSpeed()
    {

        return 1.0;
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
        
    }

    /**
     * Checks to see what position the hood is in
     * @return The value of the double solenoid
     */
    public DoubleSolenoid.Value getHoodPosition()
    {
        return Value.kForward;
    }
}