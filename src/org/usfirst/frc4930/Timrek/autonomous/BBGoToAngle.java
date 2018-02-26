package org.usfirst.frc4930.Timrek.autonomous;

import org.usfirst.frc4930.Timrek.Robot;
import org.usfirst.frc4930.Timrek.commands.DisengagePTO;

import edu.wpi.first.wpilibj.command.Command;

public class BBGoToAngle extends Command {
	double desiredAngle;
	double lSpeed;
	double rSpeed;
	
	public BBGoToAngle(double angle){
		desiredAngle = angle;
		if(angle > 0){
			lSpeed= 0.85;
			rSpeed = -0.85;
		}
		else {
			lSpeed = -0.85;
			rSpeed = 0.85;
		}
	}
	  protected void initialize() {
		  if(desiredAngle != 0){
			  Robot.navX.reset();
		  }
		  new DisengagePTO().start();
	  }

	  protected void execute() {
		  Robot.driveTrain.move(lSpeed, rSpeed);
		//  Robot.driveTrain.BBGoToAngle(desiredAngle);
	  }

	  protected boolean isFinished() {
		double currentAngle = Robot.navX.getAngle();
	    return (currentAngle < desiredAngle + 5 && currentAngle  > desiredAngle - 5) ;
	  }

	  protected void end() {
		  Robot.driveTrain.stop();
	  }

	  protected void interrupted() {
	    end();
	  }
}
