package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap;

public class Arm
{

    private void set( double speed )
    {
        RobotMap.dartR.set(speed);
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


    //check the armExtend
    public void stop()
    {
        RobotMap.armExtend.stopMotor();
    }

}   