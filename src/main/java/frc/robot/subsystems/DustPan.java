package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.RobotMap;

public class DustPan
{
    /**
     * Uses solenoid to raise the DustPan
     */
    public void raise()
    {
        RobotMap.dustPanSolenoid.set(Constants.DUSTPAN_RAISED);
    }
    /**
     * Uses solenoid to drop the DustPan
     */
    public void drop()
    {
        RobotMap.dustPanSolenoid.set(Constants.DUSTPAN_DROPPED);
    } 
}                                        