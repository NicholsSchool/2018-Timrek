package org.usfirst.frc4930.Timrek.subsystems;

import org.usfirst.frc4930.Timrek.RobotMap;
import org.usfirst.frc4930.Timrek.Constants;
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
      shoulderPos = 0;
    }
  }

  public void set(double speed) {
	  
    if (speed > 0.2) {
    	extend();
    } else if (speed < -0.2) {    	
    	retract();
    } else {
    	adjustElbow(elbowPos);
    	adjustShoulder(shoulderPos);
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
		
		lElbow.set(ControlMode.Position, position);
  }

  
  private void adjustShoulder(double position) {
	  lShoulder.config_kP(0, 0.8, 0);
	  lShoulder.config_kI(0, 0.0, 0);
	  lShoulder.config_kD(0, 0.0, 0);
	  
	  lShoulder.set(ControlMode.Position, position);
	  
  }
  
  public void updatePosition() {
	  elbowPos = lElbow.getSelectedSensorPosition(0);
	  shoulderPos = lShoulder.getSelectedSensorPosition(0);
	  SmartDashboard.putNumber("elbowPos: ", elbowPos);
	  SmartDashboard.putNumber("shoulderPos", shoulderPos);
  }
  
  private void extend() {
    // limit switches return false when pressed
    // if the upper arm is not fully extended, extend upper arm
    if (lElbow.getSelectedSensorPosition(0) < Constants.ELBOW_TO_BAR) {
      // 0.05 will maintain the position
      lShoulder.set(0.05);
      lElbow.set(Constants.ELBOW_RAISE_SPD_BELOW_BAR);
      System.out.println("EXTENDING ELBOW");
    } else if (lShoulder.getSelectedSensorPosition(0) < Constants.SHOULDER_TO_BAR) {
      // else if lower arm is not fully extended, extend lower arm
      lShoulder.set(Constants.SHOULDER_RAISE_SPD_BELOW_BAR);
      lElbow.set(0.1);
      System.out.println("EXTENDING SHOULDER");
    } else {
    	System.out.println("EXTENDING BOTH");
    	// if both arms are past the bar, run each until they reach the limit
	    if (lElbow.getSelectedSensorPosition(0) < Constants.ELBOW_EXTENDED) {
		      // elbow moves faster than the shoulder
		      lElbow.set(Constants.ELBOW_RAISE_SPD_ABOVE_BAR);
	    } else {
	    	// maintain if at the max
	    	lElbow.set(0.05);
	    }
	    
	    if (lShoulder.getSelectedSensorPosition(0) < Constants.SHOULDER_EXTENDED) {
		      lShoulder.set(Constants.SHOULDER_RAISE_SPD_ABOVE_BAR);

	    } else {
	    	// maintain if at the max
	    	lShoulder.set(0.05);
	    }
    }
    
    updatePosition();
  }

  private void retract() {
    // if both are above the bar, move both at once
    if(lShoulder.getSelectedSensorPosition(0) > Constants.SHOULDER_TO_BAR
    		&& lElbow.getSelectedSensorPosition(0) > Constants.ELBOW_TO_BAR) {
    	
    		System.out.println("RETRACTING BOTH");
    		lShoulder.set(Constants.SHOULDER_LOWER_SPD_ABOVE_BAR);
    		lElbow.set(Constants.ELBOW_LOWER_SPD_ABOVE_BAR);

    } else {
    	// if they are below the bar, move one at a time
        // if lower arm is not retracted, retract lower arm
    	if (lShoulder.getSelectedSensorPosition(0) > 0) {
    		System.out.println("RETRACTING SHOULDER");
  	      lShoulder.set(Constants.SHOULDER_LOWER_SPD_BELOW_BAR);
  	      lElbow.set(0.05);
  	    } else if (lElbow.getSelectedSensorPosition(0) > 0) {
  	    	System.out.println("RETRACTING ELBOW");
  	      // else if upper arm is not retracted, retract upper arm
  	      lShoulder.set(0.05);
  	      lElbow.set(Constants.ELBOW_LOWER_SPD_BELOW_BAR);
  	    }
    	
    }
    
    updatePosition();
  }
  
  public void MoveToPosition(int elbowPos, int shoulderPos) {
	  if(lElbow.getSelectedSensorPosition(0) > elbowPos && lShoulder.getSelectedSensorPosition(0) > shoulderPos) {
		  retract();
	  } else if(lElbow.getSelectedSensorPosition(0) < elbowPos && lShoulder.getSelectedSensorPosition(0) < shoulderPos) {
		  extend();
	  } else {
		  adjustElbow(this.elbowPos);
		  adjustShoulder(this.shoulderPos);
	  }
  }


  
  public void resetEncoders() {
    lShoulder.setSelectedSensorPosition(0, 0, 0);
    lElbow.setSelectedSensorPosition(0, 0, 0);
  }

}
