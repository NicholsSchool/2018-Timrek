package org.usfirst.frc4930.Timrek.commands;

import org.usfirst.frc4930.Timrek.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ArmMaintainOff extends Command{
	  protected void initialize() {
		  
	  }

	  protected void execute() {
		  Robot.arm.shouldMaintain = false;
	  }

	  protected boolean isFinished() {
	    return true;
	  }

	  protected void end() {}

	  protected void interrupted() {
	    end();
	  }
}
