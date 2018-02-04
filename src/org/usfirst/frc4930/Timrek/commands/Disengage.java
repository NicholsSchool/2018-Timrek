package org.usfirst.frc4930.Timrek.commands;

import org.usfirst.frc4930.Timrek.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Disengage extends Command{
	  public Disengage() {
		    requires(Robot.pto);
		  }

		  protected void initialize() {}

		  protected void execute() {
		    Robot.pto.setLowGear();
		  }

		  protected boolean isFinished() {
		    return true;
		  }

		  protected void end() {}

		  protected void interrupted() {
		    end();
		  }
}
