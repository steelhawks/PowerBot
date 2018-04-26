package org.usfirst.frc.team2601.robot.subsystems;

import org.usfirst.frc.team2601.robot.ButtonBoard;
import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.F310;
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
	public WPI_TalonSRX elevatorM3 = new WPI_TalonSRX(constants.elevatorM3Port);
	public WPI_TalonSRX elevatorM4 = new WPI_TalonSRX(constants.elevatorM4Port);
	
	SpeedControllerGroup elevatorGroup = new SpeedControllerGroup(elevatorM1, elevatorM2, elevatorM3, elevatorM4);
	
	DigitalInput upperLimit = new DigitalInput(constants.upperLimitPort);
	public DigitalInput lowerLimit = new DigitalInput(constants.lowerLimitPort);
    public void initDefaultCommand() {
    	setDefaultCommand(new ElevatorJS(true));
    }
    //Constructor for the subsystem
    public Elevator() {
    	if (constants.autonBot == false) {
    		/*elevatorM1.set(ControlMode.Follower,constants.elevatorM4Port);
    		elevatorM2.set(ControlMode.Follower,constants.elevatorM4Port);
    		elevatorM3.set(ControlMode.Follower,constants.elevatorM4Port);
    		*/
        	//elevatorJSGroup1 = new SpeedControllerGroup(elevatorM1, elevatorM2);
	    	//elevatorJSGroup2 = new SpeedControllerGroup(elevatorM3, elevatorM4);
	    	elevatorM4.getSensorCollection().setQuadraturePosition(0,0);
    	}  	
    }
    //Method for using the elevator motor for auton
    public void autoElevator(double pos, boolean up) {
    	double elevatorPos = elevatorM4.getSensorCollection().getQuadraturePosition();
    	if(constants.autonBot == false) {
    		if(up == true) {
    			//elevatorJSGroup1.set(0.5);
    	    	//elevatorJSGroup2.set(-0.5);
    			//elevatorM4.set(-0.75);
    			elevatorGroup.set(-1.0);
        		if (-elevatorPos > pos) {
					constants.autonEl = true;
				}
				if (-elevatorPos <= pos) {
					constants.autonEl = false;
				}
    		}else if (up == false){
    			elevatorGroup.set(0.65);
    	    	if (elevatorPos > pos) {
    				constants.autonEl = true;
    			}
    			if (elevatorPos <= pos) {
    				constants.autonEl = false;
    			}	
    		}
    	}
    }
    public void autoScaleElevator(double pos, boolean up) {
    	double elevatorPos = elevatorM4.getSensorCollection().getQuadraturePosition();
    	if(constants.autonBot == false) {
    		if(up == true) {
    			//elevatorJSGroup1.set(0.5);
    	    	//elevatorJSGroup2.set(-0.5);
    			//elevatorM4.set(-0.75);
    			elevatorGroup.set(-0.65);//up speed
        		if (-elevatorPos > pos) {
					constants.autonEl = true;
				}
				if (-elevatorPos <= pos) {
					constants.autonEl = false;
				}
    		}else if (up == false){
    			//elevatorM4.set(0.75);
    			//elevatorJSGroup1.set(-0.5);
    	    	//elevatorJSGroup2.set(0.5);
    			elevatorGroup.set(0.65);
    	    	if (elevatorPos > pos) {
    				constants.autonEl = true;
    			}
    			if (elevatorPos <= pos) {
    				constants.autonEl = false;
    			}	
    		}
    	}
    }
    public void stopMotors() {
    	//elevatorM4.set(0);
    	elevatorGroup.set(0);
    }
  //Method for using the motors
    public void elevatorMotorsJS(Joystick js) {
    	if(constants.autonBot == false) {
    		//double elevatorPos = elevatorM4.getSensorCollection().getQuadraturePosition();
    		double y = 0;
    		y = js.getY();	
	    	elevatorGroup.set(y);
    		//elevatorM4.set(y);
		    //SmartDashboard.putNumber("TalonEnc" , elevatorPos);
	    	
        }
	}
    public void elevatorMotorsGamepad(F310 js) {
    	if(constants.autonBot == false) {
    		double elevatorPos = elevatorM4.getSensorCollection().getQuadraturePosition();
	    	double y = 0;
    		if(upperLimit.get() == false) {
    			if(js.getLeftY() < 0) {
    				y = 0;
    			}else {
    				y = js.getLeftY();
    			}
    		}else if (lowerLimit.get() == false) {
    			if(js.getLeftY() > 0) {
    				y = 0;	
    			}else {
    				constants.slowBtnOn = true;
    				y = js.getLeftY();
    			}
    		}else {
    			y = js.getLeftY();
    		}
    		
    		elevatorGroup.set(y);
    	    if(lowerLimit.get() == false) {
	    		elevatorM4.getSensorCollection().setQuadraturePosition(0,0);
    		}
		    SmartDashboard.putNumber("TalonEnc" , elevatorPos);
	    	SmartDashboard.putBoolean("LowLimit", lowerLimit.get());//elevatorPos.elevatorM4.getSensorCollection().isFwdLimitSwitchClosed());//lowerLimit.get());
	    	SmartDashboard.putBoolean("UpperLimit", upperLimit.get());//elevatorM4.getSensorCollection().isRevLimitSwitchClosed());//upperLimit.get());
        }
	}
    public void elevatorMotorsBB(boolean up) {
    	if(constants.autonBot == false) {
    		//double elevatorPos = elevatorM4.getSensorCollection().getQuadraturePosition();
    		double y = 0;
      		//y = js.getRightY();
 	    	if(up == true) {
 	    		//elevatorM4.set(1);
 	    		elevatorGroup.set(1);
 	    	}
 	    	if(up == false) {
 	    		//elevatorM4.set(-1);
 	    		elevatorGroup.set(-1);
 	    	}
		    //SmartDashboard.putNumber("TalonEnc" , elevatorPos);
	    	
        }
    }
    public void elDown() {
    	if (lowerLimit.get() == false) {
			constants.slowBtnOn = false;
		}else {
			//elevatorM4.set(0.25);
			elevatorGroup.set(0.25);
		}
    }
    public void elUp() {
    	//elevatorM4.set(-0.75);
    }
}


