package frc.robot.commands;
import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class AngleTurn extends Command
{
    public double speed;
    public double desiredAngle;
    /**
     * The AngleTurn class turns the robot to a desired angle 
     * using the NavX sensor, using the DriveTrain.
     * 
     * @param agl - the angle the robot will rotate to
     * @param spd - the speed which the robot will rotate
     */
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

    /**
     * The excecute method determines wether the robot turns
     * to the right or left, depending on which direction 
     * is shorter.
     */
    @Override
    protected void execute() 
    {
        if(desiredAngle > 0)
            Robot.driveTrain.sigmoidMove(speed, -speed);    
        
        else
            Robot.driveTrain.sigmoidMove(-speed, speed);
        
    }

    /**
     * This method specifies a range of angles. the DriveTrain
     * stops when the robot is at an angle within the range.
     * The range is necessary becouse otherwise the robot would never 
     * return an angle that is precisely the value the method requires.   
     */
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
        Robot.driveTrain.stop();
    }
}