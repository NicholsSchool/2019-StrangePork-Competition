package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class SigmoidMoveForward extends Command
{
    private double speed;
    private double distance;
    private double startValue;
    private boolean speedReached;

    public SigmoidMoveForward(double speed, double feet)
    {
        requires(Robot.driveTrain);
        double rotations = feet / (Constants.WHEEL_DIAMETER_IN_FEET * Math.PI );
        this.distance = rotations * Constants.TICKS_PER_ROTATION; 
        this.speed = speed;
    }

    @Override
    protected void initialize() {
        Robot.driveTrain.reset();
        speedReached = false;
        startValue = RobotMap.lMidDrive.getSelectedSensorPosition(0);

    }

    @Override
    protected void execute() {
        
        System.out.println("Moving");
            Robot.driveTrain.sigmoidMove(speed, speed, 1);
    }
    @Override
    protected boolean isFinished() {
        System.out.println("Is Finished, encoder: " +RobotMap.lMidDrive.getSelectedSensorPosition(0) );
        return distance <= RobotMap.lMidDrive.getSelectedSensorPosition(0)-startValue;
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