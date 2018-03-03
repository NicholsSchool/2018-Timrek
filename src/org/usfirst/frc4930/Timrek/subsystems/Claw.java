package org.usfirst.frc4930.Timrek.subsystems;

import org.usfirst.frc4930.Timrek.Robot;
import org.usfirst.frc4930.Timrek.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Claw extends Subsystem{

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public void open() {
		RobotMap.solenoid4.set(true);
		Robot.clawOpen = true;
	}
	
	public void close() {
		RobotMap.solenoid4.set(false);
		Robot.clawOpen = false;
	}

}
