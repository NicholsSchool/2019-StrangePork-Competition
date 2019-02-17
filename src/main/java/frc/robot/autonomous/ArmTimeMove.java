package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ArmTimeMove extends Command
{
    double speed, time;

    public ArmTimeMove(double speed, double time)
    {
        requires(Robot.arm);
        this.speed = speed;
        this.time = time;
    }

    @Override
    protected void initialize() {
        
    }

    @Override
    protected void execute() {
        System.out.println("Arm Moving");
        Robot.arm.move(speed);
    }
    
    @Override
    protected boolean isFinished() {
        return timeSinceInitialized() > time;
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