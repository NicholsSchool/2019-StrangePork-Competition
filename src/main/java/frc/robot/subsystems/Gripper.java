package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * The Gripper class contains methods to run
 * the two motors on the ball gripper.
 */
public class Gripper extends Subsystem
{

    /**
     * Runs the gripper motors to intake a ball.
     */
    public void intake()
    {
        RobotMap.leftGrip.set(Constants.INTAKE_SPEED);
        RobotMap.rightGrip.set(-Constants.INTAKE_SPEED);


    } 
    /**
     * runs gripper motors to outtake ball.
     */
    public void outtake()
    {
        RobotMap.leftGrip.set(-Constants.INTAKE_SPEED);
        RobotMap.rightGrip.set(Constants.INTAKE_SPEED);


    }
    /**
     * Stops the gripper motors.
     */
    public void stop()
    {
        RobotMap.leftGrip.set(0);
        RobotMap.rightGrip.set(0);
    }

    @Override
    protected void initDefaultCommand() {

    }



}