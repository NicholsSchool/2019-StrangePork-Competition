package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Climb extends Command
{
    public double speed;
    public double yAngle;

    public Climb(double yAgl, double spd )
    {
        yAgl = yAngle;
        spd = speed;
    }

    @Override
    public void initialize()
    {

    }

    @Override
    protected void execute() 
    {
        Robot.arm.move(-0.5);
    }

    @Override
    protected boolean isFinished()
    {
       double currentYAngle = Robot.navX.getPitch();

        if (currentYAngle > 50)

         return true;

        else

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
        
        Robot.arm.stop();
    }


}
