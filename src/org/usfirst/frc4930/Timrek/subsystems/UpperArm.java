package org.usfirst.frc4930.Timrek.subsystems;

import org.usfirst.frc4930.Timrek.Robot;
import org.usfirst.frc4930.Timrek.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class UpperArm extends Subsystem {
	
	private boolean lowerArmRaised;
	private boolean fullyRaised;
	private boolean isGrounded;
	
	protected void initDefaultCommand() {
		
	}
	
	public boolean checkMove(){
		lowerArmRaised = !Robot.lowerArm.checkState();
		return lowerArmRaised;
	}
	
	public boolean checkGround() {
		// encoder values NEED TO BE TESTED
		
		if (RobotMap.lElbow.getSelectedSensorPosition(0) < 2000) {
			isGrounded = true;
		}
		else {
			isGrounded = false;
		}
		
		return isGrounded;
	}
	
	public boolean checkRaised() {
		// encoder values NEED TO BE TESTED
		if(RobotMap.lElbow.getSelectedSensorPosition(0) > 70000) {
			fullyRaised = true;
		}
		else {
			fullyRaised = false;
		}
		
		return fullyRaised;
			
	}
	
	public void move(double speed) { 
		RobotMap.lElbow.set(speed);
	}
	
	public void changeState(int state) {
		
		//currently the movements are through the move command, but once PID is proven to work
		// use the setPosition() instead
		if(state == 1) {
			move(-0.1);
		}
		
		else if(state == 2){
			if(checkRaised()) {
				move(-0.1);
			}
			else if(checkGround()){
				move(0.1);
			}
		}
		else if(state == 3) {
			move(0.1);
		}
		
	}
	
	public int getState() {
		int state;
		if(checkGround()){
			state = 1;
		}
		else if (checkRaised()) {
			state = 3;
			
		}
		else {
			state = 2;
		}
		return state;
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
