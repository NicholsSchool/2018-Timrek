package org.usfirst.frc4930.Timrek.commands;

import org.usfirst.frc4930.Timrek.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TankDrive extends Command {
	public TankDrive() {
		requires(Robot.driveTrain);
	} 
	
	protected void initialize() {
		
	}
	
	protected void execute() {
		Robot.driveTrain.tankDrive();
	}
	
	@Override
	protected boolean isFinished() {
		
		return false;
	}
	
	protected void end() {
		Robot.driveTrain.stop();
	}
	
	protected void interrupted() {
		end();
	}

}
