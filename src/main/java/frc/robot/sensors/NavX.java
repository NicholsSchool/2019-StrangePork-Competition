package frc.robot.sensors;

import com.kauailabs.navx.frc.AHRS;

public class NavX
{
    
    private AHRS navX;

    public NavX(AHRS ahrs)
    {
        this.navX = ahrs;
    }

    public double getAngle()
    {
        double angle = navX.getYaw();
        if (angle < -180)
            angle = 360 + angle;
        
        else 
        {
            angle = 360 - angle;
            angle *= -1;
        }

        return angle;
    }
    public boolean atAngle(double angle)
    {
        return (getAngle() < angle + 5 && getAngle() > angle -5);
    }
    public void reset()
    {
        navX.reset();
    }

    


}