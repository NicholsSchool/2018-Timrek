package org.usfirst.frc4930.Timrek.PathPlanning;

import edu.wpi.first.wpilibj.command.Subsystem;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

public class Path extends Subsystem {
	
	private int numPoints = 0;
	
	private Waypoint[] points = new Waypoint[10];
	
	private Trajectory.Config config; 
	private Trajectory trajectory; 
	
	private double wheelBaseWidth;
	private TankModifier modifier;

	public Trajectory left;       
	public Trajectory right;      
		
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	public void addPoints(Waypoint point) {
		points[numPoints] = point;
		numPoints ++;
	}
	
	public void config(double velocity, double acceleration, double jerk){
		config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.02, velocity, acceleration, jerk);
	}
	
	public Trajectory generate() {
		trajectory = Pathfinder.generate(points, config);
		return trajectory;
	}
	
	public void setWheelBaseWidth(double width){
		wheelBaseWidth = width;
	}
	
	public void setTankDrive() {
		modifier = new TankModifier(trajectory).modify(wheelBaseWidth);
		left  =  modifier.getLeftTrajectory(); 
		right = modifier.getRightTrajectory();
	}
	
	public void getValues(Trajectory trajectory) {
		for(int i = 0; i < trajectory.length(); i++) {
			Trajectory.Segment seg = trajectory.get(i);
			System.out.printf("%f,%f,%f,%f,%f,%f,%f,%f\n", 
			        seg.dt, seg.x, seg.y, seg.position, seg.velocity, 
			        seg.acceleration, seg.jerk, seg.heading);
		}
	}



}


