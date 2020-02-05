package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SK20ColorWheel;
import frc.robot.subsystems.base.Color2020;

/**
 * A command that is used to spin the control panel the desired amount of
 * transitions (color slices)
 */
public class SpinTransition extends CommandBase {
    private final SK20ColorWheel m_subsystem;
    private final int transitionCount;
    private boolean isDone = false;
    private Color2020 colorPrevious;


    /**
     * Creates a new instance of the SpinTransition and takes in the subsystem and the amount of
     * color transitions we want the control panel to achieve.
     * 
     * @param subsystem        The subsystem we plan on using to control the color
     *                         wheel parts.
     * @param transitionCount The amount of colors we want the wheel to rotate.
     */
    public SpinTransition(SK20ColorWheel subsystem, int transitionCount) {
        m_subsystem = subsystem;
        this.transitionCount = transitionCount;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(m_subsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_subsystem.resetSpinnerTransitionCount();
        colorPrevious = m_subsystem.getDetectedColor();
        isDone = false;
    }


    /**
     * This method spins the control panel until the desired amount of transitions
     * to rotate is met. It also increments the amount of color transitions by
     * looking if the color on the control panel has changed.
     */
    @Override
    public void execute() {
        Color2020 detectedColor = m_subsystem.getDetectedColor();
        if (colorPrevious != detectedColor) {
            m_subsystem.incrementSpinnerTransitionCount();
            colorPrevious = detectedColor;
        }

        if (m_subsystem.getSpinnerTransitionCount() >= transitionCount) {
            m_subsystem.deactivateSpinnerRoller();
            m_subsystem.resetSpinnerTransitionCount();
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
