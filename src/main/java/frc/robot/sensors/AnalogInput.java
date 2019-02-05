package frc.robot.sensors;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.Robot;
import frc.robot.commands.UltrasonicL;
import frc.robot.commands.UltrasonicR;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class AnalogInput extends Subsystem{
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  @Override
  public void initDefaultCommand() {
  }


  public double getRange(char side) 
  {
      if(side == 'L')  
        return RobotMap.ultrasonicL.getValue();
      if(side == 'R')
        return RobotMap.ultrasonicR.getValue();
      return 0;

    }
    public void reset(char side)
    {
      if(side == 'L')
          RobotMap.ultrasonicL.resetAccumulator();
            System.out.println("left ultrasonic reset");
      if(side == 'R')
          RobotMap.ultrasonicR.resetAccumulator();
            System.out.println("right ultrasonic reset");
    }
}