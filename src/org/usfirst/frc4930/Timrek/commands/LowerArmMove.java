package org.usfirst.frc4930.Timrek.commands;

import org.usfirst.frc4930.Timrek.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LowerArmMove extends Command{
	
	private boolean startState;
	
	protected void initialize() {
		startState = Robot.lowerArm.checkState();
	}
	
	protected void execute() {
		if (Robot.lowerArm.checkState()) {
			Robot.lowerArm.move(0.4);
		}
		else {
			Robot.lowerArm.move(-0.4);
		}
	}
	
	@Override
	protected boolean isFinished() {
		
		return (startState != Robot.lowerArm.checkState());
	}
	
	protected void end() {
		Robot.lowerArm.stop();
	}
	
	protected void interrupted() {
		end();
	}

}
