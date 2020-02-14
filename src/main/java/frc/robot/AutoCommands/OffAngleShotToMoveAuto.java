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
 * This sets up the auto set required to make a shot from an off angle,
 * where the launcher is aimed at the goal so it is able to make a 
 * shot.
 * 
 * This requires that no one will drive behind the robot, but no part of
 * these directly require that the robot can move to the goal and be 
 * straight on so it can make an accurate shot.
 */
public class OffAngleShotToMoveAuto
{
    private SequentialCommandGroup commandGroup;

    public OffAngleShotToMoveAuto(SK20Drive m_driveSubsystem, SK20Launcher m_launcherSubsystem)
    {
        commandGroup = new SequentialCommandGroup(new LauncherActivate(m_launcherSubsystem));
        commandGroup.addCommands(new WaitCommand(TuningParams.LAUNCHER_START_UP_TIME));
        commandGroup.addCommands(new FireNumberBallsCommand(3, m_launcherSubsystem));
        commandGroup.addCommands(new DriveStraightCommand(m_driveSubsystem, TuningParams.AUTO_OFFSETSTMOVE_DRIVE_DISTANCE));
    }
}