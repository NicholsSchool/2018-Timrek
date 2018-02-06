package org.usfirst.frc4930.Timrek.subsystems;

import org.usfirst.frc4930.Timrek.Robot;
import org.usfirst.frc4930.Timrek.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Shifter extends Subsystem {

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public void setLowGear() {
		RobotMap.solenoid0.set(true);
		Robot.shifterInLowGear = true;
	}
	
	public void setHighGear() {
		RobotMap.solenoid0.set(false);
		Robot.shifterInLowGear = false;
	}

}
