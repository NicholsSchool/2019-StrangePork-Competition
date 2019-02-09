/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;
import frc.robot.util.Controller;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public Joystick j0;
  public Joystick j1;
  public Joystick j2;
  
  public JoystickButton j0b1, j0b2, j0b3, j0b4, j0b5, j0b6, j0b7, j0b8, j0b9, j0b10, j0b11, j0b12;

  public Controller driver;
  public Controller operator;

  public OI()
  {
    j0 = new Joystick(0);
    j1 = new Joystick(1);
    j2 = new Joystick(2);

    j0b1 = new JoystickButton(j0, 1);
    j0b2 = new JoystickButton(j0, 2);
    j0b3 = new JoystickButton(j0, 3);
    j0b4 = new JoystickButton(j0, 4);
    j0b5 = new JoystickButton(j0, 5);
    j0b6 = new JoystickButton(j0, 6);

    driver = new Controller(3);
    operator = new Controller(4);
    
    j0b3.whenPressed(new WallAllign(.5));
    j0b2.whenPressed(new JumpJacksRaise());
    j0b1.whenPressed(new JumpJacksDrop());

    
    j0b4.whileHeld(new Intake());
    j0b5.whileHeld(new Outtake());
    
    j0b6.whenPressed(new AngleTurn(90, 0.5));
  }
}
