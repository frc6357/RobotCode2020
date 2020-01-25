/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.TuningParams;
import frc.robot.subsystems.SK20Drive;

/**
 * An example command that uses an example subsystem.
 */
public class DriveStraightCommand extends CommandBase {
    private final SK20Drive m_subsystem;
    private double initialLeftEncoderValue;
    private double initialRightEncoderValue;
    private final double distanceTarget;
    private boolean isDone = false;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public DriveStraightCommand(SK20Drive subsystem, double distanceTarget) {
        m_subsystem = subsystem;
        this.distanceTarget = distanceTarget;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        initialLeftEncoderValue = m_subsystem.getLeftEncoderDistance();
        initialRightEncoderValue = m_subsystem.getRightEncoderDistance();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double leftEncoderDistance = m_subsystem.getLeftEncoderDistance();
        double rightEncoderDistance = m_subsystem.getRightEncoderDistance();
        double deltaLeftEncoderDistance = leftEncoderDistance - initialLeftEncoderValue;
        double deltaRightEncoderDistance = rightEncoderDistance - initialRightEncoderValue;
        double absDelta = Math.abs(deltaLeftEncoderDistance - deltaRightEncoderDistance);

        if (absDelta <= TuningParams.STRAIGHT_DRIVE_OFFSET_TOLERANCE) {
            m_subsystem.setSpeeds(TuningParams.AUTONOMOUS_DRIVE_SPEED, TuningParams.AUTONOMOUS_DRIVE_SPEED);
        } else {
            double increment = absDelta * TuningParams.OFFSET_SPEED_INCREMENT;
            if (deltaLeftEncoderDistance > deltaRightEncoderDistance) {
                m_subsystem.setSpeeds(TuningParams.CONTROLLED_TURN_SPEED,
                        TuningParams.CONTROLLED_TURN_SPEED + increment);
            } else {
                m_subsystem.setSpeeds(TuningParams.CONTROLLED_TURN_SPEED + increment,
                        TuningParams.CONTROLLED_TURN_SPEED);
            }
        }
        if (deltaLeftEncoderDistance >= distanceTarget || deltaLeftEncoderDistance >= distanceTarget) {
            m_subsystem.setSpeeds(0, 0);
            isDone = true;
        }

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if (isDone) {
            return true;
        } else {
            return false;
        }
    }
}
