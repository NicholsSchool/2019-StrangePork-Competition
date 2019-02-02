/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SPI;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap 
{
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  public static WPI_TalonSRX frontLD;
  public static WPI_TalonSRX midLD;
  public static WPI_TalonSRX backLD;
  public static WPI_TalonSRX frontRD;
  public static WPI_TalonSRX midRD;
  public static WPI_TalonSRX backRD;
  public static WPI_TalonSRX lDart;
  public static WPI_TalonSRX rDart;
  public static WPI_TalonSRX gripMotL;
  public static WPI_TalonSRX gripMotR;
  public static WPI_TalonSRX armExtend;
  

  public static void init()
  {
    frontLD = new WPI_TalonSRX(Constants.frontLD);
    midLD = new WPI_TalonSRX(Constants.midLD);
    backLD = new WPI_TalonSRX(Constants.backLD);
    frontRD = new WPI_TalonSRX(Constants.frontRD);
    midRD = new WPI_TalonSRX(Constants.midRD);
    backRD = new WPI_TalonSRX(Constants.backRD);
    lDart = new WPI_TalonSRX(Constants.lDart);
    rDart = new WPI_TalonSRX(Constants.rDart);
    gripMotL = new WPI_TalonSRX(Constants.gripMotL);
    gripMotR = new WPI_TalonSRX(Constants.gitpMotR);
    armExtend = new WPI_TalonSRX(Constants.armExtend);

    frontLD.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,100);
    midLD.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,100);
    backLD.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,100);
    frontLD.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,100);
    midRD.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,100);
    backRD.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,100);

    
  }
}
