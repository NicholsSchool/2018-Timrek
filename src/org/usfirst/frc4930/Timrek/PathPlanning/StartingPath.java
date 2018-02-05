package org.usfirst.frc4930.Timrek.PathPlanning;

public class StartingPath {
	public Path leftSwitch;
	public Path leftScale;
	public Path rightSwitch;
	public Path rightScale;
	
	public double velocity = 1.7;
	public double acceleration = 2.0;
    public double jerk = 60.0;
    
    public void size(int lSwitch, int lScale, int rSwitch, int rScale){
    	this.leftSwitch = new Path(lSwitch);
    	this.leftScale = new Path(lScale);
    	this.rightSwitch = new Path(rSwitch);
    	this.rightScale = new Path(rScale);
    } 
    
    public void init() {
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
		
		leftSwitch.configEncoders();
		leftScale.configEncoders();
		rightSwitch.configEncoders();
		rightScale.configEncoders();
    }
}
