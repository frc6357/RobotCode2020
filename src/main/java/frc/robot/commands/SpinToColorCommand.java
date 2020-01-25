package frc.robot.commands;

import frc.robot.subsystems.SK20ColorWheel;
import frc.robot.subsystems.base.Color2020;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class SpinToColorCommand extends CommandBase {
    private final SK20ColorWheel m_subsystem;
    private final Color2020 targetColor;
    private boolean isDone = false;

    /**
     * Constructor that creates a new SpinToColorCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public SpinToColorCommand(SK20ColorWheel subsystem, Color2020 targetColor) {
        m_subsystem = subsystem;
        this.targetColor = targetColor;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        //TODO: Review SpinnerRoller activatation and deactivation commands with Rushil
        if (m_subsystem.getFieldDetectedColor() != targetColor) {
            m_subsystem.activateSpinnerRoller();
        } else {
            m_subsystem.deactivateSpinnerRoller();
            isDone = true;
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        isDone = true;
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return isDone;
    }
}
