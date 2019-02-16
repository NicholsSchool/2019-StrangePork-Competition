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
import frc.robot.autonomous.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public Joystick j0;
  public Joystick j1;
  public Joystick j2;
  
  public JoystickButton j0b1, j0b2, j0b3, j0b4, j0b5, j0b6, j0b7, j0b8, j0b9, j0b10, j0b11, j0b12;

  public JoystickButton j2b2;
  public JoystickButton j1b7, j1b8, j1b9, j1b10;

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
    j0b7 = new JoystickButton(j0, 7);
    j0b8 = new JoystickButton(j0, 8);
  

    j1b7 = new JoystickButton(j1, 7);
    j1b8 = new JoystickButton(j1, 8);
    j1b9 = new JoystickButton(j1, 9);
    j1b10 = new JoystickButton(j1, 10);

    j2b2 = new JoystickButton(j2, 2);

    driver = new Controller(3);
    operator = new Controller(4);
    
    j0b4.whileHeld(new Intake());
    j0b5.whileHeld(new Outtake());
    j0b7.whenPressed(new DustpanRaise()); 
    j0b8.whenPressed(new DustpanDrop());
    j2b2.whenPressed(new ToggleVisionCamera());

    j1b7.whileHeld(new JumpJacksRaise()); 
    j1b8.whileHeld(new JumpJacksDrop());

  /*  
    j0b3.whenPressed(new WallAllign(.5));
 

    

    
    j0b6.whenPressed(new AngleTurn(90, 0.5)); */
  }
}
