package org.usfirst.frc4930.Timrek.commands;

import org.usfirst.frc4930.Timrek.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LowerArmMove extends Command{
	
	private boolean startState;
	
	public LowerArmMove() {
		requires(Robot.lowerArm);
		requires(Robot.upperArm);
	}
	
	protected void initialize() {
		startState = Robot.lowerArm.checkState();
	}
	
	protected void execute() {
		if(Robot.lowerArm.checkMove()){
			if (Robot.lowerArm.checkState()) {
				Robot.lowerArm.move(0.1);
			}
			else {
				Robot.lowerArm.move(-0.1);
			}
		}
	}
	
	@Override
	protected boolean isFinished() {
		return (startState != Robot.lowerArm.checkState()) || (!Robot.lowerArm.checkMove());
	}
	
	protected void end() {
		Robot.lowerArm.stop();
	}
	
	protected void interrupted() {
		end();
	}

}
