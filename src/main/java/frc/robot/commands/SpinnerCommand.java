package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.SK20ColorWheel;

/**
 * A SpinnerCommand that uses SK20ColorWheel subsystem
 */
public class SpinnerCommand extends CommandBase {
  private final SK20ColorWheel m_subsystem;
  private final boolean startSpinner;
  private boolean isFinished = false;

  /**
   * Creates a new SipnnerCommand.
   *
   * @param SK20ColorWheel The subsystem used by this command.
   * @param startMotor
   */
  public SpinnerCommand(SK20ColorWheel subsystem, Boolean startMotor) {
    m_subsystem = subsystem;
    startSpinner =  startMotor;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      m_subsystem.activateSpinnerRoller();
      isFinished = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      if(m_subsystem.getIsLifterExtended() == true && startSpinner == true){
          m_subsystem.activateSpinnerRoller();
      }

      else if(m_subsystem.getIsLifterExtended() == false || startSpinner == false){
          m_subsystem.deactivateSpinnerRoller();
      }

      else{
          System.out.println("ERROR");
      }

      isFinished = true;


  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      m_subsystem.deactivateSpinnerRoller();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isFinished;
  }
}
