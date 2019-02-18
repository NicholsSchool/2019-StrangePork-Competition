/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Grab extends CommandGroup {
  /**
   * Add your docs here.
   */
  public Grab() {
    addSequential(new ElevatorTimeMove(1, 0.2));
    addSequential(new ArmTimeMove(1, 0.1));
    addSequential(new ElevatorTimeMove(-1, 0.2));
    addSequential(new DriveTimeMove(-1, 0.3));
  }
}
