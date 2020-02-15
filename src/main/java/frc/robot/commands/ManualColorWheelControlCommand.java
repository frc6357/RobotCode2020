package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.SK20ColorWheel;

/**
 * An example command that uses an example subsystem.
 */
public class ManualColorWheelControlCommand extends CommandBase {
    private final SK20ColorWheel m_subsystem;
    private boolean startMotor;
    private RobotContainer robotContainer;

    
    public ManualColorWheelControlCommand(SK20ColorWheel subsystem , boolean startSpinner,RobotContainer robotContainer) {
        m_subsystem = subsystem;
        startMotor = startSpinner;
        this.robotContainer = robotContainer;
        

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
    }
 
    @Override
    public void initialize() {
        if(){
            m_subsystem.startManuallySpinColorWheel();
        }
        
    }

    @Override
    public void execute() {
        
        super.execute();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }
}