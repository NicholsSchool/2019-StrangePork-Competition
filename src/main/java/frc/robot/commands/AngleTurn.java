package frc.robot.commands;
import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class AngleTurn extends Command
{
    public double speed;
    public double desiredAngle;
   
    public AngleTurn(double agl, double spd)
    {
        speed = spd;
        desiredAngle = agl;

        requires(Robot.driveTrain);
    }
    @Override
    public void initialize()
    {
        Robot.navX.reset();
    }

    @Override
    protected void execute() 
    {
        if(desiredAngle > 0)
        {
        Robot.driveTrain.move(speed, -speed);    
        }
        else 
        {
        Robot.driveTrain.move(-speed, speed);
        }
    }

    @Override
    protected boolean isFinished()
    {
        double currentAngle = Robot.navX.getAngle();

        if(currentAngle < desiredAngle + 5 && currentAngle > desiredAngle - 5)
        {
          return true;
        }  
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
        
        end();
    }
}