package org.usfirst.frc4930.Timrek.commands;

import org.usfirst.frc4930.Timrek.Robot;
import org.usfirst.frc4930.Timrek.Constants;

import edu.wpi.first.wpilibj.command.Command;

public class Outtake extends Command
{
  
  private int time;
  public Outtake(int seconds){
	  time = seconds;
  }
  
  public Outtake(){
	  time = 0;
  } 
  
  protected void initialize() {

  }

  protected void execute() {
    Robot.gripper.grip(Constants.OUTTAKE_SPEED);
  }

  @Override
  protected boolean isFinished() {
	  if(time != 0){
		  return timeSinceInitialized() > time;
	  }
	  else{
		  return false;
	  }
  }

  protected void end() {
    Robot.gripper.stop();
  }

  protected void interrupted() {
    end();
  }

}
