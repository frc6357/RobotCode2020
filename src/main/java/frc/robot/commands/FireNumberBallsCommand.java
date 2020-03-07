/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.TuningParams;
import frc.robot.subsystems.SK20Launcher;

/**
 * TODO: Add your docs here.
 */
public class FireNumberBallsCommand extends WaitCommand 
{
    SK20Launcher m_launcher;
    /**
     * TODO: Add your docs here.
     */
    public FireNumberBallsCommand(int numBalls, SK20Launcher launcherSubsystem) 
    {
        // Beware! RELEASE_MOTOR_RUNTIME is a millisecond count whereas WaitCommand's 
        // constructor expects a number of seconds!
        super(((double)TuningParams.RELEASE_MOTOR_RUNTIME * (double)numBalls) / 1000.0);
        addRequirements(launcherSubsystem);
        m_launcher = launcherSubsystem;
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() 
    {
        m_launcher.startLaunchReleaseMotor();
    }

    // Called once after timeout
    @Override
    public void end (boolean interrupted) 
    {
        super.end(interrupted);
        m_launcher.stopLaunchReleaseMotor();
    }

    public boolean isFinished()
    {
        return super.isFinished();
    }
}
