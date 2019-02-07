package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class SpecialMove extends Command
{
    private double speed;
    private double distance;
    private double remainingDistance;
    private boolean sigFinished;

    public SpecialMove(double speed, double feet)
    {
        requires(Robot.driveTrain);
        double rotations = feet / (Constants.WHEEL_DIAMETER_IN_FEET * Math.PI );
        this.distance = rotations * Constants.TICKS_PER_ROTATION; 
        this.speed = speed;
    }

    @Override
    protected void initialize() {
        Robot.driveTrain.resetEncoders();
        Robot.driveTrain.reset();
        sigFinished = false;
        remainingDistance = distance;
    }

    @Override
    protected void execute() {
        if (!sigFinished)
            Robot.driveTrain.sigmoidMove(speed, speed, 1);
        else
            Robot.driveTrain.setDistance(remainingDistance);

        if (Robot.driveTrain.currentSpeeds[0] >= speed && !sigFinished) {
            sigFinished = true;
            remainingDistance = distance - RobotMap.frontLD.getSelectedSensorPosition();
        }
    }
    @Override
    protected boolean isFinished() {
        return distance <= RobotMap.frontLD.getSelectedSensorPosition(0);
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