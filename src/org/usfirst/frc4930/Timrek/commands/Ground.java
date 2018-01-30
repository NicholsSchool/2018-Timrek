package org.usfirst.frc4930.Timrek.commands;

import org.usfirst.frc4930.Timrek.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Ground extends Command{
	
	private boolean lowerArmRaised;
	
	public Ground() {
		requires(Robot.upperArm);
		requires(Robot.lowerArm);
	} 
	
	protected void initialize() {
		 lowerArmRaised = Robot.upperArm.checkMove();
		//Check if the Lower arm isn't lowered, the lower it using the command I will make
	}
	
	protected void execute() {
		if(!lowerArmRaised){
		Robot.upperArm.changeState(1);
		}
	}
	
	@Override
	protected boolean isFinished() {
		
		return (Robot.upperArm.checkGround());
	}
	
	protected void end() {
		Robot.upperArm.stop();
	}
	
	protected void interrupted() {
		end();
	}
}
