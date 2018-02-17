package org.usfirst.frc4930.Timrek.sensors;

import org.usfirst.frc4930.Timrek.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Encoder extends Subsystem
{

  public Encoder() {
    DriveReset();
  }

  public void DriveReset() {
    RobotMap.lDrvMSTR.setSelectedSensorPosition(0, 0, 0);
    RobotMap.rDrvMSTR.setSelectedSensorPosition(0, 0, 0);
  }

  public void ArmReset() {
    RobotMap.lShoulder.setSelectedSensorPosition(0, 0, 0);
    RobotMap.lElbow.setSelectedSensorPosition(0, 0, 0);
    // RobotMap.dropWhl.setSelectedSensorPosition(0, 0, 0);
  }

  public int lDrvMSTR_Raw() {
    return RobotMap.lDrvMSTR.getSelectedSensorPosition(0);
  }

  public int rDrvMSTR_Raw() {
    return RobotMap.rDrvMSTR.getSelectedSensorPosition(0);
  }

  public int lShoulder_Raw() {
    return RobotMap.lShoulder.getSelectedSensorPosition(0);
  }

  public int lElbow_Raw() {
    return RobotMap.lElbow.getSelectedSensorPosition(0);
  }

  public int dropWhl_Raw() {
    return RobotMap.dropWhl.getSelectedSensorPosition(0);
  }

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
