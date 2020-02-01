package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SK20Climb;

/**
 * An example command that uses an subsystem.
 */
public class ClimbReleaseCommand extends CommandBase {
    private final SK20Climb m_subsystem;
    private double time;
    private String debugger;

    /**
     * ClimbReleaseCommand tells that arm is released by pneumatic piston or not.
     */
    public ClimbReleaseCommand(SK20Climb subsystem) {
        m_subsystem = subsystem;
        time = DriverStation.getInstance().getMatchTime();
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
    }

    
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        debugger = DriverStation.getInstance().getGameSpecificMessage();

        if (time <= 30 || debugger == "D") {
            m_subsystem.deployArm();
        } else if (time > 30) {
            // For Testing Reasons
            System.out.println("Time is not for climb");
        } else {
            // For Testing Reasons
            System.out.println("Time is not known");
        }

    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }
}
