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
public class TurnRelativeCommand extends CommandBase {
    private final SK20Drive m_subsystem;
    private double angleTarget;
    private double m_turnAngle;
    private double deltaAngle;
    private boolean isDone;

    /**
     * 
     * @param subsystem The subsystem used by this command.
     * @param turnAngle The target angle we want to turn relative to the robot.
     */
    public TurnRelativeCommand(SK20Drive subsystem, double turnAngle) {
        m_subsystem = subsystem;
        m_turnAngle = turnAngle;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        angleTarget = m_turnAngle + m_subsystem.getAngle();
        isDone = false;
    }

    /**
     * This first checks if the robot is in the desired angle range and if not, it
     * will turn in the desired direction until in desired range.
     */
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double currentAngle = m_subsystem.getAngle();
        deltaAngle = Math.abs(currentAngle - angleTarget);

        if (deltaAngle <= TuningParams.ANGLE_TURN_TOLERANCE) {
            m_subsystem.setSpeeds(0, 0);
            isDone = true;
        } else {
            if (currentAngle > angleTarget) {
                m_subsystem.setSpeeds(-TuningParams.CONTROLLED_TURN_SPEED, TuningParams.CONTROLLED_TURN_SPEED);
            } else {
                m_subsystem.setSpeeds(TuningParams.CONTROLLED_TURN_SPEED, -TuningParams.CONTROLLED_TURN_SPEED);
            }
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
