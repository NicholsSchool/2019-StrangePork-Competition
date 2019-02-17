package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ArmDown extends Command
{
    double speed;
    public ArmDown(double speed)
    {
        requires(Robot.arm);
        this.speed = speed;
    }

    @Override
    protected void initialize() {
        
    }
    @Override
    protected void execute() {
        Robot.arm.move(speed);
    }

    @Override
    protected boolean isFinished() {
        return Robot.limitswitches.isArmDown();
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