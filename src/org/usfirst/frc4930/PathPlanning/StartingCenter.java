package org.usfirst.frc4930.PathPlanning;

public class StartingCenter extends StartingPath {

	public StartingCenter(){
		
		this.size(4, 4, 4, 4);
		
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
		
		this.init();
	}
}
