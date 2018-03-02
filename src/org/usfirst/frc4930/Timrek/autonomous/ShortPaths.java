package org.usfirst.frc4930.Timrek.autonomous;

import org.usfirst.frc4930.Timrek.Constants;
import org.usfirst.frc4930.Timrek.Robot;
import org.usfirst.frc4930.Timrek.commands.Outtake;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShortPaths extends CommandGroup{
	private String gameData;
	private int dialNum;
	private int delay;
	private boolean goLeft;
	private boolean endOutside;
	
	
	
	public ShortPaths() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		dialNum = Robot.positionDial.getPosition();
		delay = Robot.timeDelayDial.getPosition();
		goLeft = gameData.charAt(1) == 'L';
//		endOnSide = endOnSideLSwitch.get()  // Timrek does not have this limit switch, defaulting to true
		endOutside = true;
		
		
		//addSequential(new Delay(delay));
		if(endOutside){
			switch (dialNum){
			case 0: leftPathEndOutside();
					break;
			case 1: middlePathEndOutside();
					break;
			case 2: rightPathEndOutside();
					break;
			}
		}
		else {
			switch (dialNum){
			case 0: leftPathEndInside();
					break;
			case 1: middlePathEndInside();
					break;
			case 2: rightPathEndInside();
					break;
			}
		}
		
		

	
	}
	
	private void leftPathEndOutside() {
		//regardless of any position or boolean, move forward 
		addSequential(new BBGoDistance(Constants.DEFAULT_FORWARD));
		
		
		if(goLeft){
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
			addSequential(new ArmToPosition(Constants.AUTO_FULL_RAISE_ELBOW_VALUE, Constants.AUTO_FULL_RAISE_SHOULDER_VALUE));
			addSequential(new BBGoToAngle(Constants.TURN_RIGHT));
			addSequential(new Outtake(3));
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
		
		
		if(goLeft){
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
		
		if(goLeft){
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
		
		if(goLeft){
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
		
		
		if(!goLeft){
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
		
		
		if(!goLeft){
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