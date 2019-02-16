package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ElevatorMove extends Command
{

    public ElevatorMove()
    {
        requires(Robot.elevator);
    }

    @Override
    public void initialize()
    {

    }

    @Override
    protected void execute() 
    {
        Robot.elevator.elevatorMove();
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
        
       Robot.elevator.stop();
    }


}
