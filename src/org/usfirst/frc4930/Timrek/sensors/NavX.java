package org.usfirst.frc4930.Timrek.sensors;

import com.kauailabs.navx.frc.AHRS;

public class NavX {
	
	private AHRS navX;
	
	public NavX(AHRS ahrs){
		this.navX = ahrs;
	}
	
	public double getYaw() {
		return navX.getYaw() % 360;
	}
	
	public double getAngle() {
		return navX.getAngle() % 360;
	}
	
	public void reset() {
		navX.reset();
	}

}
