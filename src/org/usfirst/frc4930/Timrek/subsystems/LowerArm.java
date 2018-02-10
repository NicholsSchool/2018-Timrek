package org.usfirst.frc4930.Timrek.subsystems;

import org.usfirst.frc4930.Timrek.Robot;
import org.usfirst.frc4930.Timrek.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class LowerArm extends Subsystem{
	
	private boolean upperArmRaised;
	private boolean fullyRaised;
	private boolean isGrounded;

	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
	
	public boolean checkMove() {
		upperArmRaised = Robot.upperArm.checkRaised();
		return upperArmRaised;
	}
	
	public boolean checkGround() {
		// encoder values NEED TO BE TESTED
		
		if (RobotMap.lShoulder.getSelectedSensorPosition(0) < 40000) {
			isGrounded = true;
		}
		else {
			isGrounded = false;
		}
		
		return isGrounded;
	}
	
	public boolean checkRaised() {
		// encoder values NEED TO BE TESTED
		if(RobotMap.lShoulder.getSelectedSensorPosition(0) > 40000) {
			fullyRaised = true;
		}
		else {
			fullyRaised = false;
		}
		
		return fullyRaised;
			
	}
	
	public void move(double speed) { 
		RobotMap.lShoulder.set(speed);
	}
	
	public int getState() {
		int state;
		if(checkGround()){
			state = 1;
		}
		else {
			state = 2;
		}
		return state;
	}
	

	public void setPosition(double position) {
		//NOT TESTED
		
		RobotMap.lShoulder.config_kP(0, 0.04, 100);
		RobotMap.lShoulder.config_kI(0, 0.0, 100);
		RobotMap.lShoulder.config_kD(0, 0.2, 100);
		
		RobotMap.lShoulder.set(ControlMode.Position, position);
	}
	
	public void goDown(double position){
		
		//NOT TESTED
		RobotMap.lShoulder.config_kP(0, 0.005, 100);
		RobotMap.lShoulder.config_kI(0, 0.0, 100);
		RobotMap.lShoulder.config_kD(0, 0.35, 100);
		
		RobotMap.lShoulder.set(ControlMode.Position, position);
	}
	
	
	public void rest() {
		RobotMap.lShoulder.set(0.1);
	}
	
	public void stop() {

		RobotMap.lShoulder.stopMotor();
	}
}

