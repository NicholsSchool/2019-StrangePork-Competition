/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.Constants;
import frc.robot.Robot;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class JumpJacks extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
  }
  public void drop() {
  //  RobotMap.jumpJacksSolenoid.set(Constants.JUMPJACKS_DROPPED);
    Robot.dropped = true;
    System.out.println("Drop Jumpjacks ");
  }
  public void raise() {
    System.out.println("Raise Jumpjacks ");
//    RobotMap.jumpJacksSolenoid.set(Constants.JUMPJACKS_RAISED);
    Robot.dropped = false;
	}
}
