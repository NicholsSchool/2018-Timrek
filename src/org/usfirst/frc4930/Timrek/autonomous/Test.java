package org.usfirst.frc4930.Timrek.autonomous;

import org.usfirst.frc4930.Timrek.Constants;
import org.usfirst.frc4930.Timrek.commands.Outtake;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Test extends CommandGroup {
	
	public Test() {
		addSequential(new BBGoDistance(2));
		addSequential(new BBGoToAngle(90));
		addSequential(new BBGoDistance(5));
		addSequential(new ArmToPosition(Constants.ELBOW_TO_BAR, 1000));
		addSequential(new Outtake(3));
	}
}
