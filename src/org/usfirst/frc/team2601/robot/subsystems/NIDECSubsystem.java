package org.usfirst.frc.team2601.robot.subsystems;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.commands.ControlNIDEC;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.NidecBrushless;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class NIDECSubsystem extends Subsystem {

	Constants constants = Constants.getInstance();
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	//Nidec Brushless Motor
	NidecBrushless motor = new NidecBrushless(constants.brushlessPWMPort, constants.brushlessDIOPort);//(PWM Channel, DIO Channel)
	PowerDistributionPanel pdp = new PowerDistributionPanel();
	
	public NIDECSubsystem() {
		
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ControlNIDEC());
    }
  //Control NIDEC Motor
    public void controlNidec(Joystick js) {
    	double NidecAmp = pdp.getCurrent(8);
    	SmartDashboard.putNumber("NidecAmp", NidecAmp);
    	
    	double value = js.getThrottle();
    	if(value == 0) {
    		 motor.disable(); 
    	} else { 
    		 motor.enable();
    		 motor.set(value);
    	} 
    }
}

