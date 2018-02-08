package org.usfirst.frc4930.Timrek.commands;

import org.usfirst.frc4930.Timrek.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Intake extends Command {

	protected void initialize() {

	}
	
	protected void execute() {
		Robot.gripper.grip(0.4);
	}
	
	@Override
	protected boolean isFinished() {
		
		return false;
	}
	
	protected void end() {
		Robot.gripper.stop();
	}
	
	protected void interrupted() {
		end();
	}

}