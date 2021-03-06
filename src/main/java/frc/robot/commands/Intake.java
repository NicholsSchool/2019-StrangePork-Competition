package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.subsystems.Gripper;

/**
 * The Intake Command runs the gripper motors
 * until it is manually stopped.
 */
public class Intake extends Command {
	/**
	 * Requires the gripper subsytem.
	 */

	private double time;
	public Intake() {
		this(0);
	}

	public Intake(double time)
	{
		requires(Robot.gripper);
		this.time = time;
	}

	/**
	 * Intake does nothing during initialize
	 */
	@Override
	public void initialize() {

	}

	/**
	 * Runs the intake meathod on gripper.
	 */
	@Override
	protected void execute() {

		Robot.gripper.intake();

	}

	/**
	 * isFinished returns false, so Intake will never finish on its own. Use this
	 * command with whileHeld();
	 * 
	 * @return always false
	 */
	@Override
	protected boolean isFinished() {
		if(time > 0)
			return timeSinceInitialized() > time;
		else
			return false;
	}

	/**
	 * Runs the end() method.
	 */
	@Override
	protected void interrupted() {
		end();
	}

	/**
	 * End stops the gripper motors.
	 */
	@Override
	protected void end() {
		Robot.gripper.stop();
	}

}