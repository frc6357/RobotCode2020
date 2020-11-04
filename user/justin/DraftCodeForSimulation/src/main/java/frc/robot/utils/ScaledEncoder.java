/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utils;
import edu.wpi.first.wpilibj.Encoder;
/**
 * Add your docs here.
 */
public class ScaledEncoder extends Encoder {
  private int pulsesPerRotation;

    /**
     * Constructor for the Scaled Encoder
     * @param channelA The digital input ID for channel A
     * @param channelB The digital input ID for channel B
     * @param reverseDirection Sets if the encoder direction is reversed
     * @param pulses The number of pulses per revolution for the encoder
     * @param diameter Diameter of the drivetrain wheels
     */
    public ScaledEncoder(int channelA, int channelB, boolean reverseDirection, int pulses, double diameter)
    {
        super(channelA, channelB, reverseDirection);
        pulsesPerRotation = pulses;
        setDistancePerPulse((diameter * Math.PI) / pulses);
    }

    
    public ScaledEncoder(int channelA, int channelB, int pulses, double diameter)
    {
        super(channelA, channelB);
        pulsesPerRotation = pulses;
        setDistancePerPulse((diameter * Math.PI) / pulses);
    }

    
    public double getAngleDegrees()
    {
        return ((get() * 360) / pulsesPerRotation);
    }

    
    public double getAngleRadians()
    {
        return ((get() * 2 * Math.PI) / pulsesPerRotation);
    }

    
    public double getRotations()
    {
        return(get() / pulsesPerRotation);
    }
}
