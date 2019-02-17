package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.ArmMove;
import frc.robot.sensors.Pot;

public class Arm extends Subsystem
{
    public double[] hatchLevelValues;
    public double[] ballLevelValues;
    public Arm()
    {
        hatchLevelValues = new double[3];
        hatchLevelValues[0] = Constants.LEVEL_1_HATCH_VALUE;
        hatchLevelValues[1] = Constants.LEVEL_2_HATCH_VALUE;
        hatchLevelValues[2] = Constants.LEVEL_3_HATCH_VALUE;

        ballLevelValues = new double[3];
        ballLevelValues[0] = Constants.LEVEL_1_BALL_VALUE;
        ballLevelValues[1] = Constants.LEVEL_2_BALL_VALUE;
        ballLevelValues[2] = Constants.LEVEL_3_BALL_VALUE;


    }

    private void set( double speed )
    {
        RobotMap.leftDart.set(speed);
    }

    public void move( double speed )
    {
        int armPosition = Robot.armPot.isAtExtremes();
        //2 == down
        if( /*armPosition == Pot.AT_MIN || */ Robot.limitswitches.isArmDown() )
        {
            if( speed > 0 )
                set(speed);
            else
                set(0);
            
        }
        else if( armPosition == Pot.AT_MAX )
        {
            if(speed < 0 )
                set(speed);
            else
                set(0);
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