/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SK20Intake;

/**
 * This is the command to deactivate the intake where it retracts and turns off the motor. This is also a command that only runs once, and only does its action 
 * once so that we retract everything and that the next time it runs it all works perfectly.
 */
public class DeactivateIntakeCommand extends CommandBase 
{
    private final SK20Intake m_subsystem;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public DeactivateIntakeCommand(SK20Intake subsystem) 
    {
        m_subsystem = subsystem;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() 
    {
        m_subsystem.retractIntake();
        m_subsystem.stopIntakeRoller();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() 
    {
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) 
    {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() 
    {
        return true;
    }
}
