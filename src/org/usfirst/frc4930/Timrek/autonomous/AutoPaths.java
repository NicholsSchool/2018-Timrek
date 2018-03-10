package org.usfirst.frc4930.Timrek.autonomous;

import org.usfirst.frc4930.Timrek.Constants;
import org.usfirst.frc4930.Timrek.Robot;
import org.usfirst.frc4930.Timrek.RobotMap;
import org.usfirst.frc4930.Timrek.commands.ClawClose;
import org.usfirst.frc4930.Timrek.commands.ClawOpen;
import org.usfirst.frc4930.Timrek.commands.HighGear;
import org.usfirst.frc4930.Timrek.commands.Intake;
import org.usfirst.frc4930.Timrek.commands.LowGear;
import org.usfirst.frc4930.Timrek.commands.Outtake;
import org.usfirst.frc4930.Timrek.subsystems.Arm;

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
		preference1 = Robot.preference1Dial.getPosition();
		preference2 = Robot.preference2Dial.getPosition();
		switchLeft = gameData.charAt(0) == 'L';
		scaleLeft = gameData.charAt(1) == 'L';
    	startingLeft = RobotMap.toggleSwitch.get();  
    	
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
		case 9: middlePathHighGear(switchLeft);
				break;
		case 10: middlePath(switchLeft);
				break;
		default: driveForward();
				break;
		}
	}
	
	private boolean checkPreference(int pathNum) {
		boolean runPreference;
		
		if(pathNum == 0 || pathNum == 10 || pathNum == 9){
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
			 * 	      /
			 *       .    
			 * 		 .         
			 * 		/  {}------{} 
			 *     /  
			 *    / |
			 *   *
			 */
		}
		else {
			turn = -45;
			/*
			 * 		   {}------{}
			 * 	                 \
			 *                    .
			 * 		              .
			 * 		   {}------{}  \
			 *                      \ 
			 *                       \
			 *                        *
			 */
		}
		addSequential(new BBGoDistance(22, false, 0.75));

	    addSequential(new ArmToPosition(Arm.SCALE_POSITION, 1.0));
		addSequential(new Outtake(1, 0.7));
	}
	
	//4 WORKING
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
	
	//9 
	private void middlePathHighGear(boolean goLeft){
		double turn;
		double secondTurn;
		if(goLeft){
			turn = -30;
			secondTurn = 27;
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
			secondTurn = -27;
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
		addSequential(new BBGoToAngle(turn, true));
		addSequential(new HighGear());
		addSequential(new BBGoDistance(9, true, 1.0));
		addSequential(new BBGoToAngle(secondTurn));
		addSequential(new BBGoDistance(0.75));
		addSequential(new Outtake(5, 0.6));
	}
	
	//10 WORKING
	private void middlePath(boolean goLeft) {
		double turn;
		double secondTurn;
		if(goLeft){
			turn = -30;
			secondTurn = 27;
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
			secondTurn = -27;
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
		addSequential(new BBGoDistance(7.8));
		addSequential(new BBGoToAngle(secondTurn));
		addSequential(new BBGoDistance(0.5));
		addSequential(new Outtake(1, 0.8));
	}
	
	
}

