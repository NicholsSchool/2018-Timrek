package org.usfirst.frc4930.Timrek.PathPlanning;

import org.usfirst.frc4930.Timrek.RobotMap;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;

public class StartingLeft {
	
	public Path leftSwitch = new Path();
	public Path leftScale = new Path();
	public Path rightSwitch = new Path();
	public Path rightScale = new Path();
	
	private double width;
	private double velocity = 1.7;
	private double acceleration = 2.0;
	private double jerk = 60.0;
	
	
	
	Waypoint start = new Waypoint(-3.3528, 0 , 90 );
	
	public StartingLeft() {
		leftSwitch.addPoint(start);
		leftScale.addPoint(start);
		rightSwitch.addPoint(start);
		rightScale.addPoint(start);
		
		leftSwitch.addPoint(new Waypoint(-3.3528, 4.1148, 0));
		
		leftScale.addPoint(new Waypoint(-3.3528, 8.2296, 0));
		
		leftSwitch.config(velocity, acceleration, jerk);
		leftScale.config(velocity, acceleration, jerk);
		
		leftSwitch.generate();
		leftScale.generate();
		
		leftSwitch.setTankDrive();
		leftScale.setTankDrive();
		
		leftSwitch.getValues(leftSwitch.left);
		leftSwitch.getValues(leftSwitch.right);
	
	}
}
