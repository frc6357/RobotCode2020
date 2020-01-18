package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
// TODO: Uncomment these when used
//import edu.wpi.first.wpilibj.Solenoid;
//import frc.robot.subsystems.base.BaseRoller;
import frc.robot.subsystems.base.ColorSensor2020;
import frc.robot.subsystems.base.Color2020;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The SK20ColorWheel class is the subsystem that interacts with roller and color sensor
 * mechanism used to spin the 2020 game control panel. This class must be used in 
 * conjunction with related command classes which will be responsible for activating the
 * spinner motor, detecting color sensor reading changes, and updating the color
 * transition counter stored here.
 */
public class SK20ColorWheel extends SubsystemBase
{
    // TODO: Uncomment these once they are used
    //private BaseRoller spinnerRoller;
    //private Solenoid spinnerLifter;
    private int spinnerTransitionCount = 0;
    private ColorSensor2020 colorSensor;

    /**
     * Creates the SK20ColorWheel object and all hardware resources it uses.
     */
    public SK20ColorWheel()
    {
        colorSensor = new ColorSensor2020(I2C.Port.kOnboard);
        // TODO: Write this
    }

    /**
     * This function energizes the solenoid to lift the color wheel mechanism.
     */
    public void extendLifter()
    {
        // TODO: Write this.
    }

    /**
     * This function de-energies the solenoid to retract the color wheel mechanism.
     */
    public void retractLifter()
    {
        // TODO: Write this.
    }

    /**
     * Turns on the motor which rotates the spinner which, when in contact with the 
     * control panel, spins the colored disk. This method is intended for test use.
     */
    public void activateSpinnerRoller()
    {
        // TODO: Write this.
    }

    /**
     * Turns off the motor which rotates the spinner which, when in contact with the 
     * control panel, spins the colored disk. This method is intended for test use.
     */
    public void deactivateSpinnerRoller()
    {
        // TODO: Write this.
    }

    /**
     * Checks whether the lifter is in the extended or retracted position.
     * @return True for extended, false for retracted.
     */
    public boolean getIsLifterExtended()
    {
        // TODO: Write this.
        return true;
    }

    /**
     * Returns the current speed of the spinner roller.
     * @return The speed of the spinner.
     */
    public double getSpinnerRollerSpeed()
    {
        // TODO: Write this.
        return 1.0;
    }

    /**
     * Resets the color transition counter. This method is intended to allow 
     * a zero position to be set prior to starting rotating the color wheel.
     */
    public void resetSpinnerTransitionCount()
    {
        spinnerTransitionCount = 0;
    }

    /**
     * As the control panel color disk turns, the command managing it will monitor the
     * detected color and detect changes. Each time a change is detected, this method
     * must be called to keep track of the distance the wheel has moved since the count
     * was last reset. Since several commands are likely to be needed, this keeps the
     * accumulated number of transitions (and, hence, the accumulated total rotation)
     * in a single place that each commadn can access.
     */
    public void incrementSpinnerTransitionCount()
    {
        spinnerTransitionCount++;
    }

    /**
     * Returns the color transition counter. The transition count is incremented 
     * when the spinner roller is active and the color sensor detects a change in
     * the color above it.
     * 
     * TODO: Commands for this subsystem must detect color transitions
     * and update the transition counter.
     * 
     * @return The number of detected color transitions since the last call to 
     * resetSpinnerTransitionCount().
     */
    public int getSpinnerTransitionCount()
    {
        return spinnerTransitionCount;
    }

    /**
     * Returns the color currently detected by the color sensor or NONE if
     * the proximity sensor indicates that it is not currently under the
     * control panel.
     * @return The game color closest to the color currently being detected.
     */
    public Color2020 getDetectedColor()
    {
        // TODO: Write this
        return colorSensor.getGameColor();
    }

    /**
     * Returns the color which should be detected by the field's control panel
     * color sensor based on the color the robot is currently detecting. If
     * the proximity sensor indicates that it is not currently under the
     * control panel, returns UNKNOWN.
     * @return The game color we expect the field is currently detecting or
     * UNKNOWN if the robot is not currently reading a color.
     */
    public Color2020 getFieldDetectedColor()
    {
        // TODO: Write this. Read the color from the sensor and, knowing the sequence of 
        // colors on the wheel, determine which color must be under the field sensor and
        // return that value.
        return Color2020.UNKNOWN;
    }

    /**
     * Uses the proximity sensor on the mechanism to decide whether the sensor is 
     * below the color wheel on the control panel or not. The mechanism is assumed to
     * be under the control panel when the proximity sensor detects any object within
     * its working range above it.
     * @return True if the proximity sensor detects and object above it, false otherwise.
     */
    public boolean isUnderControlPanel()
    {
        // TODO: Write this.
        return false;
    }
}