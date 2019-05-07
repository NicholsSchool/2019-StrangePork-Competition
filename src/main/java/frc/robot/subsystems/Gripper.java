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
    private double outtakeSpeed;
    public double[] outtakeSpeeds;

    public Gripper()
    {
        outtakeSpeed = -Constants.LEVEL_1_OUTTAKE_SPD;
        outtakeSpeeds = new double[3];
        outtakeSpeeds[0] = Constants.LEVEL_1_OUTTAKE_SPD;
        outtakeSpeeds[1] = Constants.LEVEL_2_OUTTAKE_SPD;
        outtakeSpeeds[2] = Constants.LEVEL_3_OUTTAKE_SPD;

    }

    public void setSpeed (double speed){
        RobotMap.leftGrip.set(-speed);
        RobotMap.rightGrip.set(speed);
    }
    /**
     * Runs the gripper motors to intake a ball.
     */
    public void intake()
    {
        if(!Robot.limitswitches.isBallIn())
          setSpeed(Constants.INTAKE_SPEED);
        else
            setSpeed(0);
    } 
    /**
     * runs gripper motors to outtake ball.
     */
    public void outtake()
    {   
        if(outtakeSpeed > 0)
            outtakeSpeed = -outtakeSpeed;
        setSpeed(outtakeSpeed);
        System.out.println("Outtake speed: " + outtakeSpeed );
    }
    /**
     * Stops the gripper motors.
     */
    public void stop()
    {
        RobotMap.leftGrip.set(0);
        RobotMap.rightGrip.set(0);
    }

    public void setOuttakeSpeed(double outtakeSpeed) {
        System.out.println("Changeing value to: " + outtakeSpeed);
        this.outtakeSpeed = outtakeSpeed;
    }

    public double getOuttakeSpeed() {
        return outtakeSpeed;
    }

    @Override
    protected void initDefaultCommand() {

    }



}