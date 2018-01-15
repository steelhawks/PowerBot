package org.usfirst.frc.team2601.robot.subsystems;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.commands.arm.ArmMotors;
import org.usfirst.frc.team2601.robot.commands.arm.ArmPiston;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class Arms extends Subsystem {
	Constants constants = Constants.getInstance();
	//Motors 
	WPI_TalonSRX leftArmM = new WPI_TalonSRX(constants.leftArmMPort);
	WPI_TalonSRX rightArmM = new WPI_TalonSRX(constants.rightArmMPort);
	//Piston
	DoubleSolenoid armSol = new DoubleSolenoid(constants.armSolPortOn, constants.armSolPortOff);
    
	//Set the default command for the subsystem
    public void initDefaultCommand() {
    	setDefaultCommand(new ArmMotors());
    }     
    //Constructor for the subsystem
    public Arms() {
    	armSol.set(DoubleSolenoid.Value.kForward);
    }
    //Method for Using the Motors
    public void armMotors(Joystick js) {
        if(constants.autonBot == true) {
    		double y = js.getY();
	    	leftArmM.set(y);
	    	rightArmM.set(-y);
        }
	}
    //Method for Using the Pistons
    public void shootPistons() {
        if(constants.autonBot == true) {
	    	if(armSol.get() == DoubleSolenoid.Value.kForward) {
		    	armSol.set(DoubleSolenoid.Value.kReverse);
		    } else {
		    	armSol.set(DoubleSolenoid.Value.kForward);
		    }
        }
	}
}

