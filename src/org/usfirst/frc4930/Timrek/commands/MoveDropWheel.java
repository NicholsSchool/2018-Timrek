package org.usfirst.frc4930.Timrek.commands;

import org.usfirst.frc4930.Timrek.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveDropWheel extends Command{

	public MoveDropWheel() {
		requires(Robot.dropWheel);
	}
	
	protected void initialize() {
		Robot.dropWheel.drop();
	}
	
	protected void execute(){
		Robot.dropWheel.pivot();
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected void end() {
		Robot.dropWheel.set(0.0);
		Robot.dropWheel.raise();
	}
	
	protected void interrupted() {
		end();
	}
}
