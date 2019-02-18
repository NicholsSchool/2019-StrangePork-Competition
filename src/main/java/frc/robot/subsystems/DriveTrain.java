package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.SigmoidDrive;

public class DriveTrain extends Subsystem
{
    private WPI_TalonSRX[] motors;
    private double[] desiredSpeeds;
    public double[] currentSpeeds;


    /**
     * Our Drivetrain class uses the wpilibj Differential Drive, along with having 
     * references to each of our individual, independent motors
     * 
     * The WPI_TalonSRX array, motors, holds each motor in this order
     *  
     *   L  front  R
     *   [0]--||--[3]
     *        ||
     *   [1]--||--[4]
     *        ||
     *   [2]--||--[5]
     *       back       
     */
    public DriveTrain()
    {
        motors = new WPI_TalonSRX[6];
        motors[0] = RobotMap.lFrontDrive;
        motors[1] = RobotMap.lMidDrive;
        motors[2] = RobotMap.lBackDrive;
        motors[3] = RobotMap.rFrontDrive;
        motors[4] = RobotMap.rMidDrive;
        motors[5] = RobotMap.rBackDrive;
        reset();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new SigmoidDrive());
    }

    /**
     * Used to get Joystick values to control the robot in a tankdrive fashion, 
     * but changes values based of a sigmoid function 
     */
    public void sigmoidDrive()
    {
        sigmoidMove(-Robot.oi.j0.getY(), -Robot.oi.j1.getY());
    }

    /**
     * Used to set the values of the desired speed of each side of the robot. 
     * Values are reached by following a sigmoid function, with the value A stored in Constants
     * 
     * @param leftSpeed - the desired speed of the left side of the robot
     * @param rightSpeed - the desired speed of the right side of the robot
     */
    public void sigmoidMove(double leftSpeed, double rightSpeed)
    {
        sigmoidMove(leftSpeed, rightSpeed, Constants.SIGMOID_A);
    }

    /**
     * Used to set the values of the desired speed of each side of the robot. Values
     * are reached by following a sigmoid function, with the value A inputed into the method
     * 
     * @param leftSpeed  - the desired speed of the left side of the robot
     * @param rightSpeed - the desired speed of the right side of the robot
     * @param a - the A value for the sigmoid function
     */
    public void sigmoidMove(double leftSpeed, double rightSpeed, double a)
    {
        leftSpeed = getSideSigValue(leftSpeed, false, a);
        rightSpeed = getSideSigValue(rightSpeed, true, a);
        move(leftSpeed, rightSpeed);
    }

    
    /**
     * Gets the current speed on the sigmoid graph
     * 
     * @param speed - the desired speed to reach
     * @param isRight - true if finding the value of the right side
     * @param a - the A value for the sigmoid function
     * @return the current speed this side of the robot should be traveling at
     */
    private double getSideSigValue(double speed, boolean isRight, double a)
    {
        //Determines the index of the array, left - 0, right - 1
        int index = 0;
        if(isRight)
            index = 1;
        
        //The sigmoid function can never reach 1 or -1, so this caps the input
        if(speed > 0.99 || speed < -0.99)
            speed = speed * 0.99;
        
        //Records the desired speed, and if that has been roughly reached by
        //the function, return the desired speed. 
        desiredSpeeds[index] = speed;
        if (Math.abs(desiredSpeeds[index] - currentSpeeds[index]) < 0.01) {
            return desiredSpeeds[index];
        } 

        //Calculates the current x value in the function
        double startTime = inverseSig(currentSpeeds[index], a);

        //Used to get the next value the robot should use
        double cycleTime = 0.02;
        if (desiredSpeeds[index] < currentSpeeds[index])
            cycleTime = -cycleTime;

        return sigmoid(startTime + cycleTime, a);
    }

    /**
     * The sigmoid function used to move by the robot
     * 2 /(1 + e^(-x * A)) - 1
     * @param time - the x value for the function
     * @param a - the A value to be used by the function
     * @return the y value (speed) from the function
     */
    private double sigmoid(double time, double a) {
        return 2 / (1 + Math.pow(Math.E, -time * a)) - 1;
    }
    
    /**
     * The inverse of the sigmoid function used by the robot
     * -ln((2 / (x + 1)) - 1) / a
     * @param speed - the x value for the function
     * @param a - the A value to be used by the function
     * @return the y value (time) from the function
     */
    private double inverseSig(double speed, double a) {
        return -Math.log(2 / (speed + 1) - 1) / a;
    }

    /**
     * Uses the wpilibj Differential Drive tankdrive method to move the robot
     * 
     * @param leftSpeed - the speed for the left side
     * @param rightSpeed - the speed for the right side
     */
    public void move(double leftSpeed, double rightSpeed)
    {
        RobotMap.driveBase.tankDrive(leftSpeed, rightSpeed);
        currentSpeeds[0] = leftSpeed;
        currentSpeeds[1] = rightSpeed;
    }
    
    protected double applyDeadband(double value, double deadband) {
        if (Math.abs(value) > deadband) {
            if (value > 0.0) {
                return (value - deadband) / (1.0 - deadband);
            } else {
                return (value + deadband) / (1.0 - deadband);
            }
        } else {
            return 0.0;
        }
    }
    
    public void set(double frontSpeed, double midSpeed, double backSpeed) 
    {
        frontSpeed = applyDeadband(frontSpeed, 0.02);
        frontSpeed = Math.copySign(frontSpeed * frontSpeed, frontSpeed);

        midSpeed = applyDeadband(midSpeed, 0.02);
        midSpeed = Math.copySign(midSpeed * midSpeed, midSpeed);
      
        backSpeed = applyDeadband(backSpeed, 0.02);
        backSpeed = Math.copySign(backSpeed * backSpeed, backSpeed);

        motors[0].set(frontSpeed);
        motors[1].set(midSpeed);
        motors[2].set(backSpeed);


        motors[3].set(-frontSpeed);
        motors[4].set(-midSpeed);
        motors[5].set(-backSpeed);
    }

    /**
     * Runs a PID distance loop on the talons
     * @param distance - the distance to travel
     */
    public void setDistance(double distance) {
        
        for (int i = 0; i < motors.length; i++) {

            //This code is assuming that the right side is reversed, must be verified
            int direction = 1;
            if (i >= 2)
                direction *= -1;

            motors[i].set(ControlMode.Position, distance * direction);
        }
    }

    /**
     * Resets the desired and current speeds to 0
     */
    public void reset() {
        desiredSpeeds = new double[2];
        currentSpeeds = new double[2];
    }

    /**
     * Sets the encoder positions to 0
     */
    public void resetEncoders() {
        for (int i = 0; i < motors.length; i++)
            motors[i].setSelectedSensorPosition(0);
    }

    /**
     * Stops moving all the motors
     */
    public void stop()
    {
        reset();
        RobotMap.driveBase.stopMotor();
    }

    /**
     * Used to stop a specific motor
     * @param index - the index of the motor in the motors array
     */
    public void stopMotor(int index)
    {
        motors[index].stopMotor();
    }

}