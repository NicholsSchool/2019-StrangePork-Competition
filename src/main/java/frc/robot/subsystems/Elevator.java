package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.sensors.Pot;

public class Elevator extends Subsystem
{
    
    private void set( double speed )
    {
        RobotMap.armExtend.set(speed);
    }

    public void move( double speed )
    {
        int elevatorPosition = Robot.elevatorPot.isAtExtremes();

        if( elevatorPosition == Pot.AT_MIN )
        {
            if( speed > 0 )
            {
                set(speed);
            }

        }
        else if( elevatorPosition == Pot.AT_MAX )
        {
            if( speed < 0 )
            {
                set(speed);
            }
        }
        else set(speed);
    }


    public void resetPot()
    {
        Robot.elevatorPot.reset();
    }

    //check the armExtend
    public void stop()
    {
        RobotMap.armExtend.stopMotor();
    }

    @Override
    protected void initDefaultCommand()
    {
        
    }


}