package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Ports;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSubsystem;

public class DriveCommand extends CommandBase {
  private final DriveSubsystem m_subsystem;
  
  public DriveCommand(DriveSubsystem subsystem) {
    m_subsystem = subsystem;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_subsystem);
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //double rightTriggerValue = RobotContainer.joystickDriver.getRawAxis(Ports.OIDriverSlowmode);
    double speedLeft = RobotContainer.joystickDriver.getFilteredAxis(Ports.OIDriverLeftDrive);
    double speedRight = RobotContainer.joystickDriver.getFilteredAxis(Ports.OIDriverRightDrive);
    
    //m_subsystem.setSpeeds(speedLeft, speedRight);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
