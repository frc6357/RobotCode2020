/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utils.filters;

/**
 * Add your docs here.
 */
public class FilterDeadband extends Filter{
  private double deadband; // The deadbanding for the input, equal to distance from zero
    private double slope; // The slope m of the y=mx line to get careful control
    private double gain; // The gain is the maximum value for a graph, and is by default 1

    /**
     * The consructor for a filter with a deadband
     * 
     * @param deadband
     *            the deadbanding zone, equivalent to the distance from zero
     */
    public FilterDeadband(double deadband)
    {
        gain = 1;
        setDeadband(deadband);
    }

    /**
     * The constructor which allows a user to specify gain
     * 
     * @param deadband
     *            deadband the deadbanding zone, equivalent to the distance from zero
     * @param gain
     *            the gain for which to set the max value
     */
    public FilterDeadband(double deadband, double gain)
    {
        setDeadband(deadband);
        this.gain = gain;
    }

    /**
     * Filters the raw input for a deadbanding zone If the given value is inside the deadband, zero is returned Otherwise, a scale is
     * implemented and slope is readjusted for careful control
     * 
     * @param rawAxis
     *            the data to be passed in, from -1 to 1
     */
    @Override
    public double filter(double rawAxis)
    {
        if (Math.abs(rawAxis) < deadband)
        {
            return 0.0;
        }
        else
        {
            return (slope * (rawAxis - deadband)) * gain;
        }
    }

    /**
     * Sets the deadband of the filter to a new deadband, with length equal to distance to zero Also adjust the slope of the y=mx line for
     * better functionality
     * 
     * @param deadband
     *            the new deadband to set the value to
     */
    public void setDeadband(double deadband)
    {
        this.deadband = deadband;
        slope = 1 / (1 - deadband);
    }

    /**
     * Sets the maximum out put of the filter
     * 
     * @param gain
     *            the new gain to adjust output
     */
    public void setGain(double gain)
    {
        this.gain = gain;
    }
}
