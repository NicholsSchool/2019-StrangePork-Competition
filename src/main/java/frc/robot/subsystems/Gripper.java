package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Gripper extends Subsystem
{

    public void intake()
    {
        RobotMap.leftGrip.set(Constants.INTAKE_SPEED);
        RobotMap.rightGrip.set(-Constants.INTAKE_SPEED);


    } 
    public void outtake()
    {
        RobotMap.leftGrip.set(-Constants.INTAKE_SPEED);
        RobotMap.rightGrip.set(Constants.INTAKE_SPEED);


    }
    public void stop()
    {
        RobotMap.leftGrip.set(0);
        RobotMap.rightGrip.set(0);
    }

    @Override
    protected void initDefaultCommand() {

    }



}