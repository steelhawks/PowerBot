package org.usfirst.frc.team2601.robot.subsystems;

import org.usfirst.frc.team2601.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
	Constants constants = Constants.getInstance();
	//Motors
	WPI_TalonSRX wormDriveM = new WPI_TalonSRX(constants.wormDriveMPort);
	WPI_TalonSRX elevatorM = new WPI_TalonSRX(constants.elevatorMPort);
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }
    public Elevator() {
    	
    }
    public void WormDriveButton() {
    	if (wormDriveM.get() == 0) {
    		wormDriveM.set(1);
    	}else {
    		wormDriveM.set(0);
    	}
    }
    public void ElevatorButton() {
    	if (elevatorM.get() == 0) {
    		elevatorM.set(1);
    	}else {
    		elevatorM.set(0);
    	}
    }
}


