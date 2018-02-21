

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
	
	public boolean goingToAngle = false;
	public boolean goingDistance = false;
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
        leftTurnController = new PIDController(0.015, 0.00, 0, Robot.navX, RobotMap.lDrvMSTR);
        rightTurnController = new PIDController(0.015, 0.00, 0, Robot.navX, RobotMap.rDrvMSTR);
        leftTurnController.setInputRange(-180.0f, 180.0f);
        rightTurnController.setInputRange(-180.0f, 180.0f);
        goingToAngle = true;

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
          goingToAngle = false;
        }
        System.out.println("Going To Angle");
    	


    }
    
    public void BBGoToAngle(double angle){
    	double currentAngle = Robot.navX.getAngle();
    	if(currentAngle > angle + 5 || currentAngle  < angle - 5 ){
    		goingToAngle = true;
    		double angleDif =  angle - currentAngle;
    		double change = angleDif /180;
    		double lSpeed;
    		double rSpeed;
    		if(angle > 0){
    			lSpeed= 0.65;
    			rSpeed = -0.65;
    		}
    		else {
    			lSpeed = -0.65;
    			rSpeed = 0.65;
    		}
    		move(lSpeed, rSpeed);
    		System.out.println("Going To Angle");
    	}    	
    	else {
    		goingToAngle = false;
    		stop();
    		System.out.println("Stopping at Angle");
    	}
    }
    
    public void BBGoDistance(double distance) {
    	if(RobotMap.lDrvMSTR.getSelectedSensorPosition(0) <= distance - 1000){
    		double speed = 0.75;
    		move(speed, speed);
    		goingDistance = true;
    		System.out.println("Going to Distance");
    	}
    	else {
    		goingDistance = false;
    		stop();
    		System.out.println("Stopping at Distance");
    	}
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

