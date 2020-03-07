/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SK20ColorWheel;

/**
 * This command toggles the control panel color wheel subsystem position.
 */
public class ToggleColorWheelLiftCommand extends CommandBase 
{
    private SK20ColorWheel subsystem;
    public ToggleColorWheelLiftCommand(SK20ColorWheel subsystem) 
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
        if(subsystem.getIsLifterExtended())
        {
            subsystem.retractLifter();
        }
        else
        {
            subsystem.extendLifter();
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
