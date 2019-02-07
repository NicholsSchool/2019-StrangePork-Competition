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

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Compressor;

import edu.wpi.first.wpilibj.DigitalInput;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Ultrasonic;

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

  public static Ultrasonic leftFrontUltraSonic;
  public static Ultrasonic rightFrontUltraSonic;

  public static DigitalInput hatchLockLimitSwitch;
  public static DigitalInput bottomArmLimitSwitch;
  public static DigitalInput topArmLimitSwitch;
  public static DigitalInput retractedJJLimitSwitch;

  public static AnalogPotentiometer elevatorArmPot;
  public static AnalogPotentiometer armPot;

  public static AHRS ahrs;

  public static Compressor compressor;
  public static Solenoid solenoid0;
  public static Solenoid solenoid1;
  public static Solenoid solenoid2;


  public static void init()
  {
    //Making Drive Motors
    frontLD = new WPI_TalonSRX(Constants.FRONTLD);
    midLD = new WPI_TalonSRX(Constants.MIDLD);
    backLD = new WPI_TalonSRX(Constants.BACKLD);
    frontRD = new WPI_TalonSRX(Constants.FRONTRD);
    midRD = new WPI_TalonSRX(Constants.MIDRD);
    backRD = new WPI_TalonSRX(Constants.BACKRD);

    //Making Dart Motors
    dartL = new WPI_TalonSRX(Constants.DARTL);
    dartR = new WPI_TalonSRX(Constants.DARTR);

    //Making Arm Motors
    leftGrip = new WPI_TalonSRX(Constants.LEFTGRIP);
    rightGrip = new WPI_TalonSRX(Constants.RIGHTGRIP);
    armExtend = new WPI_TalonSRX(Constants.ARMEXTEND);
    
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
    leftFrontUltraSonic = new Ultrasonic(0,1);
    rightFrontUltraSonic = new Ultrasonic(0,1);

    //Making Limit Switches
    hatchLockLimitSwitch = new DigitalInput(Constants.HATCH_LOCK_LIMIT_SWITCH);
    bottomArmLimitSwitch = new DigitalInput(Constants.BOTTOM_ARM_LIMIT_SWITCH);
    topArmLimitSwitch = new DigitalInput(Constants.TOP_ARM_LIMIT_SWITCH);
    retractedJJLimitSwitch = new DigitalInput(Constants.RETRACTED_JJ_LIMIT_SWITCH);

    //Making Potentiometers
    elevatorArmPot = new AnalogPotentiometer(Constants.ELEVATOR_ARM_POT);
    armPot = new AnalogPotentiometer(Constants.ARM_POT);

    compressor = new Compressor(50);
    solenoid0 = new Solenoid(50, 0);
    solenoid1 = new Solenoid(50, 1);
    solenoid2 = new Solenoid(50, 2);
  }
}
