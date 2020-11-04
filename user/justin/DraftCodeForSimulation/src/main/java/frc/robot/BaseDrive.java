package frc.robot;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.utils.ScaledEncoder;


public class BaseDrive {
  private final SpeedControllerGroup motorGroupLeft, motorGroupRight;
  private final DifferentialDrive driveDiff;
  private final ScaledEncoder encoderLeft, encoderRight;
    
  private double lastLeftSetSpeed = 0;
  private double lastRightSetSpeed = 0;

  
    public BaseDrive(SpeedControllerGroup motorGroupLeft, SpeedControllerGroup motorGroupRight,
            ScaledEncoder encoderLeft, ScaledEncoder encoderRight) {
        this.motorGroupLeft = motorGroupLeft;
        this.motorGroupRight = motorGroupRight;
        this.encoderLeft = encoderLeft;
        this.encoderRight = encoderRight;
        
        driveDiff = new DifferentialDrive(motorGroupLeft, motorGroupRight);
    }

   
    public void SetSpeed(double speedLeft, double speedRight) 
    {
            driveDiff.tankDrive(speedLeft, speedRight);
            speedLeft = lastLeftSetSpeed;
            speedRight = lastRightSetSpeed;        
    }

   
    
    
}
