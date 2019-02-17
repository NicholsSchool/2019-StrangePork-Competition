package frc.robot.util;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.buttons.Button;

public class JoystickContoller extends Joystick
 {
    public JoystickContoller(int port)
    {
        super(port);
        b1 = new JoystickButton(this, Constants.CONTROLLER_1_BUTTON_ID);
        b2 = new JoystickButton(this, Constants.CONTROLLER_2_BUTTON_ID);
        b3 = new JoystickButton(this, Constants.CONTROLLER_3_BUTTON_ID);
        b4 = new JoystickButton(this, Constants.CONTROLLER_4_BUTTON_ID);
        b5 = new JoystickButton(this, Constants.CONTROLLER_5_BUTTON_ID);
        b6 = new JoystickButton(this, Constants.CONTROLLER_6_BUTTON_ID);
        b7 = new JoystickButton(this, Constants.CONTROLLER_7_BUTTON_ID);
        b8 = new JoystickButton(this, Constants.CONTROLLER_8_BUTTON_ID);
        b9 = new JoystickButton(this, Constants.CONTROLLER_9_BUTTON_ID);
        b10 = new JoystickButton(this, Constants.CONTROLLER_10_BUTTON_ID);
        b11 = new JoystickButton(this, Constants.CONTROLLER_11_BUTTON_ID);
        b12 = new JoystickButton(this, Constants.CONTROLLER_12_BUTTON_ID);
    }


    public JoystickButton b1;
	public JoystickButton b2;
	public JoystickButton b3;
	public JoystickButton b4; 
	public JoystickButton b5; 
	public JoystickButton b6;
	public JoystickButton b7;
	public JoystickButton b8;
	public JoystickButton b9;
    public JoystickButton b10; 
    public JoystickButton b11;
    public JoystickButton b12;
}