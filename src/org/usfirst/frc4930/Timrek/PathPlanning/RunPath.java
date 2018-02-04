package org.usfirst.frc4930.Timrek.PathPlanning;

import edu.wpi.first.wpilibj.command.Command;

public class RunPath extends Command{

	private Path desiredPath;
	
	RunPath(Path path) {
		desiredPath = path;
	}
	
	protected void initialize() {
		
	}
	
	protected void execute() {
		desiredPath.run();
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		
	}
	
	protected void interrupted() {
		end();
	}
}
