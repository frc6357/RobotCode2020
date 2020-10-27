package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SK20Launcher;

/**
 * A command that sets the launcher angle.
 */
public class SetAngleCommand extends CommandBase 
{
    private final SK20Launcher subsystem;
    private boolean setHighAngle;

    /**
     * Takes in the needed subsystem as well as a boolean value to determine if we need to set the angle to be 
     */
    public SetAngleCommand(SK20Launcher subsystem, boolean highAngle) 
    {
        this.subsystem = subsystem;
        this.setHighAngle = highAngle;
        addRequirements(subsystem);
    }

    
    // Called when the command is initially scheduled and sets the hood angle
    @Override
    public void initialize() 
    {
        subsystem.setHoodForHighAngleShot(setHighAngle);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() 
    {
        return true;
    }
}
