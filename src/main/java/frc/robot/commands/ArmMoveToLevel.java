package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ArmMoveToLevel extends Command
{
    private int level;
    private double speed;
    private double originalSpeed;

    private boolean isAbove;

    private double value;
    //Change value
    private final int COMING_UP_VAL = 1;
    private final int COMING_DOWN_VAL = 4;


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
        if(!Robot.limitswitches.isBallIn())
            value = Robot.arm.hatchLevelValues[level - 1];
        else
        {
            value = Robot.arm.ballLevelValues[level - 1];
            System.out.println(" ball");
            Robot.gripper.setOuttakeSpeed(Robot.gripper.outtakeSpeeds[level - 1]);
        }
        speed = originalSpeed;
        double currentValue = Robot.armPot.getPosition();

        System.out.println("Value is: " + value + " CurrentValue: " + currentValue);
        isAbove = false;
        if(currentValue > value)
        {
            speed = -speed;
            isAbove = true;
        }
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
        if(isAbove)
            return Robot.armPot.getPosition() < value + COMING_DOWN_VAL;
        else
            return Robot.armPot.getPosition() > value - COMING_UP_VAL;
            
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
