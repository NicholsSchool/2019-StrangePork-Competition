package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class FloorHatchScoop extends Command
{
    double speed;
    double time;

    public FloorHatchScoop(double sp, double tm)
    {
        speed = sp;
        time = tm;
    }

    @Override
    public void initialize()
    {

    }

    @Override
    protected void execute() 
    {
        
    }

	@Override
    protected boolean isFinished()
    {
		return false;
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