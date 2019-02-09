package frc.robot.sensors;

import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


public class Pot
{
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
            return 2; // is down
        if (getPosition() >= highExtreme)
            return 1; // is up
        return 0; // is inbetween

    }

}