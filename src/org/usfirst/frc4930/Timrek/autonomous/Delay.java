package org.usfirst.frc4930.Timrek.autonomous;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Delay extends Command {

	private double seconds;
	
    public Delay(double seconds) {
        this.seconds = seconds;
    }

    protected void execute() {
//    	System.out.println("Delaying.......");
    }

    protected boolean isFinished() {
        return timeSinceInitialized() > seconds;
    }

    protected void end() {
//    	System.out.println("Delay finished");
    }

    protected void interrupted() {
    }
}
