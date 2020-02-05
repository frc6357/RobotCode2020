package frc.robot.test;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.TuningParams;
import frc.robot.commands.DriveStraightCommand;
import frc.robot.subsystems.SK20Drive;

public class TestStraightCommandGroup {
    private SequentialCommandGroup testStraightCommandGroup;

    /**
     * This constructor makes the sequential command group with all the commands in
     * order with a wait time in between all of the commands.
     * 
     * @param driveSubsystem The SK20Drive used to run the commaands.
     * @throws InterruptedException
     */
    public TestStraightCommandGroup(SK20Drive driveSubsystem) throws InterruptedException {
        testStraightCommandGroup = new SequentialCommandGroup(
                new DriveStraightCommand(driveSubsystem, TuningParams.TEST_DRIVE_STRAIGHT_1));
        testStraightCommandGroup.wait(1500);

        testStraightCommandGroup
                .addCommands(new DriveStraightCommand(driveSubsystem, -TuningParams.TEST_DRIVE_STRAIGHT_1));
        testStraightCommandGroup.wait(1500);

        testStraightCommandGroup
                .addCommands(new DriveStraightCommand(driveSubsystem, TuningParams.TEST_DRIVE_STRAIGHT_2));
        testStraightCommandGroup.wait(1500);

        testStraightCommandGroup
                .addCommands(new DriveStraightCommand(driveSubsystem, -TuningParams.TEST_DRIVE_STRAIGHT_2));
        testStraightCommandGroup.wait(1500);

        testStraightCommandGroup
                .addCommands(new DriveStraightCommand(driveSubsystem, TuningParams.TEST_DRIVE_STRAIGHT_3));
        testStraightCommandGroup.wait(1500);

        testStraightCommandGroup
                .addCommands(new DriveStraightCommand(driveSubsystem, -TuningParams.TEST_DRIVE_STRAIGHT_3));
        testStraightCommandGroup.wait(1500);
    }

    /**
     * Runs the all the commands sequentially.
     */
    public void startCommandGroup() {
        testStraightCommandGroup.schedule();
    }
}