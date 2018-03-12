package org.usfirst.frc.team2601.robot.subsystems;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.commands.elevator.ElevatorJS;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogOutput;
import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.AnalogTriggerOutput;
import edu.wpi.first.wpilibj.AnalogTriggerOutput.AnalogTriggerType;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends Subsystem {
	Constants constants = Constants.getInstance();
	//Motors
	SpeedControllerGroup elevatorJSGroup1;
	SpeedControllerGroup elevatorJSGroup2;
	
	WPI_TalonSRX elevatorM1 = new WPI_TalonSRX(constants.elevatorM1Port);
	WPI_TalonSRX elevatorM2 = new WPI_TalonSRX(constants.elevatorM2Port);
	WPI_TalonSRX elevatorM3 = new WPI_TalonSRX(constants.elevatorM3Port);
	public WPI_TalonSRX elevatorM4 = new WPI_TalonSRX(constants.elevatorM4Port);
	SpeedControllerGroup elevatorGroup = new SpeedControllerGroup(elevatorM1, elevatorM2, elevatorM3, elevatorM4);
	
	DigitalInput upperLimit = new DigitalInput(constants.upperLimitPort);
	DigitalInput lowerLimit = new DigitalInput(constants.lowerLimitPort);
	
	/*public AnalogInput ir1below = new AnalogInput(0);
	public AnalogInput ir1above = new AnalogInput(1);
	*/
    public void initDefaultCommand() {
    	setDefaultCommand(new ElevatorJS());
    }
    //Constructor for the subsystem
    public Elevator() {
    	if (constants.autonBot == false) {
        	elevatorJSGroup1 = new SpeedControllerGroup(elevatorM1, elevatorM2);
	    	elevatorJSGroup2 = new SpeedControllerGroup(elevatorM3, elevatorM4);
	    	elevatorM4.getSensorCollection().setQuadraturePosition(0,0);
    	}  	
    }
    //Method for using the elevator motor for auton
    public void autoElevator(double pos, boolean up) {
    	double elevatorPos = elevatorM4.getSensorCollection().getQuadraturePosition();
    	if(constants.autonBot == false) {
    		if(up == true) {
    			elevatorJSGroup1.set(1);
    	    	elevatorJSGroup2.set(-1);
    	    	if (-elevatorPos > pos) {
					constants.autonEl = true;
				}
				if (-elevatorPos <= pos) {
					constants.autonEl = false;
				}
    		}else if (up == false){
    			elevatorJSGroup1.set(-1);
    	    	elevatorJSGroup2.set(1);
    	    	if (elevatorPos < pos) {
    				constants.autonEl = true;
    			}
    			if (elevatorPos >= pos) {
    				constants.autonEl = false;
    			}	
    		}
    	}
    }
    public void stopMotors() {
    	elevatorGroup.set(0);
    }
  //Method for using the motors
    public void elevatorMotors(Joystick js) {
    	if(constants.autonBot == false) {
    		double elevatorPos = elevatorM4.getSensorCollection().getQuadraturePosition();
    		double y;
    		
    		if(elevatorPos > constants.elevatorUpperLimit) {	
    			if(js.getY() > 0) {
    				y = 0;
    			}else {
        			y = js.getY();
    			}
    		}else if(elevatorPos < constants.elevatorLowerLimit){
    			if(js.getY() < 0) {
    				y = 0;
    			}else {
        			y = js.getY();
    			}
    		}else {
		    	y = js.getY();
			}
	    	elevatorJSGroup1.set(y);
	    	elevatorJSGroup2.set(-y);
    		
		    SmartDashboard.putNumber("TalonEnc" , elevatorPos);
	    	
        }
	}
    public boolean elevatorLimit() {
    	if(upperLimit.get() == true || lowerLimit.get() == true) {
    		stopMotors();
    		return true;
    	}else {
    		return false;
    	}
    }
    
    public boolean isArmsBelow(){
    	/*int i=0;
    	while(true){
	   		if((ir1below.getValue() > 650) == true){//change threshold value
	    		i++;
	    		if(i > 10){
	    			return true;
	    		}
	    	}else{
    			return false;
    		}
    	}*/	
    	return true;
    }
    public boolean isArmsAbove(){
    	/*int i=0;
    	while(true){
	   		if((ir1above.getValue() > 650) == true){//change threshold value
	    		i++;
	    		if(i > 10){
	    			return true;
	    		}
	    	}else{
    			return false;
    		}
    	}*/	
    	return true;
    }
}


