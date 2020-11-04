package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.analog.adis16448.frc.ADIS16448_IMU;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.commands.DriveCommand;
import frc.robot.BaseDrive;
import edu.wpi.first.wpilibj.PWMVictorSPX;

import frc.robot.utils.ScaledEncoder;

public class DriveSubsystem extends SubsystemBase {
  private final WPI_TalonSRX frontLeft = new WPI_TalonSRX(Ports.frontLeftDrive);
  private final WPI_TalonSRX backLeft = new WPI_TalonSRX(Ports.backLeftDrive);
  private final WPI_TalonSRX frontRight = new WPI_TalonSRX(Ports.frontRightDrive);
  private final WPI_TalonSRX backRight = new WPI_TalonSRX(Ports.backRightDrive);

  private final SpeedControllerGroup m_leftMotors =
  new SpeedControllerGroup(new PWMVictorSPX(Ports.frontLeftDrive),new PWMVictorSPX(Ports.backLeftDrive));
  private final SpeedControllerGroup motorGroupLeft = new SpeedControllerGroup(frontLeft, backLeft);
  private final SpeedControllerGroup motorGroupRight = new SpeedControllerGroup(frontRight, backRight);

  private final ScaledEncoder encoderLeft = new ScaledEncoder(Ports.leftEncoderA, Ports.leftEncoderB,
            false, 480, 20);
  private final ScaledEncoder encoderRight = new ScaledEncoder(Ports.rightEncoderA, Ports.rightEncoderB,
            true, 480, 20);

  private final Encoder m_encoder = new Encoder(1, 2, false, CounterBase.EncodingType.k4X);
  
  private final BaseDrive drive = new BaseDrive(motorGroupLeft, motorGroupRight, encoderLeft, encoderRight);
  private final ADIS16448_IMU imu = new ADIS16448_IMU();
  private final DriveCommand driveCommand;
  /**
   * Creates a new DriveSubsystem.
   */
  public DriveSubsystem() {
    driveCommand = new DriveCommand(this);

  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
