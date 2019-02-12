package frc.robot.sensors;

import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


public class Pot
{
    public static final int AT_MAX = 1;
    public static final int AT_MIN = 2;

    public SensorCollection pot;

    public Pot(WPI_TalonSRX talonWithArmPot)
    {
       this.pot = talonWithArmPot.getSensorCollection();
    }

    public void reset() {
        pot.setAnalogPosition(0, 100);
    }

    public double getPosition() {
        return pot.getAnalogIn();
    }

    public int isAtExtremes(double lowExtreme, double highExtreme) {
        if (getPosition() <= lowExtreme)
            return AT_MIN; // is down
        if (getPosition() >= highExtreme)
            return AT_MAX; // is up
        return 0; // is inbetween

    }

}