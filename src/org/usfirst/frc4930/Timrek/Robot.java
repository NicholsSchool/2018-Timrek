
package org.usfirst.frc4930.Timrek;

import org.usfirst.frc4930.Timrek.autonomous.AutoStartPositions;
import org.usfirst.frc4930.Timrek.autonomous.AutoPaths;
import org.usfirst.frc4930.Timrek.sensors.Cameras;
import org.usfirst.frc4930.Timrek.sensors.Dial;
import org.usfirst.frc4930.Timrek.sensors.NavX;
import org.usfirst.frc4930.Timrek.subsystems.Arm;
import org.usfirst.frc4930.Timrek.subsystems.Claw;
import org.usfirst.frc4930.Timrek.subsystems.DriveTrain;
import org.usfirst.frc4930.Timrek.subsystems.DropWheel;
import org.usfirst.frc4930.Timrek.subsystems.Gripper;
import org.usfirst.frc4930.Timrek.subsystems.Mast;
import org.usfirst.frc4930.Timrek.subsystems.PTO;
import org.usfirst.frc4930.Timrek.subsystems.Shifter;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.*;

public class Robot extends TimedRobot
{

  Command autonomousCommand;
  SendableChooser<Command> chooser = new SendableChooser<>();

  public static OI oi;
  public static DriveTrain driveTrain;
  public static Gripper gripper;
  public static PTO pto;
  public static Claw claw;
  public static Shifter shifter;
  public static DropWheel dropWheel;
  public static Dial preference1Dial;
  public static Dial preference2Dial;
  public static Arm arm;
 // public static Cameras cameras;
  public static NavX navX;
  public static Mast mast;
  public static NetworkTableEntry state;
  public static NetworkTableEntry toggle;
  public static NetworkTableEntry detaunt1;
  public static NetworkTableEntry detaunt2;
  
  public static String status = "Disabled";
  
  // ALL THESE VALUES NEED TO BE CHECKED TO SEE HOW SOLENOID STATE RELATES TO ROBOT
  public static boolean shifterInLowGear = false;
  public static boolean ptoOn = false;
  public static boolean dropped = false;
  public static boolean clawOpen = false;
  
  public static boolean inAuto;

  @Override
  public void robotInit() {
    RobotMap.init();
    navX = new NavX(RobotMap.ahrs, PIDSourceType.kDisplacement);
    driveTrain = new DriveTrain();
    gripper = new Gripper();
    pto = new PTO();
    claw = new Claw();
    dropWheel = new DropWheel();
    shifter = new Shifter();
    preference1Dial = new Dial(RobotMap.preference1Pot);
    preference2Dial = new Dial(RobotMap.preference2Pot);
    arm = new Arm();
   // cameras = new Cameras();
    mast = new Mast();
    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    NetworkTable table = inst.getTable("RaspberryPi");
    state = table.getEntry("state");
    toggle = table.getEntry("toggle");
    detaunt1 = table.getEntry("detaunt1");
    detaunt2 = table.getEntry("detaunt2");

    // OI must be constructed after subsystems.
    oi = new OI();

  }
  
  public void robotPeriodic() {
//	  state.setString(status);
//	  int pref1 = preference1Dial.getPosition();
//	  int pref2 = preference2Dial.getPosition();
//	  int toggleNum = ((RobotMap.toggleSwitch.get()) ? 1 : 0);
//	  
//	  System.out.println(status + " " + toggleNum  + " " + pref1  + " " + pref2 );
	  
//	  System.out.println((RobotMap.toggleSwitch.get()) ? 1 : 0);
//	  System.out.println("detaunt1: " + preference1Dial.getPosition());
//	  System.out.println("detaunt2: " + preference2Dial.getPosition());
//	  toggle.setNumber( (RobotMap.toggleSwitch.get()) ? 1 : 0);
//	  detaunt1.setNumber(preference1Dial.getPosition());
//	  detaunt2.setNumber(preference2Dial.getPosition());
  }

  @Override
  public void disabledInit() {
 
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
	  state.setString(status);
	  toggle.setNumber( (RobotMap.toggleSwitch.get()) ? 1 : 0);
	  detaunt1.setNumber(preference1Dial.getPosition());
	  detaunt2.setNumber(preference2Dial.getPosition());
  }

  @Override
  public void autonomousInit() {
	 inAuto = true;
	 status = "Auto";
	 RobotMap.ahrs.reset();
	 RobotMap.solenoid0.set(true);
	 
    autonomousCommand = new AutoPaths();
  
    // schedule the autonomous command (example)
    if (autonomousCommand != null)
      autonomousCommand.start();
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
	  state.setString(status);
	  toggle.setNumber( (RobotMap.toggleSwitch.get()) ? 1 : 0);
	  detaunt1.setNumber(preference1Dial.getPosition());
	  detaunt2.setNumber(preference2Dial.getPosition());
  }

  @Override
  public void teleopInit() {
	  inAuto = false;
	  status = "TeleOp";
	  
    if (autonomousCommand != null)
      autonomousCommand.cancel();
    
    RobotMap.lDrvMSTR.setSelectedSensorPosition(0, 0, 100);
    RobotMap.rDrvMSTR.setSelectedSensorPosition(0, 0, 100);
    RobotMap.dropWhl.setSelectedSensorPosition(0, 0, 100);
    RobotMap.ahrs.reset();
    RobotMap.solenoid1.set(false);
    arm.setEncoders(Constants.ELBOW_START_POSITION, 0);
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
	  state.setString(status);
	  toggle.setNumber( (RobotMap.toggleSwitch.get()) ? 1 : 0);
	  detaunt1.setNumber(preference1Dial.getPosition());
	  detaunt2.setNumber(preference2Dial.getPosition());
      int pref1 = preference1Dial.getPosition();
	  int pref2 = preference2Dial.getPosition();
	  int toggleNum = ((RobotMap.toggleSwitch.get()) ? 1 : 0);
	  
	  System.out.println(status + " " + toggleNum  + " " + pref1  + " " + pref2 );
    
    if (oi.j1b10.get()) {
      RobotMap.lElbow.setSelectedSensorPosition(0, 0, 0);
      // Robot.arm.resetElbowPosition();
    }
    if (oi.j1b9.get()) {
      RobotMap.lShoulder.setSelectedSensorPosition(0, 0, 0);
      // Robot.arm.resetShoulderPosition();
    }

    SmartDashboard.putNumber("LeftShoulder", RobotMap.lShoulder.get());

    SmartDashboard.putNumber("SHOULDER ENCODER: ", RobotMap.lShoulder.getSelectedSensorPosition(0));
    SmartDashboard.putNumber("ELBOW ENCODER: ", RobotMap.lElbow.getSelectedSensorPosition(0));
    SmartDashboard.putNumber("Left Master Encoder(22):",
        RobotMap.lDrvMSTR.getSelectedSensorPosition(0));
    SmartDashboard.putNumber("Right Master Encoder(28):",
        RobotMap.rDrvMSTR.getSelectedSensorPosition(0));
    SmartDashboard.putNumber("Drop Wheel Encoder(34):",
        RobotMap.dropWhl.getSelectedSensorPosition(0));

    SmartDashboard.putNumber("Get Angle", navX.getAngle());

    SmartDashboard.putBoolean("ARM STABLIZE: ", Robot.arm.shouldMaintain);

    SmartDashboard.putNumber("PositionPot Raw: ", RobotMap.preference1Pot.get());
    SmartDashboard.putNumber("DelayPot Raw: ", RobotMap.preference2Pot.get());
    SmartDashboard.putNumber("PositionPot", Robot.preference1Dial.getPosition());
    SmartDashboard.putNumber("DelayPot", Robot.preference2Dial.getPosition());

    SmartDashboard.putBoolean("LOWER ARM DOWN: ", RobotMap.lArmDownLSwitch.get());

    SmartDashboard.putNumber("SHOULDER POWER: ", RobotMap.lShoulder.get());
    SmartDashboard.putNumber("ELBOW POWER: ", RobotMap.lElbow.get());

    SmartDashboard.putNumber("LEFT DRIVE", RobotMap.lDrvMSTR.get());
    SmartDashboard.putNumber("RIGHT DRIVE", RobotMap.rDrvMSTR.get());
    
    SmartDashboard.putBoolean("UPPER ARM UP LSWICTH", RobotMap.uArmUpLSwitch.get());

    SmartDashboard.putBoolean("TOGGLE SWITCH: ", RobotMap.toggleSwitch.get());

  }
  
 
}
