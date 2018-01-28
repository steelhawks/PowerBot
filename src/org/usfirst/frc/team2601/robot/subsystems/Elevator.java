package org.usfirst.frc.team2601.robot.subsystems;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.commands.elevator.ElevatorMotor;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
	Constants constants = Constants.getInstance();
	//Motors
	WPI_TalonSRX wormDriveM;
	WPI_TalonSRX elevatorM;
	//Set the default command for the subsystem
    public void initDefaultCommand() {

    }
    //Constructor for the subsystem
    public Elevator() {
    	if (constants.autonBot == false) {
	    	elevatorM = new WPI_TalonSRX(constants.elevatorMPort);
    	}  	
    }
    //Method for using the worm drive
    public void WormDriveButton() {
        if(constants.autonBot == false) {
	    	if (wormDriveM.get() == 0) {
	    		wormDriveM.set(1);
	    	}else {
	    		wormDriveM.set(0);
	    	}
        }
    }
    //Method for using the elevator motor
    public void ElevatorButton() {
        if(constants.autonBot == false) {
    		if (elevatorM.get() == 0) {
	    		elevatorM.set(1);
	    	}else {
	    		elevatorM.set(0);
	    	}
        }
     }
}


