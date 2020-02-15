package frc.robot.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.TuningParams;
import frc.robot.commands.DriveStraightCommand;
import frc.robot.commands.FireNumberBallsCommand;
import frc.robot.commands.LauncherActivate;
import frc.robot.commands.ToggleIntakeCommand;
import frc.robot.commands.TurnRelativeCommand;
import frc.robot.subsystems.SK20Drive;
import frc.robot.subsystems.SK20Intake;
import frc.robot.subsystems.SK20Launcher;

/**
 * This command starts at a straight shot to the goal, shoots all three balls
 * then turns 90 degrees, moves forwards to about the wall area. At that point
 * it turns 90 degrees again to be able to run down the trench run where it
 * can pick up the five balls inside.
 * 
 * This assumes that we are able to get a straight shot on the goal, then have
 * none of our alliance members try to enter the trench run, or get in the
 * path of the robot.
 */
public class StraightShotToTrenchAuto
{
    private SequentialCommandGroup commandGroup;
    private SK20Drive m_driveSubsystem; 
    private SK20Launcher m_launcherSubsystem;
    private SK20Intake m_intakeSubsystem;

    public StraightShotToTrenchAuto(SK20Drive m_driveSubsystem, SK20Launcher m_launcherSubsystem, SK20Intake m_intakeSubsytem)
    {
        this.m_driveSubsystem = m_driveSubsystem;
        this.m_launcherSubsystem = m_launcherSubsystem;
        this.m_intakeSubsystem = m_intakeSubsytem;

        // This command group follows a path of starting launcher, shooting balls, then moving to pick
        // up in the trench run.
        commandGroup = new SequentialCommandGroup(new LauncherActivate(this.m_launcherSubsystem));
        commandGroup.addCommands(new WaitCommand(TuningParams.LAUNCHER_START_UP_TIME));
        commandGroup.addCommands(new FireNumberBallsCommand(3, this.m_launcherSubsystem));
        commandGroup.addCommands(new TurnRelativeCommand(this.m_driveSubsystem, TuningParams.AUTO_STRAIGHTSTTRENCH_TURN_ANGLE));
        commandGroup.addCommands(new DriveStraightCommand(this.m_driveSubsystem, TuningParams.AUTO_STRAIGHTSTTRENCH_DRIVE_DISTANCE_1));
        commandGroup.addCommands(new TurnRelativeCommand(this.m_driveSubsystem, TuningParams.AUTO_STRAIGHTSTTRENCH_TURN_ANGLE));
        commandGroup.addCommands(new ToggleIntakeCommand(this.m_intakeSubsystem));
        commandGroup.addCommands(new DriveStraightCommand(this.m_driveSubsystem, TuningParams.AUTO_TRENCH_DRIVE_DISTANCE));
        commandGroup.addCommands(new ToggleIntakeCommand(this.m_intakeSubsystem));
    }

    public CommandGroupBase getCommandGroup() {
        return commandGroup;
    }

    public void schedule()
    {
        commandGroup.schedule();
    }
}