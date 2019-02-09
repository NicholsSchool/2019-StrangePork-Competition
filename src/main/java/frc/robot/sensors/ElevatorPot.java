package frc.robot.sensors;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.AnalogPotentiometer;


public class ElevatorPot
{
    public AnalogPotentiometer pot;

    public ElevatorPot(AnalogPotentiometer pot)
    {
       this.pot = pot;
    
    }

    public double position()
    {
        return pot.get();
    }

    public int isAtExtremes()
    {
        if( pot.get() <= Constants.ELEVATOR_POT_LOW_EXTREME_VALUE )
        {
            return 2;
        }
        else if(pot.get() >= Constants.ELEVATOR_POT_HIGH_EXTREME_VALUE )
        {
           return 1;
        }
        return 0;
    }

}