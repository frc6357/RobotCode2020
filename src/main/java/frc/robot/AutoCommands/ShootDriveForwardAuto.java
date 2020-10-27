package frc.robot.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.TuningParams;
import frc.robot.commands.DriveStraightCommand;
import frc.robot.commands.FireForTimeCommand;
import frc.robot.commands.LauncherActivate;
import frc.robot.commands.SetAngleCommand;
import frc.robot.subsystems.SK20Drive;
import frc.robot.subsystems.SK20Launcher;

/**
 * This auto sequence will shoot 3 balls into the goal and go off the line by going forward.
 * Assumes that we are directly forwards of the goal, square with the wall.
 */
public class ShootDriveForwardAuto
{
    public SequentialCommandGroup commandGroup;

    public ShootDriveForwardAuto(SK20Drive m_driveSubsystem, SK20Launcher m_launcherSubsystem)
    {
        // Must set angle first or laucher speed is wrong.
        commandGroup = new SequentialCommandGroup(new SetAngleCommand(m_launcherSubsystem, false));
        commandGroup.addCommands(new LauncherActivate(m_launcherSubsystem, true) );
        commandGroup.addCommands(new WaitCommand(TuningParams.LAUNCHER_START_UP_TIME));
        commandGroup.addCommands(new FireForTimeCommand(TuningParams.AUTO_FIRE_ALL_BALLS_TIME, m_launcherSubsystem));
        commandGroup.addCommands(new DriveStraightCommand(m_driveSubsystem, TuningParams.AUTO_DRIVE_DISTANCE));
    }

    public CommandGroupBase getCommandGroup() {
        return commandGroup;
    }

    public void schedule()
    {
        commandGroup.schedule();
    }
}