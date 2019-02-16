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
import com.ctre.phoenix.motorcontrol.RemoteLimitSwitchSource;
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
  public static WPI_TalonSRX lFrontDrive;
  public static WPI_TalonSRX lMidDrive;
  public static WPI_TalonSRX lBackDrive;
  public static WPI_TalonSRX rFrontDrive;
  public static WPI_TalonSRX rMidDrive;
  public static WPI_TalonSRX rBackDrive;
  public static WPI_TalonSRX leftDart;
  public static WPI_TalonSRX rightDart;
  public static WPI_TalonSRX leftGrip;
  public static WPI_TalonSRX rightGrip;
  public static WPI_TalonSRX armExtend;


  public static SpeedControllerGroup leftSide;
  public static SpeedControllerGroup rightSide;


  public static DifferentialDrive driveBase;

  public static AnalogInput leftFrontUltraSonic;
  public static AnalogInput rightFrontUltraSonic;

  public static AnalogPotentiometer appPot;
  public static DigitalInput appSwitch;

  public static AHRS ahrs;

  public static Compressor compressor;
  public static Solenoid solenoid0;
  public static Solenoid solenoid1;
  public static Solenoid dustPanSolenoid;


  public static void init()
  {
    //Making Drive Motors
    lFrontDrive = new WPI_TalonSRX(Constants.LEFT_FRONT_DRIVE_ID);
    lMidDrive = new WPI_TalonSRX(Constants.LEFT_MID_DRIVE_ID);
    lBackDrive = new WPI_TalonSRX(Constants.LEFT_BACK_DRIVE_ID);
    rFrontDrive = new WPI_TalonSRX(Constants.RIGHT_FRONT_DRIVE_ID);
    rMidDrive = new WPI_TalonSRX(Constants.RIGHT_MID_DRIVE_ID);
    rBackDrive = new WPI_TalonSRX(Constants.RIGHT_BACK_DRIVE_ID);

    //Config changes
    lFrontDrive.setInverted(true);
    lMidDrive.setInverted(true);
    lBackDrive.setInverted(true);

    rFrontDrive.setInverted(false);
    rMidDrive.setInverted(true);
    rBackDrive.setInverted(true);

    //Making Dart Motors
    leftDart = new WPI_TalonSRX(Constants.LEFT_DART_ID);
    rightDart = new WPI_TalonSRX(Constants.RIGHT_DART_ID);
    rightDart.set(ControlMode.Follower, Constants.LEFT_DART_ID);


    //Making Arm Motors
    leftGrip = new WPI_TalonSRX(Constants.LEFT_GRIPPER_ID);
    rightGrip = new WPI_TalonSRX(Constants.RIGHT_GRIPPER_ID);
    armExtend = new WPI_TalonSRX(Constants.ARM_EXTEND_MOTOR_ID);
    armExtend.setInverted(true);
    //Making encoders
    lFrontDrive.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,100);
    lMidDrive.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,100);
    lBackDrive.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,100);
    lFrontDrive.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,100);
    rMidDrive.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,100);
    rBackDrive.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,100);

    lFrontDrive.setSensorPhase(true);
    lMidDrive.setSensorPhase(true);
    lBackDrive.setSensorPhase(true);

    rFrontDrive.setSensorPhase(true);
    rMidDrive.setSensorPhase(false);
    rBackDrive.setSensorPhase(false);
    


    leftSide = new SpeedControllerGroup(lFrontDrive, lMidDrive, lBackDrive);
    rightSide = new SpeedControllerGroup(rFrontDrive, rMidDrive, rBackDrive);

    driveBase = new DifferentialDrive(leftSide, rightSide);

    //Making Gyro
    ahrs = new AHRS(SPI.Port.kMXP);

    //Making Ultrasonic Sensors
    rightFrontUltraSonic = new AnalogInput(0);
    leftFrontUltraSonic = new AnalogInput(1);
    
    //Making Limit Switches
    //Ball LS
    leftGrip.configLimitSwitchDisableNeutralOnLOS(true, 100);
    leftGrip.configForwardLimitSwitchSource(LimitSwitchSource.RemoteTalonSRX, LimitSwitchNormal.NormallyOpen);
    leftGrip.configForwardSoftLimitEnable(false);
    //Arm Down LS
    leftDart.configLimitSwitchDisableNeutralOnLOS(true, 100);
    leftDart.configForwardLimitSwitchSource(LimitSwitchSource.RemoteTalonSRX, LimitSwitchNormal.NormallyOpen);
    leftDart.configForwardSoftLimitEnable(false);

    //Make A.P.P. sensors
    appPot = new AnalogPotentiometer(2);
    appSwitch = new DigitalInput(0);

    compressor = new Compressor(50);
    solenoid0 = new Solenoid(50, 0);
    solenoid1 = new Solenoid(50, 2);
    dustPanSolenoid = new Solenoid(50, 1);
  }

}
