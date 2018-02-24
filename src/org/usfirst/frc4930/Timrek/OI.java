
package org.usfirst.frc4930.Timrek;

import org.usfirst.frc4930.Timrek.autonomous.*;

import org.usfirst.frc4930.Timrek.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


public class OI {

  public JoystickButton j0b1, j0b2, j0b3, j0b4, j0b5, j0b6, j0b7, j0b8, j0b9, j0b10, j0b11, j0b12;

  public JoystickButton j1b1, j1b2, j1b3, j1b4, j1b5, j1b6, j1b7, j1b8, j1b9, j1b10, j1b11, j1b12;

  public JoystickButton j2b1, j2b2, j2b3, j2b4, j2b5, j2b6, j2b7, j2b8, j2b9, j2b10, j2b11, j2b12;

  public OI() {
    j0 = new Joystick(0);
    j1 = new Joystick(1);
    j2 = new Joystick(2);

    j0b1 = new JoystickButton(j0, 1);
    j0b2 = new JoystickButton(j0, 2);
    j0b3 = new JoystickButton(j0, 3);
    j0b4 = new JoystickButton(j0, 4);
    j0b5 = new JoystickButton(j0, 5);
    j0b6 = new JoystickButton(j0, 6);
    j0b7 = new JoystickButton(j0, 7);
    j0b8 = new JoystickButton(j0, 8);
    j0b9 = new JoystickButton(j0, 9);
    j0b10 = new JoystickButton(j0, 10);
    j0b11 = new JoystickButton(j0, 11);
    j0b12 = new JoystickButton(j0, 12);

    j1b1 = new JoystickButton(j1, 1);
    j1b2 = new JoystickButton(j1, 2);
    j1b3 = new JoystickButton(j1, 3);
    j1b4 = new JoystickButton(j1, 4);
    j1b5 = new JoystickButton(j1, 5);
    j1b6 = new JoystickButton(j1, 6);
    j1b7 = new JoystickButton(j1, 7);
    j1b8 = new JoystickButton(j1, 8);
    j1b9 = new JoystickButton(j1, 9);
    j1b10 = new JoystickButton(j1, 10);
    j1b11 = new JoystickButton(j1, 11);
    j1b12 = new JoystickButton(j1, 12);

    j2b1 = new JoystickButton(j2, 1);
    j2b2 = new JoystickButton(j2, 2);
    j2b3 = new JoystickButton(j2, 3);
    j2b4 = new JoystickButton(j2, 4);
    j2b5 = new JoystickButton(j2, 5);
    j2b6 = new JoystickButton(j2, 6);
    j2b7 = new JoystickButton(j2, 7);
    j2b8 = new JoystickButton(j2, 8);
    j2b9 = new JoystickButton(j2, 9);
    j2b10 = new JoystickButton(j2, 10);
    j2b11 = new JoystickButton(j2, 11);
    j2b12 = new JoystickButton(j2, 12);

    // Shifter (Solenoid 0)
    j1b4.whenPressed(new LowGear()); // Set True
    j1b6.whenPressed(new HighGear()); // Set False

    // PTO (Solenoid 1)
    j2b1.whenPressed(new DisengagePTO()); // Set True;
    j2b2.whenPressed(new EngagePTO()); // Set False

    // Gripper
    j2b3.whileHeld(new Intake());
    j2b5.whileHeld(new Outtake());

    // DropWheel (Solenoid 2)
    // j1b9.whenPressed(new LowerDropWheel()); //Set True
    // j1b10.whenPressed(new RaiseDropWheel()); //Set False
    j1b1.whileHeld(new MoveDropWheel());

    // Claw (Solenoid 4)
    j2b4.whenPressed(new ClawOpen()); // Set True
    j2b6.whenPressed(new ClawClose()); // Set False



    j2b7.whenPressed(new ToggleCamera());
    j0b7.whenPressed(new ResetGyro());
    j0b6.whenPressed(new BreakGoToAngle());
    j0b5.whenPressed(new GoToAngle(90));
    j0b4.whenPressed(new GoToAngle(-90));
    j0b3.whenPressed(new GoToAngle(180));
    j0b11.whenPressed(new BBGoDistance(6));
    j0b10.whenPressed(new ResetEncoders());
    j0b8.whenPressed(new BBGoToAngle(90));
    j0b9.whenPressed(new BBGoToAngle(-90));
    j2b11.whenPressed(new ArmToPosition(Constants.ELBOW_TO_BAR, 1000));
    j2b12.whenPressed(new ArmToPosition(Constants.ELBOW_EXTENDED * 2, Constants.SHOULDER_EXTENDED)); 
  }
}
