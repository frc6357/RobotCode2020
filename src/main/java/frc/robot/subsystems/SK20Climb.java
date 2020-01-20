package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/* 
The SK20Climb class is a subsystem that interacts with the climbing mechanism in order to deploy the arm and winch the robot up
*/
public class SK20Climb extends SubsystemBase {

    public SK20Climb() {

    }

    /*
     * When deployArm is called the spring will release, deploying the arm in the
     * procress. After the First arm is deployed the second arm will deploy after
     * which the entire climb mechanism will lock into position
     */
    public void deployArm() {

    }

    /*
     * When raiseHook is called the pneumatic piston at the top of the robot will
     * raise allowing the driver to get into position
     */
    public void raiseHook() {

    }

    /*
     * When the lowerHook method is called the pneumatic piston at the top of the
     * robot will lower, hooking the hook onto the rug
     */
    public void lowerHook() {

    }

    /*
     * When the startWinchRobot method is called a motor will start to winch the
     * entirity of the robot upwards
     */
    public void startWinchRobot() {

    }

    /*
     * When the stopWinchRobot method is called the motor winching the robot will be
     * stopped.
     */
    public void stopWinchRobot() {

    }

}