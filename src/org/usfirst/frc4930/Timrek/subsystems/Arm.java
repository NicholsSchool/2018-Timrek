package org.usfirst.frc4930.Timrek.subsystems;

import org.usfirst.frc4930.Timrek.RobotMap;
import org.usfirst.frc4930.Timrek.Values;
import org.usfirst.frc4930.Timrek.commands.MoveArm;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem
{

  WPI_TalonSRX lShoulder = RobotMap.lShoulder;
  WPI_TalonSRX rShoulder = RobotMap.rShoulder;
  WPI_TalonSRX lElbow = RobotMap.lElbow;
  WPI_TalonSRX rElbow = RobotMap.rElbow;

  // DigitalInput uArmDownLSwitch = RobotMap.uArmDownLSwitch;
  DigitalInput lArmDownLSwitch = RobotMap.lArmDownLSwitch;

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new MoveArm());
  }

  @Override
  public void periodic() {
    // if the lower arm is down, reset the encoders
    if (!lArmDownLSwitch.get()) {
      lShoulder.setSelectedSensorPosition(0, 0, 0);
    }
  }

  public void set(double speed) {
    if (speed > 0.05) {
      extend(speed);
    } else if (speed < -0.05) {
      retract(speed);
    } else {
      maintain();
    }
  }

  // right motor will follow the left
  public void stop() {
    lShoulder.set(0.0);
    lElbow.set(0.0);
  }

  // maintains the arm position
  private void maintain() {
    lElbow.set(0.1);
    lShoulder.set(0.1);
  }

  private void extend(double speed) {
    // limit switches return false when pressed
    // if the upper arm is not fully extended, extend upper arm
    if (lElbow.getSelectedSensorPosition(0) < Values.ELBOW_EXTENDED * 0.9) {
      // 0.1 will maintain the position
      lShoulder.set(0.1);
      // elbow moves faster than the shoulder
      lElbow.set(speed * 0.8);
    } else if (lShoulder.getSelectedSensorPosition(0) < Values.SHOULDER_EXTENDED * 0.9) {
      // else if lower arm is not fully extended, extend lower arm
      lShoulder.set(speed);
      lElbow.set(0.1);
    }
  }

  private void retract(double speed) {
    // gravity will help you retract
    speed *= 0.7;
    // limit switches return false when pressed
    // if lower arm is not retracted, retract lower arm
    if (lShoulder.getSelectedSensorPosition(0) > 200) {
      lShoulder.set(speed);
      lElbow.set(0.0);
    } else if (lElbow.getSelectedSensorPosition(0) > 3000) {
      // else if upper arm is not retracted, retract upper arm
      lShoulder.set(0.0);
      // elbow moves faster than the shoulder
      lElbow.set(speed * 0.7);
    }
  }

  // set velocity
  public void pIDSetV(double speed) {
    // config pid
    lShoulder.config_kP(0, 0.0, 0);
    lShoulder.config_kI(0, 0.0, 0);
    lShoulder.config_kD(0, 0.0, 0);

    lElbow.config_kP(0, 0.0, 0);
    lElbow.config_kI(0, 0.0, 0);
    lElbow.config_kD(0, 0.0, 0);

    if (speed > 0.05) {
      pIDExtendV(speed);
    } else if (speed < -0.05) {
      pIDRetractV(speed);
    } else {
      pIDMaintainV();
    }
  }

  private void pIDMaintainV() {
    lElbow.set(ControlMode.Velocity, 0.0);
    lShoulder.set(ControlMode.Velocity, 0.0);
  }

  private void pIDExtendV(double speed) {
    // limit switches return false when pressed
    // if the upper arm is not fully extended, extend upper arm
    if (lElbow.getSelectedSensorPosition(0) < Values.ELBOW_EXTENDED * 0.8) {
      lShoulder.set(ControlMode.Velocity, 0.0);
      lElbow.set(ControlMode.Velocity, speed * Values.ELBOW_VELOCITY);
    } else if (lShoulder.getSelectedSensorPosition(0) < Values.SHOULDER_EXTENDED * 0.8) {
      // else if lower arm is not fully extended, extend lower arm
      lShoulder.set(ControlMode.Velocity, speed * Values.SHOULDER_VELOCITY);
      lElbow.set(ControlMode.Velocity, 0.0);
    }
  }

  private void pIDRetractV(double speed) {
    // limit switches return false when pressed
    // if lower arm is not retracted, retract lower arm
    if (lArmDownLSwitch.get()) {
      lShoulder.set(ControlMode.Velocity, speed * Values.SHOULDER_VELOCITY);
      lElbow.set(ControlMode.Velocity, 0.0);
    } else if (lElbow.getSelectedSensorPosition(0) > 3000) {
      // else if upp arm is not retracted, retract upper arm
      lShoulder.set(ControlMode.Velocity, 0.0);
      lElbow.set(ControlMode.Velocity, speed * Values.ELBOW_VELOCITY);
    }
  }

  // set position
  public void pIDSetP(double jPosition) {

    // config pid
    lShoulder.config_kP(0, 0.0, 0);
    lShoulder.config_kI(0, 0.0, 0);
    lShoulder.config_kD(0, 0.0, 0);

    lElbow.config_kP(0, 0.0, 0);
    lElbow.config_kI(0, 0.0, 0);
    lElbow.config_kD(0, 0.0, 0);

    // map joystick values from -1.0 to 1.0
    // to relative position values from 0.0 to 1.0
    // a joystick value of 0.0 will map to a position value of 0.5
    double position = (jPosition + 1.0) / 2.0;

    if (position > 0.05) {
      pIDExtendP(position);
    } else if (position < -0.05) {
      pIDRetractP(position);
    } else {
      pIDMaintainP();
    }
  }

  private void pIDMaintainP() {
    lElbow.set(0.1);
    lShoulder.set(0.1);
  }

  private void pIDExtendP(double position) {
    // limit switches return false when pressed
    // if the upper arm is not fully extended, extend upper arm
    if (lElbow.getSelectedSensorPosition(0) < Values.ELBOW_EXTENDED * 0.8) {
      lShoulder.set(0.05);
      lElbow.set(ControlMode.Position, position * Values.ELBOW_EXTENDED);
    } else if (lShoulder.getSelectedSensorPosition(0) < Values.SHOULDER_EXTENDED * 0.8) {
      // else if lower arm is not fully extended, extend lower arm
      lShoulder.set(ControlMode.Position, position * Values.SHOULDER_EXTENDED);
      lElbow.set(0.05);
    }
  }

  private void pIDRetractP(double position) {
    // limit switches return false when pressed
    // if lower arm is not retracted, retract lower arm
    if (lArmDownLSwitch.get()) {
      lShoulder.set(ControlMode.Position, position * Values.SHOULDER_EXTENDED);
      lElbow.set(0.05);
    } else if (lElbow.getSelectedSensorPosition(0) > 3000) {
      // else if upp arm is not retracted, retract upper arm
      lShoulder.set(0.05);
      lElbow.set(ControlMode.Position, position * Values.ELBOW_EXTENDED);
    }
  }

  public void resetEncoders() {
    lShoulder.setSelectedSensorPosition(0, 0, 0);
    lElbow.setSelectedSensorPosition(0, 0, 0);
  }

  public void testMotor(int id, double speed) {
    switch (id) {
      case Values.L_SHOULDER_ID:
        lShoulder.set(speed);
        break;
      case Values.R_SHOULDER_ID:
        rShoulder.set(speed);
        break;

      case Values.L_ELBOW_ID:
        lElbow.set(speed);
        break;
      case Values.R_ELBOW_ID:
        rElbow.set(speed);
        break;

      default:
        System.out.println("Invalid Arm Motor ID");
        break;
    }
  }

}
