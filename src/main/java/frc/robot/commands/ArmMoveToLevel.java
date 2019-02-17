package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ArmMoveToLevel extends Command
{
    private int level;
    private double speed;
    private double originalSpeed;

    private double value;
    //Change value
    private final int ARM_OFFSET = 3;

    //Speed should always be positive
    public ArmMoveToLevel(int level, double speed)
    {
        requires(Robot.arm);
        this.level = level;
        this.speed = speed;
        originalSpeed = speed;

    }

    @Override
    public void initialize()
    {
        if(Robot.limitswitches.isBallIn())
            value = Robot.arm.hatchLevelValues[level - 1];
        else
            value = Robot.arm.ballLevelValues[level - 1];
        speed = originalSpeed;
        double currentValue = Robot.armPot.getPosition();
        System.out.println("Value is: " + value + " CurrentValue: " + currentValue);
        if(currentValue > value)
            speed = -speed;
        System.out.println("Speed is: " + speed);
    }

    @Override
    protected void execute() 
    {
        Robot.arm.move(speed);
    }

    @Override
    protected boolean isFinished()
    {
        return Robot.armPot.getPosition() > value - ARM_OFFSET
            && Robot.armPot.getPosition() < value + ARM_OFFSET;
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
