/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ArmMoveToLevel extends Command {

  private int level;
  private double speed;
  private double originalSpeed;

  private boolean isAbove;

  private double value;
  // Change value
  private final int COMING_UP_VAL = 1;
  private final int COMING_DOWN_VAL = 0;

  public ArmMoveToLevel(int level, double speed) {
      requires(Robot.arm);
      this.level = level;
      this.speed = speed;
      originalSpeed = speed;
      value = 0;
  }

  public ArmMoveToLevel(double potVal, double speed)
  {
    this(-1, speed);
    value = potVal;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if(value == 0)
      {
        if(!Robot.limitswitches.isBallIn())
          value = Robot.arm.hatchLevelValues[level - 1];
        else
        {
          value = Robot.arm.ballLevelValues[level - 1];
          System.out.println("ball");
        }
    }

    speed = originalSpeed;
    double currentValue = Robot.armPot.getPosition();

    System.out.println("Value is: " + value + " CurrentValue: " + currentValue);
    isAbove = false;
    if(currentValue > value)
    {
      speed = -speed;
      isAbove = true;
    }
    System.out.println("Speed is: " + speed);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
     Robot.arm.move(speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {

    if(isAbove)
      return Robot.armPot.getPosition() < value + COMING_DOWN_VAL;
    else
      return Robot.armPot.getPosition() > value - COMING_UP_VAL;

  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.arm.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
