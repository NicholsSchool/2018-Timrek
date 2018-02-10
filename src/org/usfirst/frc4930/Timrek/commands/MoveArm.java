package org.usfirst.frc4930.Timrek.commands;

import org.usfirst.frc4930.Timrek.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveArm extends Command
{

  public MoveArm() {
    requires(Robot.arm);
    requires(Robot.upperArm);
    requires(Robot.lowerArm);
  }

  protected void initialize() {}

  protected void execute() {
    double speed = Robot.oi.j2.getY();
    Robot.arm.set(speed);
  }

  protected boolean isFinished() {
    return false;
  }

  protected void end() {
	  Robot.arm.stop();
  }

  protected void interrupted() {
    end();
  }
}
