package frc.robot.test;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.TuningParams;
import frc.robot.commands.TurnRelativeCommand;
import frc.robot.subsystems.SK20Drive;

public class TestTurnCommandGroup {
    private SequentialCommandGroup testTurnCommandGroup;

    /**
     * This constructor makes the sequential command group with all the commands in
     * order with a wait time in between all of the commands.
     * 
     * @param driveSubsystem The SK20Drive used to run the commaands.
     * @throws InterruptedException
     */
    public TestTurnCommandGroup(SK20Drive driveSubsystem) throws InterruptedException {
        testTurnCommandGroup = new SequentialCommandGroup(
                new TurnRelativeCommand(driveSubsystem, TuningParams.TEST_TURN_1));
        testTurnCommandGroup.wait(1500);

        testTurnCommandGroup.addCommands(new TurnRelativeCommand(driveSubsystem, TuningParams.TEST_TURN_2));
        testTurnCommandGroup.wait(1500);

        testTurnCommandGroup.addCommands(new TurnRelativeCommand(driveSubsystem, TuningParams.TEST_TURN_3));
        testTurnCommandGroup.wait(1500);

        testTurnCommandGroup.addCommands(new TurnRelativeCommand(driveSubsystem, TuningParams.TEST_TURN_4));
        testTurnCommandGroup.wait(1500);

        testTurnCommandGroup.addCommands(new TurnRelativeCommand(driveSubsystem, TuningParams.TEST_TURN_5));
        testTurnCommandGroup.wait(1500);

        testTurnCommandGroup.addCommands(new TurnRelativeCommand(driveSubsystem, -TuningParams.TEST_TURN_1));
        testTurnCommandGroup.wait(1500);

        testTurnCommandGroup.addCommands(new TurnRelativeCommand(driveSubsystem, -TuningParams.TEST_TURN_2));
        testTurnCommandGroup.wait(1500);

        testTurnCommandGroup.addCommands(new TurnRelativeCommand(driveSubsystem, -TuningParams.TEST_TURN_3));
        testTurnCommandGroup.wait(1500);

        testTurnCommandGroup.addCommands(new TurnRelativeCommand(driveSubsystem, -TuningParams.TEST_TURN_4));
        testTurnCommandGroup.wait(1500);

        testTurnCommandGroup.addCommands(new TurnRelativeCommand(driveSubsystem, -TuningParams.TEST_TURN_5));
        testTurnCommandGroup.wait(1500);
    }

    /**
     * Runs the all the commands sequentially.
     */
    public void startCommandGroup() {
        testTurnCommandGroup.schedule();
    }

}