package frc.robot.AutoCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.TuningParams;
import frc.robot.commands.DriveStraightCommand;
import frc.robot.commands.FireNumberBallsCommand;
import frc.robot.commands.LauncherActivate;
import frc.robot.subsystems.SK20Drive;
import frc.robot.subsystems.SK20Launcher;

/**
 * This class is the basic class for a basic auto where the robot starts the launcher
 * waits for the time it needs to start up, then feeds three balls for launching.
 * It then leaves the launcher running at the set speed, and moves completely off
 * of the line to score the extra points in auto.
 * 
 * This is the most basic of our auto functions as it sets up everything in the most
 * basic method.
 * 
 * This command assumes that the robot starts on the line directly in front of the
 * goal and that none of our alliance robots are going to be driving up to the goal
 * or crossing behind our robot as we're reversing.
 */
public class StraightShotToMoveAuto
{
    private SequentialCommandGroup commandGroup;

    public StraightShotToMoveAuto(SK20Drive m_driveSubsystem, SK20Launcher m_launcherSubsystem)
    {
        commandGroup = new SequentialCommandGroup(new LauncherActivate(m_launcherSubsystem));
        commandGroup.addCommands(new WaitCommand(TuningParams.LAUNCHER_START_UP_TIME));
        commandGroup.addCommands(new FireNumberBallsCommand(3, m_launcherSubsystem));
        commandGroup.addCommands(new DriveStraightCommand(m_driveSubsystem, TuningParams.AUTO_STRAIGHTSTMOVE_DRIVE_DISTANCE));
    }
}