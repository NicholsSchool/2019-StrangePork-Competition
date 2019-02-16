package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DustpanDrop extends Command
{

    @Override
    public void initialize()
    {

    }

    /**
     * Drops the Dustpan
     */
    @Override
    protected void execute() 
    {
        System.out.println("Dropping");
        Robot.dustpan.drop();
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