package org.usfirst.frc4930.Timrek.sensors;

import org.usfirst.frc4930.Timrek.Constants;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Cameras extends Subsystem
{

  private UsbCamera firstCam;
  private UsbCamera secondCam;
  private VideoSink server;
  public boolean currentCam;

  protected void initDefaultCommand() {
    // TODO Auto-generated method stub

  }

  public Cameras() {
    CameraServer cs = CameraServer.getInstance();
    // start usb cameras
    firstCam = cs.startAutomaticCapture("cam0", 0);
    secondCam = cs.startAutomaticCapture("cam1", 1);
    // settings for cam 1
    firstCam.setResolution(Constants.LENGTH, Constants.HEIGHT);
    firstCam.setFPS(Constants.FRAMES_PER_SECOND);
    // settings for cam 2
    secondCam.setResolution(Constants.LENGTH, Constants.HEIGHT);
    secondCam.setFPS(Constants.FRAMES_PER_SECOND);
    cs.addCamera(firstCam);
    cs.addCamera(secondCam);
    server = cs.getServer();
    server.setSource(firstCam);

  }

  public void toggleCamera() {
    if (currentCam) {
      server.setSource(secondCam);
      SmartDashboard.putString("c", "Second Camera");
    } else {
      server.setSource(firstCam);
      SmartDashboard.putString("c", "First Camera");
    }
    currentCam = !currentCam;

  }
}
