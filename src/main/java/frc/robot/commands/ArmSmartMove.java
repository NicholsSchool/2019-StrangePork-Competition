/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ArmSmartMove extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ArmSmartMove(int level, double speed) {
       addSequential(new ChangeOuttake(level));
       addSequential((new ArmMoveToLevel(level, speed)));
  }
  public ArmSmartMove(double potVal, double speed)
  {
    addSequential(new ArmMoveToLevel(potVal, speed));
  }

}
