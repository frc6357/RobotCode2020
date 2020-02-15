package frc.robot.AutoCommands;

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
 * This is the command group that sets up the path for auto for when we
 * start offset in front of the trench run, shoot our 3 balls, and then 
 * turn around and pick up the 5 balls in the trench run.
 */
public class OffAngleShotToTrenchAuto
{
    private SK20Drive m_driveSubsystem;
    private SK20Intake m_intakeSubsystem;
    private SK20Launcher m_launcherSubsystem;

    private SequentialCommandGroup commandGroup;

    public OffAngleShotToTrenchAuto(SK20Drive m_driveSubsystem, SK20Intake m_intakeSubsystem, SK20Launcher m_launcherSubsystem)
    {
        this.m_driveSubsystem = m_driveSubsystem;
        this.m_intakeSubsystem = m_intakeSubsystem;
        this.m_launcherSubsystem = m_launcherSubsystem;

        commandGroup = new SequentialCommandGroup(new LauncherActivate(this.m_launcherSubsystem));
        commandGroup.addCommands(new WaitCommand(TuningParams.LAUNCHER_START_UP_TIME));
        commandGroup.addCommands(new FireNumberBallsCommand(3, this.m_launcherSubsystem));
        commandGroup.addCommands(new TurnRelativeCommand(this.m_driveSubsystem, TuningParams.AUTO_OFFSETSTTRENCH_TURN_ANGLE_1));
        commandGroup.addCommands(new ToggleIntakeCommand(this.m_intakeSubsystem));
        commandGroup.addCommands(new DriveStraightCommand(this.m_driveSubsystem, TuningParams.AUTO_TRENCH_DRIVE_DISTANCE));
        commandGroup.addCommands(new ToggleIntakeCommand(this.m_intakeSubsystem));
    }
}