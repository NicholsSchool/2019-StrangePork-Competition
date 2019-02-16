package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveTimeMove extends Command
{

    double speed, time;

    public DriveTimeMove(double speed, double time)
    {
        requires(Robot.driveTrain);
        this.speed = speed;
        this.time = time;
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        System.out.println("Driving");
        Robot.driveTrain.sigmoidMove(speed, speed);
    }

    @Override
    protected boolean isFinished() {
        return timeSinceInitialized() > time;
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
