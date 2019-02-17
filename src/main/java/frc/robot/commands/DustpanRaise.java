package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DustpanRaise extends Command
{

    public DustpanRaise()
    {
        requires(Robot.dustpan);
    }

    @Override
    public void initialize()
    {

    }

    /**
     * Raises the Dustpan
     */
    @Override
    protected void execute() 
    {
        Robot.dustpan.raise();
    }
    /**
     * Finishes automatically
     */
	@Override
    protected boolean isFinished()
    {
		return true;
    }
    /**
     * Calls end
     */
    @Override
    protected void interrupted()
    {
        end();
    }

    @Override
    protected void end() 
    {

    }
}
