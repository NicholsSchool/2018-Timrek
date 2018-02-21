package org.usfirst.frc4930.Timrek.autonomous;

import org.usfirst.frc4930.Timrek.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmToPosition extends Command {
	
	private int timeOut;
	private int elbowPos;
	private int shoulderPos;

    public ArmToPosition(int elbowPos, int shoulderPos, int timeOut) {
    	this.elbowPos = elbowPos;
    	this.shoulderPos = shoulderPos;
        this.timeOut = timeOut;
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.arm.MoveToPosition(elbowPos, shoulderPos);
    }

    protected boolean isFinished() {
        return timeSinceInitialized() > timeOut;
    }

    protected void end() {
    }

    protected void interrupted() {
    	end();
    }
}
