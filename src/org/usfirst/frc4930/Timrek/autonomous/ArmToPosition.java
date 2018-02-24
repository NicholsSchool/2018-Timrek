package org.usfirst.frc4930.Timrek.autonomous;

import org.usfirst.frc4930.Timrek.Robot;
import org.usfirst.frc4930.Timrek.commands.MoveArm;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmToPosition extends Command {
	
	private int elbowPos;
	private int shoulderPos;

    public ArmToPosition(int elbowPos, int shoulderPos) {
    	requires(Robot.arm);
    	this.elbowPos = elbowPos;
    	this.shoulderPos = shoulderPos;
    }

    protected void initialize() {
    	Robot.arm.atPosition = false;
    }

    protected void execute() {
    	Robot.arm.moveToPosition(elbowPos, shoulderPos);
    }

    protected boolean isFinished() {
        return Robot.arm.atPosition;
    }

    protected void end() {
    	new MoveArm().start();
    }

    protected void interrupted() {
    	end();
    }
}
