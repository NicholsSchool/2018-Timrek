package org.usfirst.frc4930.Timrek.autonomous;

import org.usfirst.frc4930.Timrek.Constants;
import org.usfirst.frc4930.Timrek.Robot;
import org.usfirst.frc4930.Timrek.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class BBGoDistance extends Command {
	
	  double desiredDistance;
	  public BBGoDistance(double distance) {
		  requires(Robot.driveTrain);
		  double inches = distance*12;
		  double revolutions = (inches/(Constants.WHEEL_DIAMETER * Math.PI)) ;
		  desiredDistance =  Constants.TICKS_PER_REVOULTION * revolutions;
		  
	  }
	  protected void initialize() {
		  Robot.driveTrain.resetEncoders();
		  Robot.navX.reset();
	  }

	  protected void execute() {

		  Robot.driveTrain.move(Constants.BB_GO_DISTANCE_SPEED, Constants.BB_GO_DISTANCE_SPEED);
		  System.out.println("Distance: " + desiredDistance);
		  System.out.println("Distance Left: " + (desiredDistance - RobotMap.lDrvMSTR.getSelectedSensorPosition(0)));
	  }

	  protected boolean isFinished() {
	    return RobotMap.lDrvMSTR.getSelectedSensorPosition(0) >= desiredDistance - 1000;
	  }

	  protected void end() {
		  Robot.driveTrain.stop();
		  Robot.driveTrain.resetEncoders();
	  }

	  protected void interrupted() {
	    end();
	  }
	
}
