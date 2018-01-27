package org.usfirst.frc4930.Timrek.subsystems;

import org.usfirst.frc4930.Timrek.Robot;
import org.usfirst.frc4930.Timrek.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class LowerArm extends Subsystem{
	
	private boolean isGrounded;
	private boolean upperArmRaised;
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		upperArmRaised = Robot.upperArm.checkState();
	}
	
	public boolean checkMove() {
		return upperArmRaised;
	}
	
	public boolean checkState() {
		// encoder values NEED TO BE TESTED
		
		if (RobotMap.lShoulder.getSelectedSensorPosition(0) < 10) {
			isGrounded = true;
		}
		else if (RobotMap.lShoulder.getSelectedSensorPosition(0) > 100) {
			isGrounded = false;
		}
		
		return isGrounded;
	}
	
	public void move(double speed) { 
		RobotMap.lShoulder.set(speed);
	}
	
	public void setPosition(double position) {
		RobotMap.lShoulder.config_kP(0, 0.5, 100);
		RobotMap.lShoulder.config_kI(0, 0.0, 100);
		RobotMap.lShoulder.config_kD(0, 0.1, 100);
		
		RobotMap.lShoulder.set(ControlMode.Position, position);
	}
	
	public void stop() {
		RobotMap.lShoulder.stopMotor();
	}
}

