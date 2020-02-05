package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
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
        fieldColor =  DriverStation.getInstance().getGameSpecificMessage();

        // Check to see if we get the game color. If we havent reached Stage 3 then the command ends
        // and the motor is turned off.
        if(fieldColor.length() == 0)
        {
            m_subsystem.deactivateSpinnerRoller();
            isDone = true;
        }
        else
        {
            switch(fieldColor.charAt(0))
            {
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
                
                // If we get bad game data we will stop the command and print out what the bad data is
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
        if (targetColor == m_subsystem.getFieldDetectedColor()) {
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
