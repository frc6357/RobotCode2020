/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ClimbReleaseCommand;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.WinchRobotCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.SK20Climb;
import frc.robot.subsystems.SK20Drive;
import frc.robot.subsystems.SK20Intake;
import frc.robot.utils.FilteredJoystick;
import frc.robot.utils.filters.FilterDeadband;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer 
{
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  public static final SK20Drive m_driveSubsystem = new SK20Drive();
  public static final SK20Climb m_climbSubsystem = new SK20Climb();
  public static final SK20Intake m_intakeSubsystem = new SK20Intake();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  public static FilteredJoystick joystickDriver = new FilteredJoystick(Ports.OIDriverJoystick);

  //Climb Buttons
  public static Joystick JoystickOperator = new Joystick(Ports.OIOperatorJoystick);
  public static JoystickButton operatorClimbArmDeploy = new JoystickButton(JoystickOperator, Ports.OIOperatorDeployArm);
  public static JoystickButton startWinchRobot = new JoystickButton(JoystickOperator, Ports.OIOperatorStartWinchArm);
  public static JoystickButton stopWinchRobot = new JoystickButton(JoystickOperator, Ports.OIOperatorStopWinchArm);
  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() 
  {
    joystickDriver.setFilter(Ports.OIDriverLeftDrive, new FilterDeadband(0.06, -1.0));
    joystickDriver.setFilter(Ports.OIDriverRightDrive, new FilterDeadband(0.06, -1.0));
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings()
  {
    // TODO: Implentation has no interlock (MUST FIX !!!!!!!!!!!!!)
    // TODO: use class DriverStation to know the Game time
    // Sets robot button for the climb command
    operatorClimbArmDeploy.whenPressed(new ClimbReleaseCommand(m_climbSubsystem));
    startWinchRobot.whenPressed(new WinchRobotCommand(m_climbSubsystem, true));
    stopWinchRobot.whenPressed(new WinchRobotCommand(m_climbSubsystem, false));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() 
  {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
