package org.usfirst.frc4930.Timrek.commands;

import org.usfirst.frc4930.Timrek.Constants;
import org.usfirst.frc4930.Timrek.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class HoldCube extends Command {

    public HoldCube() {
        requires(Robot.gripper);
    }

    protected void initialize() {
    }

    protected void execute() {
    	if(Robot.gripper.hasCube) {
        	Robot.gripper.grip(Constants.GRIPPER_HOLD_SPD);
    	}
    }

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
