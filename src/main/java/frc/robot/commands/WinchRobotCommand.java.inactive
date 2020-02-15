package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.SK20Climb;

/**
 * An example command that uses an example subsystem.
 */
public class WinchRobotCommand extends CommandBase {
    private final SK20Climb m_subsystem;
    private boolean startWinch; // tells whether or not winch is turned on or not
    private RobotContainer robotContainer;

    /**
     * WinchRobot command tells whether or not to winch or stop winching the robot.
     * 
     * @param subsytem   is the SK20Climb subsystem
     * @param startMotor tells whether or not the winch motor should be on or off
     */
    public WinchRobotCommand(SK20Climb subsystem, Boolean startMotor, RobotContainer robotContainer) {
        m_subsystem = subsystem;
        startWinch = startMotor;
        this.robotContainer = robotContainer;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        // when true start winch
        if (robotContainer.isClimbArmed() && startWinch) {
            m_subsystem.startWinchRobot();
        }
        // when false stop winch
        else {
            m_subsystem.stopWinchRobot();
        }
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }
}
