package org.usfirst.frc4930.Timrek.PathPlanning;

import jaci.pathfinder.Waypoint;

public class StartingLeft {
	
	public Path leftSwitch = new Path();
	public Path leftScale = new Path();
	public Path rightSwitch = new Path();
	public Path rightScale = new Path();
	
	Waypoint start = new Waypoint(-3.3528, 0 , 0 );
	
	public StartingLeft() {
		leftSwitch.addPoints(start);
		leftScale.addPoints(start);

	}
}
