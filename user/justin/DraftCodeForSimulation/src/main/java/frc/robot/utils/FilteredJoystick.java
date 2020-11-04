/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utils;
import frc.robot.utils.filters.Filter;



import edu.wpi.first.wpilibj.Joystick;
/**
 * Add your docs here.
 */
public class FilteredJoystick extends Joystick {
  static final int MAX_AXES = 16;

    private Filter[] filters;

    /**
     * Default constructor, calls superclass constructor from Joystick
     * 
     * @param joystick_num
     *            the port of the joystick being passed
     */
    public FilteredJoystick(int joystick_num)
    {
        super(joystick_num);
        filters = new Filter[MAX_AXES];
    }

    /**
     * This method returns a filtered number from the requested axis The Filter used
     * is determined from the static mapping
     * 
     * @param axis
     *            the axis to get the data from
     * @return the filtered data from the joystick axis
     */
    public double getFilteredAxis(int axis)
    {
        if (filters[axis] != null)
        {
            return filters[axis].filter(getRawAxis(axis));
        } else
        {
            return getRawAxis(axis);
        }
    }

    /**
     * Sets a pair between a axis and a filter for user's general control
     * 
     * @param axis
     *            - the joystick axis to pair with a filter
     * @param f
     *            - the filter to pair with a axis
     */
    public void setFilter(int axis, Filter f)
    {
        filters[axis] = f;
    }

    /**
     * Returns the filter object currently associated with a given joystick axis or
     * null if no filter is currently in use.
     *
     * @param axis
     *            - The joystick axis whose filter is being queried.
     * @return The axis current filter.
     */
    public Filter getFilter(int axis)
    {
        return filters[axis];
    }
}
