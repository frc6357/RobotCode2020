/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Ports;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
  private final WPI_TalonSRX motorWheel     = new WPI_TalonSRX(Ports.motorColorWheel);

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
   
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
    
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
  }
  @Override
  public void testInit(){
    SmartDashboard.putBoolean("Test mode enabled", true);
  }

  @Override
  public void disabledInit(){
    SmartDashboard.putBoolean("Test mode enabled", false);
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    ColorSensorV3.RawColor valColor = m_colorSensor.getRawColor();
    Color normalized = guessColorWithMagnitude(valColor);
    System.out.println(normalized.red + " " + normalized.green + " " + normalized.blue + " " + valColor.red + " " + valColor.green + " " + valColor.blue);
    double motorspeed = SmartDashboard.getNumber("Motor Controller", 1.0);

    motorWheel.set(motorspeed);
  }
  
  public static Color guessColor(ColorSensorV3.RawColor color)
  {
    // This code allows us to find the maximum 
    int maximum = Math.max(Math.max(color.red, color.green), color.blue);
    int red = 0;
    int green = 0;
    int blue = 0;
    if(maximum != 0)
    {
      red   = (color.red * 255) / maximum;
      green = (color.green * 255) / maximum;
      blue  = (color.blue * 255) /maximum;
    }
    Color8Bit normalized = new Color8Bit(red, green, blue);
    return new Color(normalized);
  
  }
  public static Color guessColorWithMagnitude(ColorSensorV3.RawColor color)
  {
    //This is a hack test DO NOT KEEP!!!!
    color.green /= 2;

    // This code allows us to find the magnitude to scale answers
    int magnitude = color.red + color.green + color.blue;
    int red = 0;
    int green = 0;
    int blue = 0;
    if(magnitude != 0)
    {
      red   = (color.red * 255) / magnitude;
      green = (color.green * 255) / magnitude;
      blue  = (color.blue * 255) /magnitude;
    }
    Color8Bit normalized = new Color8Bit(red, green, blue);
    return new Color(normalized);
  
  }
}
