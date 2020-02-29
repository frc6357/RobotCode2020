package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
// import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
// import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;
import frc.robot.TuningParams;
import frc.robot.commands.LauncherActivate;
import frc.robot.subsystems.base.BaseRoller;

// TODO: IMPORTANT! This class contains a PID controller for the launcher speed
// but this has never actually been tested or tuned. If we don't have time to do
// this or if it ends up taking longer than we can affort, it may be a good idea
// to have a fallback which merely sets the motor speed and leaves it running
// open loop (without a controller) (DW).

/**
 * This is the launcher subsystem that controls everything that has to do with 
 * the launcher including the transfer from the handling system to the launcher
 * as well the speed of the launcher and the position of the hood to change the set angle.
 * 
 * launcherMotor is the motor used to spin the high energy flywheel launch roller.
 * releaseMotor is the motor driving the roller which feeds balls into the launch roller.
 */
public class SK20Launcher extends SubsystemBase
{
    private final CANSparkMax launcherMotor = new CANSparkMax(Ports.ballLauncherMotor, MotorType.kBrushless);
    // private final CANPIDController PIDControl = launcherMotor.getPIDController();
    private final CANEncoder launcherMotorEncoder = new CANEncoder(launcherMotor);

    private final CANSparkMax releaseMotor = new CANSparkMax(Ports.ballReleaseMotor, MotorType.kBrushless);
    private final BaseRoller releaseRoller = new BaseRoller(releaseMotor, TuningParams.RELEASE_MOTOR_SPEED);

    private final DoubleSolenoid hoodMover = new DoubleSolenoid(Ports.pcm, Ports.launcherHoodExtend, Ports.launcherHoodRetract);

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
        // setPIDValues();
        // TODO: IMPORTANT: RENEBALE THIS TO MAKE LAUNCHER WORK
        // setDefaultCommand(new LauncherActivate(this));
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
     * Sets the PID values for the controller
     * @param pVal The proportianal value
     * @param iVal The integral value
     * @param dVal The derivative value
     */
    /**private void setPIDValues()
    {
        PIDControl.setP(TuningParams.LAUNCHER_P_VALUE);
        PIDControl.setI(TuningParams.LAUNCHER_I_VALUE);
        PIDControl.setD(TuningParams.LAUNCHER_D_VALUE);
    }*/

    /**
     * Sets the setpoint of the PID controller
     * @param value The set double value
     */
    private void setSetpoint(double value)
    {
        // PIDControl.setReference(value, ControlType.kVelocity);
        launcherMotor.set(value);
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
     * fire balls.
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
}