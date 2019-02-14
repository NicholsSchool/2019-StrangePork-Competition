package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Elevator;
import frc.robot.*;
import frc.robot.util.*;

public class ElevatorMove extends Command
{
    public double speed;
    
    public ElevatorMove(double spd)
    {
        speed = spd;

        requires(Robot.elevator);
    }
@Override
public void initialize()
{

}

@Override
protected void execute() 
{
    Robot.elevator.move(speed);
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
