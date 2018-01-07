package org.usfirst.frc.team2601.robot.subsystems;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.commands.DiffDrive;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.NidecBrushless;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class Drivetrain extends Subsystem {
	
	Constants constants = Constants.getInstance();
	
	//Left Talons
	WPI_TalonSRX frontLeftM = new WPI_TalonSRX(constants.frontLeftMPort);
	WPI_TalonSRX midLeftM = new WPI_TalonSRX(constants.midLeftMPort);
	WPI_TalonSRX backLeftM = new WPI_TalonSRX(constants.backLeftMPort);
	//Right Talons
	WPI_TalonSRX frontRightM = new WPI_TalonSRX(constants.frontRightMPort);
	WPI_TalonSRX midRightM = new WPI_TalonSRX(constants.midRightMPort);
	WPI_TalonSRX backRightM = new WPI_TalonSRX(constants.backRightMPort);
	//Speed Controller Group
	SpeedControllerGroup leftGroup = new SpeedControllerGroup(frontLeftM, midLeftM, backLeftM);
	SpeedControllerGroup rightGroup = new SpeedControllerGroup(frontRightM, midRightM, backRightM);
	//Drivetrain Type (Tank)
	DifferentialDrive diffDrive = new DifferentialDrive(leftGroup, rightGroup);
	//Solenoid
	DoubleSolenoid shiftSol = new DoubleSolenoid(constants.shiftSolPortOn, constants.shiftSolPortOff);//Shifting
	NidecBrushless motor = new NidecBrushless(constants.brushlessPWMPort, constants.brushlessDIOPort);//(PWM Channel, DIO Channel)
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Drivetrain() {
		shiftSol.set(DoubleSolenoid.Value.kReverse);//Setting to low gear by default
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DiffDrive());
    }
    public void arcadeDrive(Joystick js) {
    	double x = js.getY();
    	double rotate = js.getTwist(); 
    	diffDrive.arcadeDrive(x, rotate);
    } 
    //Method for Shifting
    public void shiftGears() {
    	if(shiftSol.get() == DoubleSolenoid.Value.kForward) {
    		shiftSol.set(DoubleSolenoid.Value.kReverse);
    	}else {
    		shiftSol.set(DoubleSolenoid.Value.kForward);
    	}
    }
    //Control NIDEC Motor
    public void controlNidec(Joystick js) {
    	double value = js.getThrottle();
    	if(value == 0) {
    		  motor.disable(); 
    	} else { 
    		  motor.set(value);
    	}
    }
    
}

