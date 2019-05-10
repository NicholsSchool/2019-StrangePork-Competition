package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.ElevatorMove;
import frc.robot.sensors.Pot;

public class Elevator extends Subsystem
{
    private boolean extended;
    private double lastCheck;

    public Elevator() {
        extended = false;
        lastCheck = System.currentTimeMillis();
    }
    
    private void set( double speed )
    {
        RobotMap.armExtend.set(speed);
    }

    private boolean fullExtend(double time) {
        if (!extended) {
            lastCheck = System.currentTimeMillis();
            extended = true;
        }
        return System.currentTimeMillis() < lastCheck + time;
    }

    public void move( double speed )
    {
        int elevatorPosition = Robot.elevatorPot.isAtExtremes();

        //Dilan wanted no pot control on elevator

        // if( elevatorPosition == Pot.AT_MIN )
        // {
        //     if( speed > 0 )
        //         set(speed);
            
        //     else
        //         set(0);
        //     extended = false;
        // }
        // else if( elevatorPosition == Pot.AT_MAX )
        // {
        //     if( speed < 0 )
        //     {
        //         set(speed);
        //         extended = false;
        //     } else if (fullExtend(0.5))
        //         set(speed / 2);
        //     else
        //         set(0);
        // }
        // else
        // { 
            set(speed);
        //    extended = false;
        // }
    }

    public void elevatorMove()
    {
        // double elevatorPos = Robot.elevatorPot.getPosition() - Constants.ELEVATOR_POT_LOW_EXTREME_VALUE;
        // elevatorPos /= Constants.ELEVATOR_POT_HIGH_EXTREME_VALUE - Constants.ELEVATOR_POT_LOW_EXTREME_VALUE;

        // double armPos = Robot.armPot.getPosition() - Constants.ARM_POT_LOW_EXTREME_VALUE;
        // armPos /= Constants.ARM_POT_HIGH_EXTREME_VALUE - Constants.ARM_POT_LOW_EXTREME_VALUE;

        // if(armPos < Constants.THIRTY_INCH_RULE_THRESH && elevatorPos > Constants.THIRTY_INCH_RULE_RATIO * armPos)
        // {
        //     move(-0.5);
        //     return;
        // }

        // int pov = Robot.oi.j2.getPOV();
        // if(pov == 315 || pov == 0 || pov ==45)
        //     move(0.5);
        // else if(pov == 225 || pov == 180 || pov == 135)
        //     move(-0.5);
        // else
        //     move(0);

        if(Robot.oi.controller.rightBumper.get())
            move(0.5);
        else if(Robot.oi.controller.leftBumper.get())
            move(-0.5);
        else
            move(0);

    }

    public void resetPot()
    {
        Robot.elevatorPot.reset();
    }

    public void stop()
    {
        RobotMap.armExtend.stopMotor();
    }

    @Override
    protected void initDefaultCommand()
    {
        setDefaultCommand(new ElevatorMove());
    }


}