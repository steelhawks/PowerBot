package org.usfirst.frc.team2601.robot.subsystems;

import org.usfirst.frc.team2601.robot.ButtonBoard;
import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.F310;
import org.usfirst.frc.team2601.robot.commands.ArmPivot.ArmPivotCommand;
import org.usfirst.frc.team2601.robot.commands.ArmPivot.PivotJS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.AnalogTriggerOutput;
import edu.wpi.first.wpilibj.AnalogTriggerOutput.AnalogTriggerType;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */

public class ArmPivot extends Subsystem {
	Constants constants = Constants.getInstance();

	//AnalogInput pivotInput = new AnalogInput(constants.pivotSensorPort);
	AnalogTrigger pivotSensor = new AnalogTrigger(constants.pivotSensorPort);
	public Counter rotCounter = new Counter(pivotSensor);
	//AnalogTriggerOutput pivotOutput;
	Spark armPivotM1 = new Spark(constants.pivotM1Port);
	//Spark armPivotM2 = new Spark(constants.pivotM2Port);
	
	DigitalInput autoLimit = new DigitalInput(constants.autoPivotLimitPort);
	
	//public SpeedControllerGroup pivotGroup = new SpeedControllerGroup(armPivotM1, armPivotM2);//3,4
	
	public ArmPivot() {
		constants.up = true;
		//pivotSensor.setLimitsRaw(2048, 3200);
		pivotSensor.setLimitsVoltage(3.5, 3.8);
		//pivotOutput = pivotSensor.createOutput(AnalogTriggerOutput.AnalogTriggerType.kRisingPulse);
		//rotCounter = new Counter(pivotOutput);
		rotCounter.setUpSource(pivotSensor, AnalogTriggerOutput.AnalogTriggerType.kRisingPulse);
		rotCounter.setUpSourceEdge(true, false);
		rotCounter.reset();
		rotCounter.setUpdateWhenEmpty(false);
	
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new PivotJS());
    	//setDefaultCommand(new ArmPivotCommand());
    }
    public void armPivotJS(Joystick js) {
    	double pivotPos = rotCounter.get();
    	double y = 0;
    	y = js.getY();
    	armPivotM1.set(y);
    	//pivotGroup.set(y);
    	SmartDashboard.putNumber("Hall", pivotPos);
    }
    public void armPivotGamepad(F310 js) {
    	double pivotPos = rotCounter.get();
    	double y = 0;
    	y = js.getRightY();	
    	armPivotM1.set(y);
    	//pivotGroup.set(y);
    	SmartDashboard.putNumber("Hall", pivotPos);
    	SmartDashboard.putBoolean("PivotLimit", autoLimit.get());
    }
    public void armPivotBB(ButtonBoard js) {
    	double pivotPos = rotCounter.get();
    	double y = 0;
    	y = js.getLeftY();	
    	armPivotM1.set(y);
    	//pivotGroup.set(y);
    	SmartDashboard.putNumber("Hall", pivotPos);
    }
    public void armPivotDown(double pos) {
        	double pivotPos = rotCounter.get();
        	//pivotGroup.set(-1);
        	armPivotM1.set(-1);        	
        	if (pivotPos < pos) {
				constants.autonArm = false;
			}
			if (pivotPos >= pos) {
				constants.autonArm = true;
			}	
    }
    public void armPivotUp(double pos) {
        double pivotPos = rotCounter.get();
        int i = 0;
    	//pivotGroup.set(1);
        armPivotM1.set(1);        	
        if (pivotPos < pos) {
			constants.autonArm = false;
		}
        if (pivotPos >= pos) {
        	i++;
    		if(i > 10){
    			//return true;
    		}
			constants.autonArm = true;
		}
    }
    public void stopPivotMotors() {
    	//pivotGroup.set(0);
    	armPivotM1.set(0);        	
    }
    public void autoArmPivotDown() {
    	armPivotM1.set(-1);
    	if(autoLimit.get() == false) {
    		constants.autonArm = true;
    	}else {
    		constants.autonArm = false;
    	}
    }
    public void ghettoAutoPivot(double pos, boolean up) {
    	double pivotPos = rotCounter.get();
    	if(constants.autonBot == false) {
    		if(up == true) {
    	    	//pivotGroup.set(-1);
    			armPivotM1.set(-1);        	
    			if (pivotPos < pos) {
    				constants.autonArm = false;
    			}
    			if (pivotPos >= pos) {
    				constants.autonArm = true;
    			}	
    		}else if (up == false){
    	    	//pivotGroup.set(1);
    			armPivotM1.set(1);       
    			if (pivotPos < pos) {
    				constants.autonArm = false;
    			}
    			if (pivotPos >= pos) {
    				constants.autonArm = true;
    			}	
    		}
    	}
    }
    
}