package org.usfirst.frc4930.Timrek.commands;

import org.usfirst.frc4930.Timrek.Robot;
import org.usfirst.frc4930.Timrek.Values;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Outtake extends Command {

    public Outtake() {
        requires(Robot.gripper);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.gripper.set(Values.OUTTAKE_SPEED);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.gripper.set(0.0);
    }

    
    protected void interrupted() {
    	end();
    }
}
