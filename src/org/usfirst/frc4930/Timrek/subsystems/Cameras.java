package org.usfirst.frc4930.Timrek.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Cameras extends Subsystem{
	
	private UsbCamera cam;
	
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public Cameras() {
		// start usb cameras
		cam = CameraServer.getInstance().startAutomaticCapture("cam0", 0);
		// settings for cam 0
		cam.setResolution(640, 480);
		cam.setFPS(24);
	}
}
