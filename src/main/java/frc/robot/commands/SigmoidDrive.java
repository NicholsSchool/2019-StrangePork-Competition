package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SigmoidDrive extends Command
{
    public SigmoidDrive()
    {
        requires(Robot.driveTrain);
    }

    @Override
    protected void initialize() {
        
    }

    @Override
    protected void execute() {
        Robot.driveTrain.sigmoidDrive();
    }
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.driveTrain.stop();
    }

    @Override
    protected void interrupted() {
        end();
    }
}