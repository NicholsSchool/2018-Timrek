package org.usfirst.frc4930.Timrek.autonomous;

import org.usfirst.frc4930.Timrek.Robot;
import org.usfirst.frc4930.Timrek.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ResetEncoders extends Command{
	  protected void initialize() {
		  
	  }
	  
	  protected void execute() {
		  Robot.driveTrain.resetEncoders();
	  }

	  protected boolean isFinished() {
	    return true;
	  }

	  protected void end() {}

	  protected void interrupted() {
	    end();
	  }
}
