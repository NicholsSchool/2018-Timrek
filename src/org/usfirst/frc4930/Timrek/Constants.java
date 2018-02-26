package org.usfirst.frc4930.Timrek;

public class Constants
{
  // drive train speed controllers
  public static final int
  	 L_DRV_MSTR_ID = 22, L_DRV_SLV1_ID = 24, L_DRV_SLV2_ID = 26,

     R_DRV_MSTR_ID = 28, R_DRV_SLV1_ID = 30, R_DRV_SLV2_ID = 32,

     DROP_WHL_ID = 34;

  // arm speed controllers
  public static final int
    L_SHOULDER_ID = 21, R_SHOULDER_ID = 23,

    L_ELBOW_ID = 25, R_ELBOW_ID = 27,

    L_INTAKE_ID = 29, R_INTAKE_ID = 31;

  // other speed controllers
  public static final int 
  	MAST_ID = 33;

  // sensors
  public static final int 
  	GRIPPER_LSWITCH_CHNL = 0, 
  	UARM_UP_LSWITCH_CHNL = 0,
    UARM_DOWN_LSWITCH_CHNL = 0, 
    LARM_DOWN_LSWITCH_CHNL = 8,

    POSITIONPOT_CHNL = 0,
    DELAYPOT_CHNL = 1;

  // speeds
  public static final double 
	ELBOW_RELATIVE_SPD = 0.6,
	ARM_LERP_T = 0.85,
  
  	INTAKE_SPEED = .4,
  	OUTTAKE_SPEED = -1.0,
  	
  	LOWER_MAST_SPEED = -0.4,
    RAISE_MAST_SPEED = 0.4,
    
	ELBOW_RAISE_SPD_BELOW_BAR = 0.7,		//0.5
	SHOULDER_RAISE_SPD_BELOW_BAR = 0.8,		//0.6
	ELBOW_RAISE_SPD_ABOVE_BAR = 0.8,		//0.6
	SHOULDER_RAISE_SPD_ABOVE_BAR = 0.8,		//0.6
	    	  	
	ELBOW_LOWER_SPD_BELOW_BAR = -0.1,
	SHOULDER_LOWER_SPD_BELOW_BAR = -0.05,
	ELBOW_LOWER_SPD_ABOVE_BAR = -0.15,
	SHOULDER_LOWER_SPD_ABOVE_BAR = -0.25,
  
  	BB_GO_DISTANCE_SPEED = 0.75;

  // encoder values for the arm
  public static final int 
  	SHOULDER_EXTENDED = 190000,
  	ELBOW_EXTENDED = 90000,
  	SHOULDER_TO_BAR = 100000,
  	ELBOW_TO_BAR = 70000,
    LOWER_ARM_INCRAMENTS = 40000;

  // values for Auto
  public static final int
  	TICKS_PER_REVOULTION = 900, //NEEDS TO BE TESTED
  	WHEEL_DIAMETER = 2;
  
  
  
  // inverts
  public static final int 
  	INVERT_JOYSTICK = -1;

  // Camera resolution
  public static final int 
  	LENGTH = 320,
  	HEIGHT = 240, 
  	FRAMES_PER_SECOND = 5;

}
