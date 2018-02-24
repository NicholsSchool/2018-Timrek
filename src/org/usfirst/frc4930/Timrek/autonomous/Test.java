package org.usfirst.frc4930.Timrek.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Test extends CommandGroup {
	
	public Test() {
		addSequential(new BBGoDistance(12));
		addSequential(new BBGoToAngle(-90));
		addSequential(new BBGoDistance(10));
		addSequential(new BBGoToAngle(-90));
		addSequential(new BBGoDistance(12));
		addSequential(new BBGoToAngle(-90));
		addSequential(new BBGoDistance(10));
		addSequential(new BBGoToAngle(-90));

		addSequential(new BBGoDistance(12));
		addSequential(new BBGoToAngle(-90));
		addSequential(new BBGoDistance(10));
		addSequential(new BBGoToAngle(-90));
		addSequential(new BBGoDistance(12));
		addSequential(new BBGoToAngle(-90));
		addSequential(new BBGoDistance(10));
		addSequential(new BBGoToAngle(-90));
		
		addSequential(new BBGoDistance(12));
		addSequential(new BBGoToAngle(-90));
		addSequential(new BBGoDistance(10));
		addSequential(new BBGoToAngle(-90));
		addSequential(new BBGoDistance(12));
		addSequential(new BBGoToAngle(-90));
		addSequential(new BBGoDistance(10));
		addSequential(new BBGoToAngle(-90));
	}
}
