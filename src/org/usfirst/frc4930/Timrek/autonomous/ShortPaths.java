package org.usfirst.frc4930.Timrek.autonomous;

import org.usfirst.frc4930.Timrek.Constants;
import org.usfirst.frc4930.Timrek.Robot;
import org.usfirst.frc4930.Timrek.commands.ClawClose;
import org.usfirst.frc4930.Timrek.commands.ClawOpen;
import org.usfirst.frc4930.Timrek.commands.Intake;
import org.usfirst.frc4930.Timrek.commands.Outtake;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShortPaths extends CommandGroup{
	private String gameData;
	private int preference1;
	private int preference2;
	
	private boolean runPreference1;
	private boolean runPreference2;
	
	private boolean switchLeft;
	private boolean scaleLeft;
	private boolean startingLeft;
	
	
	
	public ShortPaths() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		preference1 = Robot.positionDial.getPosition();
		preference2 = Robot.timeDelayDial.getPosition();
		switchLeft = gameData.charAt(0) == 'L';
		scaleLeft = gameData.charAt(1) == 'L';
//		startingLeft = sideLSwitch.get()  // Timrek does not have this limit switch, defaulting to true
		startingLeft = true;
		runPreference1 = checkPreference(preference1);
		runPreference2 = checkPreference(preference2);
		
		if(runPreference1){
			runPath(preference1);
		}
		else if((!runPreference1) && runPreference2 ){
			runPath(preference2);
		}
		else {
			driveForward();
		}
		
		System.out.println("RUNNING AUTO");
		correspondingScale(scaleLeft);
	}
	
	private void runPath(int pathNum){
		switch (pathNum){
		case 0: driveForward();
				break;
		case 1: correspondingSwitch(startingLeft);
				break;
		case 2: leftTwoCubesSwitch();
				break;
		case 3: correspondingScale(startingLeft);
				break;
		case 4: oppositeSideSwitchFromBack();
				break;
		case 5: oppositeSideScale();
				break;
		default: driveForward();
				break;
		}
	}
	
	private boolean checkPreference(int pathNum) {
		boolean runPreference;
		
		if( pathNum <= 2){
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
	
	//0
	private void driveForward() {
		addSequential(new BBGoDistance(10));
	}
	
	//1 WORKING
	private void correspondingSwitch(boolean goLeft) {
		double turn;
		if(goLeft){
			turn = Constants.TURN_RIGHT;
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
			turn = Constants.TURN_LEFT;
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
		
	//	addSequential(new BBGoDistance(11));
		addSequential(new BBGoDistance(5));
		
		addSequential(new BBGoToAngle(turn));
		
		//addSequential(new BBGoDistance(2.5));
		addSequential(new BBGoDistance(1));
		
		addSequential(new Outtake(3, 0.7));
	}

	//2
	private void leftTwoCubesSwitch() {
//		addSequential(new BBGoDistance(-18));
//		addSequential(new BBGoToAngle(-60));
//		addSequential(new BBGoDistance(3));
//		addSequential(new Outtake(1, 1.0));
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
	private void correspondingScale(boolean goLeft) {
		double turn;
		if(goLeft){
			turn = 55;
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
			turn = -55;
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
	//	addSequential(new BBGoDistance(21));
		addSequential(new BBGoDistance(7));
		
		
		addSequential(new BBGoToAngle(turn));
		addSequential(new BBGoDistance(1.8));
		// addSequential(new ArmToPosition(Constants.AUTO_FULL_RAISE_ELBOW_VALUE, Constants.AUTO_FULL_RAISE_SHOULDER_VALUE));
		addSequential(new Outtake(3, 0.9));
	}
	

	//4
	private void oppositeSideSwitchFromBack(){
	
		/*
		 * 		   {}------{}
		 * 	     
		 *       ._________.    
		 * 		 |         |
		 * 		 | {}------{} 
		 *       |
		 *       |
		 *       *
		 */
	}
	//5
	private void oppositeSideScale() {
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
	
	private void middlePath() {
		
	}
	
	private void middlePath2Cube(){
		
	}
	
	private void leftPathEndOutside() {
		//regardless of any position or boolean, move forward 
		addSequential(new BBGoDistance(Constants.DEFAULT_FORWARD));
		
		
		if(scaleLeft){
			//If scale is left, then go forward, because you are already on left side, then turn right to scale.
			/*
			 * 		 ._{}------{}
			 * 	     |
			 *       |    
			 * 		 |
			 * 		 | {}------{} 
			 *       |
			 *       |
			 *       *
			 */
			
			addSequential(new BBGoDistance(Constants.VERTICAL_DISTANCE_TO_SCALE_OUTSIDE)); // feet value needs to be tested
		//	addSequential(new ArmToPosition(Constants.AUTO_FULL_RAISE_ELBOW_VALUE, Constants.AUTO_FULL_RAISE_SHOULDER_VALUE));
			addSequential(new BBGoToAngle(Constants.TURN_RIGHT));
			addSequential(new Outtake(3, 0.8));
		}
		else {
			//If scale is right, then turn right, go forward, turn left, go forward to scale, turn left to scale.
			/*
			 * 		   {}------{}_.
			 * 			          |
			 *                    |
			 * 		              |
			 * 		   {}------{} |
			 *       .____________.
			 *       |
			 *       *
			 */
			
			
			addSequential(new BBGoToAngle(Constants.TURN_RIGHT));
			addSequential(new BBGoDistance(Constants.HORIZONTAL_ACROSS_FIELD)); //feet value needs to be tested
			addSequential(new BBGoToAngle(Constants.TURN_LEFT));
			addSequential(new BBGoDistance(Constants.VERTICAL_DISTANCE_TO_SCALE_OUTSIDE)); // feet value needs to be tested
			addSequential(new BBGoToAngle(Constants.TURN_LEFT));
		}
	}
	
	private void leftPathEndInside() {
		//regardless of any position or boolean, move forward 
		addSequential(new BBGoDistance(Constants.DEFAULT_FORWARD));
		
		
		if(scaleLeft){
			//If scale is left, then go forward, because you are already on left side
			// then go right, forward, turn left, and go forward to scale
			/*
			 * 		   {}------{}
			 * 		    |
			 *       .__.
			 * 		 |
			 * 		 | {}------{} 
			 *       |
			 *       |
			 *       *
			 */
			
			addSequential(new BBGoDistance(Constants.VERTICAL_DISTANCE_TO_SCALE_INSIDE)); // feet value needs to be tested
			addSequential(new BBGoToAngle(Constants.TURN_RIGHT));
			addSequential(new BBGoDistance(Constants.HORIZONTAL_SCALE_INSIDE)); // feet value needs to be tested
			addSequential(new BBGoToAngle(Constants.TURN_LEFT));
			addSequential(new BBGoDistance(Constants.FORWARD_TO_SCALE_FROM_INSIDE));  // feet value needs to be tested
		}
		else {
			//If scale is right, then turn right, go forward, turn left, go forward
			// turn left, go forward, turn right, and go forward to scale
			/*
			 * 		   {}------{}
			 * 			       |
			 *                 .__.
			 * 		              |
			 * 		   {}------{} |
			 *       .____________.
			 *       |
			 *       *
			 */
			
			addSequential(new BBGoToAngle(Constants.TURN_RIGHT));
			addSequential(new BBGoDistance(Constants.HORIZONTAL_ACROSS_FIELD)); //feet value needs to be tested
			addSequential(new BBGoToAngle(Constants.TURN_LEFT));
			addSequential(new BBGoDistance(Constants.VERTICAL_DISTANCE_TO_SCALE_INSIDE)); // feet value needs to be tested
			addSequential(new BBGoToAngle(Constants.TURN_LEFT));
			addSequential(new BBGoDistance(Constants.HORIZONTAL_SCALE_INSIDE)); // feet value needs to be tested
			addSequential(new BBGoToAngle(Constants.TURN_RIGHT));
			addSequential(new BBGoDistance(Constants.FORWARD_TO_SCALE_FROM_INSIDE));  // feet value needs to be tested
		}
	}
	
	private void middlePathEndOutside() {
		//regardless of any position or boolean, move forward
		addSequential(new BBGoDistance(Constants.DEFAULT_FORWARD));
		
		if(scaleLeft){
			//if Scale is left, turn left, go forward, turn right, go forward to scale, turn right to face scale
			/*
			 * 		 ._{}------{}
			 * 	     |
			 *       |    
			 * 		 |
			 * 		 | {}------{} 
			 *       ._____.
			 *             |
			 *             *
			 */
			
			addSequential(new BBGoToAngle(Constants.TURN_LEFT));
			addSequential(new BBGoDistance(9));  // feet value needs to be tested
			addSequential(new BBGoToAngle(Constants.TURN_RIGHT));
			addSequential(new BBGoDistance(Constants.VERTICAL_DISTANCE_TO_SCALE_OUTSIDE));
			addSequential(new BBGoToAngle(Constants.TURN_RIGHT));
		}
		
		else {
			//If scale is right, turn right, go forward, turn left, go forward to scale, turn left to face scale
			/*
			 * 		   {}------{}_.
			 * 					  |	
			 *       		      |
			 * 		 	 	      |
			 * 		   {}------{} |
			 *       	   .______.		  
			 *             |
			 *             *
			 */
			addSequential(new BBGoToAngle(Constants.TURN_RIGHT));
			addSequential(new BBGoDistance(9));  // feet value needs to be tested
			addSequential(new BBGoToAngle(Constants.TURN_LEFT));
			addSequential(new BBGoDistance(Constants.VERTICAL_DISTANCE_TO_SCALE_OUTSIDE));
			addSequential(new BBGoToAngle(Constants.TURN_LEFT));
		}
	}
	
	private void middlePathEndInside() {
		//regardless of any position or boolean, move forward 
		addSequential(new BBGoDistance(Constants.DEFAULT_FORWARD));
		
		if(scaleLeft){
			//if scale is left, turn left, go forward, turn right, go forward but not all the way to scale,
			//then turn right, forward, left, forward to scale
			/*
			 * 		   {}------{}
			 * 	        |
			 *       .__.    
			 * 		 |
			 * 		 | {}------{} 
			 *       ._____.
			 *             |
			 *             *
			 */
			
			addSequential(new BBGoToAngle(Constants.TURN_LEFT));
			addSequential(new BBGoDistance(9));  // feet value needs to be tested
			addSequential(new BBGoToAngle(Constants.TURN_RIGHT));
			addSequential(new BBGoDistance(Constants.VERTICAL_DISTANCE_TO_SCALE_INSIDE));
			addSequential(new BBGoToAngle(Constants.TURN_RIGHT));
			addSequential(new BBGoDistance(Constants.HORIZONTAL_SCALE_INSIDE)); // feet value needs to be tested
			addSequential(new BBGoToAngle(Constants.TURN_LEFT));
			addSequential(new BBGoDistance(Constants.FORWARD_TO_SCALE_FROM_INSIDE));  // feet value needs to be tested
		}
		else {
			//if scale is right, turn right, go forward, turn left, go forward but not all the way to scale,
			// then turn left, forward, left, forward to scale
			/*
			 * 		   {}------{}
			 * 				    |	
			 *       		    ._.
			 * 		 	 	      |
			 * 		   {}------{} |
			 *       	   .______.		  
			 *             |
			 *             *
			 */
			addSequential(new BBGoToAngle(Constants.TURN_RIGHT));
			addSequential(new BBGoDistance(9));  // feet value needs to be tested
			addSequential(new BBGoToAngle(Constants.TURN_LEFT));
			addSequential(new BBGoDistance(Constants.VERTICAL_DISTANCE_TO_SCALE_INSIDE));
			addSequential(new BBGoToAngle(Constants.TURN_LEFT));
			addSequential(new BBGoDistance(Constants.HORIZONTAL_SCALE_INSIDE)); // feet value needs to be tested
			addSequential(new BBGoToAngle(Constants.TURN_RIGHT));
			addSequential(new BBGoDistance(Constants.FORWARD_TO_SCALE_FROM_INSIDE));  // feet value needs to be tested
		}
	}
	
	private void rightPathEndOutside() {
		//regardless of any position or boolean, move forward 
		addSequential(new BBGoDistance(Constants.DEFAULT_FORWARD));
		
		
		if(!scaleLeft){
			//If scale is right, then go forward, because you are already on right side, then turn left to scale.
			/*
			 * 		   {}------{}_.
			 * 					  |	
			 *       		      |
			 * 		 	 	      |
			 * 		   {}------{} |
			 *       			  |
			 *                    |
			 *                    *
			 */
			addSequential(new BBGoDistance(Constants.VERTICAL_DISTANCE_TO_SCALE_OUTSIDE)); // feet value needs to be tested
			addSequential(new BBGoToAngle(Constants.TURN_LEFT));

		}
		else {
			//If scale is left, then turn left, go forward, turn right, go forward to scale, turn right to scale.
			/*
			 * 		 .-{}------{}
			 * 		 |	
			 *       |
			 * 		 |
			 * 		 | {}------{}
			 *       .____________.
			 *                    |
			 *                    *
			 */
			
			
			
			addSequential(new BBGoToAngle(Constants.TURN_LEFT));
			addSequential(new BBGoDistance(Constants.HORIZONTAL_ACROSS_FIELD)); //feet value needs to be tested
			addSequential(new BBGoToAngle(Constants.TURN_RIGHT));
			addSequential(new BBGoDistance(Constants.VERTICAL_DISTANCE_TO_SCALE_OUTSIDE)); // feet value needs to be tested
			addSequential(new BBGoToAngle(Constants.TURN_RIGHT));
		}
	}
	
	private void rightPathEndInside() {
		//regardless of any position or boolean, move forward 
		addSequential(new BBGoDistance(Constants.DEFAULT_FORWARD));
		
		
		if(!scaleLeft){
			//If scale is right, then go forward, because you are already on right side, then turn left, go forward, turn right,
			// and go forward to scale
			/*
			 * 		   {}------{}
			 * 					|	
			 *       		    ._.
			 * 		 	 	      |
			 * 		   {}------{} |
			 *       			  |
			 *                    |
			 *                    *
			 */
			
			
			addSequential(new BBGoDistance(Constants.VERTICAL_DISTANCE_TO_SCALE_INSIDE)); // feet value needs to be tested
			addSequential(new BBGoToAngle(Constants.TURN_LEFT));
			addSequential(new BBGoDistance(Constants.HORIZONTAL_SCALE_INSIDE)); // feet value needs to be tested
			addSequential(new BBGoToAngle(Constants.TURN_RIGHT));
			addSequential(new BBGoDistance(Constants.FORWARD_TO_SCALE_FROM_INSIDE));  // feet value needs to be tested
		}
		else {
			//If scale is left, then turn left, go forward, turn right, go forward, turn right, go forward, turn left
			/*
			 * 		   {}------{}
			 * 			|
			 *       .__.
			 * 		 |
			 * 		 | {}------{}
			 *       .____________.
			 *                    |
			 *                    *
			 */
			
			addSequential(new BBGoToAngle(Constants.TURN_LEFT));
			addSequential(new BBGoDistance(Constants.HORIZONTAL_ACROSS_FIELD)); //feet value needs to be tested
			addSequential(new BBGoToAngle(Constants.TURN_RIGHT));
			addSequential(new BBGoDistance(Constants.VERTICAL_DISTANCE_TO_SCALE_INSIDE)); // feet value needs to be tested
			addSequential(new BBGoToAngle(Constants.TURN_RIGHT));
			addSequential(new BBGoDistance(Constants.HORIZONTAL_SCALE_INSIDE)); // feet value needs to be tested
			addSequential(new BBGoToAngle(Constants.TURN_LEFT));
			addSequential(new BBGoDistance(Constants.FORWARD_TO_SCALE_FROM_INSIDE));  // feet value needs to be tested
		}
	}
}

