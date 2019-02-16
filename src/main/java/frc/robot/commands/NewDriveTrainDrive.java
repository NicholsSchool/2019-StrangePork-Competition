package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class NewDriveTrainDrive extends Command
{
    public NewDriveTrainDrive()
    {
        requires(Robot.newDriveTrain);
    }

    @Override
    protected void initialize() {
        
    }

    @Override
    protected void execute() {
        Robot.newDriveTrain.drive();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.newDriveTrain.stop();
    }

    @Override
    protected void interrupted() {
        end();
    }
}