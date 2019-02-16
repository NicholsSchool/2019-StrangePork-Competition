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

    public boolean isBallIn()
    {
        return Robot.isBallIn;
        //return ballLM.isFwdLimitSwitchClosed();
    }

    public boolean isArmDown()
    {
        return armLM.isFwdLimitSwitchClosed();
    }
}