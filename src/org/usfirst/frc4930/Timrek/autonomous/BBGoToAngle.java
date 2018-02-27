package org.usfirst.frc4930.Timrek.autonomous;

import org.usfirst.frc4930.Timrek.Robot;
import org.usfirst.frc4930.Timrek.commands.DisengagePTO;

import edu.wpi.first.wpilibj.command.Command;

public class BBGoToAngle extends Command {
	double desiredAngle;
	double lSpeed;
	double rSpeed;
	double speed;
	public BBGoToAngle(double angle){
		  requires(Robot.driveTrain);
		desiredAngle = angle;
		
		if(angle > 0){
			speed = 0.8;
		}
		else {
			speed = -0.8;
		}
		lSpeed = speed;
		rSpeed = -speed;
	}
	  protected void initialize() {
		
		  if(desiredAngle != 0){
			  Robot.navX.reset();
		  }
		  new DisengagePTO().start();
	  }

	  protected void execute() {
		  double currentAngle = Robot.navX.getAngle();
		  Robot.driveTrain.move(lSpeed, rSpeed);
		//  Robot.driveTrain.BBGoToAngle(desiredAngle);
	  }

	  protected boolean isFinished() {
		double currentAngle = Robot.navX.getAngle();
	    return (currentAngle < desiredAngle + 8 && currentAngle  > desiredAngle - 8) ;
	  }

	  protected void end() {
		  Robot.driveTrain.stop();
	  }

	  protected void interrupted() {
	    end();
	  }
}
