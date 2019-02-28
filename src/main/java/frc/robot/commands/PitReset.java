package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class PitReset extends Command {
  double speed;
  double resetPos;
  public PitReset() {
    requires(Robot.arm);
    speed = 0.2;
    resetPos = 380;
  }

  @Override
  protected void initialize() {
    if(Robot.armPot.getPosition() > 380)
      speed = -speed;
  }

  @Override
  protected void execute() {
    Robot.arm.move(speed);
  }

  @Override
  protected boolean isFinished() {
    return Math.abs(Robot.armPot.getPosition() - 380) < 3;
  }

  @Override
  protected void end() {
    Robot.arm.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
