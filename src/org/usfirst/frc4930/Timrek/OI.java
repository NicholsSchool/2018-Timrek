

package org.usfirst.frc4930.Timrek;

import org.usfirst.frc4930.Timrek.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;



public class OI {
	public Joystick j0;
	public Joystick j1;
	public Joystick j2;
	
	public JoystickButton j0b1;
	public JoystickButton j0b7;
	public JoystickButton j0b8;
	
	public JoystickButton j1b1;
	public JoystickButton j1b4;
	public JoystickButton j1b6;

	public JoystickButton j2b1;
	public JoystickButton j2b2;
	public JoystickButton j2b3;
	public JoystickButton j2b4;
	public JoystickButton j2b5;
	public JoystickButton j2b6;
	public JoystickButton j2b7;
	public JoystickButton j2b9;
	public JoystickButton j2b10;
	public JoystickButton j2b12;
	
	
    public OI() {
        j0 = new Joystick(0);
        j1 = new Joystick(1);
        j2 = new Joystick(2);

        j0b1 = new JoystickButton(j0, 1);
        j0b7 = new JoystickButton(j0, 7);
        j0b8 = new JoystickButton(j0, 8);
        
        
        j1b1 = new JoystickButton(j1, 1);

        j1b4 = new JoystickButton(j1, 4);
        j1b6 = new JoystickButton(j1, 6);
    
        
        j2b1 = new JoystickButton(j2, 1);
        j2b2 = new JoystickButton(j2, 2);
        j2b3 = new JoystickButton(j2, 3);
        j2b4 = new JoystickButton(j2, 4);
        j2b5 = new JoystickButton(j2, 5);
        j2b6 = new JoystickButton(j2, 6);
        j2b7 = new JoystickButton(j2, 7);
        j2b9 = new JoystickButton(j2, 9);
        j2b10 = new JoystickButton(j2, 10);
        j2b12 = new JoystickButton(j2, 12);
       
        //Shifter (Solenoid 0)
        j1b4.whenPressed(new LowGear());  //Set True
        j1b6.whenPressed(new HighGear());   //Set False
       
        //PTO (Solenoid 1)
        j0b1.whenPressed(new DisengagePTO());
//        j2b2.whenPressed(new EngagePTO());  //Set False
        
        //Gripper
        j2b3.whileHeld(new Intake());
        j2b5.whileHeld(new Outtake());
       
       //DropWheel (Solenoid 2)
//       j1b9.whenPressed(new LowerDropWheel());   //Set True
//       j1b10.whenPressed(new RaiseDropWheel()); //Set False
       j1b1.whileHeld(new MoveDropWheel());
       
       //Claw (Solenoid 4)
       j2b4.whenPressed(new ClawOpen());  //Set True
       j2b6.whenPressed(new ClawClose()); //Set False
      
       //Mast
       j2b10.whileHeld(new RaiseMast());
       j2b12.whileHeld(new LowerMast());
 
       j2b7.whenPressed(new ToggleCamera());
    
    }

}

