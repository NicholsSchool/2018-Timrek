package org.usfirst.frc4930.Timrek.PathPlanning;

public class StartingCenter extends StartingPath {

	public StartingCenter(){
		leftSwitch.addPoint(Points.centerStart);
		leftScale.addPoint(Points.centerStart);
		rightSwitch.addPoint(Points.centerStart);
		rightScale.addPoint(Points.centerStart);
		
		//Create Paths
		
		leftSwitch.addPoint(Points.centerTurnLeft);
		leftSwitch.addPoint(Points.forwardLeftSide);
		leftSwitch.addPoint(Points.switchLeftSide);
		
		leftScale.addPoint(Points.centerTurnLeft);
		leftScale.addPoint(Points.forwardLeftSide);
		leftScale.addPoint(Points.scaleLeftSide);
		
		rightSwitch.addPoint(Points.centerTurnRight);
		rightSwitch.addPoint(Points.forwardRightSide);
		rightSwitch.addPoint(Points.switchRightSide);
		
		rightScale.addPoint(Points.centerTurnRight);
		rightScale.addPoint(Points.forwardRightSide);
		rightScale.addPoint(Points.scaleRightSide);
		
		leftSwitch.config(velocity, acceleration, jerk);
		leftScale.config(velocity, acceleration, jerk);
		rightSwitch.config(velocity, acceleration, jerk);
		rightScale.config(velocity, acceleration, jerk);
		
		leftSwitch.generate();
		leftScale.generate();
		rightSwitch.generate();
		rightScale.generate();
		
		leftSwitch.setTankDrive();
		leftScale.setTankDrive();
		rightSwitch.setTankDrive();
		rightScale.setTankDrive();
	}
}
