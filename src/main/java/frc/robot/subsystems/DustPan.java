package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.RobotMap;

/**
 * Subsystem class used for controlling the dustpan, which is used for picking up
 * hatches on the ground
 */
public class DustPan extends Subsystem
{
    /**
     * Raises the DustPan
     */
    public void raise()
    {
        RobotMap.dustPanSolenoid.set(Constants.DUSTPAN_RAISED);
    }
    /**
     * Drops the DustPan
     */
    public void drop()
    {
        RobotMap.dustPanSolenoid.set(Constants.DUSTPAN_DROPPED);
    }

    @Override
    protected void initDefaultCommand() {

    }
}                                        