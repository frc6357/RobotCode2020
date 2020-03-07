package frc.robot.test;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SK20Drive;

public class TestDummyDriveCommand extends CommandBase{

    /**
     * This command is used to interrupt any existing drive command.
     * 
     * @param driveSubsystem The SK20Drive used to run the commands.
     */
    public TestDummyDriveCommand(SK20Drive driveSubsystem) {
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(driveSubsystem);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }
}