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

	double time;
	boolean whileHeld;
	public Outtake() {
		this(0);
	}

	public Outtake(double time)
	{
		requires(Robot.gripper);
		this.time = time;
		this.whileHeld = false;
	}

	public Outtake(boolean whileHeld)
	{
		requires(Robot.gripper);
		this.whileHeld = whileHeld;
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
		if(time > 0)
			return timeSinceInitialized() > time;
		else if(whileHeld)
			return !Robot.oi.j2.b1.get();
		else
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