package org.usfirst.frc4930.Timrek.autonomous;



import org.usfirst.frc4930.Timrek.Robot;
import org.usfirst.frc4930.Timrek.RobotMap;
import org.usfirst.frc4930.Timrek.commands.Outtake;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class PickAuto extends CommandGroup{
	private String gameData;
	private int dialNum;
	private int delay;
	private boolean goLeft;
	private boolean goToSwitch;
	
	public PickAuto() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		dialNum = Robot.positionDial.getPosition();
		delay = Robot.timeDelayDial.getPosition();
		goToSwitch = true; //Only Because Timrek does not have autoSwitch on it
		double distanceToPlatform;
		
		if(goToSwitch){
			goLeft = gameData.charAt(0) == 'L';
			distanceToPlatform = 10.5;
		}
		else {
			goLeft = gameData.charAt(1) == 'L';
			distanceToPlatform = 24;
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
	//	addParallel(new UpperArmMove(2));
		addSequential(new BBGoToAngle(firstTurn));
		addSequential(new BBGoDistance(horizontalDistance)); 
		addSequential(new BBGoToAngle(secondTurn));
		addSequential(new BBGoDistance(distanceToPlatform));
		addSequential(new BBGoToAngle(thirdTurn));
		addSequential(new Outtake());
		

	}
}
