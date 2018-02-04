package org.usfirst.frc4930.Timrek.PathPlanning;

import org.usfirst.frc4930.Timrek.commands.Outtake;
import org.usfirst.frc4930.Timrek.commands.UpperArmMove;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Auto extends CommandGroup {
	
	public Auto(Path path,  boolean toSwitch) {
		if(toSwitch){
			addParallel(new UpperArmMove(2));
			//Not sure if these will work, because addSequential, runs until isFinished method returns true, neither of them
			//return that
			addSequential(new RunPath(path));
			addSequential(new Outtake());
		}

		else {
			addParallel(new UpperArmMove(3));
			//Add LowerArmMove Once it is verified to work
			
			//Not sure if these will work, because addSequential, runs until isFinished method returns true, neither of them
			//return that
			addSequential(new RunPath(path));
			addSequential(new Outtake());
		}
	}
}
