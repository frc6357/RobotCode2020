package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SK20BallHandling;

/**
 * This command toggle the state of the ball management system
 */
public class ToggleBallManagementCommand extends CommandBase
{
    private final SK20BallHandling m_subsystem;

    /**
     * Creates a new ToggleBallManagementCommand that takes in the subsystem needed
     * to change the ball handling system state
     *
     * @param subsystem The subsystem used the command to switch the ball handling
     *                  system state
     */
    public ToggleBallManagementCommand(SK20BallHandling subsystem)
    {
        m_subsystem = subsystem;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(m_subsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize()
    {
        if (m_subsystem.motorsAreEnabled())
        {
            m_subsystem.stopRoller();
        }
        else
        {
            m_subsystem.startRoller();
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}

    // Always returns true because all the work is done in the command
    // initialization.
    @Override
    public boolean isFinished()
    {
        return true;
    }
}