package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.hal.HAL;
import edu.wpi.first.hal.FRCNetComm.tInstances;
import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.SigmoidDrive;

public class DriveTrain extends Subsystem
{
    private WPI_TalonSRX[] motors;
    private double[] desiredSpeeds;
    private boolean[] speedsReached;
    public double[] currentSpeeds;
    private double[] lastCheck;
    private boolean[] motorSpinning;
    public boolean moveTest;
    private boolean m_reported;

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
        motors[5] = RobotMap.lBackDrive;
        reset();
        config();
        moveTest = true;
        motorSpinning = new boolean[6];
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new SigmoidDrive());
    }

    private void config()
    {
        for(WPI_TalonSRX _talon : motors)
        {
            _talon.configNominalOutputForward(0, 30);
            _talon.configNominalOutputReverse(0, 30);
            _talon.configPeakOutputForward(1, 30);
            _talon.configPeakOutputReverse(-1, 30);

            /* Config the Velocity closed loop gains in slot0 */
            _talon.config_kF(0, 1023.0 / 7200.0, 30);
            _talon.config_kP(0, 0.01, 30);
            _talon.config_kI(0, 0, 30);
            _talon.config_kD(0, 0, 30);
        }
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
 
        set(leftSpeed, rightSpeed);
    //    move(leftSpeed, rightSpeed);
       // velocityLoop(leftSpeed, rightSpeed);
    //    move(leftSpeed, rightSpeed);
    //    moveMiddle();
      //  motors[4].set(0);
        // if(speedsReached[0])
        //     checkForSpinning(false);
        // if(speedsReached[1])
        //     checkForSpinning(true);
    
    }

    /*
        Note: Could do 
    */

    
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
        speedsReached[index] = false;
        if (Math.abs(desiredSpeeds[index] - currentSpeeds[index]) < 0.01) {
            speedsReached[index] = true;
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



    public void moveMiddle()
    {
        double leftSpeed = currentSpeeds[0];
        double rightSpeed= currentSpeeds[1];
        if(Math.abs(motors[1].getSelectedSensorVelocity()) > Math.abs(motors[0].getSelectedSensorVelocity()) + 100)
        {
            System.out.println("Half Left");
            leftSpeed /= 2;
        }
        if (Math.abs(motors[4].getSelectedSensorVelocity()) > Math.abs(motors[3].getSelectedSensorVelocity())+ 100)
        {
            System.out.println("Half right");
            rightSpeed /= 2;
        }

        // leftSpeed = applyDeadband(leftSpeed, 0.02);
        // rightSpeed = applyDeadband(rightSpeed, 0.02);

        // leftSpeed = Math.copySign(leftSpeed * leftSpeed, leftSpeed);
        // rightSpeed = Math.copySign(rightSpeed * rightSpeed, rightSpeed);
        // motors[1].set(leftSpeed);
        // motors[4].set(-rightSpeed);
        RobotMap.midDriveBase.tankDrive(leftSpeed, rightSpeed);

    }

    public void moveMiddle(double leftSpeed, double rightSpeed)
    {

        System.out.println("Left: " + leftSpeed + "\nright: " + rightSpeed);
        leftSpeed = applyDeadband(leftSpeed, 0.02);
        rightSpeed = applyDeadband(rightSpeed, 0.02);

        leftSpeed = Math.copySign(leftSpeed * leftSpeed, leftSpeed);
        rightSpeed = Math.copySign(rightSpeed * rightSpeed, rightSpeed);
        motors[1].set(leftSpeed);
        motors[4].set(-rightSpeed);
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
        lastCheck = new double[2];
        speedsReached = new boolean[]{true, true}; // Desired and current speed should be 0
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


    public void velocityLoop(double leftSpeed, double rightSpeed)
    {
        currentSpeeds[0] = leftSpeed;
        currentSpeeds[1] = rightSpeed;
        leftSpeed = Math.copySign(leftSpeed * leftSpeed, leftSpeed);
        rightSpeed = Math.copySign(rightSpeed * rightSpeed, rightSpeed);
        System.out.println("Left: " + leftSpeed + "Right: " + rightSpeed);
        leftSpeed = leftSpeed * 500.0 * 4096 / 600;
        rightSpeed = rightSpeed * 500.0 * 4096 / 600; 
        System.out.println("Velocity Looping LeftSpeed: " + leftSpeed + " RightSpeed: " + rightSpeed + "\n"); 
        
        for(int i = 0; i < motors.length; i ++)
        {
            if(i < motors.length/2)
                motors[i].set(ControlMode.Velocity, leftSpeed);
            else
                motors[i].set(ControlMode.Velocity, rightSpeed);
        }
    }


    public boolean lastCheck(boolean isRight)
    {
        int index = 0;
        if(isRight)
            index = 1;

        //If its been longer than a second, check again
        if((System.currentTimeMillis() - lastCheck[index]) > 1000)
        {
                lastCheck[index] = System.currentTimeMillis();
                return true;
        }
        //It hasn't been a second yet, dont check
        return false;


    }


    public void set(double leftSpeed, double rightSpeed) {
        if (!m_reported) {
            HAL.report(tResourceType.kResourceType_RobotDrive, 2, tInstances.kRobotDrive2_DifferentialTank);
            m_reported = true;
        }
        currentSpeeds[0] = leftSpeed;
        currentSpeeds[1] = rightSpeed;

        leftSpeed = applyDeadband(leftSpeed, 0.02);

        rightSpeed = applyDeadband(rightSpeed, 0.02);

        leftSpeed = Math.copySign(leftSpeed * leftSpeed, leftSpeed);
        rightSpeed = Math.copySign(rightSpeed * rightSpeed, rightSpeed);
    //    System.out.println("Right Speed: " + rightSpeed);
        double rightAvg = average(true);
        double leftAvg = average(false);
        for(int i = 0; i < 3; i++)
        {
            System.out.println(
                "Reached: " + speedsReached[0] 
                +"Check: " + lastCheck(false)
                + "Avg: " + (Math.abs(motors[i].getSelectedSensorVelocity()) > Math.abs(rightAvg) + 100)
            );
            // if(speedsReached[0]) && lastCheck(false) && (Math.abs(motors[i].getSelectedSensorVelocity()) > Math.abs(rightAvg) + 100) ) 
            // {
  
            //     motors[i].set(0); 
            //     System.out.println("Stopping " + i);
            // }
            // else
            // {
            //     motors[i].set(leftSpeed);
            // }
        }
    //     motors[3].set(-rightSpeed);
         motors[4].set(-rightSpeed);
         motors[5].set(-rightSpeed);

        // for(int i = 0; i < motors.length; i++) {
        //     if (i < motors.length / 2)
        //         motors[i].set(leftSpeed);
        //     else
        //         motors[i].set(-rightSpeed);

        //     motors[i].feed();
        // }
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

    public void moveMotor(double speed, int motorIndex)
    {
        System.out.println("Moving the specific motor: " + motorIndex + " Speed: " + speed + "\n");
        speed = Math.copySign(speed * speed, speed);
        if(motorIndex >= motors.length/2)
            speed = -speed;
        motors[motorIndex].set(speed);
        RobotMap.driveBase.feed();
    }

    public void checkForSpinning(boolean isRight)
    {
        double avg = average(isRight);
        int start = 0;
        int end = motors.length/2;
        int index = 0;
        if(isRight)
        {
            start = end;
            end = motors.length;
            index = 1;
        }
        for(int i = start; i < end; i++)
        {
            if(Math.abs(motors[i].getSelectedSensorVelocity()) > Math.abs(avg) + 100 )
                moveMotor(0, i);
        }

    }

    public double average(boolean isRight)
    {
        int start = 0;
        int end = motors.length/2;
        if(isRight)
        {
            start = end;
            end = motors.length;
        }
        double avg = 0;
        for(int i = start; i < end; i ++)
            avg += motors[i].getSelectedSensorVelocity(); //This may have to change
        return avg/(motors.length/2);
    }

    public void displayInfo()
    {
        String[] names = {"Left Front Motor ", "Left Middle Motor ", "Left Back Motor ",
                          "Right Front Motor ", "Right Middle Motor ", "Right Back Motor " };

        for(int i = 0; i < motors.length; i ++)
        {
            SmartDashboard.putNumber(names[i] + "Encoder", motors[i].getSelectedSensorPosition());
            SmartDashboard.putNumber(names[i] + "Velocity", motors[i].getSelectedSensorVelocity());
            // SmartDashboard.putNumber(names[i] + "Current", motors[i].getOutputCurrent());
            // SmartDashboard.putNumber(names[i] + "Voltage", motors[i].getMotorOutputVoltage());
            // SmartDashboard.putNumber(names[i] + "Percent", motors[i].getMotorOutputPercent());
        }

        SmartDashboard.putNumber("Left Velocity Average", average(false));
        SmartDashboard.putNumber("Right Velocity Average", average(true));
    }
}