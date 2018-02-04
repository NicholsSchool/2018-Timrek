package org.usfirst.frc4930.Timrek.commands;


import org.usfirst.frc4930.Timrek.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class HighGear extends Command{
	  public HighGear() {
		    requires(Robot.shifter);
		  }

		  protected void initialize() {}

		  protected void execute() {
		    Robot.shifter.setHighGear();
		  }

		  protected boolean isFinished() {
		    return true;
		  }

		  protected void end() {}

		  protected void interrupted() {
		    end();
		  }
}
