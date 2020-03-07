package frc.robot.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.TuningParams;
import frc.robot.commands.DriveStraightCommand;
import frc.robot.commands.FireNumberBallsCommand;
import frc.robot.commands.LauncherActivate;
import frc.robot.commands.SetAngleCommand;
import frc.robot.subsystems.SK20Drive;
import frc.robot.subsystems.SK20Launcher;

/**
 * This auto sequence will go forwards to the wall then shoot 3 balls into the goal.
 * Assumes that we are directly forwards of the goal, square with the wall.
 */
public class DriveForwardShootAuto
{
    public SequentialCommandGroup commandGroup;

    public DriveForwardShootAuto(SK20Drive m_driveSubsystem, SK20Launcher m_launcherSubsystem)
    {
        // Must set angle first or laucher speed is wrong.
        commandGroup = new SequentialCommandGroup(new SetAngleCommand(m_launcherSubsystem, true));
        commandGroup.addCommands(new LauncherActivate(m_launcherSubsystem, true) );
        commandGroup.addCommands(new DriveStraightCommand(m_driveSubsystem, TuningParams.AUTO_DRIVE_DISTANCE_TO_WALL));
        commandGroup.addCommands(new FireNumberBallsCommand(3, m_launcherSubsystem));   
    }

    public CommandGroupBase getCommandGroup() {
        return commandGroup;
    }

    public void schedule()
    {
        commandGroup.schedule();
    }
}