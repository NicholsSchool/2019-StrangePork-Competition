package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ElevatorTimeMove extends Command
{
    double speed, time;
    double startTime;
    public ElevatorTimeMove(double speed, double time)
    {
        requires(Robot.elevator);
        this.speed = speed;
        this.time = time;
    }

    @Override
    protected void initialize() {
        
    }

    @Override
    protected void execute() {
        System.out.println("Elevator moving");
        Robot.elevator.move(speed);
    }
    
    @Override
    protected boolean isFinished() {
        return timeSinceInitialized() > time ;
    }

    @Override
    protected void end() {
        Robot.elevator.stop();
    }

    @Override
    protected void interrupted() {
        end();
    }
}