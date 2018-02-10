package org.usfirst.frc4930.Timrek.commands;

import org.usfirst.frc4930.Timrek.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LowerMast extends Command{
	 public LowerMast() {
		  requires(Robot.mast);
		  }

		  protected void initialize() {}

		  protected void execute() {
		    Robot.mast.set(-0.4);
		  }

		  protected boolean isFinished() {
		    return false;
		  }

		  protected void end() {
			  Robot.mast.stop();
		  }

		  protected void interrupted() {
		    end();
		  }
}
