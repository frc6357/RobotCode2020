package frc.robot.subsystems;

import frc.robot.TuningParams;
import frc.robot.subsystems.base.SuperClasses.BaseDrive;

/**
 * The SmoothDrive class provides an interface suitable for implementing the
 * 2020 tank drive. It allows the motors to accelerate evenly and
 * proportionately to prevent skidding and jerking on the robot. It sets the
 * motors by using the BaseDrive class.
 */
public class SmoothDrive {
    private final BaseDrive drive;

    private final int LEFT = 0;
    private final int RIGHT = 1;

    private double speedTarget[] = { 0.0, 0.0 };
    private double speedCurrentTarget[] = { 0.0, 0.0 };

    /**
     * This constructor accepts the BaseDrive object that allows it to interface the
     * robot hardware
     * 
     * @param Drive
     */
    public SmoothDrive(BaseDrive drive) {
        this.drive = drive;
    }

    /**
     * This method is used to send a double to the speed controller on the left side
     * of the robot.
     *
     * @param speed - Speed is the double number between 1 and -1, usually from the
     *              joystick axis.
     */
    public void setLeftSpeed(double speed) {
        speedTarget[LEFT] = speed;
    }

    /**
     * This method is used to send a double to the speed controller on the right
     * side of the robot.
     *
     * @param speed - Speed is the double number between 1 and -1, usually from the
     *              joystick axis.
     */
    public void setRightSpeed(double speed) {
        speedTarget[RIGHT] = speed;
    }

    /**
     * This method is used to query the number of rotations the left encoder has
     * recorded since the last time it was reset.
     *
     * @return Returns the number of full rotations the left encoder has recorded.
     */
    // TODO: Need to set up the methods to do this in BaseDrive
    /*
     * public double getLeftEncoderRotations() { return
     * Drive.getLeftEncoderRotations(); }
     */

    /**
     * This method is used to query the number of rotations the right encoder has
     * recorded since the last time it was reset.
     *
     * @return Returns the number of full rotations the right encoder has recorded.
     */
    // TODO: Need to set up the methods to do this in BaseDrive
    /*
     * public double getRightEncoderRotations() { return
     * Drive.getRightEncoderRotations(); }
     */

    /**
     * This method is used to query the distance the left encoder has recorded since
     * the last time it was reset.
     *
     * @return Returns the number of inches the left encoder has measured.
     */
    // TODO: Need to set up the methods to do this in BaseDrive
    /*
     * public double getLeftEncoderDistance() { return
     * Drive.getLeftEncoderDistance(); }
     */

    /**
     * This method is used to query the distance the right encoder has recorded
     * since the last time it was reset.
     *
     * @return Returns the number of inches the right encoder has measured.
     */
    // TODO: Need to set up the methods to do this in BaseDrive
    /*
     * public double getRightEncoderDistance() { return getRightEncoderDistance(); }
     */

    /**
     * This method must be called from the relevant top level "periodic" call.
     * First, it calculates the speed that the left and right motors need to
     * achieve, then makes sure both sides are accelerated proportionatly. It is
     * responsible for updating the current drivetrain motor speeds to
     * decellerate/accelerate towards the given value without exceeding the
     * acceleration limits.
     */
    public void SmoothDrivePeriodic() {
        double[] delta = { speedTarget[LEFT] - speedCurrentTarget[LEFT],
                speedTarget[RIGHT] - speedCurrentTarget[RIGHT] };
        boolean leftIsLarger = Math.abs(delta[LEFT]) > Math.abs(delta[RIGHT]);
        double[] speedNew = leftIsLarger ? calculateNewSpeeds(delta, LEFT, RIGHT)
                : calculateNewSpeeds(delta, RIGHT, LEFT);

        drive.SetSpeed(speedNew[LEFT], speedNew[RIGHT]);
        speedCurrentTarget = speedNew;
    }

    /**
     * This method takes the delta values, the accel side that needs to be scaled,
     * and the one that doesn't need to be scaled so that it can calculate how to
     * scale the accel values proportionately. It then uses those scaled accel
     * values and returns the new speeds to put on the motors.
     * 
     * @param delta    <br>
     *                 -The change between the target speed and the current speed.
     * @param unscaled <br>
     *                 -The drivetrain side that will be unscaled for acceleration
     *                 amount
     * @param scaled   <br>
     *                 - The drivetrain side that will be scaled to accelrate
     *                 proportionately with the other side
     * @return <br>
     *         - It returns the speeds we need to set the new speeds of motors.
     */
    private double[] calculateNewSpeeds(double[] delta, int unscaled, int scaled) {
        double speedTemp = calculateSendSpeed(scaled);
        double[] speedNew = new double[2];
        speedNew[scaled] = Math.abs((delta[scaled] / delta[unscaled]) * speedTemp) * Math.signum(speedTemp);
        speedNew[unscaled] = calculateSendSpeed(unscaled);
        return speedNew;
    }

    /**
     * This method takes in the side of which the motors are being calculated and
     * uses it to make sure that the motor speed does not exceed the targeted speed
     * if accelerated regularly. If the acceleration would exceed the targeted
     * speed, then the speed will just be set to the targeted value.
     * 
     * @param side - The side of the drivetrain
     * @return - Will return the exact unscaled speed that the motors should be set
     *         to.
     */
    private double calculateSendSpeed(int side) {
        double acceleration = getAccel(speedTarget[side], speedCurrentTarget[side]);
        double driveSpeed = speedCurrentTarget[side] + acceleration;

        // If the speed to be set has just crossed the set speed in the correct
        // direction
        // then set the speed to the setpoint.
        if (Math.signum(acceleration) == Math.signum(driveSpeed - speedTarget[side])) {
            driveSpeed = speedTarget[side];
        }
        return driveSpeed;
    }

    /**
     * This method takes the targeted speed and the current speed we are trying to
     * target and find whether we are accelerating or decelerating and to see which
     * direction we are trying to target (forward or backward).
     * 
     * @param target        <br>
     *                      - The target speed that we are trying to reach.
     * @param currentTarget <br>
     *                      - The acceleration incremented speed that we last sent
     *                      to the motors.
     * @return <br>
     *         - The limit for the acceleration depending on the current
     *         target we last sent to the motors and the final target we are
     *         attempting to reach.
     */
    public double getAccel(double target, double currentTarget) {
        if (target == currentTarget) {
            return 0.0;
        } else if (target > currentTarget) {
            if (target >= 0) {
                return TuningParams.ACCEL_MAX_TOWARDS_FORWARD;
            } else {
                return TuningParams.DECEL_MAX_TOWARDS_FORWARD;
            }
        } else {
            if (target <= 0) {
                return TuningParams.ACCEL_MAX_TOWARDS_BACKWARD;
            } else {
                return TuningParams.DECEL_MAX_TOWARDS_BACKWARD;
            }
        }
    }
}
