package frc.robot.test;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.TuningParams;
import frc.robot.commands.TurnRelativeCommand;
import frc.robot.subsystems.SK20Drive;

public class TestTurnInterruptCommandGroup {
    private SequentialCommandGroup testTurnInterruptCommandGroup;

    /**
     * This constructor makes the sequential command group with all the commands in
     * order with a wait time in between all of the commands.
     * 
     * @param driveSubsystem The SK20Drive used to run the commaands.
     * @throws InterruptedException
     */
    public TestTurnInterruptCommandGroup(SK20Drive driveSubsystem) throws InterruptedException{
        testTurnInterruptCommandGroup = new SequentialCommandGroup(
                new TurnRelativeCommand(driveSubsystem, TuningParams.TEST_TURN_5));

        testTurnInterruptCommandGroup.wait(1500);
        
        testTurnInterruptCommandGroup
                .addCommands(new TurnRelativeCommand(driveSubsystem, -TuningParams.TEST_TURN_5));
    }

    /**
     * Runs the all the commands sequentially.
     */
    public void startCommandGroup()
    {
        testTurnInterruptCommandGroup.schedule();
    }
}