/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.Intake;
import frc.robot.commands.Outtake;
import frc.robot.commands.AngleTurn;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public Joystick j0;
  public Joystick j1;
  public Joystick j2;

  public JoystickButton j0b1;
  public JoystickButton j0b2;
  public JoystickButton j0b3;
  public OI()
  {
    j0 = new Joystick(0);
    j1 = new Joystick(1);

    j0b1 = new JoystickButton(j0, 1);
    j0b2 = new JoystickButton(j0, 2);
    j0b3 = new JoystickButton(j0, 3)
    
    j0b1.whileHeld(new Intake());
    j0b2.whileHeld(new Outtake());
    
    j0b3.whenPressed(new AngleTurn(90, 0.5));

  }
}
