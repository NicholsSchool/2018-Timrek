package org.usfirst.frc4930.Timrek.PathPlanning;

import org.usfirst.frc4930.Timrek.Robot;
import org.usfirst.frc4930.Timrek.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

public class Path extends Subsystem {
	
	private int numPoints = 0;
	private int totalPoints;
	
	private Waypoint[] points;  //Find out if the size 10 could be a problem
	
	private Trajectory.Config config; 
	private Trajectory trajectory; 
	
	private double wheelBaseWidth  = 0.6096;
	private double wheelDiameter = 0.09525;
	private TankModifier modifier;

	private double velocity;
	
	public Trajectory left;       
	public Trajectory right;  
	
	private EncoderFollower leftEncoder;
	private EncoderFollower rightEncoder;
		
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public Path(int totalPoints){
		this.totalPoints = totalPoints;
		this.points = new Waypoint[totalPoints];
	}
	
	
	public void addPoint(Waypoint point) {
		if(this.numPoints < this.totalPoints){
		this.points[numPoints] = point;
		this.numPoints ++;
		}
	}
	
	public void config(double velocity, double acceleration, double jerk){
		this.config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH,
				0.02, velocity, acceleration, jerk);
		this.velocity = velocity;
	}
	
	public Trajectory generate() {
		this.trajectory = Pathfinder.generate(this.points, this.config);
		return trajectory;
	}
	

	
	public void setTankDrive() {
		this.modifier = new TankModifier(this.trajectory).modify(this.wheelBaseWidth);
		this.left  =  this.modifier.getLeftTrajectory(); 
		this.right = this.modifier.getRightTrajectory();
	}
	
	public void getValues(Trajectory trajectory) {
		for(int i = 0; i < trajectory.length(); i++) {
			Trajectory.Segment seg = trajectory.get(i);
			System.out.printf("%f,%f,%f,%f,%f,%f,%f,%f\n", 
			        seg.dt, seg.x, seg.y, seg.position, seg.velocity, 
			        seg.acceleration, seg.jerk, seg.heading);
		}
	}
	
	public void configEncoders() {
		this.leftEncoder = new EncoderFollower(this.left);
	    this.rightEncoder = new EncoderFollower(this.right);
		
		this.leftEncoder.configureEncoder(RobotMap.lDrvMSTR.getSelectedSensorPosition(0), 1600, wheelDiameter);
		this.rightEncoder.configureEncoder(RobotMap.rDrvMSTR.getSelectedSensorPosition(0), 1600, wheelDiameter);
		
		this.leftEncoder.configurePIDVA(0.1, 0.0, 0.0, 1 / velocity, 0);
		this.rightEncoder.configurePIDVA(0.1, 0.0, 0.0, 1 / velocity, 0);
	}
	
	public void run() {

		double output = leftEncoder.calculate(RobotMap.lDrvMSTR.getSelectedSensorPosition(0));
		double l = leftEncoder.calculate(RobotMap.lDrvMSTR.getSelectedSensorPosition(0));
		double r = rightEncoder.calculate(RobotMap.rDrvMSTR.getSelectedSensorPosition(0));
		
		double gyroHeading = RobotMap.ahrs.getAngle();
		double desiredHeading = Pathfinder.r2d(leftEncoder.getHeading());

		double angleDifference = Pathfinder.boundHalfDegrees(desiredHeading - gyroHeading);
		double turn = 0.8 * (-1.0/80.0) * angleDifference;
		
		Robot.driveTrain.move(l + turn, r - turn);
	}

}


