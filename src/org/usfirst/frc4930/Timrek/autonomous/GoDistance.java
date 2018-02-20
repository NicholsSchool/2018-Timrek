package org.usfirst.frc4930.Timrek.autonomous;

import org.usfirst.frc4930.Timrek.Constants;
import org.usfirst.frc4930.Timrek.Robot;
import org.usfirst.frc4930.Timrek.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class GoDistance extends Command {
	
	  double desiredDistance;
	  public GoDistance(double distance){
		
	  double inches = distance*12;
	  double revolutions = (inches/(Constants.WHEEL_DIAMETER * Math.PI)) ;
	  desiredDistance =  Constants.TICKS_PER_REVOULTION * revolutions;
	  
	  }
	  protected void initialize() {
		  Robot.driveTrain.resetEncoders();
		  
		  System.out.println("deisiredDistance: " + desiredDistance);
		  Robot.driveTrain.setPosition(desiredDistance);
	  }

	  protected void execute() {

		  System.out.println("Going To Distance");
		 
	  }

	  protected boolean isFinished() {
	    return desiredDistance >= RobotMap.lDrvMSTR.getSelectedSensorPosition(0);
	  }

	  protected void end() {
		  Robot.driveTrain.stop();
	  }

	  protected void interrupted() {
	    end();
	  }
}
