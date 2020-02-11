package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SK20Launcher;

// TODO: We may like to rethink how we use this knowing, now, that the hood will
// interfere with the control panel if it is in the high position. We need to 
// make sure we leave it in the low position whenever we're driving and only
// switch to high when we're stopped and ready to shoot.

/**
 * An example command that uses an subsystem.
 */
public class SetLaunchAngleHighCommand extends CommandBase {
    private final SK20Launcher subsystem;
    private boolean setHighAngle;

    /**
     * The 
     */
    public SetLaunchAngleHighCommand(SK20Launcher subsystem, boolean highAngle) {
        this.subsystem = subsystem;
        this.setHighAngle = highAngle;
        addRequirements(subsystem);
    }

    
    // Called when the command is initially scheduled and sets the hood angle
    @Override
    public void initialize() {
        subsystem.setHoodUp(setHighAngle);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }
}
