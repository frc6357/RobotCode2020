package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.*;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Ports;
import frc.robot.TuningParams;
import frc.robot.subsystems.base.BaseRoller;
import frc.robot.subsystems.base.ColorSensor2020;
import frc.robot.utils.ScaledEncoder;
import frc.robot.subsystems.base.Color2020;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The SK20ColorWheel class is the subsystem that interacts with roller and
 * color sensor mechanism used to spin the 2020 game control panel. This class
 * must be used in conjunction with related command classes which will be
 * responsible for activating the spinner motor, detecting color sensor reading
 * changes, and updating the color transition counter stored here.
 */


 // TODO: How do we detect which color we need to position the Control Panel sensor 
 // over during the final control panel phase? Read the following:
 //
 // https://frc-docs.readthedocs.io/en/latest/docs/software/wpilib-overview/2020-Game-Data.html
 //
 
public class SK20ColorWheel extends SubsystemBase {
    // TODO: Uncomment these once they are used
    private BaseRoller spinnerRoller;
    private Solenoid spinnerLifter;
    private CANSparkMax spinnerRollerMotor;
    private int spinnerTransitionCount = 0;
    private ColorSensor2020 colorSensor;
    private CANEncoder spinnerRollerEncoder;
    private static Color2020[] fieldColors = {Color2020.RED, Color2020.GREEN, Color2020.CYAN, Color2020.YELLOW};

    /**
     * Creates the SK20ColorWheel object and all hardware resources it uses.
     */
    public SK20ColorWheel() {
        colorSensor = new ColorSensor2020(I2C.Port.kOnboard);
        spinnerRollerMotor = new CANSparkMax(Ports.colorWheelSpinner, MotorType.kBrushless);
        spinnerRoller = new BaseRoller(spinnerRollerMotor, TuningParams.INTAKE_MAX_SPEED);
        spinnerLifter = new Solenoid(Ports.intakeMoverExtend, Ports.intakeMoverRetract);
        spinnerRollerEncoder = new CANEncoder(spinnerRollerMotor);
        // TODO: Finish writing this
    }

    /**
     * This function energizes the solenoid to lift the color wheel mechanism.
     */
    public void extendLifter() {
        spinnerLifter.set(true);
    }

    /**
     * This function de-energies the solenoid to retract the color wheel mechanism.
     */
    public void retractLifter() {
        spinnerLifter.set(false);
    }

    /**
     * Turns on the motor which rotates the spinner which, when in contact with the
     * control panel, spins the colored disk. This method is intended for test use.
     */
    public void activateSpinnerRoller() {
        spinnerRoller.setForwards();
    }

    /**
     * Turns off the motor which rotates the spinner which, when in contact with the
     * control panel, spins the colored disk. This method is intended for test use.
     */
    public void deactivateSpinnerRoller() {
        spinnerRoller.setStop();
    }

    /**
     * Checks whether the lifter is in the extended or retracted position.
     * 
     * @return True for extended, false for retracted.
     */
    public boolean getIsLifterExtended() {
        return spinnerLifter.get();
    }

    /**
     * Returns the current speed of the spinner roller.
     * 
     * @return The speed of the spinner.
     */
    public double getSpinnerRollerSpeed() {
        return spinnerRollerEncoder.getVelocity();
    }

    /**
     * Resets the color transition counter. This method is intended to allow a zero
     * position to be set prior to starting rotating the color wheel.
     */
    public void resetSpinnerTransitionCount() {
        spinnerTransitionCount = 0;
    }

    /**
     * As the control panel color disk turns, the command managing it will monitor
     * the detected color and detect changes. Each time a change is detected, this
     * method must be called to keep track of the distance the wheel has moved since
     * the count was last reset. Since several commands are likely to be needed,
     * this keeps the accumulated number of transitions (and, hence, the accumulated
     * total rotation) in a single place that each commadn can access.
     */
    public void incrementSpinnerTransitionCount() {
        spinnerTransitionCount++;
    }

    /**
     * Returns the color transition counter. The transition count is incremented
     * when the spinner roller is active and the color sensor detects a change in
     * the color above it.
     * 
     * TODO: Commands for this subsystem must detect color transitions and update
     * the transition counter.
     * 
     * @return The number of detected color transitions since the last call to
     *         resetSpinnerTransitionCount().
     */
    public int getSpinnerTransitionCount() {
        return spinnerTransitionCount;
    }

    /**
     * Returns the color currently detected by the color sensor or NONE if the
     * proximity sensor indicates that it is not currently under the control panel.
     * 
     * @return The game color closest to the color currently being detected.
     */
    public Color2020 getDetectedColor() {
        return colorSensor.getGameColor();
    }

    /**
     * Returns the color which should be detected by the field's control panel color
     * sensor based on the color the robot is currently detecting. If the proximity
     * sensor indicates that it is not currently under the control panel, returns
     * UNKNOWN.
     * 
     * @return The game color we expect the field is currently detecting or UNKNOWN
     *         if the robot is not currently reading a color.
     */
    public Color2020 getFieldDetectedColor() {
        // TODO: Write this. Read the color from the sensor and, knowing the sequence of
        // colors on the wheel (stored in the fieldColors member), determine which color
        // must be under the field sensor and return that value. You know that the field
        // sensor is 90 degrees ahead of our detector's position so that will be 2 entries
        // ahead in the fieldColors array. Hint: Figure out which entry in the array 
        // matches the current detected color then advance two positions in the array to
        // find the color under the field sensor. The modulus operator (%) may be helpful.
        return Color2020.UNKNOWN;
    }

    /**
     * Uses the proximity sensor on the mechanism to decide whether the sensor is
     * below the color wheel on the control panel or not. The mechanism is assumed
     * to be under the control panel when the proximity sensor detects any object
     * within its working range above it.
     * 
     * @return True if the proximity sensor detects an object above it, false
     *         otherwise.
     */
    public boolean isUnderControlPanel() {
        if (colorSensor.getProximity() < TuningParams.COLOR_WHEEL_PROXIMITY_THRESHOLD) {
            return false;
        } else {
            return true;
        }
    }
}