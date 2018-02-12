package org.usfirst.frc4930.Timrek.subsystems;

import org.usfirst.frc4930.Timrek.RobotMap;
import org.usfirst.frc4930.Timrek.Values;
import org.usfirst.frc4930.Timrek.commands.MoveArm;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Arm extends Subsystem
{

  WPI_TalonSRX lShoulder = RobotMap.lShoulder;
  WPI_TalonSRX rShoulder = RobotMap.rShoulder;
  WPI_TalonSRX lElbow = RobotMap.lElbow;
  WPI_TalonSRX rElbow = RobotMap.rElbow;
  
  
  private double elbowPos = 0;
  private double shoulderPos = 0;
  
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
    	elbowPos = lElbow.getSelectedSensorPosition(0);
    	shoulderPos = lShoulder.getSelectedSensorPosition(0);
    } else if (speed < -0.05) {
    	retract(speed);
    	elbowPos = lElbow.getSelectedSensorPosition(0);
    	shoulderPos = lShoulder.getSelectedSensorPosition(0);
    } else {
    	/*if(lElbow.getSelectedSensorPosition(0) < position - 1000 || lElbow.getSelectedSensorPosition(0) >position + 1000 ){
    		System.out.println("Adjusting Elbow");
    		adjust(position);
    	}
    	else {
    		maintain();
    	}*/
    	
    	System.out.println("Adjusting Elbow");
    	adjustElbow(elbowPos);
    	adjustShoulder(shoulderPos);
    }
  }

  // right motor will follow the left
  public void stop() {
    lShoulder.set(0.0);
    lElbow.set(0.0);
  }

  private void adjustElbow(double position){
		lElbow.config_kP(0, 0.04, 100);
		lElbow.config_kI(0, 0, 100);
		lElbow.config_kD(0, 0.06, 100);
		
		lElbow.set(ControlMode.Position, position);
  }
  
  private void adjustShoulder(double position) {
	  lShoulder.config_kP(0, 0.001, 0);
	  lShoulder.config_kI(0, 0.0, 0);
	  lShoulder.config_kD(0, 0.001, 0);
	  
	  lShoulder.set(ControlMode.Position, position);
  }
  
  // maintains the arm position
  private void maintain() {
    lElbow.set(0.1);
    lShoulder.set(0.1);
  }

  public void resetPosition() {
	  position = 100;
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


  public void resetEncoders() {
    lShoulder.setSelectedSensorPosition(0, 0, 0);
    lElbow.setSelectedSensorPosition(0, 0, 0);
  }

}
