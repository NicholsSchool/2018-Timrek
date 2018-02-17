
package org.usfirst.frc4930.Timrek;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into to a variable name.
 * This provides flexibility changing wiring, makes checking the wiring easier and significantly
 * reduces the number of magic numbers floating around.
 */

public class RobotMap
{
  public static WPI_TalonSRX lShoulder;
  public static WPI_TalonSRX rShoulder;

  public static WPI_TalonSRX lElbow;
  public static WPI_TalonSRX rElbow;

  public static WPI_TalonSRX lIntake;
  public static WPI_TalonSRX rIntake;

  public static WPI_TalonSRX lDrvMSTR;
  public static WPI_VictorSPX lDrvSlv1;
  public static WPI_VictorSPX lDrvSlv2;
  public static WPI_TalonSRX rDrvMSTR;
  public static WPI_VictorSPX rDrvSlv1;
  public static WPI_VictorSPX rDrvSlv2;

  public static WPI_TalonSRX dropWhl;
  public static WPI_TalonSRX mast;

  public static Compressor compressor;
  public static Solenoid solenoid0;
  public static Solenoid solenoid1;
  public static Solenoid solenoid2;
  public static Solenoid solenoid4;
  public static Solenoid solenoid5;

  public static AHRS ahrs;
  public static AnalogPotentiometer positionPot;
  public static AnalogPotentiometer timeDelayPot;
  public static DigitalInput lArmDownLSwitch;

  public static DifferentialDrive driveTank;

  public static void init() {

    lShoulder = new WPI_TalonSRX(Constants.L_SHOULDER_ID);
    rShoulder = new WPI_TalonSRX(Constants.R_SHOULDER_ID);

    lElbow = new WPI_TalonSRX(Constants.L_ELBOW_ID);
    rElbow = new WPI_TalonSRX(Constants.R_ELBOW_ID);

    lIntake = new WPI_TalonSRX(Constants.L_INTAKE_ID);
    rIntake = new WPI_TalonSRX(Constants.R_INTAKE_ID);

    lDrvMSTR = new WPI_TalonSRX(Constants.L_DRV_MSTR_ID);
    lDrvSlv1 = new WPI_VictorSPX(Constants.L_DRV_SLV1_ID);
    lDrvSlv2 = new WPI_VictorSPX(Constants.L_DRV_SLV2_ID);

    rDrvMSTR = new WPI_TalonSRX(Constants.R_DRV_MSTR_ID);
    rDrvSlv1 = new WPI_VictorSPX(Constants.R_DRV_SLV1_ID);
    rDrvSlv2 = new WPI_VictorSPX(Constants.R_DRV_SLV2_ID);

    dropWhl = new WPI_TalonSRX(Constants.DROP_WHL_ID);
    mast = new WPI_TalonSRX(Constants.MAST_ID);

    // Invert necessary motors
    lDrvMSTR.setInverted(true);

    rDrvMSTR.setInverted(true);

    rShoulder.setInverted(true);

    lElbow.setInverted(true);

    rIntake.setInverted(true);

    // Assign Slaves
    lDrvSlv1.follow(lDrvMSTR);
    lDrvSlv1.setNeutralMode(NeutralMode.Brake);

    lDrvSlv2.follow(lDrvMSTR);
    lDrvSlv2.setNeutralMode(NeutralMode.Brake);

    rDrvSlv1.follow(rDrvMSTR);
    rDrvSlv1.setNeutralMode(NeutralMode.Brake);

    rDrvSlv2.follow(rDrvMSTR);
    rDrvSlv2.setNeutralMode(NeutralMode.Brake);

    rShoulder.set(ControlMode.Follower, Constants.L_SHOULDER_ID);
    rElbow.set(ControlMode.Follower, Constants.L_ELBOW_ID);

    // Assign Encoders
    lShoulder.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 100);
    lElbow.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 100);

    lDrvMSTR.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 100);
    rDrvMSTR.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 100);
    dropWhl.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 100);

    // Invert Necessary Encoders
    lDrvMSTR.setSensorPhase(true);
    driveTank = new DifferentialDrive(lDrvMSTR, rDrvMSTR);
    driveTank.setSafetyEnabled(false);

    ahrs = new AHRS(SPI.Port.kMXP);
    positionPot = new AnalogPotentiometer(Constants.POSITIONPOT_CHNL, 360, 0);
    timeDelayPot = new AnalogPotentiometer(Constants.DELAYPOT_CHNL, 360, 0);
    lArmDownLSwitch = new DigitalInput(0);

    // Pneumatics
    compressor = new Compressor(50);
    solenoid0 = new Solenoid(50, 0);
    solenoid1 = new Solenoid(50, 1);
    solenoid2 = new Solenoid(50, 2);
    solenoid4 = new Solenoid(50, 4);
    solenoid5 = new Solenoid(50, 5);

    solenoid0.set(true);
    solenoid1.set(true);
    solenoid2.set(true);
    solenoid4.set(true);
    solenoid5.set(true);
  }

}
