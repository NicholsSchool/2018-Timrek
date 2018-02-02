package org.usfirst.frc4930.Timrek.commands;

import org.usfirst.frc4930.Timrek.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClawClose extends Command {
	 public ClawClose() {
		    requires(Robot.claw);
		  }

		  protected void initialize() {}

		  protected void execute() {
		    Robot.claw.close();
		  }

		  protected boolean isFinished() {
		    return true;
		  }

		  protected void end() {}

		  protected void interrupted() {
		    end();
		  }
}
