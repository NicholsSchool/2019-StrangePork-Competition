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
import frc.robot.sensors.Vision;

public class AlignWithLineBB extends Command {

    private double speed;
    private double angleToLine;
    private double distanceToLine;
    private double angleToWall;

    private boolean isFacingLine;
    private boolean isOnLine;
    private boolean isAligned;

    public AlignWithLineBB(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.driveTrain);
        this.speed = speed;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        isFacingLine = false;
        isOnLine = false;
        isAligned = false;

        System.out.println("\n\n[VISION]: Starting...\n\n");
        Robot.navX.reset();
        angleToLine = Vision.angleToLine;
        // distanceToLine = Vision.distanceToLine / Constants.WHEEL_DIAMETER_IN_FEET / Math.PI
        //         * Constants.TICKS_PER_ROTATION;
        // angleToWall = Vision.angleToWall - Vision.angleToLine;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        if (!isFacingLine) {

            if (Robot.navX.getAngle() > angleToLine + 3) {
                Robot.driveTrain.move(-speed, speed);
            } else if (Robot.navX.getAngle() < angleToLine - 3) {
                Robot.driveTrain.move(speed, -speed);
            } else {
                System.out.println("\n\n[VISION]: Faced Line\n\n");
                isFacingLine = true;

                distanceToLine = Vision.distanceToLine / Constants.WHEEL_DIAMETER_IN_FEET /
                    Math.PI * Constants.TICKS_PER_ROTATION;
                angleToWall = Vision.angleToWall;

                Robot.driveTrain.resetEncoders();
            }

        } else if (!isOnLine) {

            if (Math.abs(RobotMap.lMidDrive.getSelectedSensorPosition(0)) < distanceToLine) {
                Robot.driveTrain.sigmoidMove(speed, speed);
            } else {
                System.out.println("\n\n[VISION]: On Line\n\n");
                isOnLine = true;
                Robot.navX.reset();
            }

        } else if (!isAligned) {

            if (Robot.navX.getAngle() > angleToWall + 3) {
                Robot.driveTrain.move(-speed, speed);
            } else if (Robot.navX.getAngle() < angleToWall - 3) {
                Robot.driveTrain.move(speed, -speed);
            } else {
                System.out.println("\n\n[VISION]: Aligned\n\n");
                isAligned = true;
            }

        }

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return isAligned;
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
