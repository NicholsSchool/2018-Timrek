package org.usfirst.frc4930.Timrek.autonomous;

import org.usfirst.frc4930.Timrek.Robot;
import org.usfirst.frc4930.Timrek.commands.DisengagePTO;

import edu.wpi.first.wpilibj.command.Command;

public class GoToAngle extends Command{
		
	  private double desiredAngle;
	
	  public GoToAngle(double angle) {
		desiredAngle = angle;
	  }
	
	  protected void initialize() {
		 new DisengagePTO().start();
		  Robot.navX.reset();
	  }

	  protected void execute() {
		  System.out.println("In a PID loop");
		  Robot.driveTrain.goToAngle(desiredAngle);
	  }

	  protected boolean isFinished() {
	    return !Robot.driveTrain.goingToAngle;
	  }

	  protected void end() {
		  Robot.driveTrain.stop();
		  Robot.driveTrain.endLoop();
		  System.out.println("Out a PID loop");
	  }

	  protected void interrupted() {
	    end();
	  }
}
