package org.usfirst.frc4930.Timrek.subsystems;

import org.usfirst.frc4930.Timrek.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Mast extends Subsystem {

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	
	public void set(double speed){
		RobotMap.mast.set(speed);
	}
	
	public void stop() {
		RobotMap.mast.stopMotor();
	}
	
}