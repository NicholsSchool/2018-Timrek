package org.usfirst.frc4930.Timrek.autonomous;

import org.usfirst.frc4930.Timrek.Robot;
import org.usfirst.frc4930.Timrek.commands.DisengagePTO;

import edu.wpi.first.wpilibj.command.Command;

public class BBGoToAngle extends Command {
	double desiredAngle;
	
	public BBGoToAngle(double angle){
		desiredAngle = angle;
	}
	  protected void initialize() {
		  Robot.navX.reset();
		  new DisengagePTO().start();
	  }

	  protected void execute() {
		  Robot.driveTrain.BBGoToAngle(desiredAngle);
	  }

	  protected boolean isFinished() {
	    return !Robot.driveTrain.goingToAngle;
	  }

	  protected void end() {
		  Robot.driveTrain.stop();
	  }

	  protected void interrupted() {
	    end();
	  }
}
