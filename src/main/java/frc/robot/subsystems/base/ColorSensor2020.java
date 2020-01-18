package frc.robot.subsystems.base;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
//import com.revrobotics.ColorMatchResult;
//import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.util.Color;

/**
 * The class ColorSensor2020 will have three methods that will return Color rgb values,
 * returns the game color, and finally will return a normalized distance to determine
 * if the color sensor can acurately determine the color or if it is too far.
 */ 

 public class ColorSensor2020
 {
     /**
      * This is the ColorSensorV3 member variable that is used by all three methods.
      */
     private ColorSensorV3 colSensor;
     
     /**
      * This is the constructor that needs a I2C port to be passed in when creating an instance of
      * ColorSensor2020.
      */
     public ColorSensor2020(I2C.Port port)
     {
         colSensor = new ColorSensorV3(port);
     }
     
     /**
      * This method will return Color rgb values that are normalized.
      */
     public Color getColor()
     {
         // TODO: Write This
         return colSensor.getColor();
     }

     /**
      * This method will return the color that the ColorSensorV3 is reading.
      */
     public Color2020 getGameColor()
     {
        return Color2020.CYAN;
     }

     /**
      * This method returns a normalized integer value that tells how far the
      * ColorSensorV3 needs to go before it can accuratly read the colors.
      */
     public int getProximity()
     {
        return 0;
     }

 }