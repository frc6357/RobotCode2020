/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SK20Intake;

/**
 * This command toggles the direction of the intake motors.
 */
public class ReverseIntakeCommand extends CommandBase 
{
    private SK20Intake subsystem;
    public ReverseIntakeCommand(SK20Intake subsystem) 
    {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        this.subsystem = subsystem;
        addRequirements(subsystem);
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() 
    {
        boolean isRunning = subsystem.IsIntakeRollerRunning();
        boolean isForward = subsystem.IsIntakeRollerDirectionForwards();

        // This command does nothing at all if the intake roller is 
        // already turned off.
        if(isRunning)
        {
            if(isForward)
            {
                subsystem.reverseIntakeRoller();
            }
            else
            {
                subsystem.startIntakeRoller();
            }
        }
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() 
    {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() 
    {
        return true;
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) 
    {
    }
}
