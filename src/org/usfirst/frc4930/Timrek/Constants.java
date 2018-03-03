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
  
  	INTAKE_SPEED = -1.0,
  	OUTTAKE_SPEED = 1.0,
  	
  	GRIPPER_HOLD_SPD = -0.1,

  	
  	LOWER_MAST_SPEED = -0.4,
    RAISE_MAST_SPEED = 0.4,
    
	ELBOW_RAISE_SPD = 1.0,
  	ELBOW_LOWER_SPD = -0.3,
  	SHOULDER_RAISE_SPD = 0.8,
  	SHOULDER_LOWER_SPD = -0.1,
  	
  	ELBOW_RAISE_SPD_CUBE = 1.0,
  	ELBOW_LOWER_SPD_CUBE = -0.3,
  	SHOULDER_RAISE_SPD_CUBE = 0.8,
  	SHOULDER_LOWER_SPD_CUBE = -0.1;


  // encoder values for the arm
  public static final int 
  	SHOULDER_EXTENDED = 190000,
  	ELBOW_EXTENDED = 90000,
  	SHOULDER_TO_BAR = 100000,
  	ELBOW_TO_BAR = 70000,
    LOWER_ARM_INCRAMENTS = 40000;

  // values for Auto
  public static final int
  	TICKS_PER_REVOULTION = 1300, //NEEDS TO BE TESTED
  	WHEEL_DIAMETER = 4,
  
  	BB_G0_TO_ANGLE_OFFSET = 8,
  	
  	AUTO_TO_BAR_ELBOW_VALUE = ELBOW_TO_BAR,
  	AUTO_TO_BAR_SHOULDER_VALUE = 1000,
  	AUTO_FULL_RAISE_ELBOW_VALUE = ELBOW_EXTENDED * 2,
  	AUTO_FULL_RAISE_SHOULDER_VALUE = SHOULDER_EXTENDED;
  
  // speeds for Auto
  public static final double
  	BB_GO_DISTANCE_SPEED = 0.75,
  	BB_GO_TO_ANGLE_SPEED = 0.8;
  
  // inverts
  public static final int 
  	INVERT_JOYSTICK = -1;

  // Camera resolution
  public static final int 
  	LENGTH = 320,
  	HEIGHT = 240, 
  	FRAMES_PER_SECOND = 5;

}
