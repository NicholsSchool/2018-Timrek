
package org.usfirst.frc4930.Timrek;

import org.usfirst.frc4930.Timrek.autonomous.AutoStartPositions;
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

import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
  public static Dial positionDial;
  public static Dial timeDelayDial;
  public static Arm arm;
  public static Cameras cameras;
  public static NavX navX;
  public static Mast mast;

  // ALL THESE VALUES NEED TO BE CHECKED TO SEE HOW SOLENOID STATE RELATES TO ROBOT
  public static boolean shifterInLowGear = true;
  public static boolean ptoOn = false;
  public static boolean dropped = false;
  public static boolean clawOpen = false;

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
    positionDial = new Dial(RobotMap.positionPot);
    timeDelayDial = new Dial(RobotMap.timeDelayPot);
    arm = new Arm();
    cameras = new Cameras();
    mast = new Mast();
    // OI must be constructed after subsystems.
    oi = new OI();

  }

  @Override
  public void disabledInit() {
    driveTrain.endLoop();
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
    driveTrain.endLoop();

  }

  @Override
  public void autonomousInit() {
    autonomousCommand = new AutoStartPositions();
    RobotMap.ahrs.reset();
    // schedule the autonomous command (example)
    if (autonomousCommand != null)
      autonomousCommand.start();
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();

  }

  @Override
  public void teleopInit() {
    if (autonomousCommand != null)
      autonomousCommand.cancel();
    RobotMap.lShoulder.setSelectedSensorPosition(0, 0, 100);
    RobotMap.lElbow.setSelectedSensorPosition(0, 0, 100);
    RobotMap.lDrvMSTR.setSelectedSensorPosition(0, 0, 100);
    RobotMap.rDrvMSTR.setSelectedSensorPosition(0, 0, 100);
    RobotMap.dropWhl.setSelectedSensorPosition(0, 0, 100);
    RobotMap.ahrs.reset();
    arm.updatePosition();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

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
    // SmartDashboard.putNumber("Get Yaw", navX.getYaw());

    SmartDashboard.putBoolean("ARM STABLIZE: ", Robot.arm.shouldMaintain);

    SmartDashboard.putNumber("PositionPot Raw: ", RobotMap.positionPot.get());
    SmartDashboard.putNumber("DelayPot Raw: ", RobotMap.timeDelayPot.get());
    SmartDashboard.putNumber("PositionPot", Robot.positionDial.getPosition());
    SmartDashboard.putNumber("DelayPot", Robot.timeDelayDial.getPosition());

    SmartDashboard.putBoolean("LOWER ARM DOWN: ", RobotMap.lArmDownLSwitch.get());

    SmartDashboard.putNumber("SHOULDER POWER: ", RobotMap.lShoulder.get());
    SmartDashboard.putNumber("ELBOW POWER: ", RobotMap.lElbow.get());

    SmartDashboard.putNumber("LEFT DRIVE", RobotMap.lDrvMSTR.get());
    SmartDashboard.putNumber("RIGHT DRIVE", RobotMap.rDrvMSTR.get());

    // two button engage for the pto
    // if(oi.j0b7.get() && oi.j0b8.get())
    // {
    // new EngagePTO().start();
    // }
  }
}
