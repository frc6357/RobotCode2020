package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SK20ColorWheel;
import frc.robot.subsystems.base.Color2020;

/**
 * A default color wheel command that reports the field detected color to the
 * driver's station
 */
public class DefaultColorWheelCommand extends CommandBase
{
    private final SK20ColorWheel m_subsystem;
    private Color2020 prevColor;

    /**
     * Creates a new DefaultColorWheelCommand that sets up the member subsystem.
     *
     * @param subsystem The subsystem used by the command to read the colors
     */
    public DefaultColorWheelCommand(SK20ColorWheel subsystem)
    {
        m_subsystem = subsystem;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(m_subsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize()
    {
        prevColor = Color2020.NONE;
    }

    /**
     * This method, which is usually run every 20ms, reads and reports the field
     * detected color to the driver's station
     */
    @Override
    public void execute()
    {
        Color2020 readColor = m_subsystem.getDebouncedFieldDetectedColor();
        if (prevColor != readColor)
        {
            prevColor = readColor;
            m_subsystem.reportColor(prevColor);
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}

    // Returns true when the command should end which in this case is never.
    @Override
    public boolean isFinished()
    {
        return false;
    }
}
