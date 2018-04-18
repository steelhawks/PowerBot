package org.usfirst.frc.team2601.robot.subsystems;

import org.usfirst.frc.team2601.robot.ButtonBoard;
import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.F310;
import org.usfirst.frc.team2601.robot.commands.ArmPivot.PivotJS;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.AnalogTriggerOutput;
import edu.wpi.first.wpilibj.AnalogTriggerOutput.AnalogTriggerType;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */

public class ArmPivot extends PIDSubsystem {
	Constants constants = Constants.getInstance();
	public WPI_TalonSRX armPivotM = new WPI_TalonSRX(constants.pivotMPort);
	
	//public DigitalInput lowerLimit = new DigitalInput(constants.lowerPivotLimitPort);
	public DigitalInput upperLimit = new DigitalInput(constants.upperPivotLimitPort);
	
	private PIDController controller;
	private double rampBand;
	public ArmPivot() {
		super("ArmPivot", 2.0, 0.0, 0.0);
		setAbsoluteTolerance(0.05);
		getPIDController().setContinuous(false);
		constants.up = true;
		armPivotM.getSensorCollection().setQuadraturePosition(0,0);
		/*	
	 	armPivotM.set(ControlMode.Position, 0); 
		armPivotM.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,0);
		armPivotM.configClosedloopRamp(0,0);
		armPivotM.setInverted(true);
		
		//armPivotM.configNominalOutputForward(1, 0);
		//armPivotM.configNominalOutputReverse(-1, 0);
    	//shooterMotor.setForwardSoftLimit(+15.0);
    	//shooterMotor.setReverseSoftLimit(-15.0);
    	//shooterMotor.setPosition(0);
		armPivotM.config_kF(0, 0, 0);
		armPivotM.config_kP(0, 16, 0);
		armPivotM.config_kI(0, 0.03125, 0);
		armPivotM.config_kD(0, 0.012, 0);    	
		armPivotM.selectProfileSlot(0,0);
    	
		//armPivotM.set(0.0);
		armPivotM.setSafetyEnabled(false);
		int startPos = armPivotM.getSelectedSensorPosition(0);
		armPivotM.setSelectedSensorPosition(startPos, 0, 0);//0,10
*/		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new PivotJS());
    }
    public void autoPivot(double pos, boolean up) {
		double pivotEncPos = armPivotM.getSensorCollection().getQuadraturePosition();
    	if(constants.autonBot == false) {
    		if(up == true) {
    			armPivotM.set(-0.5);
        		if (pivotEncPos > pos) {
					constants.autonArm = true;
				}
				if (pivotEncPos <= pos) {
					constants.autonArm = false;
				}
    		}else if (up == false){
    			armPivotM.set(0.5);
    	    	if (-pivotEncPos > pos) {
    				constants.autonArm = true;
    			}
    			if (-pivotEncPos <= pos) {//1000
    				constants.autonArm = false;
    			}	
    		}
    	}
    }
    public void armPivotGamepad(F310 js) {
		double pivotEncPos = armPivotM.getSensorCollection().getQuadraturePosition();
    	double y = 0;
    	if(upperLimit.get() == false) {
			if(js.getRightY() < 0) {
				y = 0;
			}else {
				y = js.getRightY();
			}
		}else if (pivotEncPos < -950) {
			if(js.getRightY() > 0) {
				y = 0;	
			}else {
				y = js.getRightY();
			}
		}else {
			y = js.getRightY();
			if(y < 0) {	
	    		y = 0.7*y;
	    	}else if (y > 0) {
	    		y = 0.4*y;
	    	}else {
	    		y = 0;
	    	}
		}
		armPivotM.set(y);
    	if(upperLimit.get() == false) {
    		armPivotM.getSensorCollection().setQuadraturePosition(0,0);
    	}
    	//SmartDashboard.putBoolean("LowPivotLimit", lowerLimit.get());//elevatorPos.elevatorM4.getSensorCollection().isFwdLimitSwitchClosed());//lowerLimit.get());
    	SmartDashboard.putBoolean("UpperPivotLimit", upperLimit.get());//elevatorM4.getSensorCollection().isRevLimitSwitchClosed());//upperLimit.get());
    	SmartDashboard.putNumber("PivotEncPos", pivotEncPos);
    }
    public void stopPivotMotors() {
    	armPivotM.set(0);
    }
	protected double returnPIDInput() {
		return armPivotM.getSensorCollection().getQuadraturePosition();
	}
	public void usePIDOutput(double output) {
		armPivotM.pidWrite(output);
	}    
}