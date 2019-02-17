package frc.robot.sensors;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Constants;



public class ArmPot extends Pot
{

    public ArmPot(WPI_TalonSRX talonWithArmPot)
    {
       super(talonWithArmPot);
    }

    public int isAtExtremes()
    {
        return isAtExtremes(Constants.ARM_POT_LOW_EXTREME_VALUE , Constants.ARM_POT_HIGH_EXTREME_VALUE );

    }


}