package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SK20ColorWheel;
import frc.robot.subsystems.base.Color2020;

/**
 * A command that turns the control panel to the desired color for the field.
 */
public class SpinToColorCommand extends CommandBase {
    private final SK20ColorWheel m_subsystem;
    private final Color2020 targetColor;
    private boolean isDone = false;

    /**
     * Constructor that creates a new SpinToColorCommand and sets what we want the
     * target color to be.
     *
     * @param subsystem The subsystem used by this command to determine the field
     *                  color and control the motors that spin the color wheel.
     */
    public SpinToColorCommand(SK20ColorWheel subsystem, Color2020 targetColor) {
        m_subsystem = subsystem;
        this.targetColor = targetColor;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(m_subsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        isDone = false;
    }

    /**
     * This method, which usually runs every 20ms, spins the control panel until the
     * control panel is in the position so that the field reads the color we want it
     * to read.
     */
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // TODO: Review SpinnerRoller activation and deactivation commands with Rushil
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
        // If we were interrupted, for safety, stop the motor.
        if (interrupted) {
            m_subsystem.deactivateSpinnerRoller();
            isDone = true;
        }
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return isDone;
    }
}
