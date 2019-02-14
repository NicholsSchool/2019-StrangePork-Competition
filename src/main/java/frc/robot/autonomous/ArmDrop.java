package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ArmDrop extends Command
{
    public double speed;
    public double desiredAngle;

    public ArmDrop(double yAgl, double spd )
    {
        yAgl = desiredAngle;
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

        if (currentYAngle > desiredAngle)

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
