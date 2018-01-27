package org.usfirst.frc4930.Timrek.subsystems;

import org.usfirst.frc4930.Timrek.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Gripper extends Subsystem {

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

	public void grip(double speed) {
		RobotMap.lIntake.set(speed);
		RobotMap.rIntake.set(speed);
	}
	
	public void stop() {
		RobotMap.lIntake.stopMotor();
		RobotMap.rIntake.stopMotor();
	}
}

