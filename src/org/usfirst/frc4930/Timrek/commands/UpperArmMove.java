package org.usfirst.frc4930.Timrek.commands;

import org.usfirst.frc4930.Timrek.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class UpperArmMove extends Command {
	
	private boolean startState;
	
	public UpperArmMove() {
		requires(Robot.upperArm);
		requires(Robot.lowerArm);
	} 
	
	protected void initialize() {
		startState = Robot.upperArm.checkState();
	}
	
	protected void execute() {
		if(Robot.lowerArm.checkMove()){
			if (Robot.upperArm.checkState()) {
				Robot.upperArm.move(0.4);
			}
			else {
				Robot.upperArm.move(-0.4);
			}
		}
	}
	
	@Override
	protected boolean isFinished() {
		
		return (startState != Robot.upperArm.checkState()) || (!Robot.upperArm.checkMove());
	}
	
	protected void end() {
		Robot.upperArm.stop();
	}
	
	protected void interrupted() {
		end();
	}

}
