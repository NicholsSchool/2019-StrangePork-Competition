package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.subsystems.Gripper;

public class Intake extends Command {

	public Intake() {
		requires(Robot.gripper);
	}

	@Override
	public void initialize()
	{
		
	}
	
	@Override
	protected void execute() 
	{
		
		Robot.gripper.intake();

	}
	
	@Override
	protected boolean isFinished()
	{
		return false;
	}
	
	@Override
	protected void interrupted()
	{
		end();
	}
	
	@Override
	protected void end() 
	{
		Robot.gripper.stop();
	}
	
}