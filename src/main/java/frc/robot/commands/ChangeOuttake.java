package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ChangeOuttake extends Command
{
    private int level;
    public ChangeOuttake(int level)
    {
        requires(Robot.arm);
        this.level = level;

    }

    @Override
    public void initialize()
    {

        Robot.gripper.setOuttakeSpeed(Robot.gripper.outtakeSpeeds[level - 1]);
    }

    @Override
    protected void execute() 
    {
    }

    @Override
    protected boolean isFinished()
    {

        return true;
    }

    @Override
    protected void interrupted()
    {
        end();
    }

    @Override
    protected void end() 
    {
    }


}
