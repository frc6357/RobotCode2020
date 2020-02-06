package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.SK20ColorWheel;

/**
 * A ThreeRotateCommandGroup that will extend the lifter, turn on the motor, and spin the control panel
 * a little more than three times.
 */
public class ThreeRotateCommandGroup extends SequentialCommandGroup {
    /**
     * Creates a new ThreeRotateCommandGroup.
     *
     * @param SK20ColorWheel the subsystem that will be used
     */
    public ThreeRotateCommandGroup(SK20ColorWheel subsystem, int transitions) {
      addCommands(
          // Extends the mechanism and checks to see if we are under the control panel. If we are
          // then start the motor if not then we have to move closer.
          new SpinnerStartCommand(subsystem),
  
          // Rotates the control panel for the certain amount of transitions set by the constructor.
          new SpinTransition(subsystem,transitions));
    }
  
  }