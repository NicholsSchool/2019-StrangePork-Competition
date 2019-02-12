package frc.robot.sensors;

import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


public class ElevatorPot extends Pot
{
    public ElevatorPot(WPI_TalonSRX talonWithArmPot)
    {
       super(talonWithArmPot);
    
    }

    public int isAtExtremes() {
        return isAtExtremes(Constants.ELEVATOR_POT_LOW_EXTREME_VALUE, Constants.ELEVATOR_POT_HIGH_EXTREME_VALUE);
    }

}