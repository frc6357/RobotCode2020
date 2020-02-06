package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SK20ColorWheel;

/**
 * A command that stops the color wheel in case of an emergency.
 */
public class StopColorWheelCommand extends CommandBase {
  private final SK20ColorWheel m_subsystem;

  /**
   * Creates a new StopColorWheelCommand.
   *
   * @param SK20ColorWheel The subsystem used by this command.
   */
  public StopColorWheelCommand(SK20ColorWheel subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  // Deactivates everything when it's called
  @Override
  public void initialize() {
      m_subsystem.deactivateSpinnerRoller();
      m_subsystem.retractLifter();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
