package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SK20ColorWheel;

/**
 * A command that does three different parts. The first part extends the mechanism(assuming that the
 * driver is already at the position to spin the color panel). The second part is where the color sensor
 * monitors it's postion using isUnderControlPanel, this second part keeps running until the color sensor
 * detects if it is under the control panel. The third and final part is where it starts the motor to spin
 * the color panel.
 */
public class SpinnerCommand extends CommandBase {
  private final SK20ColorWheel m_subsystem;
  private boolean isFinished = false;

  /**
   * Creates a new SpinnerCommand instance that sets up the member subsystem
   * and sets startSpinner if the instance wants to start the motor.
   *
   * @param SK20ColorWheel The subsystem used by this command to see if the mechanism is extended.
   * @param startMotor     Checks to see if the instance wants to start the motor.
   */
  public SpinnerCommand(SK20ColorWheel subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      m_subsystem.extendLifter();
      isFinished = false;
  }

  /**
   * This method checks to see if the mechanism is extended and if the instance wants to start the spinner.
   * This works every 20ms, when both of these conditions are true then the SK20ColorWheel instance starts the
   * motor. If any one of these parameters are false then it turns off the motor. And finally if somehow the 
   * conditions meet none of these requirements, then it returns an error.
   */
  @Override
  public void execute() {
        if(m_subsystem.isUnderControlPanel() == true){
            m_subsystem.activateSpinnerRoller();
      }

        else if(m_subsystem.isUnderControlPanel() == false){
            System.out.println("Keep moving closer");
      }


        else{
            System.out.println("ERROR");
      }

      isFinished = true;


  }

  // Called once the command ends or is interrupted.
  // For safety if something were to happen then we should just deactivate the motor.
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
