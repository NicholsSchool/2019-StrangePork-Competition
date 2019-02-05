package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.RobotMap;

public class DustPan
{
    public void raise()
    {
        RobotMap.solenoid0.set(Constants.DUSTPAN_RAISED);
    }
    public void drop()
    {
        RobotMap.solenoid0.set(Constants.DUSTPAN_DROPPED);
    } 
}                                        