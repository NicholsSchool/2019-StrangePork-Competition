
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.util.TractionControl;

public class NewDriveTrainDrive extends Command {

  private TractionControl tractionControl;

  public NewDriveTrainDrive() {
    requires(Robot.newDriveTrain);
    tractionControl = new TractionControl(Robot.newDriveTrain.motors);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.newDriveTrain.drive();
    tractionControl.checkForSpinning();
    tractionControl.display();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.newDriveTrain.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
