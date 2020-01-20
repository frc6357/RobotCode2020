package frc.robot.subsystems.base;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * Class for any sensor that has a limit or only a true
 * or false, 0 or 1, state
 */
public class LimitSensor extends DigitalInput
{
    // TODO: Document this class
    boolean inverted;
    public LimitSensor(int channel, boolean inverted)
    {
        super(channel);
        this.inverted = inverted;
    }

    @Override
    public boolean get() 
    {
        boolean returnVal = get();
        returnVal = inverted ? !returnVal: returnVal;
        return returnVal;
    }
}