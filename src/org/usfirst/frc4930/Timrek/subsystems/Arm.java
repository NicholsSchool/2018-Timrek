package org.usfirst.frc4930.Timrek.subsystems;

import org.usfirst.frc4930.Timrek.RobotMap;
import org.usfirst.frc4930.Timrek.Constants;
import org.usfirst.frc4930.Timrek.Robot;
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
  public boolean shouldMaintain = true;
  
  DigitalInput uArmDownLSwitch = RobotMap.uArmDownLSwitch;
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
      shoulderPos = 0;
    }
    if(!uArmDownLSwitch.get()){
    	 lElbow.setSelectedSensorPosition(0, 0, 0);
         elbowPos = 0;
    }
    

  }

  public void set(double speed) {
	  
    if (speed > 0.2) {
    	extend(speed);
    } else if (speed < -0.2) {    	
    	retract(speed);
    } else {
    	//adjustElbow(elbowPos);
    	//adjustShoulder(shoulderPos);
    }
  }

  // right motor will follow the left
  public void stop() {
    lShoulder.set(0.0);
    lElbow.set(0.0);
  }
  
  // if was previously extending, meaning we must move the upper arm down
  private void adjustElbow(double position) {
		lElbow.config_kP(0, 0.3, 100);
		lElbow.config_kI(0, 0.0, 100);
		lElbow.config_kD(0, 0.0, 100);
		
		if(shouldMaintain) {
			System.out.println("GOING TO: " + position);
			lElbow.set(ControlMode.Position, position);
		} else {
			lElbow.set(0.0);
			// make sure to update the position if the robot is not maintaining
			updatePosition();
		}
  }

  
  private void adjustShoulder(double position) {
	  lShoulder.config_kP(0, 0.8, 0);
	  lShoulder.config_kI(0, 0.0, 0);
	  lShoulder.config_kD(0, 0.0, 0);
	  
	  if(shouldMaintain) {
		  lShoulder.set(ControlMode.Position, position);
	  } else {
		  lShoulder.set(0.0);
		  updatePosition();
	  }
	  
  }
  
  public void setEncoders(int elbow, int shoulder) {
	  lElbow.setSelectedSensorPosition(elbow, 0, 0);
	  lShoulder.setSelectedSensorPosition(shoulder, 0, 0);
	  updatePosition();
  }
  
  public void updatePosition() {
	  elbowPos = lElbow.getSelectedSensorPosition(0);
	  shoulderPos = lShoulder.getSelectedSensorPosition(0);
	  SmartDashboard.putNumber("elbowPos: ", elbowPos);
	  SmartDashboard.putNumber("shoulderPos", shoulderPos);
  }
  
  private void extend(double joystickVal) {
	  double elbowSpd = 0.0;
	  double shoulderSpd = 0.0;
	  if(Robot.clawOpen) {
		  elbowSpd = Constants.ELBOW_RAISE_SPD * joystickVal;
		  shoulderSpd = Constants.SHOULDER_RAISE_SPD * joystickVal;
	  } else {
		  elbowSpd = Constants.ELBOW_RAISE_SPD_CUBE * joystickVal;
		  shoulderSpd = Constants.SHOULDER_RAISE_SPD_CUBE * joystickVal;
	  }
    // limit switches return false when pressed
    // if the upper arm is not fully extended, extend upper arm
   // if (lElbow.getSelectedSensorPosition(0) < Constants.ELBOW_EXTENDED) \
	 if(RobotMap.uArmUpLSwitch.get()){
      // 0.05 will maintain the position
      lShoulder.set(0.05); // changed to .05
      lElbow.set(elbowSpd);
    }
    else if (lShoulder.getSelectedSensorPosition(0) < Constants.SHOULDER_EXTENDED) {
      // else if lower arm is not fully extended, extend lower arm
      lShoulder.set(shoulderSpd);
      lElbow.set(0.05);
    }
    
    updatePosition();
  }

  private void retract(double joystickVal) {
	  double elbowSpd = 0.0;
	  double shoulderSpd = 0.0;
	  if(Robot.clawOpen) {
		  elbowSpd = Constants.ELBOW_LOWER_SPD * -joystickVal;
		  shoulderSpd = Constants.SHOULDER_LOWER_SPD * -joystickVal;
	  } else {
		  elbowSpd = Constants.ELBOW_LOWER_SPD_CUBE * -joystickVal;
		  shoulderSpd = Constants.SHOULDER_LOWER_SPD_CUBE * -joystickVal;
	  }
        // if lower arm is not retracted, retract lower arm
    	if (lArmDownLSwitch.get()) {
    		System.out.println(shoulderSpd);
  	      lShoulder.set(shoulderSpd);
  	      lElbow.set(0.05);
  	    } 
    	else if (uArmDownLSwitch.get()) {
  	      // else if upper arm is not retracted, retract upper arm
  	      lShoulder.set(0.05);//was .05 
  	      lElbow.set(elbowSpd);
  	    }
    
    updatePosition();
  }
  
  public boolean atPosition;
  public void moveToPosition(int elbowPos, int shoulderPos) {
	  if(lElbow.getSelectedSensorPosition(0) > elbowPos && lShoulder.getSelectedSensorPosition(0) > shoulderPos) {
		 // retract();
	  } else if(lElbow.getSelectedSensorPosition(0) < elbowPos && lShoulder.getSelectedSensorPosition(0) < shoulderPos) {
		 // extend();
	  } else {
		  atPosition = true;
	  }
  }


  
  public void resetEncoders() {
    lShoulder.setSelectedSensorPosition(0, 0, 0);
    lElbow.setSelectedSensorPosition(0, 0, 0);
  }

}
