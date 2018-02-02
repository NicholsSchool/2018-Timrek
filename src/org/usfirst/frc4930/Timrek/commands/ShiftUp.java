package org.usfirst.frc4930.Timrek.commands;


import org.usfirst.frc4930.Timrek.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShiftUp extends Command{
	  public ShiftUp() {
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
