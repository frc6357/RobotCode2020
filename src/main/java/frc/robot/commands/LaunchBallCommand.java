package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.TuningParams;
import frc.robot.subsystems.SK20Launcher;

/**
 * A command that launches the ball
 */
public class LaunchBallCommand extends CommandBase {
    private final SK20Launcher subsystem;
    private int runTimeCount;
    private boolean isDone = false;

    /**
     * The 
     */
    public LaunchBallCommand(SK20Launcher subsystem) {
        this.subsystem = subsystem;
        addRequirements(subsystem);
    }

    
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        runTimeCount = 0;
        subsystem.startLaunchReleaseMotor();
        isDone = false;
    }

    // Runs once every 20ms
    @Override
    public void execute() {
        if (runTimeCount >= TuningParams.RELEASE_MOTOR_RUNTIME) {
           subsystem.stopLaunchReleaseMotor();
           isDone = true;
        }
        runTimeCount += 20;
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return isDone;
    }
}
