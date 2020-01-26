package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SK20ColorWheel;

/**
 * An example command that uses an example subsystem.
 */
public class SpinTransition extends CommandBase {
    private final SK20ColorWheel m_subsystem;
    private final int transitionTarget;
    private boolean isDone = false;

    /**
     * Creates a new SpinTransition
     *
     * @param subsystem The subsystem used by this command.
     */
    public SpinTransition(SK20ColorWheel subsystem, int transitionTarget) {
        m_subsystem = subsystem;
        this.transitionTarget = transitionTarget;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_subsystem.resetSpinnerTransitionCount();
        isDone = false;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        //TODO: Review SpinnerRoller activatation and deactivation commands with Rushil
        if (m_subsystem.getSpinnerTransitionCount() < transitionTarget) {
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
