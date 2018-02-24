package org.usfirst.frc4930.Timrek.autonomous;

import org.usfirst.frc4930.Timrek.Constants;
import org.usfirst.frc4930.Timrek.Robot;
import org.usfirst.frc4930.Timrek.RobotMap;

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
		  if(Math.abs(Robot.navX.getAngle()) >=  1){
			  Robot.driveTrain.BBGoToAngle(0);
		  }
		  Robot.driveTrain.BBGoDistance(desiredDistance);
		  System.out.println("Distance: " + desiredDistance);
	  }

	  protected boolean isFinished() {
	    return RobotMap.lDrvMSTR.getSelectedSensorPosition(0) >= desiredDistance - 1000;
	  }

	  protected void end() {
		  Robot.driveTrain.stop();
	  }

	  protected void interrupted() {
	    end();
	  }
	
}
