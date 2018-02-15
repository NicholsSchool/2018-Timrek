package org.usfirst.frc4930.Timrek.commands;

import org.usfirst.frc4930.Timrek.Constants;
import org.usfirst.frc4930.Timrek.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UpperArmMove extends Command
{

  private int startState;
  private int desiredState;
  private int change;

  public UpperArmMove(int state) {
    requires(Robot.upperArm);
    requires(Robot.lowerArm);
    requires(Robot.arm);
    desiredState = state;
    startState = Robot.upperArm.getState();
    change = (desiredState - startState) * Constants.LOWER_ARM_INCRAMENTS;
    SmartDashboard.putNumber("change", change);
  }

  protected void initialize() {
    // Run This code to test PID Loop
    if (change > 0) {
      Robot.upperArm.setPosition(change);
    } else {
      Robot.upperArm.goDown(change);
    }
  }

  protected void execute() {
    // Robot.upperArm.move(0.35 * change);

  }

  @Override
  protected boolean isFinished() {

    boolean lowerBound = Robot.upperArm.getEncoder() > change - 100;
    boolean upperBound = Robot.upperArm.getEncoder() < change + 100;

    return (Robot.upperArm.getState() == desiredState) && lowerBound && upperBound;

  }

  protected void end() {
    // Robot.upperArm.rest();
    // start joystick control
    new MoveArm().start();
  }

  protected void interrupted() {
    end();
  }

}
