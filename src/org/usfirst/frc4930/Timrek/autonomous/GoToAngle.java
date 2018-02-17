package org.usfirst.frc4930.Timrek.autonomous;

import org.usfirst.frc4930.Timrek.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GoToAngle extends Command{
		
	  private double desiredAngle;
	
	  public GoToAngle(double angle) {
		desiredAngle = angle;
	  }
	
	  protected void initialize() {
		  Robot.navX.reset();
	  }

	  protected void execute() {
		  Robot.driveTrain.goToAngle(desiredAngle);
	  }

	  protected boolean isFinished() {
	    return Robot.navX.atAngle(desiredAngle);
	  }

	  protected void end() {
		  Robot.driveTrain.stop();
		  Robot.driveTrain.endLoop();
	  }

	  protected void interrupted() {
	    end();
	  }
}
