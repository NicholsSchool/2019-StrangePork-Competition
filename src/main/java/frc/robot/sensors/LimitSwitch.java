package frc.robot.sensors;

import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Robot;
import frc.robot.RobotMap;

public class LimitSwitch
{
    SensorCollection ballLM, armLM;

    public LimitSwitch(WPI_TalonSRX ballLMTalon, WPI_TalonSRX armLMTalon)
    {
        ballLM = ballLMTalon.getSensorCollection();
        armLM = armLMTalon.getSensorCollection();
    }

    /**
     * Checks the limit switch to see if the cargo has been placed in the gripper
     * @return true if the limit switch is pressed by the ball
     */

    public boolean isBallIn()
    {
        if(!Robot.sensorOverride)
            return ballLM.isFwdLimitSwitchClosed();
        return false;
    }

    /**
     * Checks the limit switch to see if the arm has reached the bottom
     * @return true if the limit switch is pressed by the arm
     */

    public boolean isArmDown()
    {
        return armLM.isFwdLimitSwitchClosed();
    }
}