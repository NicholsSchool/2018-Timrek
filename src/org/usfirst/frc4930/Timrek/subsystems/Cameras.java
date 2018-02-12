package org.usfirst.frc4930.Timrek.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Cameras extends Subsystem
{

  private UsbCamera cam;

  protected void initDefaultCommand() {
    // TODO Auto-generated method stub

  }

  public Cameras() {
    CameraServer cs = CameraServer.getInstance();
    // start usb cameras
    cam = cs.startAutomaticCapture("cam0", 0);
    // settings for cam 0
    cam.setResolution(320, 240);
    cam.setFPS(5);

    cs.addCamera(cam);
  }
}
