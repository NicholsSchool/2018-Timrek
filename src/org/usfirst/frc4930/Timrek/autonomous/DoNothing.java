package org.usfirst.frc4930.Timrek.autonomous;

import edu.wpi.first.wpilibj.command.Command;

public class DoNothing extends Command{

	  protected void initialize() {}

	  protected void execute() {}

	  protected boolean isFinished() {
	    return true;
	  }

	  protected void end() {}

	  protected void interrupted() {
	    end();
	  }
}
