package org.usfirst.frc4930.Timrek.subsystems;

import org.usfirst.frc4930.Timrek.RobotMap;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Dial extends Subsystem{
	
	private AnalogPotentiometer dial;
	
	public Dial(AnalogPotentiometer pot){
		dial = pot;
	}
	
	  public int getDialNumber() {
		  
		  //NEEDS TO BE TESTED
		    double autoSwitch = dial.get();
		    double dialNum = (autoSwitch + 7) / (360 / 11);
		    double floor = Math.floor(dialNum);
		    if ((dialNum - floor) >= 0.5) {
		      dialNum = Math.ceil(dialNum);
		    } else {
		      dialNum = floor;
		    }
		    return (int) dialNum;
		  }

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
