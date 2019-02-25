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
 * Subsystem class for the JumpJacks (back pneumatic wheels)
 */
public class JumpJacks extends Subsystem {
 

  @Override
  public void initDefaultCommand() {
  }

  /**
   * Drops the back wheels
   */
  public void drop() {
    RobotMap.jumpJacksSolenoid.set(Constants.JUMPJACKS_DROPPED);
    Robot.dropped = true;
  }

  /**
   * Raises the back wheels
   */
  public void raise() {
    RobotMap.jumpJacksSolenoid.set(Constants.JUMPJACKS_RAISED);
    Robot.dropped = false;
	}
}
