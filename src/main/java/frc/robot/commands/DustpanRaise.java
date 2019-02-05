package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DustpanRaise extends Command
{

    @Override
    public void initialize()
    {

    }

    @Override
    protected void execute() 
    {
        Robot.dustpan.raise();
    }

	@Override
    protected boolean isFinished()
    {
		return true;
    }
    
    @Override
    protected void interrupted()
    {
    
    }

    @Override
    protected void end() 
    {
        
        end();
    }


}
