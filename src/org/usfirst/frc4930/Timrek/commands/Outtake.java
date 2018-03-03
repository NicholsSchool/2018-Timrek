package org.usfirst.frc4930.Timrek.commands;

import org.usfirst.frc4930.Timrek.Robot;
import org.usfirst.frc4930.Timrek.Constants;

import edu.wpi.first.wpilibj.command.Command;

public class Outtake extends Command
{
  
  private int runTime;
  public Outtake(int seconds){
	  runTime = seconds;
  }
  
  public Outtake(){
	  runTime = 0;
  } 
  
  protected void initialize() {

  }

  protected void execute() {
    Robot.gripper.grip(Constants.OUTTAKE_SPEED);
  }

  @Override
  protected boolean isFinished() {
	  if(runTime != 0){
		  return timeSinceInitialized() > runTime;
	  }
	  else{
		  return false;
	  }
  }

  protected void end() {
    Robot.gripper.stop();
    new HoldCube().start();
  }

  protected void interrupted() {
    end();
  }

}
