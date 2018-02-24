package org.usfirst.frc4930.Timrek.autonomous;

import org.usfirst.frc4930.Timrek.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class BreakGoToAngle extends Command {
	  protected void initialize() {}

	  protected void execute() {
		  Robot.driveTrain.endLoop();
	  }

	  protected boolean isFinished() {
	    return true;
	  }

	  protected void end() {
		  Robot.driveTrain.stop();
	  }

	  protected void interrupted() {
	    end();
	  }
}
