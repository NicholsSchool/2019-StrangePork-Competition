package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class DustPan extends Subsystem
{
    /**
     * Uses solenoid to raise the DustPan
     */
    public void raise()
    {
      //  RobotMap.dustPanSolenoid.set(Constants.DUSTPAN_RAISED);
    }
    /**
     * Uses solenoid to drop the DustPan
     */
    public void drop()
    {
      //  RobotMap.dustPanSolenoid.set(Constants.DUSTPAN_DROPPED);
    }

    @Override
    protected void initDefaultCommand() {

    }
}                                        