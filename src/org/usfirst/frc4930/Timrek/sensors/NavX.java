package org.usfirst.frc4930.Timrek.sensors;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class NavX implements PIDSource {
	
	private AHRS navX;
	private PIDSourceType source;
	
	public NavX(AHRS ahrs, PIDSourceType sourceType){
		this.navX = ahrs;
		this.source = sourceType;
	}
	
	public double getYaw() {
		return navX.getYaw() % 360;
	}
	
	public double getAngle() {
		double angle = navX.getAngle();
		if(angle < -180 ){
			angle = 360 + angle;
		}
		else if (angle > 180) {
			angle = 360 - angle;
			angle *= -1;
		}
		return angle;
	}
	
	public boolean atAngle(double angle) {
		if(getAngle() < angle + 5 && getAngle() > angle -5){
			return true;
		}
			return false;
	}
	
	public void reset() {
		navX.reset();
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub
		this.source = pidSource;
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return this.source;
	}

	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return getAngle();
	}

}
