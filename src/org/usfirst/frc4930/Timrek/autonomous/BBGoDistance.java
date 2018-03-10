package org.usfirst.frc4930.Timrek.autonomous;

import org.usfirst.frc4930.Timrek.Constants;
import org.usfirst.frc4930.Timrek.Robot;
import org.usfirst.frc4930.Timrek.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class BBGoDistance extends Command {
	
	  double desiredDistance;
	  double speed;
	  double startPosition;
	  double currentPosition;
	  boolean ranOnce;
	  public BBGoDistance(double distance) {
		  requires(Robot.driveTrain);
		  double inches = distance*12;
		  double revolutions = (inches/(Constants.WHEEL_DIAMETER * Math.PI)) ;
		  desiredDistance =  Constants.TICKS_PER_REVOULTION_LOW_GEAR * revolutions;
		  startPosition = 0;
		  currentPosition = 0;
	
		  if(desiredDistance > 0){
			  speed = Constants.BB_GO_DISTANCE_SPEED;
		  }
		  else {
			  speed = -Constants.BB_GO_DISTANCE_SPEED;
		  }
	  }
	  public BBGoDistance(double distance, boolean highGear, double driveSpeed){
		  requires(Robot.driveTrain);
		  double inches = distance*12;
		  double revolutions = (inches/(Constants.WHEEL_DIAMETER * Math.PI)) ;
		  if(highGear){
			  desiredDistance =  Constants.TICKS_PER_REVOLUTION_HIGH_GEAR * revolutions;
		  }
		  else {
			  desiredDistance =  Constants.TICKS_PER_REVOULTION_LOW_GEAR * revolutions;
		  }
		  startPosition = 0;
		  currentPosition = 0;
		  speed = driveSpeed;
	  }
	  protected void initialize() {
//		  Robot.driveTrain.resetEncoders();
		  Robot.navX.reset();
//		  RobotMap.lDrvMSTR.setSelectedSensorPosition(0, 0, 0);
		  startPosition = RobotMap.lDrvMSTR.getSelectedSensorPosition(0);
//		  System.out.println("INITAL ENCODER POSITION: " + RobotMap.lDrvMSTR.getSelectedSensorPosition(0));
//		  System.out.println("STARTING DISTANCE COMMAND TO " + desiredDistance);
		  
	  }

	  protected void execute() {
		  currentPosition = RobotMap.lDrvMSTR.getSelectedSensorPosition(0) - startPosition; 
		 
		  System.out.println("RUNNING DISTANCE COMMAND TO " + desiredDistance);
		  System.out.println("START ENCODER POSITION: " + startPosition );
		  System.out.println("Actual ENCODER POSITION: " + RobotMap.lDrvMSTR.getSelectedSensorPosition(0));
		  System.out.println("CURRENT POSITION" + currentPosition);
		  if(desiredDistance > 0){
			  Robot.driveTrain.move(speed + 0.015, speed);
		  }
		  else {
			  Robot.driveTrain.move(speed - 0.015, speed);
		  }
		 // System.out.println("Distance: " + desiredDistance);
		 // System.out.println("Distance Left: " + (desiredDistance - RobotMap.lDrvMSTR.getSelectedSensorPosition(0)));
	  }

	  protected boolean isFinished() {
		  if(desiredDistance > 0){
			//  System.out.println("ENCODER POSITION: " + RobotMap.lDrvMSTR.getSelectedSensorPosition(0));
			  return currentPosition >= desiredDistance;
		  }
		  else {
			  return currentPosition <= desiredDistance;
		  }
	}

	  protected void end() {
		  Robot.driveTrain.stop();
		  Robot.driveTrain.resetEncoders();
	  }

	  protected void interrupted() {
	    end();
	  }
	
}
