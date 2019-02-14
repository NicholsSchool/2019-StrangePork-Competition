package frc.robot.commands;

import javax.lang.model.util.ElementScanner6;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ArmMove extends Command
{

@Override
public void initialize()
{

}

@Override
protected void execute() 
{
    Robot.arm.move(speed);
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
