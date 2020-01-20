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
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
//import edu.wpi.first.wpilibj.Joystick;
//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
//import frc.robot.Ports;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
  ColorMatch m_colorMatcher = new ColorMatch();
  //private final WPI_TalonSRX motorWheel     = new WPI_TalonSRX(Ports.motorColorWheel);
  //private final Joystick joystickDriver     = new Joystick(Ports.OIDriverJoystick);

  private Color kRedTarget    = ColorMatch.makeColor(0.42, 0.39,  0.17);
  private Color kGreenTarget  = ColorMatch.makeColor(0.22, 0.52,  0.23);
  private Color kCyanTarget   = ColorMatch.makeColor(0.18, 0.445, 0.38);
  private Color kYellowTarget = ColorMatch.makeColor(0.31, 0.53,  0.15);

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

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
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
    case kCustomAuto:
      // Put custom auto code here
      break;
    case kDefaultAuto:
    default:
      // Put default auto code here
      break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    //m_colorMatcher.addColorMatch(kRedTarget);
    //m_colorMatcher.addColorMatch(kGreenTarget);
    //m_colorMatcher.addColorMatch(kCyanTarget);
    //m_colorMatcher.addColorMatch(kYellowTarget);
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    // Set the wheel motor speed
    //int motorspeed = (int)(255.0 * joystickDriver.getRawAxis(Ports.OIDriverLeftDrive));
    //motorWheel.set(motorspeed);
    int valProx = m_colorSensor.getProximity();
    //ColorSensorV3.RawColor valColor = m_colorSensor.getRawColor();
    //Color detectedColor = m_colorSensor.getColor();
    
    //ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    System.out.println(valProx);
    //Color normalized = guessColorWithMagnitude(valColor);
    //System.out.println("R: " + detectedColor.red + " G: " + detectedColor.green + " B: " + detectedColor.blue);
    
    // System.out.println("Proximity: " + valProx + " R:" + valColor.red + " G:" +
    // valColor.green + " B:" + valColor.blue + " Likely color is:" +
    // Integer.toHexString(likelyColor.hashCode()));
    // System.out.printf("Proximity: %d R: %d G: %d B: %d Likely: %08x", valProx,
    // valColor.red, valColor.green, valColor.blue, likelyColor.hashCode());

    /*
    This was our first algorithm and the problem was the green was overly higher than any other color we tested
    Another problem problem we encountered was that any small variation got scaled into a much larger variation and any pure grey values would result in an error
    int minimum = Math.min(Math.min(red, green), blue);
    red -= minimum;
    green -= minimum;
    blue -= minimum;
    int maximum = Math.max(Math.max(red, green), blue);
    if(maximum)
    {
      red = red * 100 / maximum;
      green = green * 100 / maximum;
      blue = blue * 100 / maximum;
    }
    else
    {
      red = 0;
      green = 0;
      blue = 0;
    }
    System.out.println("R: " + red + " G: " + green + " B: " + blue + " Raw R: " + valColor.red + " G: " + valColor.green + " B: " + valColor.blue);
    */
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
  
  // Given color it will return the most likely color or "Unknown".
  public String getColorName(Color color)
  {
    if(color == kCyanTarget)
    {
      return "Cyan";
    }
    else if(color == kRedTarget)
    {
      return "Red";
    }
    else if(color == kGreenTarget)
    {
      return "Green";
    }
    else if(color == kYellowTarget)
    {
      return "Yellow";
    }
    else
    {
      return "Unknown";
    }
  }
}
