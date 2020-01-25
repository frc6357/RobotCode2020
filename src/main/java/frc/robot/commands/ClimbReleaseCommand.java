/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SK20Climb;

/**
 * An example command that uses an subsystem.
 */
public class ClimbReleaseCommand extends CommandBase 
{
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final SK20Climb m_subsystem;

  /**
   * ClimbReleaseCommand tells that arm is released by pneumatic piston or not.
   */
  public ClimbReleaseCommand(SK20Climb subsystem) 
  {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  //TODO: don't alow outside of endgame
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    m_subsystem.deployArm();

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    return true;
  }
}
