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
  
  private boolean wasExtending = false;
  
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
    	wasExtending = true;
    	updatePosition();
    	// lerp the elbow and shoulder speeds
    	double elbowSpd = lerp(lElbow.get(), speed, Constants.ARM_LERP_T) * Constants.ELBOW_RELATIVE_SPD;
    	double shoulderSpd = lerp(lShoulder.get(), speed, Constants.ARM_LERP_T);
    	
    	extend(elbowSpd, shoulderSpd);
    } else if (speed < -0.1) {
    	wasExtending = false;
    	updatePosition();
    	
    	double elbowSpd = lerp(lElbow.get(), speed, Constants.ARM_LERP_T) * Constants.ELBOW_RELATIVE_SPD;
    	double shoulderSpd = lerp(lShoulder.get(), speed, Constants.ARM_LERP_T);
    	
    	retract(elbowSpd, shoulderSpd);
    } else {
    	if(wasExtending) {
    		adjustElbowDown(elbowPos, false);
    		adjustShoulderDown(shoulderPos, false);
    	} else {
    		adjustElbowUp(elbowPos, false);
    		adjustShoulderUp(shoulderPos, false);
    	}
    }
  }

  // right motor will follow the left
  public void stop() {
    lShoulder.set(0.0);
    lElbow.set(0.0);
  }

  private void adjustElbowUp(double position, boolean hasCube){
	  	if(hasCube) {
			lElbow.config_kP(0, 10.0, 100);
			lElbow.config_kI(0, 0.0, 100);
			lElbow.config_kD(0, 0.0, 100);
	  	} else {
			lElbow.config_kP(0, 10.0, 100);
			lElbow.config_kI(0, 0.0, 100);
			lElbow.config_kD(0, 0.0, 100);
	  	}
		
		lElbow.set(ControlMode.Position, position);
  }
  
  private void adjustElbowDown(double position, boolean hasCube) {
		if(hasCube) {
			lElbow.config_kP(0, 0.1, 100);
			lElbow.config_kI(0, 0.0001, 100);
			lElbow.config_kD(0, 0.0, 100);
		} else {
			lElbow.config_kP(0, 0.1, 100);
			lElbow.config_kI(0, 0.0001, 100);
			lElbow.config_kD(0, 0.0, 100);
		}
		
		lElbow.set(ControlMode.Position, position);
  }
  
  private void adjustShoulderUp(double position, boolean hasCube) {
	  if(hasCube) {
		  lShoulder.config_kP(0, 0.06, 0);
		  lShoulder.config_kI(0, 0.0, 0);
		  lShoulder.config_kD(0, 0.04, 0);
	  } else {
		  lShoulder.config_kP(0, 0.06, 0);
		  lShoulder.config_kI(0, 0.0, 0);
		  lShoulder.config_kD(0, 0.04, 0);
	  }
	  
	  lShoulder.set(ControlMode.Position, position);
  }
  
  private void adjustShoulderDown(double position, boolean hasCube) {
	  if(hasCube) {
		  lShoulder.config_kP(0, 0.06, 0);
		  lShoulder.config_kI(0, 0.0, 0);
		  lShoulder.config_kD(0, 0.04, 0);
	  } else {
		  lShoulder.config_kP(0, 0.06, 0);
		  lShoulder.config_kI(0, 0.0, 0);
		  lShoulder.config_kD(0, 0.04, 0);
	  }
	  
	  lShoulder.set(ControlMode.Position, position);
	  
  }
  
  public void updatePosition() {
	  elbowPos = lElbow.getSelectedSensorPosition(0);
	  shoulderPos = lShoulder.getSelectedSensorPosition(0);
	  SmartDashboard.putNumber("elbowPos: ", elbowPos);
	  SmartDashboard.putNumber("shoulderPos", shoulderPos);
  }
  
  private void extend(double elbowSpd, double shoulderSpd) {
    // limit switches return false when pressed
    // if the upper arm is not fully extended, extend upper arm
    if (lElbow.getSelectedSensorPosition(0) < Constants.ELBOW_TO_BAR * 0.9) {
      // 0.05 will maintain the position
      lShoulder.set(0.05);
      // elbow moves faster than the shoulder
      lElbow.set(elbowSpd);
    } else if (lShoulder.getSelectedSensorPosition(0) < Constants.SHOULDER_TO_BAR * 0.9) {
      // else if lower arm is not fully extended, extend lower arm
      lShoulder.set(shoulderSpd);
      lElbow.set(0.05);
    } else {
    	System.out.println("extending both now....");
    	
    	// if both arms are past the bar, run each until they reach the limit
	    if (lElbow.getSelectedSensorPosition(0) < Constants.ELBOW_EXTENDED * 0.9) {
		      // elbow moves faster than the shoulder
		      lElbow.set(elbowSpd);
	    } else {
	    	// maintain if at the max
	    	lElbow.set(0.05);
	    }
	    
	    if (lShoulder.getSelectedSensorPosition(0) < Constants.SHOULDER_EXTENDED * 0.9) {
		      lShoulder.set(shoulderSpd);

	    } else {
	    	// maintain if at the max
	    	lShoulder.set(0.05);
	    }
    }
  }

  private void retract(double elbowSpd, double shoulderSpd) {
    // gravity will help you retract
    elbowSpd *= 0.4;
    shoulderSpd *= 0.4;
    // limit switches return false when pressed
    // if both are above the bar, move both at once
    if(lShoulder.getSelectedSensorPosition(0) > Constants.SHOULDER_TO_BAR && lElbow.getSelectedSensorPosition(0) > Constants.ELBOW_TO_BAR) {
    		System.out.println("retracting both now....");
    		lShoulder.set(shoulderSpd);
    		lElbow.set(elbowSpd);

    } else {
    	// don't slam
    	elbowSpd += 0.12;
    	shoulderSpd += 0.2;
    	// if they are below the bar, move one at a time
        // if lower arm is not retracted, retract lower arm
    	if (lShoulder.getSelectedSensorPosition(0) > 200) {
  	      lShoulder.set(shoulderSpd);
  	      lElbow.set(0.05);
  	    } else if (lElbow.getSelectedSensorPosition(0) > 3000) {
  	      // else if upper arm is not retracted, retract upper arm
  	      lShoulder.set(0.05);
  	      // elbow moves faster than the shoulder
  	      lElbow.set(elbowSpd);
  	    }
    	
    }
  }
  
  // linearly interpolate between a and b with factor t
  private double lerp(double a, double b, double t) {
	  return a + t * (b - a);
  }


  public void resetEncoders() {
    lShoulder.setSelectedSensorPosition(0, 0, 0);
    lElbow.setSelectedSensorPosition(0, 0, 0);
  }

}
