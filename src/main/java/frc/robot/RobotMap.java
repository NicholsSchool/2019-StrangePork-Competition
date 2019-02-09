/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Compressor;

import edu.wpi.first.wpilibj.DigitalInput;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.AnalogInput;

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
  public static WPI_TalonSRX dartL;
  public static WPI_TalonSRX dartR;
  public static WPI_TalonSRX leftGrip;
  public static WPI_TalonSRX rightGrip;
  public static WPI_TalonSRX armExtend;

  public static AnalogPotentiometer pot;


  public static SpeedControllerGroup leftSide;
  public static SpeedControllerGroup rightSide;


  public static DifferentialDrive driveBase;

  public static AnalogInput leftFrontUltraSonic;
  public static AnalogInput rightFrontUltraSonic;

  public static AHRS ahrs;

  public static Compressor compressor;
  public static Solenoid solenoid0;
  public static Solenoid solenoid1;
  public static Solenoid solenoid2;


  public static void init()
  {
    //Making Drive Motors
    frontLD = new WPI_TalonSRX(Constants.FRONT_LEFT_DRIVE_ID);
    midLD = new WPI_TalonSRX(Constants.MID_LEFT_DRIVE_ID);
    backLD = new WPI_TalonSRX(Constants.BACK_LEFT_DRIVE_ID);
    frontRD = new WPI_TalonSRX(Constants.FRONT_RIGHT_DRIVE_ID);
    midRD = new WPI_TalonSRX(Constants.MID_RIGHT_DRIVE_ID);
    backRD = new WPI_TalonSRX(Constants.BACK_RIGHT_DRIVE_ID);

    //Making Dart Motors
    dartL = new WPI_TalonSRX(Constants.LEFT_DART_ID);
    dartR = new WPI_TalonSRX(Constants.RIGHT_DART_ID);
    dartR.set(ControlMode.Follower, Constants.LEFT_DART_ID);


    //Making Arm Motors
    leftGrip = new WPI_TalonSRX(Constants.LEFT_GRIPPER_ID);
    rightGrip = new WPI_TalonSRX(Constants.RIGHT_GRIPPER_ID);
    armExtend = new WPI_TalonSRX(Constants.ARM_EXTEND_MOTOR_ID);
    
    //Making encoders
    frontLD.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,100);
    midLD.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,100);
    backLD.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,100);
    frontLD.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,100);
    midRD.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,100);
    backRD.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,100);
    
    pot = new AnalogPotentiometer(0);

    leftSide = new SpeedControllerGroup(frontLD, midLD, backLD);
    rightSide = new SpeedControllerGroup(frontRD, midRD, backRD);

    driveBase = new DifferentialDrive(leftSide, rightSide);

    //Making Gyro
    ahrs = new AHRS(SPI.Port.kMXP);

    //Making Ultrasonic Sensors
    leftFrontUltraSonic = new AnalogInput(0);
    rightFrontUltraSonic = new AnalogInput(0);
    
    //Making Limit Switches
    //Ball LS
    leftGrip.configForwardLimitSwitchSource(LimitSwitchSource.RemoteTalonSRX, LimitSwitchNormal.NormallyOpen);
    //Arm Down LS
    dartL.configForwardLimitSwitchSource(LimitSwitchSource.RemoteTalonSRX, LimitSwitchNormal.NormallyOpen);


    compressor = new Compressor(50);
    solenoid0 = new Solenoid(50, 0);
    solenoid1 = new Solenoid(50, 1);
    solenoid2 = new Solenoid(50, 2);
  }

}
