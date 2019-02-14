package frc.robot.subsystems;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
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
    public void setSpeed (double speed){
        RobotMap.leftGrip.set(speed);
        RobotMap.rightGrip.set(-speed);
    }
    /**
     * Runs the gripper motors to intake a ball.
     */
    public void intake()
    {
        if(!Robot.limitswitches.isBallIn())
        {
            RobotMap.leftGrip.set(Constants.INTAKE_SPEED);
            RobotMap.rightGrip.set(-Constants.INTAKE_SPEED);
        }
    } 
    /**
     * runs gripper motors to outtake ball.
     */
    public void outtake()
    {
        if(Robot.limitswitches.isBallIn())
        {
            RobotMap.leftGrip.set(-Constants.OUTTAKE_SPEED);
            RobotMap.rightGrip.set(Constants.OUTTAKE_SPEED);
        }
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