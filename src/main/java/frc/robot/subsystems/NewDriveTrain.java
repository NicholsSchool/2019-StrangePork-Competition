package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.NewDriveTrainDrive;

public class NewDriveTrain extends Subsystem
{
    DriveMotor[] motors;

    public NewDriveTrain()
    {
        motors = new DriveMotor[6];
        motors[0] = new DriveMotor(RobotMap.lFrontDrive);
        motors[1] = new DriveMotor(RobotMap.lMidDrive);
        motors[2] = new DriveMotor(RobotMap.lBackDrive);
        motors[3] = new DriveMotor(RobotMap.rFrontDrive);
        motors[4] = new DriveMotor(RobotMap.rMidDrive);
        motors[5] = new DriveMotor(RobotMap.rBackDrive);

        motors[3].setInverted(true);
        motors[4].setInverted(true);
        motors[5].setInverted(true);

        motors[0].setName("Left Front");
        motors[1].setName("Left Middle");
        motors[2].setName("Left Back");
        motors[3].setName("Right Front");
        motors[4].setName("Right Mid");
        motors[5].setName("Right Back");


    }

    public void drive()
    {
        move(-Robot.oi.j0.getY(), -Robot.oi.j1.getY());
        SmartDashboard.putNumber("Left Joy", -Robot.oi.j0.getY());
        SmartDashboard.putNumber("Right Joy", -Robot.oi.j1.getY());
    }

    public void move(double leftSpeed, double rightSpeed)
    {
        set(leftSpeed, rightSpeed);
        checkForSpinning();
    }

    private void set(double leftSpeed, double rightSpeed)
    {
        for(int i = 0; i < motors.length; i ++)
        {
            if(i < motors.length/2)
                motors[i].move(leftSpeed);
            else
                motors[i].move(rightSpeed);
        }
    }

    private void checkForSpinning()
    {
      //  double leftAvg = getAverage(false);
     //   double rightAvg = getAverage(true);
        int wheelToCompare = 1;
        for(int i = 0; i < motors.length; i ++)
        {
            if(i >= motors.length/2)
                wheelToCompare = 5;
            // if(i == wheelToCompare)
            //     continue;
            if(Math.abs(motors[wheelToCompare].getVelocity()) < Math.abs(motors[i].getVelocity()) + 100 )
                motors[i].setSpinningOut(true);
            else
                motors[i].setSpinningOut(false);
        }
    }

    public void reset()
    {
        for(DriveMotor motor: motors)
            motor.reset();
    }

    private double getAverage(boolean isRight)
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
            avg += motors[i].getVelocity(); //This may have to change
        return avg/(motors.length/2);
    
    }

    public void displayInfo()
    {
        for(DriveMotor motor: motors)
            motor.displayInfo();
        
    }

    public void stop()
    {
        for(DriveMotor motor: motors)
            motor.stop();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new NewDriveTrainDrive());
    }
}