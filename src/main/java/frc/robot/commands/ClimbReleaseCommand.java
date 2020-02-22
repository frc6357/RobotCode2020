package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.SK20Climb;

/**
 * An example command that uses an subsystem.
 */
public class ClimbReleaseCommand extends CommandBase {
    private final SK20Climb m_subsystem;
    private RobotContainer robotContainer;

    /**
     * ClimbReleaseCommand tells that arm is released by pneumatic piston or not.
     */
    public ClimbReleaseCommand(SK20Climb subsystem, RobotContainer robotContainer) {
        m_subsystem = subsystem;
        this.robotContainer = robotContainer;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
    }

    
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void initialize() {
        if (robotContainer.isClimbArmed()) {
            m_subsystem.deployArm();
        }
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }
}
