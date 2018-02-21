package org.usfirst.frc4930.Timrek.autonomous;

import org.usfirst.frc4930.Timrek.Constants;
import org.usfirst.frc4930.Timrek.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class BBGoDistance extends Command {
	
		double desiredDistance;
	  public BBGoDistance(double distance) {
		  double inches = distance*12;
		  double revolutions = (inches/(Constants.WHEEL_DIAMETER * Math.PI)) ;
		  desiredDistance =  Constants.TICKS_PER_REVOULTION * revolutions;
		  
	  }
	  protected void initialize() {
		  Robot.driveTrain.resetEncoders();
		  Robot.navX.reset();
	  }

	  protected void execute() {
//		  if(Robot.navX.getAngle() != 0){
//			  Robot.driveTrain.BBGoToAngle(0);
//		  }
		  Robot.driveTrain.BBGoDistance(desiredDistance);
		  System.out.println("Distance: " + desiredDistance);
	  }

	  protected boolean isFinished() {
	    return !Robot.driveTrain.goingDistance;
	  }

	  protected void end() {
		  Robot.driveTrain.stop();
	  }

	  protected void interrupted() {
	    end();
	  }
	
}
