/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.JumpJacks;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * An example command.  You can replace me with your own command.
 */

public class JumpJacksDrop extends Command {
  public JumpJacksDrop() {
    requires(Robot.m_subsystem);
    // Use requires() here to declare subsystem dependencies
    //requires(Robot.m_subsystem);
}
public Joystick j0;
public JoystickButton j0b1, j0b2;
public void OI() {
  j0 = new Joystick(0);

  j0b1 = new JoystickButton(j0, 1);
  j0b2 = new JoystickButton(j0, 2);
}
  
  @Override
  protected void initialize() {
    Robot.jumpJacks.drop();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //j0b1.whenPressed(new dropJumpJacks());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}