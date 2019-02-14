package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ArmMoveToLevel extends Command
{
    private int level;
    private double speed;

    //Change value
    private final int ARM_OFFSET = 100;

    //Speed should always be positive
    public ArmMoveToLevel(int level, double speed)
    {
        requires(Robot.arm);
        this.level = level;
        this.speed = speed;
    }

    @Override
    public void initialize()
    {
        double currentValue = Robot.armPot.getPosition();
        if(currentValue > Robot.arm.armLevelValues[level -1])
            speed = -speed;
    }

    @Override
    protected void execute() 
    {
        Robot.arm.move(speed);
    }

    @Override
    protected boolean isFinished()
    {
        return Robot.armPot.getPosition() > Robot.arm.armLevelValues[level - 1 ] - ARM_OFFSET
            && Robot.armPot.getPosition() < Robot.arm.armLevelValues[level - 1 ] + ARM_OFFSET;
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
