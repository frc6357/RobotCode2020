package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SK20ColorWheel;

/**
 * An example command that uses an example subsystem.
 */
public class ManualColorWheelControlCommand extends CommandBase {
    private final SK20ColorWheel m_subsystem;
    private boolean startMotor;

    public ManualColorWheelControlCommand(SK20ColorWheel subsystem, boolean startSpinner) {
        m_subsystem = subsystem;
        startMotor = startSpinner;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
    }

    @Override
    public void initialize()
    {
        if (startMotor)
        {
            m_subsystem.activateSpinnerRoller();
        }
        else
        {
            m_subsystem.deactivateSpinnerRoller();
        }
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished()
    {
        return true;
    }
}