package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ArmMove extends Command
{

    public ArmMove()
    {
        requires(Robot.arm);
    }

    @Override
    public void initialize()
    {

    }

    @Override
    protected void execute() 
    {
        Robot.arm.armMove();
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }

    @Override
    protected void end() {
        Robot.arm.stop();
    }

    @Override
    protected void interrupted() {
        end();
    }
}
