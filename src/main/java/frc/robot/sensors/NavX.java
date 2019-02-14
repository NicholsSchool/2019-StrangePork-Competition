package frc.robot.sensors;

import com.kauailabs.navx.frc.AHRS;

public class NavX
{
    
    private AHRS navX;

    public NavX(AHRS ahrs)
    {
        this.navX = ahrs;
    }

    public double getYaw()
    {
        return navX.getYaw() % 360;
    }

    public double getPitch()
    {
        double yAngle = navX.getPitch();
        if (yAngle < 0)
          {
            return yAngle * -1;
          }

        return yAngle;
    }
    public double getAngle()
    {
        double zAngle = navX.getAngle();
        if (zAngle < -180)
            zAngle = 360 + zAngle;
        
        else 
        {
            zAngle = 360 - zAngle;
            zAngle *= -1;
        }

        return zAngle;
    }
    public boolean atAngle(double zAngle)
    {
        return (getAngle() < zAngle + 5 && getAngle() > zAngle -5);
    }
    public void reset()
    {
        navX.reset();
    }

    


}