package org.usfirst.frc4930.Timrek.sensors;

import org.usfirst.frc4930.Timrek.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class LimitSwitch extends Subsystem{

	  public static boolean lowerArmDown;
	  // public static boolean upperArmDown;
	 //  public static boolean hasCube;
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public LimitSwitch() {
	    lowerArmDown = RobotMap.lArmDownLSwitch.get();
	    //  upperArmDown = RobotMap.uArmDownLSwitch.get();
	    //  hasCube = RobotMap.clawLSwitch.get();
	}
	
	public void checkForChange() {
		if(lowerArmDown != RobotMap.lArmDownLSwitch.get()){
			lowerArmDown = !lowerArmDown;
		}
//		if(	if(upperArmDown != RobotMap.uArmDownLSwitch.get()){
//			upperArmDown = !upperArmDown;
//		}
//		if(hasCube != RobotMap.clawLSwitch.get()){
//			hasCube = !hasCube;
//		}
	}
}
