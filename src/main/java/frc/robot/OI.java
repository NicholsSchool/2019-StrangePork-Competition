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
import frc.robot.util.JoystickContoller;
import frc.robot.autonomous.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public JoystickContoller j0;
  public JoystickContoller j1;
  public JoystickContoller j2;



  public OI()
  {
    j0 = new JoystickContoller(0);
    j1 = new JoystickContoller(1);
    j2 = new JoystickContoller(2);
 
    
   // j0.b1.whenPressed(new Place());
    double armMoveSpeed = 0.5;
    j0.b1.whenPressed(new DustpanDrop());
    j0.b1.whenReleased(new DustpanRaise());
    j0.b4.whenPressed(new AlignWithLineBB(0.7));

    //j1b11 then j0b11 to override everything
    // j1b3 and j1b4 switch between cameras

    j1.b1.whileHeld(new Intake());
    
    j2.b1.whileHeld(new Outtake());
    j2.b1.whenReleased(new Intake(1))    ;    


    j2.b2.whenPressed(new DustpanDrop());
    j2.b2.whenReleased(new DustpanRaise());

    j2.b11.whenPressed(new ClimbSequence());
    // Add j1b2 to climb

    j2.b4.whenPressed(new ArmMoveToLevel(1, armMoveSpeed));
    j2.b3.whenPressed(new ArmMoveToLevel(2, armMoveSpeed));
    j2.b5.whenPressed(new ArmMoveToLevel(3, armMoveSpeed));

 //   j2.b3.whenPressed(new ToggleVisionCamera());
//    



  }
}
