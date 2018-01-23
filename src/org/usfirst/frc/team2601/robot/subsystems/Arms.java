package org.usfirst.frc.team2601.robot.subsystems;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.commands.arm.ArmMotors;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class Arms extends Subsystem {
	Constants constants = Constants.getInstance();
	//Motors 
	WPI_TalonSRX leftArmM; 
	WPI_TalonSRX rightArmM;    
	//Set the default command for the subsystem
    public void initDefaultCommand() {
    	setDefaultCommand(new ArmMotors());
    }     
    //Constructor for the subsystem
    public Arms() {
    	if (constants.autonBot == false) {
    		leftArmM = new WPI_TalonSRX(constants.leftArmMPort);
    		rightArmM = new WPI_TalonSRX(constants.rightArmMPort);
    	}
    }
    //Method for Using the Motors
    public void armMotors(Joystick js) {
        if(constants.autonBot == false) {
    		double y = js.getY();
	    	leftArmM.set(y);
	    	rightArmM.set(-y);
        }
	}
}