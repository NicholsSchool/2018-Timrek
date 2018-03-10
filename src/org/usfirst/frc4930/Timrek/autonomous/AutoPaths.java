package org.usfirst.frc4930.Timrek.autonomous;

import org.usfirst.frc4930.Timrek.Constants;
import org.usfirst.frc4930.Timrek.Robot;
import org.usfirst.frc4930.Timrek.commands.ClawClose;
import org.usfirst.frc4930.Timrek.commands.ClawOpen;
import org.usfirst.frc4930.Timrek.commands.Intake;
import org.usfirst.frc4930.Timrek.commands.Outtake;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoPaths extends CommandGroup{
	private String gameData;
	private int preference1;
	private int preference2;
	
	private boolean runPreference1;
	private boolean runPreference2;
	
	private boolean switchLeft;
	private boolean scaleLeft;
	private boolean startingLeft;
		
	public AutoPaths() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		preference1 = Robot.positionDial.getPosition();
		preference2 = Robot.timeDelayDial.getPosition();
		switchLeft = gameData.charAt(0) == 'L';
		scaleLeft = gameData.charAt(1) == 'L';
//		startingLeft = sideLSwitch.get()  // Timrek does not have this limit switch, defaulting to true
		startingLeft = true;
		runPreference1 = checkPreference(preference1);
		runPreference2 = checkPreference(preference2);
		
		
		System.out.println("RUNNING AUTO");
		
		if(runPreference1){
			runPath(preference1);
		}
		else if(runPreference2){
			runPath(preference2);
		}
		else {
			driveForward();
		}
	}
	
	private void runPath(int pathNum){
		switch (pathNum){
		case 0: driveForward();
				break;
		case 1: correspondingSwitch(startingLeft);
				break;
		case 2: leftTwoCubesSwitch(startingLeft);
				break;
		case 3: correspondingScale(startingLeft);
				break;
		case 4: oppositeSideSwitchFromBack(startingLeft);
				break;
		case 5: oppositeSideScale(startingLeft);
				break;
		case 10: middlePath(switchLeft);
				break;
		default: driveForward();
				break;
		}
	}
	
	private boolean checkPreference(int pathNum) {
		boolean runPreference;
		
		if(pathNum == 0 || pathNum == 10){
			runPreference = true;
		}
		else if( pathNum <= 2){
			runPreference = (switchLeft == startingLeft);
		}
		else if (pathNum == 3){
			runPreference = (scaleLeft == startingLeft);
		}
		else if (pathNum == 4){
			runPreference = (switchLeft != startingLeft);
		}
		else if (pathNum == 5){
			runPreference = (scaleLeft != startingLeft);
		}
		else {
			runPreference = false;
		}
		return runPreference;
	}
	
	//0 WORKING
	private void driveForward() {
		addSequential(new BBGoDistance(10));
	}
	
	//1 WORKING
	private void correspondingSwitch(boolean startingLeft) {
		System.out.println("RUNNING CORRESPONDING SWITCH");
		double turn;
		if(startingLeft){
			turn = 80;
			/*
			 * 		   {}------{}
			 * 	     
			 *           
			 * 		          
			 * 		 ._{}------{} 
			 *       |
			 *       |
			 *       *
			 */
		}
		else {
			turn = -80;
			/*
			 * 		   {}------{}
			 * 	                 
			 *                    
			 * 		              
			 * 		   {}------{}_.
			 *                    | 
			 *                    |
			 *                    *
			 */
		}
		//Starting from Left side
		
		addSequential(new BBGoDistance(12));
	//	addSequential(new BBGoDistance(5));
		
		addSequential(new BBGoToAngle(turn));
		
	//	addSequential(new BBGoDistance(2.5));
		addSequential(new BBGoDistance(0.5));
		
		addSequential(new Outtake(1, 0.4));
	}

	//2 IN TEST PHASE
	private void leftTwoCubesSwitch(boolean startingLeft) {
		double turn;
		if(startingLeft){
			turn = -60;
		}
		else {
			turn = 60;
		}
		
		addSequential(new BBGoDistance(-18));
		addSequential(new BBGoToAngle(turn));
		addSequential(new BBGoDistance(3));
		addSequential(new Outtake(1, 1.0));
	// addSequential(new ArmToPosition(, )); // Arm Down
		addSequential(new Delay(3));
		addSequential(new ClawOpen());
		addParallel(new Intake(3, 3.0));
		addSequential(new BBGoDistance(0.5));
		addSequential(new Delay(3));
		addSequential(new ClawClose());
		addSequential(new Delay(3));
		addSequential(new BBGoDistance(-0.5));
		addSequential(new Delay(3));
		// addSequential(new ArmToPosition(, )); // Arm Switch State
		addSequential(new Outtake(3, 1.0));
	}
	
	//3 WORKING
	private void correspondingScale(boolean startingLeft) {
		double turn;
		if(startingLeft){
			turn = 45;
			/*
			 * 		   {}------{}
			 * 	     /
			 *       |    
			 * 		 |         
			 * 		 | {}------{} 
			 *       |
			 *       |
			 *       *
			 */
		}
		else {
			turn = -45;
			/*
			 * 		   {}------{}
			 * 	                 \
			 *                    |
			 * 		              |
			 * 		   {}------{} |
			 *                    | 
			 *                    |
			 *                    *
			 */
		}
		addSequential(new BBGoDistance(23));
	//	addSequential(new BBGoDistance(7));
		
		
//		addSequential(new BBGoToAngle(turn));
//		addSequential(new BBGoDistance(1.8));
		// addSequential(new ArmToPosition(Constants.AUTO_FULL_RAISE_ELBOW_VALUE, Constants.AUTO_FULL_RAISE_SHOULDER_VALUE));
		addSequential(new Outtake(1, 0.9));
	}
	
	//4 NEEDS TO BE TESTED
	private void oppositeSideSwitchFromBack(boolean startingLeft){
		double turn;
		if(startingLeft){
			turn = 80;
			/*
			 * 		   {}------{}
			 * 	     
			 *       .__________.    
			 * 		 |          |
			 * 		 | {}------{} 
			 *       |
			 *       |
			 *       *
			 */
		}
		else {
			turn = -80;
			/*
			 * 		   {}------{}
			 * 	                 
			 *         .__________.
			 * 		   |          |
			 * 		   {}------{} |
			 *                    | 
			 *                    |
			 *                    *
			 */
		}
		

		
		addSequential(new BBGoDistance(18));
		addSequential(new BBGoToAngle(turn));
		addSequential(new BBGoDistance(14));
		addSequential(new BBGoToAngle(turn));
		addSequential(new BBGoDistance(0.5));
		addSequential(new Outtake(1, 0.75));
	}
	
	//5 NEEDS TO BE TESTED
	private void oppositeSideScale(boolean startingLeft) {
		double turn;
		if(startingLeft){
			turn = Constants.TURN_RIGHT;
			/*
			 * 		   {}------{}
			 * 	                |
			 *       .__________.    
			 * 		 |         
			 * 		 | {}------{} 
			 *       |
			 *       |
			 *       *
			 */
		}
		else {
			turn = Constants.TURN_LEFT;
			/*
			 * 		   {}------{}
			 * 	       |          
			 *         .__________.
			 * 		              |
			 * 		   {}------{} |
			 *                    | 
			 *                    |
			 *                    *
			 */
		}
		

		
		addSequential(new BBGoDistance(14));
		addSequential(new BBGoToAngle(turn));
		addSequential(new BBGoDistance(11));
		addSequential(new BBGoToAngle(-turn));
		addSequential(new BBGoDistance(2));
		addSequential(new Outtake(1, 0.8));
	}
	
	//10 NEEDS TO BE TESTED
	private void middlePath(boolean goLeft) {
		double turn;
		double secondTurn;
		if(goLeft){
			turn = -30;
			secondTurn = 22;
			/*
			 * 		   {}------{}
			 * 	     
			 *           
			 * 		          
			 * 		   {}------{} 
			 *           \
			 *            \
			 *             *
			 */
		}
		else {
			turn = 30;
			secondTurn = -22;
			/*
			 * 		   {}------{}
			 * 	     
			 *           
			 * 		          
			 * 		   {}------{} 
			 *                / 
			 *               /
			 *             *
			 */
		}
		addSequential(new BBGoDistance(0.5));
		addSequential(new BBGoToAngle(turn));
		addSequential(new BBGoDistance(6.8));
		addSequential(new BBGoToAngle(secondTurn));
		addSequential(new Outtake(1, 0.9));
	}
	
	private void middlePath2Cube(){
		
	}
	
}

