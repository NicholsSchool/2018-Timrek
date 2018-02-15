package org.usfirst.frc4930.Timrek.commands;

import org.usfirst.frc4930.Timrek.Constants;
import org.usfirst.frc4930.Timrek.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LowerArmMove extends Command
{

  private int startState;
  private int desiredState;
  private int change;

  public LowerArmMove(int state) {
    requires(Robot.lowerArm);
    requires(Robot.upperArm);
    requires(Robot.arm);
    desiredState = state;
    startState = Robot.lowerArm.getState();
    change = (desiredState - startState) * Constants.LOWER_ARM_INCRAMENTS;
  }

  protected void initialize() {

    if (change > 0) {
      Robot.lowerArm.setPosition(change);
    } else {
      Robot.lowerArm.goDown(change);
    }
  }

  protected void execute() {
    // Robot.lowerArm.move(0.35 * change);
  }

  @Override
  protected boolean isFinished() {
    return (Robot.lowerArm.getState() == desiredState);
  }

  protected void end() {
    // If lowerArm does not keep its position, change to .rest()

    // Robot.lowerArm.stop();
    // start joystick control
    new MoveArm().start();

  }

  protected void interrupted() {
    end();
  }

}
