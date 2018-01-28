package org.usfirst.frc.team2601.robot.subsystems;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Scaler extends Subsystem {
	Constants constants = Constants.getInstance();
	//Motors
	WPI_TalonSRX scalerM1;
	WPI_TalonSRX scalerM2;
	SpeedControllerGroup scalerGroup;
	//Set default command for the subsystem
    public void initDefaultCommand() {
    }
    //Constructor for the subsystem
    public Scaler() { 
    	if (constants.autonBot == false) {
    		scalerM1 = new WPI_TalonSRX(constants.scalerM1Port);
    		scalerM2 = new WPI_TalonSRX(constants.scalerM2Port);
    		scalerGroup = new SpeedControllerGroup(scalerM1, scalerM2);
    	}
    }
    //Method for activating the scaler
    public void ScalerButton () {
        if(constants.autonBot == false) {
    		if (scalerGroup.get() == 0) {
	    		Robot.compressor.stop();
	    		scalerGroup.set(1);
	    	}else {
	    		Robot.compressor.start();
	    		scalerGroup.set(0);
	    	}
        }
    }
    //Method for stopping the scaler
    public void ScalerButtonStop () {
    	Robot.compressor.start();
        if(constants.autonBot == false) {
    		scalerGroup.set(0);
        }
    }
}