package org.usfirst.frc4930.Timrek.PathPlanning;

public class StartingRight extends StartingPath{
	

	public StartingRight() {
		leftSwitch.addPoint(Points.rightStart);
		leftScale.addPoint(Points.rightStart);
		rightSwitch.addPoint(Points.rightStart);
		rightScale.addPoint(Points.rightStart);
		
		//Create Paths
		rightSwitch.addPoint(Points.switchRightSide);
		
		rightScale.addPoint(Points.scaleRightSide);
		
		leftSwitch.addPoint(Points.turnLeft);
		leftSwitch.addPoint(Points.forwardLeftSide);
		leftSwitch.addPoint(Points.switchLeftSide);
		
		leftScale.addPoint(Points.turnLeft);
		leftScale.addPoint(Points.forwardLeftSide);
		leftScale.addPoint(Points.scaleLeftSide);
		
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
