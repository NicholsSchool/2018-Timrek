package org.usfirst.frc4930.PathPlanning;



public class StartingLeft extends StartingPath {

	
	
	
	public StartingLeft() {
		
		this.size(2, 2, 4, 4);
		
		leftSwitch.addPoint(Points.leftStart);
		leftScale.addPoint(Points.leftStart);
		rightSwitch.addPoint(Points.leftStart);
		rightScale.addPoint(Points.leftStart);
		
		//Create Paths
		leftSwitch.addPoint(Points.switchLeftSide);
		
		leftScale.addPoint(Points.scaleLeftSide);
		
		rightSwitch.addPoint(Points.turnRight);
		rightSwitch.addPoint(Points.forwardRightSide);
		rightSwitch.addPoint(Points.switchRightSide);
		
		rightScale.addPoint(Points.turnRight);
		rightScale.addPoint(Points.forwardRightSide);
		rightScale.addPoint(Points.scaleRightSide);
		
		this.init();
		
	}
}
