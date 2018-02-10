package org.usfirst.frc4930.Timrek;


public class Values {
	// drive train speed controllers
	public static final int 
		L_DRV_MSTR_ID = 22,
		L_DRV_SLV1_ID = 24,
		L_DRV_SLV2_ID = 26,
	
		R_DRV_MSTR_ID = 28,
		R_DRV_SLV1_ID = 30,
		R_DRV_SLV2_ID = 32,
	
		DROP_WHL_ID = 34;
	
	
	
	// arm speed controllers
	public static final int
		L_SHOULDER_ID = 21,
		R_SHOULDER_ID = 23,
	
		L_ELBOW_ID = 25,
		R_ELBOW_ID = 27,
	
		L_INTAKE_ID = 29,
		R_INTAKE_ID = 31;
	
	
	// other speed controllers
	public static final int
		MAST_ID = 33;
	
	
	// sensors
	public static final int
		GRIPPER_LSWITCH_CHNL = 0,
		UARM_UP_LSWITCH_CHNL = 0,
		UARM_DOWN_LSWITCH_CHNL = 0,
		LARM_DOWN_LSWITCH_CHNL = 8;
	
	
	// speeds
	public static final double
		INTAKE_SPEED = 1.0,
		OUTTAKE_SPEED = -1.0;
	
	
	// encoder values for the arm
	public static final int
		SHOULDER_VELOCITY = 500,
		ELBOW_VELOCITY = 500,
		SHOULDER_EXTENDED = 75000,
		ELBOW_EXTENDED = 100000;
		
	
	// inverts
	public static final int
		INVERT_JOYSTICK = -1;
	
}
