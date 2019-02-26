package frc.robot.sensors;

import com.kauailabs.navx.frc.AHRS;

public class NavX
{
    
    private AHRS navX;

    public NavX(AHRS ahrs)
    {
        this.navX = ahrs;
    }

    /**
     * Returns the current orientation of the Robot
     * @return the angle of the Robot
     */

    public double getAngle()
    {
        return navX.getYaw();

    }

    /**
     * Takes the current angle and checks if it is within five degrees 
     * of the current angle
     * @param angle angle to check
     * @return true if the angle is within five degrees
     */

    public boolean atAngle(double angle)
    {
        return (getAngle() < angle + 5 && getAngle() > angle -5);
    }

    /**
     * Resets the NavX
     */

    public void reset()
    {
        navX.reset();
    }

    


}