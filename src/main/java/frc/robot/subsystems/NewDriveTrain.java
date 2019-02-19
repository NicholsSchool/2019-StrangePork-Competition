package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
//import frc.robot.commands.NewDriveTrainDrive;
import frc.robot.commands.NewDriveTrainDrive;

public class NewDriveTrain extends Subsystem {
    public DriveMotor[] motors;

    public NewDriveTrain() {
        motors = new DriveMotor[6];
        motors[0] = new DriveMotor(RobotMap.lFrontDrive, "Left Front", true);
        motors[1] = new DriveMotor(RobotMap.lMidDrive, "Left Middle", true);
        motors[2] = new DriveMotor(RobotMap.lBackDrive, "Left Back", true);
        motors[3] = new DriveMotor(RobotMap.rFrontDrive, "Right Front", false);
        motors[4] = new DriveMotor(RobotMap.rMidDrive, "Right Mid", false);
        motors[5] = new DriveMotor(RobotMap.rBackDrive, "Right Back", false);

        motors[3].setInverted(true);
        motors[4].setInverted(true);
        motors[5].setInverted(true);

    }

    public void drive() {
        move(-Robot.oi.j0.getY(), -Robot.oi.j1.getY());
        SmartDashboard.putNumber("Left Joy", -Robot.oi.j0.getY());
        SmartDashboard.putNumber("Right Joy", -Robot.oi.j1.getY());
    }

    public void move(double leftSpeed, double rightSpeed) {
        set(leftSpeed, rightSpeed);
    }

    private void set(double leftSpeed, double rightSpeed) {
        for (int i = 0; i < motors.length; i++) {
            if (i < motors.length / 2)
                motors[i].move(leftSpeed);
            else
                motors[i].move(rightSpeed);
        }
    }

    public void reset() {
        for (DriveMotor motor : motors)
            motor.reset();
    }


    public void displayInfo() {
        for (DriveMotor motor : motors)
            motor.displayInfo();

    }

    public void stop() {
        for (DriveMotor motor : motors)
            motor.stop();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new NewDriveTrainDrive());
    }
}
