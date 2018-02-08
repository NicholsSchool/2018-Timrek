package org.usfirst.frc4930.Timrek.commands;

import org.usfirst.frc4930.Timrek.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class UpperArmMove extends Command {
	
	private int startState;
	private int desiredState;
	private int change;
	
	public UpperArmMove(int state) {
		requires(Robot.upperArm);
		requires(Robot.lowerArm);
		desiredState = state;
		startState = Robot.upperArm.getState();
		change = desiredState - startState;
	} 
	
	protected void initialize() {
		// Run This code to test PID Loop 
		if(change > 0){
			Robot.upperArm.setPosition(40000 * change);
		}
		else {
			Robot.upperArm.goDown(40000 * change);
		}
	}
	
	protected void execute() {
		//	Robot.upperArm.move(0.35 * change);
			
	}
	
	@Override
	protected boolean isFinished() {
		
		return (Robot.upperArm.getState() == desiredState);
		
	}
	
	protected void end() {
		Robot.upperArm.rest();
		// start joystick control
		new MoveArm().start();
	}
	
	protected void interrupted() {
		end();
	}

}
