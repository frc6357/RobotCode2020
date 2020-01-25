/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands;

import frc.robot.subsystems.SK20Climb;
import edu.wpi.first.wpilibj2.command.CommandBase;
/**
 * An example command that uses an example subsystem.
 */
public class WinchRobotCommand extends CommandBase 
{
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final SK20Climb m_subsystem;
  private boolean startWinch; //tells whether or not winch is turned on or not

  /**
   * WinchRobot command tells whether or not to winch or stop winching the robot.
   * @param subsytem is the SK20Climb subsystem
   * @param startMotor tells whether or not the winch motor should be on or off
   */
  public WinchRobotCommand(SK20Climb subsystem, Boolean startMotor) 
  {
    m_subsystem = subsystem;
    startWinch = startMotor;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    //when true start winch
    if(startWinch)
    {
      m_subsystem.startWinchRobot();
    }
    //when false stop winch
    else 
    {
      m_subsystem.stopWinchRobot();
    }
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    return true;
  }
}
