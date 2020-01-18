/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Ports;
import frc.robot.subsystems.SmoothDrive;
import frc.robot.subsystems.base.SuperClasses.BaseDrive;
import frc.robot.subsystems.base.SuperClasses.ShiftPolarity;
import frc.robot.utils.ScaledEncoder;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    private Command m_autonomousCommand;
    private RobotContainer m_robotContainer;
    private final Joystick joystickDriver = new Joystick(Ports.OIDriverJoystick);
    private SmoothDrive smoothDrive;

    // public Robot() {
    //     WPI_VictorSPX frontLeft = new WPI_VictorSPX(Ports.frontLeftDrive);
    //     WPI_VictorSPX backLeft = new WPI_VictorSPX(Ports.backLeftDrive);
    //     WPI_VictorSPX frontRight = new WPI_VictorSPX(Ports.frontRightDrive);
    //     WPI_VictorSPX backRight = new WPI_VictorSPX(Ports.backRightDrive);
    //     SpeedControllerGroup motorGroupLeft = new SpeedControllerGroup(frontLeft, backLeft);
    //     SpeedControllerGroup motorGroupRight = new SpeedControllerGroup(frontRight, backRight);
    //     ScaledEncoder encoderLeft = null;
    //     ScaledEncoder encoderRight = null;
    //     Solenoid gearShiftSolenoid = null;
    //     ShiftPolarity shiftPolarity = ShiftPolarity.PRESSURE_IS_LOW;
    //     BaseDrive drive = new BaseDrive(motorGroupLeft, motorGroupRight, encoderLeft, encoderRight, gearShiftSolenoid,
    //             shiftPolarity);
    //     smoothDrive = new SmoothDrive(drive);
    // }

    /**
     * This function is run when the robot is first started up and should be used
     * for any initialization code.
     */
    public Robot() {
        WPI_VictorSPX frontLeft = new WPI_VictorSPX(Ports.frontLeftDrive);
        WPI_VictorSPX backLeft = new WPI_VictorSPX(Ports.backLeftDrive);
        WPI_VictorSPX frontRight = new WPI_VictorSPX(Ports.frontRightDrive);
        WPI_VictorSPX backRight = new WPI_VictorSPX(Ports.backRightDrive);
        SpeedControllerGroup motorGroupLeft = new SpeedControllerGroup(frontLeft, backLeft);
        SpeedControllerGroup motorGroupRight = new SpeedControllerGroup(frontRight, backRight);
        ScaledEncoder encoderLeft = null;
        ScaledEncoder encoderRight = null;
        Solenoid gearShiftSolenoid = null;
        ShiftPolarity shiftPolarity = ShiftPolarity.PRESSURE_IS_LOW;
        BaseDrive drive = new BaseDrive(motorGroupLeft, motorGroupRight, encoderLeft, encoderRight, gearShiftSolenoid,
                shiftPolarity);
        smoothDrive = new SmoothDrive(drive);
    }

    /**
     * This function is called every robot packet, no matter the mode. Use this for
     * items like diagnostics that you want ran during disabled, autonomous,
     * teleoperated and test.
     *
     * <p>
     * This runs after the mode specific periodic functions, but before LiveWindow
     * and SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
        // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
        // commands, running already-scheduled commands, removing finished or interrupted commands,
        // and running subsystem periodic() methods.  This must be called from the robot's periodic
        // block in order for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable chooser
     * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
     * remove all of the chooser code and uncomment the getString line to get the
     * auto name from the text box below the Gyro
     *
     * <p>
     * You can add additional auto modes by adding additional comparisons to the
     * switch structure below with additional strings. If using the SendableChooser
     * make sure to add them to the chooser code above as well.
     */
    @Override
    public void autonomousInit() {
        m_autonomousCommand = m_robotContainer.getAutonomousCommand();

        // schedule the autonomous command (example)
        if (m_autonomousCommand != null) {
          m_autonomousCommand.schedule();
        }
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopInit() {
      // This makes sure that the autonomous stops running when
      // teleop starts running. If you want the autonomous to
      // continue until interrupted by another command, remove
      // this line or comment it out.
      if (m_autonomousCommand != null) {
        m_autonomousCommand.cancel();
      }
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        double leftSpeed = joystickDriver.getRawAxis(Ports.OIDriverLeftDrive);
        double rightSpeed = joystickDriver.getRawAxis(Ports.OIDriverRightDrive);
        smoothDrive.setLeftSpeed(leftSpeed);
        smoothDrive.setRightSpeed(rightSpeed);
        smoothDrive.SmoothDrivePeriodic();
    }

    @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
    }
}
