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
    private double distanceToAccelerate;
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
        Robot.driveTrain.resetEncoders();
        Robot.driveTrain.reset();
        speedReached = false;
        startValue = RobotMap.lMidDrive.getSelectedSensorPosition(0);
        distanceToAccelerate = 0;
    }

    @Override
    protected void execute() {
        
        System.out.println("Moving");
   //     if (distance - RobotMap.lMidDrive.getSelectedSensorPosition(0) > distanceToAccelerate)
            Robot.driveTrain.sigmoidMove(speed, speed, 1);
   //     else
    //        Robot.driveTrain.sigmoidMove(0, 0, 1);

        if (Robot.driveTrain.currentSpeeds[0] >= speed && !speedReached) {
            speedReached = true;
            distanceToAccelerate = RobotMap.lMidDrive.getSelectedSensorPosition(0) - startValue;
        }
    }
    @Override
    protected boolean isFinished() {
        System.out.println("Is Finished, encoder: " +RobotMap.lMidDrive.getSelectedSensorPosition(0) );
        return distance <= RobotMap.lMidDrive.getSelectedSensorPosition(0);
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