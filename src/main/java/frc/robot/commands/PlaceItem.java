package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.autonomous.Place;

public class PlaceItem extends Command
{
    private Command command;
    @Override
    protected void initialize() {
    
        if(Robot.limitswitches.isBallIn())
            command = new Shoot();
        else
            command = new Place();
        command.start();
    }

    @Override
    protected void execute() {
        
    }
    
    @Override
    protected boolean isFinished() {
        return command.isCompleted();
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
        end();
    }

}