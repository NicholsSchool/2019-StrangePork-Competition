package frc.robot.util;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.buttons.Button;

public class JoystickContoller 
 {
    public JoystickContoller(Joystick joystick)
    {
        joy = joystick;
        b1 = new JoystickButton(joy, Constants.CONTROLLER_1_BUTTON_ID);
        b2 = new JoystickButton(joy, Constants.CONTROLLER_2_BUTTON_ID);
        b3 = new JoystickButton(joy, Constants.CONTROLLER_3_BUTTON_ID);
        b4 = new JoystickButton(joy, Constants.CONTROLLER_4_BUTTON_ID);
        b5 = new JoystickButton(joy, Constants.CONTROLLER_5_BUTTON_ID);
        b6 = new JoystickButton(joy, Constants.CONTROLLER_6_BUTTON_ID);
        b7 = new JoystickButton(joy, Constants.CONTROLLER_7_BUTTON_ID);
        b8 = new JoystickButton(joy, Constants.CONTROLLER_8_BUTTON_ID);
        b9 = new JoystickButton(joy, Constants.CONTROLLER_9_BUTTON_ID);
        b10 = new JoystickButton(joy, Constants.CONTROLLER_10_BUTTON_ID);
    }

    public Joystick joy;

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
}