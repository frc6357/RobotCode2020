package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SK20Launcher;

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
        subsystem.setHoodPositionHigh(setHighAngle);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }
}
