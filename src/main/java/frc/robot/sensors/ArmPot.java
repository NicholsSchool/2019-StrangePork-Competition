package frc.robot.sensors;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import frc.robot.Constants;



public class ArmPot
{
    public AnalogPotentiometer pot;

    public ArmPot(AnalogPotentiometer pot)
    {
       this.pot = pot;
    
    }

    public double position()
    {
        return pot.get();
    }

    public int isAtExtremes()
    {
        if( pot.get() <= Constants.ARM_POT_LOW_EXTREME_VALUE )
        {
            return 2;
        } 
        else if( pot.get() >= Constants.ARM_POT_HIGH_EXTREME_VALUE )
        {
            return 1;
        }
        return 0;
        
    }


}