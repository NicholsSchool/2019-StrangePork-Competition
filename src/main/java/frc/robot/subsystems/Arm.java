package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Arm extends Subsystem
{

    private void set( double speed )
    {
        RobotMap.leftDart.set(speed);
    }

    public void move( double speed )
    {
        int armPosition = Robot.armPot.isAtExtremes();
        //2 == down
        if( armPosition == 2 )
        {
            if( speed > 0 )
            {
                set(speed);
            }
        }
        else if( armPosition == 1 )
        {
            if(speed < 0 )
            {
                set(speed);
            }
        }
        else set(speed);
    }

    public void armMove()
    {
        move(Robot.oi.j2.getY());
    }

    public void resetPot()
    {
        Robot.armPot.reset();
    }

    //check the armExtend
    public void stop()
    {
        RobotMap.armExtend.stopMotor();
    }

    @Override
    protected void initDefaultCommand() {

    }

}   