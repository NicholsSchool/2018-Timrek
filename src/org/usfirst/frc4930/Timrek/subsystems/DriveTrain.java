

package org.usfirst.frc4930.Timrek.subsystems;


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
    	RobotMap.driveTank.tankDrive(left, right);
    }

    public void tankDrive() {
    	double left = -Robot.oi.j0.getY();
    	double right = -Robot.oi.j1.getY();
    	move(left, right);
    }
    
    public void goToAngle(double angle){
    	leftTurnController = new PIDController(0.015, 0.01, 0, 0, Robot.navX, RobotMap.lDrvMSTR);
    	rightTurnController = new PIDController(0.015, 0, 0.01, 0, Robot.navX, RobotMap.rDrvMSTR);
    	leftTurnController.setInputRange(-180.0f,  180.0f);
    	rightTurnController.setInputRange(-180.0f,  180.0f);
    	
    	if(angle <= 0){
    		leftTurnController.setOutputRange(-1.0, 0.0);
    		rightTurnController.setOutputRange(0.0, 1.0);
    	}
    	else {
    		leftTurnController.setOutputRange(0.0, 1.0);
        	rightTurnController.setOutputRange(-1.0, 0.0);
    	}
    	
    	leftTurnController.setAbsoluteTolerance(5.0f);
    	rightTurnController.setAbsoluteTolerance(5.0f);
    	leftTurnController.setContinuous(false);
    	rightTurnController.setContinuous(false);
    	
    	leftTurnController.setSetpoint(angle);
    	rightTurnController.setSetpoint(angle);
    	if(angle <= 0){
    		rightTurnController.enable();
    		RobotMap.lDrvMSTR.set(-rightTurnController.get());
    	}
    	else{
    		leftTurnController.enable();
    		RobotMap.rDrvMSTR.set(-leftTurnController.get());
    	}
    	
//    	double currentAngle = Robot.navX.getAngle();
//    	if(angle > 180 ){
//    		angle -= 360;
//    	}
//    	if(currentAngle > angle + 5 || currentAngle  < angle - 5 ){
//    		
//    		double angleDif =  angle - currentAngle;
//    		double change = angleDif /360;
//    		
//    		double lSpeed = 0.5 * change;
//    		double rSpeed = -0.5 * change;
//    		
//
//    		move(lSpeed, rSpeed);
//    		System.out.println("Going To Angle");
//    	}    	
//    	else {
//    		stop();
//    		System.out.println("Stopping at Angle");
//    	}

    }
    
    public void endLoop() {
    	if(rightTurnController != null && leftTurnController != null){
    		rightTurnController.disable();
    		leftTurnController.disable();
    	}
    }
    
    public void setPosition(double position) {
    	RobotMap.lDrvMSTR.config_kP(0, 0.1, 100);
		RobotMap.lDrvMSTR.config_kI(0, 0.0, 100);
		RobotMap.lDrvMSTR.config_kD(0, 0.1, 100);
		
		RobotMap.rDrvMSTR.config_kP(0, 0.1, 100);
		RobotMap.rDrvMSTR.config_kI(0, 0.0, 100);
		RobotMap.rDrvMSTR.config_kD(0, 0.1, 100);
		
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
    
    public void stop() {
    	RobotMap.driveTank.stopMotor();
    }

}

