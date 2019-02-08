package frc.robot.commands;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.*;
/** import frc.robot.subsystems.JumpJacks;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
 */
public class WallAllign extends Command {
  double speed;
  double lspeed;
  double rspeed;
  public WallAllign(double speed) {
    this.speed = speed;
  }
  /**
   * tells which side is further from wall and runs the wheels on 
   * that side to allign with the wall
   */
  @Override
  protected void initialize() {
    double left = RobotMap.leftFrontUltraSonic.getValue();
    double right = RobotMap.rightFrontUltraSonic.getValue();
    if(right < left)
      lspeed = speed;
    else
      rspeed = speed;
  }
  
  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.driveTrain.sigmoidMove(lspeed, rspeed);     
  }
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    double left = RobotMap.leftFrontUltraSonic.getValue();
    double right = RobotMap.rightFrontUltraSonic.getValue();
   return (Math.abs(left - right)<=10); 
  }
  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain.stop();
  }
  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}