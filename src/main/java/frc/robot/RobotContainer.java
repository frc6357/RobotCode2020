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
import frc.robot.commands.DriveStraightCommand;
import edu.wpi.first.wpilibj2.command.button.POVButton;
// import frc.robot.commands.ClimbReleaseCommand;
import frc.robot.commands.ExampleCommand;
// import frc.robot.commands.WinchRobotCommand;
import frc.robot.subsystems.ExampleSubsystem;
// import frc.robot.subsystems.SK20BallHandling;
// import frc.robot.subsystems.SK20Climb;
// import frc.robot.subsystems.SK20ColorWheel;
import frc.robot.subsystems.SK20Drive;
import frc.robot.test.TestDummyDriveCommand;
// import frc.robot.subsystems.SK20Intake;
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

  // private final SK20ColorWheel m_colorWheelSubsystem = new SK20ColorWheel();
  private final SK20Drive m_driveSubsystem = new SK20Drive();
  // private final SK20Climb m_climbSubsystem = new SK20Climb();
  // private final SK20Intake m_intakeSubsystem = new SK20Intake();
  // public final SK20BallHandling m_handlingSubsystem = new SK20BallHandling();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  public static FilteredJoystick joystickDriver = new FilteredJoystick(Ports.OIDriverJoystick);

  //TODO: Remove these operator buttons. These are only for test use.
  public static JoystickButton driveInterrupt = new JoystickButton(joystickDriver, Ports.OIDriverDummyDriveCommand);
  public static JoystickButton driveInterruptTestCommand = new JoystickButton(joystickDriver, Ports.OIDriverDriveCommand);
  public static POVButton incrementDriveDistance = new POVButton(joystickDriver, Ports.OIDriverIncrementDriveDistance);
  public static POVButton decrementDriveDistance = new POVButton(joystickDriver, Ports.OIDriverDecrementDriveDistance);
  public static POVButton incrementTurnAngle = new POVButton(joystickDriver, Ports.OIDriverIncrementTurnAngle);
  public static POVButton decrementTurnAngle = new POVButton(joystickDriver, Ports.OIDriverDecrementTurnAngle);

  //Climb Buttons
  public static Joystick joystickOperator = new Joystick(Ports.OIOperatorJoystick);
  public static JoystickButton operatorClimbArmDeploy = new JoystickButton(joystickOperator, Ports.OIOperatorDeployArm);
  public static JoystickButton startWinchRobot = new JoystickButton(joystickOperator, Ports.OIOperatorStartWinchArm);
  public static JoystickButton stopWinchRobot = new JoystickButton(joystickOperator, Ports.OIOperatorStopWinchArm);
  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */

   //color wheel buttons
   public static JoystickButton startSpinner = new JoystickButton(joystickOperator, Ports.colorWheelSpinner);

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
    // operatorClimbArmDeploy.whenPressed(new ClimbReleaseCommand(m_climbSubsystem));
    // startWinchRobot.whenPressed(new WinchRobotCommand(m_climbSubsystem, true));
    // stopWinchRobot.whenPressed(new WinchRobotCommand(m_climbSubsystem, false));
    driveInterrupt.whenPressed(new TestDummyDriveCommand(m_driveSubsystem));
    driveInterruptTestCommand.whenPressed(new DriveStraightCommand(m_driveSubsystem, 500));
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
