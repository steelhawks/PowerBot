package org.usfirst.frc.team2601.robot.subsystems;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.commands.arm.ArmMotors;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class Arms extends Subsystem {
	Constants constants = Constants.getInstance();
	//Motors 
	public static WPI_TalonSRX leftArmM; 
	public static WPI_TalonSRX rightArmM;
	public static boolean slowIntake;
	DoubleSolenoid armSol;
	public static AnalogInput cubeir = new AnalogInput(2);
	//Set the default command for the subsystem
    public void initDefaultCommand() {
    	setDefaultCommand(new ArmMotors());
    }     
    //Constructor for the subsystem
    public Arms() {
    	if (constants.autonBot == false) {
    		leftArmM = new WPI_TalonSRX(constants.leftArmMPort);
    		rightArmM = new WPI_TalonSRX(constants.rightArmMPort);
    		armSol = new DoubleSolenoid(constants.armSolPortOn, constants.armSolPortOff);
    		armSol.set(DoubleSolenoid.Value.kReverse);
    	}
    }
    //Method for using the motors through a joystick
    public void armMotors(Joystick js) {
    	double rot = 0;
    	if(constants.autonBot == false) {
        	rot = js.getTwist();
	    	leftArmM.set(rot);
	    	rightArmM.set(-rot);
	    	//SmartDashboard.putNumber("IRValue", cubeir.getValue());
        }
	}
    //Method for using the arm intake pistons
    public void armIntakePistons() {
    	if(armSol.get() == DoubleSolenoid.Value.kForward) {
    		armSol.set(DoubleSolenoid.Value.kReverse);
    	}else {
    		armSol.set(DoubleSolenoid.Value.kForward);
    	}
    }
    public void armIntakeButton(){
    	if(leftArmM.get() == 0 && rightArmM.get() == 0){
    		leftArmM.set(0.75);
    		rightArmM.set(-0.75);
    	}else{
    		leftArmM.set(0);
    		rightArmM.set(0);
    	}
    }
    public void armShootButton() {
    	if(leftArmM.get() == 0 && rightArmM.get() == 0){
    		leftArmM.set(-1);
    		rightArmM.set(1);
    	}else{
    		leftArmM.set(0);
    		rightArmM.set(0);
    	}
    }
    public void armStop() {
    	leftArmM.set(0);
    	rightArmM.set(0);
    }
    public void rollerOuttake(boolean fast) {
    	if(fast == false) {
	    	leftArmM.set(-0.75);
	    	rightArmM.set(0.75);
    	}else {
    		leftArmM.set(-1.0);
	    	rightArmM.set(1.0);
    	}	
    }
    public void rollerIntake(boolean fast) {
    	if(fast == false) {
	    	leftArmM.set(0.75);
	    	rightArmM.set(-0.75);
    	}else {
    		leftArmM.set(1.0);
	    	rightArmM.set(-1.0);
    	}	
    }
    public static boolean AutonWait(double timeout) {
    	for(int i = 0; i > timeout;i++) {
    		return false;
    	}
    	return true;
    }
    public static boolean isCubeIn(){
        	int i=0;
        	while(true){
    	   		if((cubeir.getValue() > 1400) == true && (cubeir.getValue() < 2400) == true){//change threshold value
    	    		i++;
    	    		//if(i > 5){
    	    			//rollerSlowIntake(true);
    	    			//slowIntake = true;
    	    			return true;//AutonWait(120);
    	   			}else{
    	    			slowIntake = false;
    	   				return false;
    	    		}
    	   		//}	
        	}	
    }
    public static void stopMotors() {
    	leftArmM.set(0);
		rightArmM.set(0);
    
    }
}