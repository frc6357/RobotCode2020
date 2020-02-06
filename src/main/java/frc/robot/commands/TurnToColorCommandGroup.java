package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.SK20ColorWheel;

/**
 * A TurnToColorCommandGroup that will extend the lifter, turn on the motor, and rotate the control panel
 * to the color that the field sensor needs to detect.
 */
public class TurnToColorCommandGroup extends SequentialCommandGroup {
    /**
     * Creates a new TurnToColorCommandGroup.
     *
     * @param SK20ColorWheel The subsystem will be used
     */
    public TurnToColorCommandGroup(SK20ColorWheel subsystem) {
      addCommands(
          // Extends the mechanism and checks to see if we are under the control panel. If we are
          // then start the motor if not then we have to move closer.
          new SpinnerStartCommand(subsystem),
  
          // Rotates the control panel to get the right color that the field sensor needs to detect.
          new SpinToColorCommand(subsystem));
    }
  
  }