package org.usfirst.frc4930.Timrek.PathPlanning;

import org.usfirst.frc4930.Timrek.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.MatchType;
import edu.wpi.first.wpilibj.command.Command;

public class PickAuto {
	private Command autoCommand;
	private String gameData;
	//gameData 
	
	
	private boolean isQualification;
	private boolean switchLeft;
	private boolean scaleLeft;
	
	private int position;
	private StartingPath startPosition;
	

	public Command getCommand() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		switchLeft = gameData.charAt(0) == 'L';
		scaleLeft = gameData.charAt(1) == 'L';
		
		isQualification = DriverStation.getInstance().getMatchType() == MatchType.Qualification;
		position = Robot.positionDial.getDialNumber();  //DIAL CODE NEEDS TO BE TESTED
		
		switch (position) {
	      case 1:
	    	  startPosition = new StartingLeft();
	      case 2:
	    	  startPosition = new StartingRight();
	      case 3:
	    	  startPosition = new StartingCenter();
	      default:
	    	  startPosition = null; //I dont know whether I should put null or something else
	    }
		
		if(isQualification){
			if(switchLeft){
				autoCommand = new Auto(startPosition.leftSwitch, isQualification);
				}
			else {
				autoCommand = new Auto(startPosition.rightSwitch, isQualification);
			}
		}
		else {
			if(scaleLeft){
				autoCommand = new Auto(startPosition.leftScale, isQualification);
			}
			else {
				autoCommand = new Auto(startPosition.leftScale, isQualification);
			}
		}
		return autoCommand;
	}

}
