package frc.robot.util;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.buttons.Button;

public class Controller extends Joystick
{
    public Controller(int port)
    {
        super(port);
    }

    public JoystickButton xButton = new JoystickButton(this, Constants.CONTROLLER_X_BUTTON_ID);
	public JoystickButton yButton = new JoystickButton(this, Constants.CONTROLLER_Y_BUTTON_ID);
	public JoystickButton aButton = new JoystickButton(this, Constants.CONTROLLER_A_BUTTON_ID);
	public JoystickButton bButton = new JoystickButton(this, Constants.CONTROLLER_B_BUTTON_ID);
	public JoystickButton rightBumper = new JoystickButton(this, Constants.CONTROLLER_RIGHT_BUMPER_ID);
	public JoystickButton leftBumper = new JoystickButton(this, Constants.CONTROLLER_LEFT_BUMPER_ID);
	public JoystickButton startButton = new JoystickButton(this, Constants.CONTROLLER_START_BUTTON_ID);
	public JoystickButton selectButton = new JoystickButton(this, Constants.CONTROLLER_SELECT_BUTTON_ID);
	public JoystickButton leftStickButton = new JoystickButton(this, Constants.CONTROLLER_LEFTSTICK_BUTTON_ID);
    public JoystickButton rightStickButton = new JoystickButton(this, Constants.CONTROLLER_RIGHTSTICK_BUTTON_ID);
    
    public Button leftTriggerButton = new JoystickButton(this, Constants.CONTROLLER_LEFT_TRIGGER_BUTTON_ID);
    public Button rightTriggerButton = new JoystickButton(this, Constants.CONTROLLER_RIGHT_TRIGGER_BUTTON_ID);
    
    public double getTriggerTwist() 
    {
		double leftTriggerValue = this.getRawAxis(2);
		double rightTriggerValue = -1 * this.getRawAxis(3);

		return leftTriggerValue + rightTriggerValue;
	}

    public double getLeftStickX() 
    {
		return this.getRawAxis(Constants.CONTROLLER_LEFTSTICK_X_AXIS_ID);
	}

    public double getLeftStickY() 
    {
		return -this.getRawAxis(Constants.CONTROLLER_LEFTSTICK_Y_AXIS_ID);
	}

    public double getRightStickX() 
    {
		return this.getRawAxis(Constants.CONTROLLER_RIGHTSTICK_X_AXIS_ID); 
	}

    public double getRightStickY() 
    {
		return -this.getRawAxis(Constants.CONTROLLER_RIGHTSTICK_Y_AXIS_ID);
	}

    public void setRumble(double leftValue, double rightValue) 
    {
		setRumble(RumbleType.kLeftRumble, leftValue);
		setRumble(RumbleType.kRightRumble, rightValue);
	}
    public boolean getLeftTrigger()
        {
            return this.getRawAxis(Constants.CONTROLLER_LEFT_TRIGGER_AXIS_ID) > .25;
        }

    public boolean getRightTrigger()
        {
            return this.getRawAxis(Constants.CONTROLLER_RIGHT_TRIGGER_AXIS_ID) > .25;
        }
}