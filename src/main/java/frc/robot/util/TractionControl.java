package frc.robot.util;

import frc.robot.subsystems.DriveMotor;

public class TractionControl
{
    DriveMotor[] motors;

    public TractionControl(DriveMotor[] motors)
    {
        this.motors = motors;
    }

    public void display()
    {
        for(DriveMotor motor: motors)
            if(motor.getBuffer() < 0.95)
                System.out.println(motor.getName() + " is spinning out. Buffer: " + motor.getBuffer());
    }

    private double average(boolean isLeft)
    {
        double avg = 0;
        for(int i = 0; i < motors.length; i ++)
            if(motors[i].isOnLeftSide()  == isLeft)
                avg += motors[i].getVelocity();
        
        return avg/3;

    }

    public void checkForSpinning()
    {
        double leftAvg = average(true);
        double rightAvg = average(false);
        
        double[] highestLeft = getHighest(0, 3, leftAvg);
        double[] highestRight = getHighest(3, 6, rightAvg);

        int leftSpinner = -1;
        int rightSpinner = -1;
        if(highestLeft.length != 1)
        {
            leftSpinner = (int)highestLeft[0];
            motors[leftSpinner].setBuffer(highestLeft[1]/leftAvg);
        }
        for(int i = 0; i < 3; i++)
            if (i != leftSpinner)
                motors[i].setBuffer(1);

        if(highestRight.length != 1)
        {
            rightSpinner = (int)highestRight[0];
            motors[rightSpinner].setBuffer(highestRight[1]/rightAvg);
        }
        for(int i = 3; i < 4; i++)
            if (i != rightSpinner)
                motors[i].setBuffer(1);
    }

    private double[] getHighest(int start, int end, double avg)
    {
        double highest = -1;
        int index = -1;

        for(int i = start; i < end; i ++)
        {
            if(motors[i].getVelocity() > highest)
            {
                highest = motors[i].getVelocity();
                index = i;
            }
        }
        if(highest > avg + 1000)
            return new double[]{index, highest};
        return new double[1];
    }


    public void setBuffer()
    {

    }

}