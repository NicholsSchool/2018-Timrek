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
    }
  }

  public void set(double speed) {
    if (speed > 0.1) {
    	updatePosition();
    	extend(speed);
    } else if (speed < -0.1) {
    	updatePosition();
    	retract(speed);
    } else {
    	/*if(lElbow.getSelectedSensorPosition(0) < position - 1000 || lElbow.getSelectedSensorPosition(0) >position + 1000 ){
    		System.out.println("Adjusting Elbow");
    		adjust(position);
    	}
    	else {
    		maintain();
    	}*/
    	
    	System.out.println("Adjusting....");
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
		lElbow.config_kP(0, 0.03, 100);
		lElbow.config_kI(0, 0, 100);
		lElbow.config_kD(0, 0.05, 100);
		
		lElbow.set(ControlMode.Position, position);
  }
  
  private void adjustShoulder(double position) {
	  lShoulder.config_kP(0, 0.06, 0);
	  lShoulder.config_kI(0, 0.0, 0);
	  lShoulder.config_kD(0, 0.04, 0);
	  
	  lShoulder.set(ControlMode.Position, position);
  }
  
  public void updatePosition() {
	  elbowPos = lElbow.getSelectedSensorPosition(0);
	  shoulderPos = lShoulder.getSelectedSensorPosition(0);
	  SmartDashboard.putNumber("elbowPos: ", elbowPos);
	  SmartDashboard.putNumber("shoulderPos", shoulderPos);
  }
  
  private void extend(double speed) {
    // limit switches return false when pressed
    // if the upper arm is not fully extended, extend upper arm
    if (lElbow.getSelectedSensorPosition(0) < Constants.ELBOW_TO_BAR * 0.9) {
      // 0.05 will maintain the position
      lShoulder.set(0.05);
      // elbow moves faster than the shoulder
      lElbow.set(speed * Constants.ELBOW_RELATIVE_SPD);
    } else if (lShoulder.getSelectedSensorPosition(0) < Constants.SHOULDER_TO_BAR * 0.9) {
      // else if lower arm is not fully extended, extend lower arm
      lShoulder.set(speed);
      lElbow.set(0.05);
    } else {
    	System.out.println("extending both now....");
    	
    	// if both arms are past the bar, run each until they reach the limit
	    if (lElbow.getSelectedSensorPosition(0) < Constants.ELBOW_EXTENDED * 0.9) {
		      // elbow moves faster than the shoulder
		      lElbow.set(speed * Constants.ELBOW_RELATIVE_SPD);
	    } else {
	    	// maintain if at the max
	    	lElbow.set(0.05);
	    }
	    
	    if (lShoulder.getSelectedSensorPosition(0) < Constants.SHOULDER_EXTENDED * 0.9) {
		      lShoulder.set(speed);

	    } else {
	    	// maintain if at the max
	    	lShoulder.set(0.05);
	    }
    }
  }

  private void retract(double speed) {
    // gravity will help you retract
    speed *= 0.3;
    // limit switches return false when pressed
    // if both are above the bar, move both at once
    if(lShoulder.getSelectedSensorPosition(0) > Constants.SHOULDER_TO_BAR && lElbow.getSelectedSensorPosition(0) > Constants.ELBOW_TO_BAR) {
    		System.out.println("retracting both now....");
    		lShoulder.set(speed);
    		lElbow.set(speed * Constants.ELBOW_RELATIVE_SPD);

    } else {
    	// don't slam
    	speed = 0.1;
    	// if they are below the bar, move one at a time
        // if lower arm is not retracted, retract lower arm
    	if (lShoulder.getSelectedSensorPosition(0) > 200) {
  	      lShoulder.set(speed);
  	      lElbow.set(0.05);
  	    } else if (lElbow.getSelectedSensorPosition(0) > 3000) {
  	      // else if upper arm is not retracted, retract upper arm
  	      lShoulder.set(0.05);
  	      // elbow moves faster than the shoulder
  	      lElbow.set(speed * Constants.ELBOW_RELATIVE_SPD);
  	    }
    	
    }
  }


  public void resetEncoders() {
    lShoulder.setSelectedSensorPosition(0, 0, 0);
    lElbow.setSelectedSensorPosition(0, 0, 0);
  }

}
