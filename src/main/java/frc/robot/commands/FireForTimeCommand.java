/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.SK20Launcher;

/**
 * This command is used to fire a given amount of time.
 */
public class FireForTimeCommand extends WaitCommand 
{
    SK20Launcher m_launcher;
    /**
     * This constructor creates a new command and gets the needed info
     * 
     * @param Time_mS The number of milliseconds to run the launcher release motor for.
     * @param launcherSubsystem The subsystem needed to shoot
     */
    public FireForTimeCommand(double Time_mS, SK20Launcher launcherSubsystem) 
    {
        super(Time_mS / 1000.0);
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
