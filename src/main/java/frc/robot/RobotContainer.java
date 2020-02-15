/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.AutoCommands.OffAngleShotToMoveAuto;
import frc.robot.AutoCommands.OffAngleShotToTrenchAuto;
import frc.robot.AutoCommands.StraightShotToMoveAuto;
import frc.robot.AutoCommands.StraightShotToTrenchAuto;
import frc.robot.commands.ClimbReleaseCommand;
import frc.robot.commands.LaunchBallCommand;
import frc.robot.commands.SetGear;
import frc.robot.commands.SetAngleCommand;
import frc.robot.commands.SetSlowmodeCommand;
import frc.robot.commands.StopColorWheelCommand;
import frc.robot.commands.ThreeRotateCommandGroup;
import frc.robot.commands.ToggleIntakeCommand;
import frc.robot.commands.TurnToColorCommandGroup;
import frc.robot.commands.WinchRobotCommand;
import frc.robot.commands.DefaultBallHandlingCommand;
import frc.robot.subsystems.SK20BallHandling;
import frc.robot.subsystems.SK20Climb;
import frc.robot.subsystems.SK20ColorWheel;
import frc.robot.subsystems.SK20Drive;
import frc.robot.subsystems.SK20Intake;
import frc.robot.subsystems.SK20Launcher;
import frc.robot.subsystems.base.SuperClasses.Gear;
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

    private enum testModeChoice 
    {
        DRIVE, LAUNCHER, CLIMB, INTAKE, COLOR_WHEEL, OTHER
    };
    private enum AutoCommands{
        OffAngleShot,OffAngleRecollectShot, StraightShot, StraightRecollectShot
    };

    SendableChooser<testModeChoice> testModeSelector = new SendableChooser<testModeChoice>();
    SendableChooser<AutoCommands> autoCommandSelector = new SendableChooser<AutoCommands>();

    // The robot's subsystems and commands are defined here...
    private final SK20ColorWheel m_colorWheelSubsystem = new SK20ColorWheel();
    private final SK20Drive m_driveSubsystem = new SK20Drive();
    private final SK20Climb m_climbSubsystem = new SK20Climb();
    private final SK20Intake m_intakeSubsystem = new SK20Intake();
    private final SK20Launcher m_launcherSubsystem = new SK20Launcher();
    private final SK20BallHandling m_ballHandlingSubsystem = new SK20BallHandling();

    public static FilteredJoystick joystickDriver = new FilteredJoystick(Ports.OIDriverJoystick);
    public static Joystick joystickOperator = new Joystick(Ports.OIOperatorJoystick);

    // Slowmode Buttons
    public static JoystickButton slowmodeLeft = new JoystickButton(joystickDriver, Ports.OIDriverSetSlowmodeLeft);
    public static JoystickButton slowmodeRight = new JoystickButton(joystickDriver, Ports.OIDriverSetSlowmodeRight);

    // Gear Shifter Button
    public static JoystickButton setLowGear = new JoystickButton(joystickDriver, Ports.OIDriverSetLowGear);
    public static JoystickButton setHighGear = new JoystickButton(joystickDriver, Ports.OIDriverSetHighGear);

    // Intake control button
    public static JoystickButton toggleIntake = new JoystickButton(joystickOperator, Ports.OIOperatorToggleIntake);

    // Launcher control buttons
    public static JoystickButton launchBall = new JoystickButton(joystickOperator, Ports.OIOperatorShootBall);
    public static JoystickButton setHighAngle = new JoystickButton(joystickOperator, Ports.OIOperatorSetHighAngle);

    // Climb Buttons
    public static JoystickButton operatorClimbArmDeploy = new JoystickButton(joystickOperator,
            Ports.OIOperatorDeployArm);
    public static JoystickButton runWinchRobot = new JoystickButton(joystickOperator, Ports.OIOperatorRunWinchArm);
    public static JoystickButton armClimbSystem = new JoystickButton(joystickOperator, Ports.OIOperatorArmClimb);

    // Color wheel buttons
    public static JoystickButton startThreeRotate = new JoystickButton(joystickOperator,
            Ports.OIOperatorStartThreeRotate);
    public static JoystickButton startSetColor = new JoystickButton(joystickOperator, Ports.OIOperatorStartSetColor);
    public static JoystickButton stopColorWheel = new JoystickButton(joystickOperator, Ports.OIOperatorStopColorWheel);

    public static Button runBallHandler = new Button();

    public RobotContainer() 
    {
        //auto commands
        autoCommandSelector.addOption("OffAngleShooting", AutoCommands.OffAngleShot);
        autoCommandSelector.addOption("OffAngleShooting/Recollection", AutoCommands.OffAngleRecollectShot);
        autoCommandSelector.addOption("StraightShooting", AutoCommands.StraightShot);
        autoCommandSelector.addOption("StraightShooting/Recollection", AutoCommands.StraightRecollectShot);

        joystickDriver.setFilter(Ports.OIDriverLeftDrive, new FilterDeadband(0.06, -1.0));
        joystickDriver.setFilter(Ports.OIDriverRightDrive, new FilterDeadband(0.06, -1.0));

        // Configure the button bindings
        configureButtonBindings();
        testModeSelector.setDefaultOption("OTHER", testModeChoice.OTHER);
        testModeSelector.addOption("DRIVE", testModeChoice.DRIVE);
        testModeSelector.addOption("COLOR_WHEEL", testModeChoice.COLOR_WHEEL);
        testModeSelector.addOption("CLIMB", testModeChoice.CLIMB);
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
        // Sets buttons for gear shifting
        setLowGear.whenPressed(new SetGear(m_driveSubsystem, Gear.LOW));
        setHighGear.whenPressed(new SetGear(m_driveSubsystem, Gear.HIGH));

        // Sets buttons for slowmode activation/deactivation
        slowmodeLeft.whenPressed(new SetSlowmodeCommand(m_driveSubsystem, true));
        slowmodeLeft.whenReleased(new SetSlowmodeCommand(m_driveSubsystem, false));

        slowmodeRight.whenPressed(new SetSlowmodeCommand(m_driveSubsystem, true));
        slowmodeRight.whenReleased(new SetSlowmodeCommand(m_driveSubsystem, false));

        // Sets robot buttons for the climb command
        operatorClimbArmDeploy.whenPressed(new ClimbReleaseCommand(m_climbSubsystem, this));
        runWinchRobot.whenPressed(new WinchRobotCommand(m_climbSubsystem, true, this));
        runWinchRobot.whenReleased(new WinchRobotCommand(m_climbSubsystem, false, this));

        // Set the ball launcher buttons to do correct commands
        setHighAngle.whenPressed(new SetAngleCommand(m_launcherSubsystem, true));
        setHighAngle.whenReleased(new SetAngleCommand(m_launcherSubsystem, false));
        launchBall.whenPressed(new LaunchBallCommand(m_launcherSubsystem));

        // Sets robot buttons for the control panel command
        stopColorWheel.whenPressed(new StopColorWheelCommand(m_colorWheelSubsystem));
        startThreeRotate
                .whenPressed(new ThreeRotateCommandGroup(m_colorWheelSubsystem, TuningParams.COLOR_WHEEL_TRANSITIONS));
        startSetColor.whenPressed(new TurnToColorCommandGroup(m_colorWheelSubsystem));

        // Sets the buttons to activate/deactivate intake
        toggleIntake.whenPressed(new ToggleIntakeCommand(m_intakeSubsystem));

        runBallHandler.whenPressed(new DefaultBallHandlingCommand(m_ballHandlingSubsystem));
        runBallHandler.whenReleased(new DefaultBallHandlingCommand(m_ballHandlingSubsystem));
    }

    public void testSelector() 
    {
        switch (testModeSelector.getSelected()) 
        {
        case OTHER:
            // add later:
            // CommandScheduler.getInstance().schedule(Command);
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
        AutoCommands myAuto = autoCommandSelector.getSelected();
        switch (myAuto) {
            case OffAngleShot:
                OffAngleShotToMoveAuto m_autoPath = new OffAngleShotToMoveAuto(m_driveSubsystem, m_launcherSubsystem);
                return m_autoPath.getCommandGroup();

            case OffAngleRecollectShot:
                OffAngleShotToTrenchAuto m_autoPath1 = new OffAngleShotToTrenchAuto(m_driveSubsystem, m_intakeSubsystem, m_launcherSubsystem);
                return m_autoPath1.getCommandGroup();

            case StraightShot:
                StraightShotToMoveAuto m_autoPath2 = new StraightShotToMoveAuto(m_driveSubsystem, m_launcherSubsystem);

                return m_autoPath2.getCommandGroup();
            case StraightRecollectShot:
                StraightShotToTrenchAuto m_autoPath3 = new StraightShotToTrenchAuto(m_driveSubsystem, m_launcherSubsystem, m_intakeSubsystem);

                return m_autoPath3.getCommandGroup();
            default:
                return (Command) null;
        }
    }
    
    public boolean isClimbArmed() 
    {
        String debugger = DriverStation.getInstance().getGameSpecificMessage();
        double time = DriverStation.getInstance().getMatchTime();

        if ((time <= 30 || debugger == "D") && armClimbSystem.get()) {
            return true;
        } else {
            return false;
        }
    }
}
