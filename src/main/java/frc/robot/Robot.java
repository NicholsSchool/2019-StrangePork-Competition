/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;
import frc.robot.autonomous.ArmTimeMove;
import frc.robot.autonomous.AutoPaths;
import frc.robot.autonomous.ElevatorTimeMove;
import frc.robot.sensors.*;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 * 
 * @param <driveTrain>
 */

public class Robot extends TimedRobot {
  public static JumpJacks jumpJacks;
  public static Ultrasonic ultrasonic;
  public static OI oi;
  public static DriveTrain driveTrain;
  public static ArmPot armPot; 
  public static ElevatorPot elevatorPot;
  public static DustPan dustpan;
  public static NavX navX;
  public static Gripper gripper;
  public static LimitSwitch limitswitches;
  public static Elevator elevator;
  public static Arm arm;
  public static Dial appDial;

  public static boolean sensorOverride;
  public static boolean dropped;

  

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    // chooser.addOption("My Auto", new MyAutoCommand());
    //RobotMap should be first
    RobotMap.init();

    SmartDashboard.putData("Auto mode", m_chooser);

    //subsystems
    jumpJacks = new JumpJacks();
    driveTrain = new DriveTrain();
    dustpan = new DustPan();
    gripper = new Gripper();
    elevator = new Elevator();
    arm = new Arm();

    //sensors
    navX = new NavX(RobotMap.ahrs);
    ultrasonic = new Ultrasonic();
    limitswitches = new LimitSwitch(RobotMap.leftGrip, RobotMap.leftDart);
    armPot = new ArmPot(RobotMap.leftDart);
    elevatorPot = new ElevatorPot(RobotMap.armExtend);
    appDial = new Dial(RobotMap.appPot);
    sensorOverride = false;
    dropped = false;

    //OI gets Instantiated LAST!
    oi = new OI();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
    navX.reset();
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
    SmartDashboard.putBoolean("App Switch", RobotMap.appSwitch.get());
    SmartDashboard.putNumber("App pot raw", RobotMap.appPot.get());
    SmartDashboard.putNumber("App Pot", appDial.getPosition());
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    //  if(RobotMap.appSwitch.get())
    //     m_autonomousCommand = new AutoPaths();

    // schedule the autonomous command (example)
    // if (m_autonomousCommand != null) {
    //   m_autonomousCommand.start();
    // }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    RobotMap.jumpJacksSolenoid.set(Constants.JUMPJACKS_RAISED);
    RobotMap.dustPanSolenoid.set(Constants.DUSTPAN_RAISED);
   driveTrain.resetEncoders();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

    SmartDashboard.putBoolean("Override", sensorOverride);
    SmartDashboard.putBoolean("bottomArmLimitSwitch Value:", limitswitches.isArmDown());
    SmartDashboard.putBoolean("ball Limit Switch Value:", limitswitches.isBallIn());

    SmartDashboard.putNumber("ElevatorArmPot Value:", elevatorPot.getPosition());
    SmartDashboard.putNumber("ArmPot Value:", armPot.getPosition());
    SmartDashboard.putNumber("Outtake Speed", gripper.getOuttakeSpeed());
  }
  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() 
  {

    SmartDashboard.putNumber("POV", oi.j2.getPOV());

    SmartDashboard.putNumber("FrontLD Encoder Value:", RobotMap.lFrontDrive.getSelectedSensorPosition(0));
    SmartDashboard.putNumber("MidLD Encoder Value:", RobotMap.lMidDrive.getSelectedSensorPosition(0));
    SmartDashboard.putNumber("BackLD Encoder Value:", RobotMap.lBackDrive.getSelectedSensorPosition(0));
    SmartDashboard.putNumber("FrontRD Encoder Value:", RobotMap.rFrontDrive.getSelectedSensorPosition(0));
    SmartDashboard.putNumber("MidRD Encoder Value:", RobotMap.rMidDrive.getSelectedSensorPosition(0));
    SmartDashboard.putNumber("BackRD Encoder Value:", RobotMap.rBackDrive.getSelectedSensorPosition(0));

    SmartDashboard.putBoolean("bottomArmLimitSwitch Value:", limitswitches.isArmDown());
    SmartDashboard.putBoolean("ball Limit Switch Value:", limitswitches.isBallIn());

    SmartDashboard.putNumber("ElevatorArmPot Value:", elevatorPot.getPosition());
    SmartDashboard.putNumber("ArmPot Value:", armPot.getPosition());

    SmartDashboard.putNumber("Left Ultrasonic", ultrasonic.getRange(true));
    SmartDashboard.putNumber("Right Ultrasonic", ultrasonic.getRange(false));

    SmartDashboard.putBoolean("Override", sensorOverride);
    SmartDashboard.putNumber("Outtake Speed", gripper.getOuttakeSpeed());


    SmartDashboard.putNumber("Navx ", navX.getAngle());

    SmartDashboard.putBoolean("App Switch", RobotMap.appSwitch.get());
    SmartDashboard.putNumber("App pot raw", RobotMap.appPot.get());
    SmartDashboard.putNumber("App Pot", appDial.getPosition());
  }
 
  


}
