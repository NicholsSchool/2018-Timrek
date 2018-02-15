package org.usfirst.frc4930.Timrek.subsystems;

import org.usfirst.frc4930.Timrek.Robot;
import org.usfirst.frc4930.Timrek.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class PTO extends Subsystem {

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public void turnOff() {
		RobotMap.solenoid1.set(true);
		Robot.ptoOn = false;
		
	}
	
	public void turnOn() {
		RobotMap.solenoid1.set(false);
		Robot.ptoOn = true;
	}

}
