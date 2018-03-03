package org.usfirst.frc4930.Timrek.commands;

import org.usfirst.frc4930.Timrek.Robot;
import org.usfirst.frc4930.Timrek.Constants;

import edu.wpi.first.wpilibj.command.Command;

public class Intake extends Command
{
	int runTime;
	double speed;
  public Intake(int seconds, double intakeSpeed){
		  runTime = seconds;
		  speed = -intakeSpeed;
		  requires(Robot.gripper);	 
	  }
	 
  public Intake(){
		  runTime = 0;
		  speed = Constants.INTAKE_SPEED;
		  requires(Robot.gripper);
	  }
  protected void initialize() {

  }

  protected void execute() {
    Robot.gripper.grip(speed);
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
    if(!Robot.inAuto){
    new HoldCube().start();
    }
  }

  protected void interrupted() {
    end();
  }

}
