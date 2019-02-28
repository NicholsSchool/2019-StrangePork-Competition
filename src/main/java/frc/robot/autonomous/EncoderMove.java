/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class EncoderMove extends Command {
    private double speed;
    private double ticks;

    private long startPosition;

    public EncoderMove(double speed, double feet) {
        requires(Robot.driveTrain);

        this.speed = Math.copySign(speed, feet);
        ticks = feet / (Constants.WHEEL_DIAMETER_IN_FEET * Math.PI) * Constants.TICKS_PER_ROTATION;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        startPosition = RobotMap.lMidDrive.getSelectedSensorPosition();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.driveTrain.sigmoidMove(speed, speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return Math.abs(ticks) < Math.abs(RobotMap.lMidDrive.getSelectedSensorPosition() - startPosition);
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
