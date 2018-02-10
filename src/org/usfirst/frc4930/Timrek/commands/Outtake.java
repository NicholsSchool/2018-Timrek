package org.usfirst.frc4930.Timrek.commands;

import org.usfirst.frc4930.Timrek.Robot;

import org.usfirst.frc4930.Timrek.Values;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */


import edu.wpi.first.wpilibj.command.Command;

public class Outtake extends Command {
	
		protected void initialize() {

		}
		
		protected void execute() {
			Robot.gripper.grip(-0.6);
		}
		
		@Override
		protected boolean isFinished() {
			
			return false;
		}
		
		protected void end() {
			Robot.gripper.stop();
		}
		
		protected void interrupted() {
			end();
		}

	}

