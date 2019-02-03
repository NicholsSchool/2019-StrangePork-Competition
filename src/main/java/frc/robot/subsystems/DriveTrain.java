package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class DriveTrain extends Subsystem
{
    private WPI_TalonSRX[] motors;
    private double[] desiredSpeeds;
    public double[] currentSpeeds;

    public DriveTrain()
    {
        motors = new WPI_TalonSRX[6];
        motors[0] = RobotMap.frontLD;
        motors[1] = RobotMap.midLD;
        motors[2] = RobotMap.backLD;
        motors[3] = RobotMap.frontRD;
        motors[4] = RobotMap.midRD;
        motors[5] = RobotMap.backLD;
        reset();
    }

    public void reset()
    {
        desiredSpeeds = new double[2];
        currentSpeeds = new double[2];
    }

    @Override
    protected void initDefaultCommand() {

    }

    public void sigmoidDrive()
    {
        sigmoidMove(Robot.oi.j0.getY(), Robot.oi.j0.getY());
    }

    public void sigmoidMove(double leftSpeed, double rightSpeed)
    {
        sigmoidMove(leftSpeed, rightSpeed, Constants.SIGMOID_A);
    }

    public void sigmoidMove(double leftSpeed, double rightSpeed, double a)
    {
        leftSpeed = getSideSigValue(leftSpeed, false, a);
        rightSpeed = getSideSigValue(rightSpeed, true, a);
        move(leftSpeed, rightSpeed);
    }

    
    private double getSideSigValue(double speed, boolean isRight, double a)
    {
        int index = 0;
        if(isRight)
            index = 1;
        
        if(speed > 0.99 || speed < -0.99)
            speed = speed * 0.99;
        
        desiredSpeeds[index] = speed;

        if (Math.abs(desiredSpeeds[index] - currentSpeeds[index]) < 0.01) {
            return desiredSpeeds[index];
        } 

        double startTime = inverseSig(currentSpeeds[index], a);
        double cycleTime = 0.02;

        if (desiredSpeeds[index] < currentSpeeds[index])
            cycleTime = -cycleTime;

        return sigmoid(startTime + cycleTime, a);
    }

    private double sigmoid(double time, double a) {
        return 2 / (1 + Math.pow(Math.E, -time * a)) - 1;
    }
    
    private double inverseSig(double speed, double a) {
        return -Math.log(2 / (speed + 1) - 1) / a;
    }

    public void move(double leftSpeed, double rightSpeed)
    {
        RobotMap.driveBase.tankDrive(leftSpeed, rightSpeed);
        currentSpeeds[0] = leftSpeed;
        currentSpeeds[1] = rightSpeed;
    }

    public void stop()
    {
        reset();
        RobotMap.driveBase.stopMotor();
    }

    public void stopMotor(int index)
    {
        motors[index].stopMotor();
    }

}