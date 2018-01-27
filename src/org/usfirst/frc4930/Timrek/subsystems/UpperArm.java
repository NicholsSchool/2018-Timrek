package org.usfirst.frc4930.Timrek.subsystems;

import org.usfirst.frc4930.Timrek.Robot;
import org.usfirst.frc4930.Timrek.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class UpperArm extends Subsystem {
	
	private boolean lowerArmRaised;
	private boolean isGrounded;
	
	protected void initDefaultCommand() {
		
	}
	
	public boolean checkMove(){
		lowerArmRaised = !Robot.lowerArm.checkState();
		return lowerArmRaised;
	}
	
	public boolean checkState() {
		// encoder values NEED TO BE TESTED
		
		if (RobotMap.lElbow.getSelectedSensorPosition(0) < 10) {
			isGrounded = true;
		}
		else if (RobotMap.lElbow.getSelectedSensorPosition(0) > 100) {
			isGrounded = false;
		}
		
		return isGrounded;
	}
	
	public void move(double speed) { 
		RobotMap.lElbow.set(speed);
	}
	
	public void setPosition(double position) {
		RobotMap.lElbow.config_kP(0, 0.5, 100);
		RobotMap.lElbow.config_kI(0, 0.0, 100);
		RobotMap.lElbow.config_kD(0, 0.1, 100);
		
		RobotMap.lElbow.set(ControlMode.Position, position);
	}
	
	public void stop() {
		RobotMap.lElbow.stopMotor();
	}
	
	
}
