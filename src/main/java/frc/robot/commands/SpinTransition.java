package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.TuningParams;
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
    private Color2020[] colorRay = new Color2020[TuningParams.COLOR_WHEEL_ARRAY_SIZE];
    private int indexOfColorRay = 0;
    private boolean colorArrayIsFull = false;
    private boolean firstColorSet = false;

    /**
     * Creates a new instance of the SpinTransition and takes in the subsystem and
     * the amount of color transitions we want the control panel to achieve.
     * 
     * @param subsystem       The subsystem we plan on using to control the color
     *                        wheel parts.
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
        this.colorPrevious = Color2020.UNKNOWN;
        indexOfColorRay = 0;
        colorArrayIsFull = false;
        isDone = false;
    }

    /**
     * This method spins the control panel until the desired amount of transitions
     * to rotate is met. It also increments the amount of color transitions by
     * looking if the color on the control panel has changed. It also 'debounces'
     * the values to ensure our change in values are correct.
     */
    @Override
    public void execute() {
        // Updates the color array.
        colorRay[indexOfColorRay] = m_subsystem.getFieldDetectedColor();

        // This line is used to make sure that the index values don't go out of bounds.
        indexOfColorRay = (indexOfColorRay + 1) % TuningParams.COLOR_WHEEL_ARRAY_SIZE;

        // This checks that the color array has been filled
        if (indexOfColorRay == 0) {
            colorArrayIsFull = true;
        }

        // This code is to run once the color array has been filled in at least once.
        if (colorArrayIsFull) {

            // This part checks that all of values in the color array are the same to
            // unbounce the reading.
            for (int i = 1; i < TuningParams.COLOR_WHEEL_ARRAY_SIZE; i++) {
                if (colorRay[i] != colorRay[0]) {
                    return;
                }
            }

            // Checks to see if the original read color was set to make sure transitions are
            // calculated correctly.
            if (!firstColorSet) {
                colorPrevious = colorRay[0];
                firstColorSet = true;
            }

            // Checks to see if the wheel has actually changed and increments the transition
            // amount if the value has actually changed.
            if (colorRay[0] != colorPrevious) {
                m_subsystem.incrementSpinnerTransitionCount();
                colorPrevious = colorRay[0];

                // Update the driver station.
                m_subsystem.reportColor(colorPrevious);
            }
        }

        // If we have reached the desired transition amount, we will deactivate
        // everything and retract the mechanism.
        if (m_subsystem.getSpinnerTransitionCount() >= transitionCount) {
            m_subsystem.deactivateSpinnerRoller();
            m_subsystem.retractLifter();
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
