package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.ArmMove;
import frc.robot.sensors.Pot;

public class Arm extends Subsystem
{
    public double[] armLevelValues;
    public Arm()
    {
        armLevelValues[0] = Constants.LEVEL_1_POT_VALUE;
        armLevelValues[1] = Constants.LEVEL_2_POT_VALUE;
        armLevelValues[2] = Constants.LEVEL_3_POT_VALUE;
    }

    private void set( double speed )
    {
        RobotMap.leftDart.set(speed);
    }

    public void move( double speed )
    {
        int armPosition = Robot.armPot.isAtExtremes();
        if( armPosition == Pot.AT_MIN )
        {   
            if( speed > 0 )
                set(speed);
        }
        else if( armPosition == Pot.AT_MAX )
        {
            if( speed < 0 )
                set(speed);
        }
        else 
            set(speed);
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
        RobotMap.leftDart.stopMotor();
    }

    @Override
    protected void initDefaultCommand() 
    {
        setDefaultCommand(new ArmMove());
    }

}   