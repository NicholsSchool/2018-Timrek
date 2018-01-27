package org.usfirst.frc4930.Timrek.subsystems;

import org.usfirst.frc4930.Timrek.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class LowerArm extends Subsystem{
	
	public static boolean isGrounded;
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean checkState() {
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
	
	public void stop() {
		RobotMap.lShoulder.stopMotor();
	}
}

