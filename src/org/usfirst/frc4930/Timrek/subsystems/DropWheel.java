package org.usfirst.frc4930.Timrek.subsystems;

import org.usfirst.frc4930.Timrek.Robot;
import org.usfirst.frc4930.Timrek.RobotMap;
import org.usfirst.frc4930.Timrek.commands.MoveDropWheel;


import edu.wpi.first.wpilibj.command.Subsystem;

public class DropWheel extends Subsystem {

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
//		setDefaultCommand(new MoveDropWheel());
	}

	public void drop() {
		RobotMap.solenoid2.set(true);
		Robot.dropped = true;
	}
	
	public void raise() {
		RobotMap.solenoid2.set(false);
		Robot.dropped = false;
	}
	
	public void set(double speed) {
		RobotMap.dropWhl.set(speed);
	}
	
	public void pivot() {
		double speed = Robot.oi.j1.getX();
		/*if(Math.abs(speed) < 0.2) {
			return;
		}*/
		set(speed);
	}
	
	public void stop() {
		RobotMap.dropWhl.stopMotor();
	}
}
