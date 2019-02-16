package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class DriveMotor 
{
    private WPI_TalonSRX motor;
    private String name;
    private double desiredSpeed, currentSpeed;
    private boolean isSpinningOut;
    private boolean speedReached;
    private double timeSinceLastInput;

    private boolean isInverted;
    public DriveMotor(WPI_TalonSRX motor)
    {
       this(motor, "");
    }

    public DriveMotor(WPI_TalonSRX motor, String name)
    {
        this.motor = motor;
        this.name = name;
        isInverted = false;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setSpinningOut(boolean isSpinningOut)
    {
        if(System.currentTimeMillis() - timeSinceLastInput > 100)
        {
            this.isSpinningOut = isSpinningOut;
            timeSinceLastInput = System.currentTimeMillis();
        }
    }

    public void setInverted(boolean isInverted)
    {
        this.isInverted = isInverted;
    }

    public void move(double speed)
    {
        
        if(isSpinningOut)
        {
            set(getSigVal(speed * 0.9 * (isInverted ? -1 : 1)));
            if(name.equals("Right Back"))
                System.out.println(name + " is spinning out");
        }
       else
       {
            set(getSigVal(speed * (isInverted ? -1 : 1)));
            if (name.equals("Right Back"))
                System.out.println(name + " is not spinning out");
        }
    }


    public void set(double speed)
    {
        currentSpeed = speed;
        speed = applyDeadband(speed, 0.02);
        speed = Math.copySign(speed * speed, speed);
        motor.set(speed);
    }

    protected double applyDeadband(double value, double deadband) {
        if (Math.abs(value) > deadband) {
            if (value > 0.0) {
                return (value - deadband) / (1.0 - deadband);
            } else {
                return (value + deadband) / (1.0 - deadband);
            }
        } else {
            return 0.0;
        }
    }
    public double getSigVal(double speed)
    {
        return getSigVal(speed, Constants.SIGMOID_A);
    }

    public void reset()
    {
        desiredSpeed = 0;
        currentSpeed = 0;
    }

    public void stop()
    {
        motor.stopMotor();
    }

    public double getSigVal(double speed, double a)
    {
    
        // The sigmoid function can never reach 1 or -1, so this caps the input
        if (speed > 0.99 || speed < -0.99)
            speed = speed * 0.99;

        // Records the desired speed, and if that has been roughly reached by
        // the function, return the desired speed.
        desiredSpeed = speed;
        speedReached = false;
        if (Math.abs(desiredSpeed - currentSpeed) < 0.01) {
            speedReached = true;
            return desiredSpeed;
        }

        // Calculates the current x value in the function
        double startTime = inverseSig(currentSpeed, a);

        // Used to get the next value the robot should use
        double cycleTime = 0.02;
        if (desiredSpeed < currentSpeed)
            cycleTime = -cycleTime;

        return sigmoid(startTime + cycleTime, a);
    }

    private double sigmoid(double time, double a) {
        return 2 / (1 + Math.pow(Math.E, -time * a)) - 1;
    }

    /**
     * The inverse of the sigmoid function used by the robot -ln((2 / (x + 1)) - 1)
     * / a
     * 
     * @param speed - the x value for the function
     * @param a     - the A value to be used by the function
     * @return the y value (time) from the function
     */
    private double inverseSig(double speed, double a) {
        return -Math.log(2 / (speed + 1) - 1) / a;
    }

    public double getEncoderValue()
    {
        return motor.getSelectedSensorPosition();
    }

    public double getVelocity()
    {
        return motor.getSelectedSensorVelocity();
    }

    public void displayInfo()
    {
        SmartDashboard.putNumber(name + " Encoder: ", getEncoderValue());
        SmartDashboard.putNumber(name + " Velocity: ", getVelocity());
    }

}