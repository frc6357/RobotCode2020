package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.TuningParams;
import frc.robot.subsystems.SK20ColorWheel;
import frc.robot.subsystems.base.Color2020;
import edu.wpi.first.wpilibj.DriverStation;

/**
 * A command that turns the control panel to the desired color for the field.
 */
public class SpinToColorCommand extends CommandBase {
    private final SK20ColorWheel m_subsystem;
    private Color2020 targetColor = Color2020.NONE;
    private boolean isDone = false;
    private String fieldColor;
    private Color2020[] colorArray = new Color2020[TuningParams.COLOR_WHEEL_ARRAY_SIZE];
    private int indexOfColorArray = 0;
    private boolean colorArrayIsFull = false;
    private Color2020 currentFieldColor = Color2020.UNKNOWN;

    /**
     * Constructor that creates a new SpinToColorCommand and sets what we want the
     * target color to be.
     *
     * @param subsystem The subsystem used by this command to determine the field
     *                  color and control the motors that spin the color wheel.
     */
    public SpinToColorCommand(SK20ColorWheel subsystem) {
        m_subsystem = subsystem;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(m_subsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        isDone = false;
        fieldColor = DriverStation.getInstance().getGameSpecificMessage();
        indexOfColorArray = 0;
        colorArrayIsFull = false;

        // Check to see if we get the game color. If we havent reached Stage 3 then the
        // command ends
        // and the motor is turned off.
        if (fieldColor.length() == 0) {
            m_subsystem.deactivateSpinnerRoller();
            isDone = true;
        } else {
            switch (fieldColor.charAt(0)) {
            case 'R':
                targetColor = Color2020.RED;
                break;

            case 'G':
                targetColor = Color2020.GREEN;
                break;

            case 'B':
                targetColor = Color2020.CYAN;
                break;

            case 'Y':
                targetColor = Color2020.YELLOW;
                break;

            // If we get bad game data we will stop the command and print out what the bad
            // data is
            default:
                System.out.println("Got unknown game data" + fieldColor);
                targetColor = Color2020.NONE;
                m_subsystem.deactivateSpinnerRoller();
                isDone = true;
                break;

            }
        }
    }

    /**
     * This method, which usually runs every 20ms, spins the control panel until the
     * control panel is in the position so that the field reads the color we want it
     * to read.
     */
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // Updates the color array.
        colorArray[indexOfColorArray] = m_subsystem.getFieldDetectedColor();

        // This line is used to make sure that the index values don't go out of bounds.
        indexOfColorArray = (indexOfColorArray + 1) % TuningParams.COLOR_WHEEL_ARRAY_SIZE;

        // This checks that the color array has been filled
        if (indexOfColorArray == 0) {
            colorArrayIsFull = true;
        }

        // This code is to run once the color array has been filled in at least once.
        if (colorArrayIsFull) {

            // This part checks that all of values in the color array are the same to
            // unbounce the reading.
            for (int i = 1; i < TuningParams.COLOR_WHEEL_ARRAY_SIZE; i++) {
                if (colorArray[i] != colorArray[0]) {
                    return;
                }
            }

            // Sets what the field color is reading.
            currentFieldColor = colorArray[0];
        }

        // Checks to see that the current field color we are reading is the color we
        // want the field to read.
        if (targetColor == currentFieldColor) {
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
