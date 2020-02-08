/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ClimbReleaseCommand;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.StopColorWheelCommand;
import frc.robot.commands.ThreeRotateCommandGroup;
import frc.robot.commands.TurnToColorCommandGroup;
import frc.robot.commands.WinchRobotCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.SK20Climb;
import frc.robot.subsystems.SK20ColorWheel;
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
  public static UsbCamera camera;

  private enum testModeChoice{DRIVE, LAUNCHER, CLIMB, INTAKE, COLOR_WHEEL, OTHER};
  SendableChooser<testModeChoice> testModeSelector = new SendableChooser<testModeChoice>();
  
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final SK20ColorWheel m_colorWheelSubsystem = new SK20ColorWheel();
  private final SK20Drive m_driveSubsystem = new SK20Drive();
  private final SK20Climb m_climbSubsystem = new SK20Climb();
  private final SK20Intake m_intakeSubsystem = new SK20Intake();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  public static FilteredJoystick joystickDriver = new FilteredJoystick(Ports.OIDriverJoystick);

  // Climb Buttons
  public static Joystick JoystickOperator = new Joystick(Ports.OIOperatorJoystick);
  public static JoystickButton operatorClimbArmDeploy = new JoystickButton(JoystickOperator, Ports.OIOperatorDeployArm);
  public static JoystickButton startWinchRobot = new JoystickButton(JoystickOperator, Ports.OIOperatorStartWinchArm);
  public static JoystickButton stopWinchRobot = new JoystickButton(JoystickOperator, Ports.OIOperatorStopWinchArm);

   // Color wheel buttons
   public static JoystickButton startThreeRotate = new JoystickButton(JoystickOperator, Ports.OIOperatorStartThreeRotate);
   public static JoystickButton startSetColor = new JoystickButton(JoystickOperator, Ports.OIOperatorStartSetColor);
   public static JoystickButton stopColorWheel = new JoystickButton(JoystickOperator, Ports.OIOperatorStopColorWheel);
   

  public RobotContainer() 
  {
    
    joystickDriver.setFilter(Ports.OIDriverLeftDrive, new FilterDeadband(0.06, -1.0));
    joystickDriver.setFilter(Ports.OIDriverRightDrive, new FilterDeadband(0.06, -1.0));
    // Configure the button bindings
    configureButtonBindings();
    testModeSelector.setDefaultOption("OTHER", testModeChoice.OTHER);
    testModeSelector.addOption("DRIVE", testModeChoice.DRIVE);
    testModeSelector.addOption("COLOR_WHEEL", testModeChoice.COLOR_WHEEL);
    testModeSelector.addOption("CLIMB",testModeChoice.CLIMB);
    testModeSelector.addOption("INTAKE", testModeChoice.INTAKE);
    testModeSelector.addOption("LAUNCHER", testModeChoice.LAUNCHER);

    // Driver camera configuration.
    camera = CameraServer.getInstance().startAutomaticCapture("Driver Front Camera", 0);
    camera.setResolution(240, 240);
    camera.setFPS(15);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings()
  {
    
    // Sets robot button for the climb command
    operatorClimbArmDeploy.whenPressed(new ClimbReleaseCommand(m_climbSubsystem));
    startWinchRobot.whenPressed(new WinchRobotCommand(m_climbSubsystem, true));
    stopWinchRobot.whenPressed(new WinchRobotCommand(m_climbSubsystem, false));
    stopColorWheel.whenPressed(new StopColorWheelCommand(m_colorWheelSubsystem));
    startThreeRotate.whenPressed(new ThreeRotateCommandGroup(m_colorWheelSubsystem, TuningParams.COLOR_WHEEL_TRANSITIONS));
    startSetColor.whenPressed(new TurnToColorCommandGroup(m_colorWheelSubsystem));

  }

  public void testSelector(){
    switch (testModeSelector.getSelected()){
      case OTHER:
      //add later:
      //CommandScheduler.getInstance().schedule(Command);
        System.out.println("Doing OTHER");
      break;
      case DRIVE:
        System.out.println("Doing Driver");
        
      break;
      case LAUNCHER:
        System.out.println("Doing LAUNCHER");
      break;
      case INTAKE:
        System.out.println("Doing INTAKE");
      break;
      case CLIMB:
        System.out.println("Doing CLIMB");
      break;
      case COLOR_WHEEL:
        System.out.println("Doing COLOR_WHEEL");
      break;
    }
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
