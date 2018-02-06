package org.usfirst.frc4930.Timrek.commands;

import org.usfirst.frc4930.Timrek.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LowerArmMove extends Command{
	
	private int startState;
	private int desiredState;
	private int change;
	
	public LowerArmMove(int state) {
		requires(Robot.lowerArm);
		requires(Robot.upperArm);
		desiredState = state;
		startState = Robot.lowerArm.getState();
		change = desiredState - startState;
	}
	
	protected void initialize() {
		// Run This code to test PID Loop 
		//Robot.lowerArm.setPosition(45000 * change);
	}
	
	protected void execute() {
		Robot.lowerArm.move(0.35 * change);
	}
	
	@Override
	protected boolean isFinished() {
		return (Robot.lowerArm.getState() == desiredState);
	}
	
	protected void end() {
		//If lowerArm does not keep its position, change to .rest()
		Robot.lowerArm.stop();
	}
	
	protected void interrupted() {
		end();
	}

}
