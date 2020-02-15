package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SK20BallHandling;

/**
 * A command that launches the ball
 */
public class DefaultBallHandlingCommand extends CommandBase {
    private final SK20BallHandling subsystem;

    /**
     * The
     */
    public DefaultBallHandlingCommand(SK20BallHandling subsystem) {
        this.subsystem = subsystem;
        addRequirements(subsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {

    }

    // Runs once every 20ms
    @Override
    public void execute() {
        if (subsystem.isFull()) {
            subsystem.stopRoller();
        } else {
            subsystem.startRoller();
        }
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
