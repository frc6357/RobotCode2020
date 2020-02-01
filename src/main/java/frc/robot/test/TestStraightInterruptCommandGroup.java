package frc.robot.test;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.TuningParams;
import frc.robot.commands.DriveStraightCommand;
import frc.robot.commands.TurnRelativeCommand;
import frc.robot.subsystems.SK20Drive;

public class TestStraightInterruptCommandGroup {
    private SequentialCommandGroup testStraightInterruptCommandGroup;

    /**
     * This constructor makes the sequential command group with all the commands in
     * order with a wait time in between all of the commands.
     * 
     * @param driveSubsystem The SK20Drive used to run the commaands.
     * @throws InterruptedException
     */
    public TestStraightInterruptCommandGroup(SK20Drive driveSubsystem) throws InterruptedException {
        testStraightInterruptCommandGroup = new SequentialCommandGroup(
                new DriveStraightCommand(driveSubsystem, TuningParams.TEST_DRIVE_STRAIGHT_3));

        testStraightInterruptCommandGroup.wait(2000);
        
        testStraightInterruptCommandGroup
                .addCommands(new TurnRelativeCommand(driveSubsystem, TuningParams.TEST_TURN_4));
    }

    /**
     * Runs the all the commands sequentially.
     */
    public void startCommandGroup() {
        testStraightInterruptCommandGroup.schedule();
    }
}