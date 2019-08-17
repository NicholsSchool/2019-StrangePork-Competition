package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ChangeOuttake extends Command
{
    private boolean goUp;

    public ChangeOuttake(boolean goUp)
    {
        requires(Robot.arm);
        this.goUp = goUp;
    }

    @Override
    public void initialize()
    {
 
    }

    @Override
    protected void execute() 
    {
        double currentSpeed = Math.abs(Robot.gripper.getOuttakeSpeed());
        if (goUp && currentSpeed != 1.0)
            currentSpeed += 0.1;
        else if (!goUp && currentSpeed != 0)
            currentSpeed -= 0.1;
        Robot.gripper.setOuttakeSpeed(currentSpeed);
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
