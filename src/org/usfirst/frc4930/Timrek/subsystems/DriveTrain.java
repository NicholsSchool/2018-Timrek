

package org.usfirst.frc4930.Timrek.subsystems;


import org.usfirst.frc4930.Timrek.Constants;
import org.usfirst.frc4930.Timrek.Robot;
import org.usfirst.frc4930.Timrek.RobotMap;
import org.usfirst.frc4930.Timrek.commands.TankDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {

	PIDController leftTurnController;
	PIDController rightTurnController;
    @Override
    public void initDefaultCommand() {
    	setDefaultCommand(new TankDrive());
    }


    public void move(double left, double right) {
    	// if the pto is on, don't turn
    	if(Robot.ptoOn) {
    		double average = (left + right) / 2;
    		left = average;
    		right = average;
    	}
    	RobotMap.driveTank.tankDrive(left, right);
    }

    public void tankDrive() {
    	double left = -Robot.oi.j0.getY();
    	double right = -Robot.oi.j1.getY();
    	move(left, right);
    }
    
    public void goToAngle(double angle){
        leftTurnController = new PIDController(0.015, 0.00, 0, Robot.navX, RobotMap.lDrvMSTR);
        rightTurnController = new PIDController(0.015, 0.00, 0, Robot.navX, RobotMap.rDrvMSTR);
        leftTurnController.setInputRange(-180.0f, 180.0f);
        rightTurnController.setInputRange(-180.0f, 180.0f);
  //      goingToAngle = true;

        leftTurnController.setPercentTolerance(30.0);
        rightTurnController.setPercentTolerance(30.0);
        
        leftTurnController.setContinuous(true);
        rightTurnController.setContinuous(true);

        leftTurnController.setSetpoint(angle);
        rightTurnController.setSetpoint(angle);
        
        leftTurnController.setOutputRange(-1.0, 1.0);
        rightTurnController.setOutputRange(-1.0, 1.0);
        
        leftTurnController.enable();
        rightTurnController.enable();
        

        System.out.println("Left Speed: " + RobotMap.lDrvMSTR.get());
        System.out.println("Right Speed: " + RobotMap.rDrvMSTR.get());
        if (!leftTurnController.isEnabled() && !rightTurnController.isEnabled()) {
   //       goingToAngle = false;
        }
        System.out.println("Going To Angle");
    	


    }
    
    public void endLoop() {
    	if(rightTurnController != null && leftTurnController != null){
    		rightTurnController.disable();
    		leftTurnController.disable();
    	}
    }
    
    public void setPosition(double position) {
    	RobotMap.lDrvMSTR.config_kP(0, 0.15, 100);
		RobotMap.lDrvMSTR.config_kI(0, 0.0, 100);
		RobotMap.lDrvMSTR.config_kD(0, 0.0, 100);
		
		RobotMap.rDrvMSTR.config_kP(0, 0.15, 100);
		RobotMap.rDrvMSTR.config_kI(0, 0.0, 100);
		RobotMap.rDrvMSTR.config_kD(0, 0.0, 100);
		
		RobotMap.lDrvMSTR.set(ControlMode.Position, position);
		RobotMap.rDrvMSTR.set(ControlMode.Position, position);
    }
    
    public void setVelocity(double velocity){
    	RobotMap.lDrvMSTR.config_kP(0, 0.1, 100);
		RobotMap.lDrvMSTR.config_kI(0, 0.0, 100);
		RobotMap.lDrvMSTR.config_kD(0, 0.1, 100);
		
		RobotMap.rDrvMSTR.config_kP(0, 0.1, 100);
		RobotMap.rDrvMSTR.config_kI(0, 0.0, 100);
		RobotMap.rDrvMSTR.config_kD(0, 0.1, 100);
		
		RobotMap.lDrvMSTR.set(ControlMode.Velocity, velocity);
		RobotMap.rDrvMSTR.set(ControlMode.Velocity, velocity);
    } 
    public void resetEncoders() {
    	RobotMap.lDrvMSTR.setSelectedSensorPosition(0, 0, 0);
    	RobotMap.rDrvMSTR.setSelectedSensorPosition(0, 0, 0);
    }
    
    public void stop() {
    	RobotMap.driveTank.stopMotor();
    }

}

