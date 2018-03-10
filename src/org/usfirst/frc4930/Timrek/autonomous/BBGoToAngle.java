package org.usfirst.frc4930.Timrek.autonomous;

import org.usfirst.frc4930.Timrek.Constants;
import org.usfirst.frc4930.Timrek.Robot;
import org.usfirst.frc4930.Timrek.commands.DisengagePTO;

import edu.wpi.first.wpilibj.command.Command;

public class BBGoToAngle extends Command {
	double desiredAngle;
	double lSpeed;
	double rSpeed;

	public BBGoToAngle(double angle){
		requires(Robot.driveTrain);
		desiredAngle = angle;
		
		if(desiredAngle > 0){
			lSpeed = Constants.BB_GO_TO_ANGLE_SPEED;
		}
		else {
			lSpeed = -Constants.BB_GO_TO_ANGLE_SPEED;
		}
		rSpeed = -lSpeed;
	}
	public BBGoToAngle(double angle, boolean oneSide){
		requires(Robot.driveTrain);
		desiredAngle = angle;
		
		if(desiredAngle > 0){
			lSpeed = 1.0;
			rSpeed = 0;
		}
		else {
			lSpeed = 0;
			rSpeed = 1.0;
		}
	}
	
	protected void initialize() {
		  Robot.navX.reset();
		  
		  new DisengagePTO().start();
	  }

	  protected void execute() {
		  Robot.driveTrain.move(lSpeed, rSpeed);
	  }

	  protected boolean isFinished() {
		double currentAngle = Robot.navX.getAngle();
	    return (currentAngle < desiredAngle + Constants.BB_G0_TO_ANGLE_OFFSET 
	         && currentAngle  > desiredAngle - Constants.BB_G0_TO_ANGLE_OFFSET);
	  }

	  protected void end() {
		  Robot.driveTrain.stop();
		  Robot.driveTrain.resetEncoders(); 
	  }

	  protected void interrupted() {
	    end();
	  }
}
