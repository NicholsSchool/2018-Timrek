package org.usfirst.frc4930.Timrek.PathPlanning;

import jaci.pathfinder.Waypoint;

public class Points {
	
	private static double 
	right = 3.3528,
	left = -3.3528,
	maxYVal = 8.2296,
	faceForward = 90,
	faceRight = 0,
	faceLeft = 180;
	
	//Starting Positions
	public static Waypoint
	leftStart = new Waypoint(left, 0 , faceForward ),
	rightStart = new Waypoint(right, 0, faceForward),
	centerStart = new Waypoint(0, 0, faceForward);
	
	//Ending Positions
	public static Waypoint
	switchLeftSide = new Waypoint(left, maxYVal/2, faceRight),
	switchRightSide = new Waypoint(right, maxYVal/2, faceLeft),
	scaleLeftSide = new Waypoint(left, maxYVal, faceRight),
	scaleRightSide = new Waypoint(right, maxYVal, faceLeft);
	
	//Turning Points (Y VALUES NEEDS TO BE CONFIRMED)
	public static Waypoint
	turnRight = new Waypoint(left, maxYVal/5, faceRight),
	turnLeft = new Waypoint(right, maxYVal/5, faceLeft),
	centerTurnRight = new Waypoint(0, maxYVal/5, faceRight),
	centerTurnLeft = new Waypoint(0, maxYVal/5, faceLeft);
	
	public static Waypoint
	forwardRightSide = new Waypoint(right, maxYVal/5, faceForward),
	forwardLeftSide = new Waypoint(left, maxYVal/5, faceForward);
}
