package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.subsystems.Gripper;
/**
 * The Outtake Command runs the gripper motors
 * until it is manually stopped.
 */
public class Outtake extends Command {
/**
	 * Requires the gripper subsytem.
	 */
	public Outtake() {
		requires(Robot.gripper);
	}
/**
	 * Outtake does nothing during initialize
	 */
	@Override
	public void initialize()
	{
		
	}
	/**
	 * Runs the outtake method on gripper.
	 */
	@Override
	protected void execute() 
	{
		Robot.gripper.outtake();
	}
	/**
	 * isFinished returns false, so Outtake will never finish on its own. Use this
	 * command with whileHeld();
	 * 
	 * @return always false
	 */
	@Override
	protected boolean isFinished()
	{
		return false;
	}
	/**
	 * Runs the end() method.
	 */
	@Override
	protected void interrupted()
	{
		end();
	}
	/**
	 * End stops the gripper motors.
	 */
	@Override
	protected void end() 
	{
		Robot.gripper.stop();
	}
	
}