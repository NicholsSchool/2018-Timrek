package org.usfirst.frc4930.Timrek.autonomous;



import org.usfirst.frc4930.Timrek.Constants;
import org.usfirst.frc4930.Timrek.Robot;
import org.usfirst.frc4930.Timrek.RobotMap;
import org.usfirst.frc4930.Timrek.commands.Outtake;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoStartPositions extends CommandGroup{
	private String gameData;
	private int dialNum;
	private int delay;
	private boolean goLeft;
	private boolean goToSwitch;
	
	public AutoStartPositions() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		dialNum = Robot.positionDial.getPosition();
		delay = Robot.timeDelayDial.getPosition();
		goToSwitch = true; //Only Because Timrek does not have autoSwitch on it
		
		double distanceToPlatform;
		int shoulderVal;
		int elbowVal;
		
		if(goToSwitch){
			goLeft = gameData.charAt(0) == 'L';
			distanceToPlatform = 10.5;
			shoulderVal = Constants.AUTO_TO_BAR_SHOULDER_VALUE;
			elbowVal = Constants.AUTO_TO_BAR_ELBOW_VALUE;
		}
		else {
			goLeft = gameData.charAt(1) == 'L';
			distanceToPlatform = 24;
			shoulderVal = Constants.AUTO_FULL_RAISE_SHOULDER_VALUE;
			elbowVal = Constants.AUTO_FULL_RAISE_ELBOW_VALUE;
		}
		
		double firstTurn;
		double secondTurn;
		double thirdTurn;
		double horizontalDistance;
		
		if(goLeft){
			firstTurn = -90;
			secondTurn = 90;
			thirdTurn = 90;
			horizontalDistance = dialNum *2; //I think I should subtract by some value, so it doesn't hit the wall
		
		} 
		else {
			firstTurn = 90;
			secondTurn = -90;
			thirdTurn = -90;
			horizontalDistance = 20- (dialNum *2); //I think I should subtract by some value, so it doesn't hit the wall
			//the logic for this value^ should change;
		}
		if(dialNum == 0 || dialNum ==10){
			firstTurn = 0;
			secondTurn = 0;
		}
		
		addSequential(new Delay(delay));
		addSequential(new BBGoDistance(2.5));
		addSequential(new BBGoToAngle(firstTurn));
		addSequential(new BBGoDistance(horizontalDistance)); 
		addSequential(new BBGoToAngle(secondTurn));
		addSequential(new BBGoDistance(distanceToPlatform));
		addSequential(new ArmToPosition(elbowVal, shoulderVal));
		addSequential(new BBGoToAngle(thirdTurn));
		addSequential(new Outtake());
		

	}
}
