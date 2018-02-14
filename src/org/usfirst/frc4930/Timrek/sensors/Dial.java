package org.usfirst.frc4930.Timrek.sensors;



import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Dial extends Subsystem{

	AnalogPotentiometer dial;
	
	public Dial(AnalogPotentiometer pot) {
		dial = pot;
	}
	
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public int getPosition() {
	    double autoSwitch = dial.get();
	    int change;
	    if(autoSwitch > 265){
	    	change = -7;
	    }
	    else {
	    	change = 5;
	    }
	    
	    double dialNum = (autoSwitch + change) / (360 / 10);
	    double floor = Math.floor(dialNum);
	    if ((dialNum - floor) >= 0.5) {
	      dialNum = Math.ceil(dialNum);
	    } else {
	      dialNum = floor;
	    }
	    return (int) dialNum;
	    

	}
	
}
