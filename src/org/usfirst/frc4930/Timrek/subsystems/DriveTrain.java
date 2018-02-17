

package org.usfirst.frc4930.Timrek.subsystems;


import org.usfirst.frc4930.Timrek.Robot;
import org.usfirst.frc4930.Timrek.RobotMap;
import org.usfirst.frc4930.Timrek.commands.TankDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {


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

