package org.usfirst.frc4930.Timrek.subsystems;

import org.usfirst.frc4930.Timrek.Robot;
import org.usfirst.frc4930.Timrek.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DropWheel extends Subsystem {

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

	public void drop() {
		RobotMap.solenoid2.set(true);
		Robot.dropped = true;
	}
	
	public void raise() {
		RobotMap.solenoid2.set(false);
		Robot.dropped = false;
	}

}
