package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SK20ColorWheel;
import frc.robot.subsystems.base.Color2020;

// TODO: REVIEW - this comment needs to be updated.
/**
 * A command that is used to spin the control panel the desired amount of
 * transitions (color slices)
 */
public class SpinTransition extends CommandBase {
    private final SK20ColorWheel m_subsystem;
    private final int transitionTarget;
    private boolean isDone = false;
    private Color2020 colorCurrent;

    // TODO: REVIEW - this comment is somewhat pointless. A constructor always
    // creates a new instance of the object. You need to update this to explain
    // what the command does and what the two parameters to the constructor are.

    /**
     * Creates a new SK20ColorWheel and takes in the subsystem and the amount of
     * color transitions we want the control panel to achieve.
     * 
     * @param subsystem        The subsystem we plan on using to control the color
     *                         wheel parts.
     * @param transitionTarget The amount of colors we want the wheel to rotate.
     */
    public SpinTransition(SK20ColorWheel subsystem, int transitionTarget) {
        m_subsystem = subsystem;
        this.transitionTarget = transitionTarget;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(m_subsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_subsystem.resetSpinnerTransitionCount();
        colorCurrent = m_subsystem.getDetectedColor();
        isDone = false;
    }

    // TODO: REVIEW - this comment needs to be updated to explain what the command
    // is actually doing every time this function is called (every 20mS).

    // TODO: REVIEW - this command will not do what is needed. Comments in the
    // SK20ColorWheel class state that the command is responsible for reading the
    // color sensor, detecting transitions, and updating the transition counter
    // as it runs. This implementation seems to assume that something else is
    // doing this.

    // TODO: REVIEW - Consider whether you need to accelerate and decelerate the
    // roller rather than just starting and stopping it immediately. This could
    // reduce wear on the friction wheels.
    // TODO: Look how we can so this due to the way that BaseRoller is made.

    // TODO: REVIEW - Consider when you end the command. Once you can detect
    // transitions, do you want to stop immediately after the last transition or
    // try to move the wheel such that the sensor is right in the middle of the
    // color quadrant (thus making it more likely that the field sensor is not
    // positioned right over a transition and reading an unstable or incorrect
    // color).

    /**
     * This method spins the control panel until the desired amount of transitions
     * to rotate is met. It also increments the amount of color transitions by
     * looking if the color on the control panel has changed.
     */
    @Override
    public void execute() {
        Color2020 detectedColor = m_subsystem.getDetectedColor();
        // TODO: Review SpinnerRoller activation and deactivation commands with Rushil
        if (m_subsystem.getSpinnerTransitionCount() < transitionTarget) {
            m_subsystem.activateSpinnerRoller();
        } else {
            m_subsystem.deactivateSpinnerRoller();
            isDone = true;
        }
        if (colorCurrent != detectedColor) {
            m_subsystem.incrementSpinnerTransitionCount();
            colorCurrent = detectedColor;
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
