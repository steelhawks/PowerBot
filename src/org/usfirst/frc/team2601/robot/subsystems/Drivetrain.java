package org.usfirst.frc.team2601.robot.subsystems;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.commands.ControlNIDEC;
import org.usfirst.frc.team2601.robot.commands.drivetrain.DiffDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.NidecBrushless;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends Subsystem implements PIDOutput {
		
	public Drivetrain( SpeedControllerGroup leftGroup ,
					   SpeedControllerGroup rightGroup,
					   //
					   DifferentialDrive diffDrive,
					   //
					   DoubleSolenoid shiftSol,
					   //SENSORS
					   //Encoders
					   Encoder leftEncoder,
					   Encoder rightEncoder
		) {

		this.leftGroup = leftGroup;
		this.rightGroup = rightGroup;
		this.diffDrive = diffDrive;
		
		this.shiftSol = shiftSol;
		
		this.leftEncoder = leftEncoder;
		this.rightEncoder = rightEncoder;
		
		this.init();
	}
	
	
	
	
	private void init() {
		
		shiftSol.set(DoubleSolenoid.Value.kReverse);//Setting to low gear by default
		//Resetting encoder values
		leftEncoder.reset();
		rightEncoder.reset();
		ultra.setEnabled(true);
		ultra.setAutomaticMode(true);
		
		//Resetting gyro values
		gyro.reset();
		gyro.zeroYaw();
		
		pid = new PIDController(kP,kI,kD,kF,gyro, this);
		pid.disable();
		pid.setInputRange(-180.0f, 180.0f);
		pid.setOutputRange(-0.5f, 0.5f);
		pid.setAbsoluteTolerance(kToleranceDegrees);
		pid.setContinuous(true);

	}
	
    public void initDefaultCommand() {
	    // Set the default command for a subsystem here.
	    	setDefaultCommand(new DiffDrive());//Default command for driving
    }

    //Method for Driving 
    public void arcadeDrive(Joystick js) {
    	
	    	double x = js.getY();
	    	double rotate = js.getTwist(); 
	    	diffDrive.arcadeDrive(x, rotate); 
    	
		SmartDashboard.putNumber("GyroAngle", getGyroAngle());
		SmartDashboard.putNumber("GetBoardAxis", gyro.getBoardYawAxis().board_axis.getValue());
		
		SmartDashboard.putNumber("Ultra Distance", getAnalogUltraDistance());
		SmartDashboard.putNumber("Vex Distance", getUltraDistanceInches());
		// Output Encoder values to SD
		SmartDashboard.putNumber("LeftEncValue", getLeftEncoderRate());
		SmartDashboard.putNumber("LeftEncDist", getLeftEncoderDist());
		SmartDashboard.putNumber("RightEncValue", getRightEncoderRate());
		SmartDashboard.putNumber("RightEncDist", getRightEncoderDist());
    
    }

    //Method for Shifting
    public void shiftGears() {
    	
	    	if(shiftSol.get() == DoubleSolenoid.Value.kForward) {
	    		shiftSol.set(DoubleSolenoid.Value.kReverse);
	    	}else {
	    		shiftSol.set(DoubleSolenoid.Value.kForward);
	    	}
    }
    
    public void ultraGyroForward(double dist, double speed, boolean forward) {
    		double gyroAngle = getGyroAngle();
		double ultraDist = getUltraDistanceInches();
		if(forward == true) {
			diffDrive.tankDrive(speed, gyroAngle * kPGyro);
		}else {
			diffDrive.tankDrive(-speed, gyroAngle * kPGyro);
		}
	
		if (ultraDist <= dist + constants.ultraTolerance) {
			constants.ultraBool = true;
		}
		if (ultraDist > dist + constants.ultraTolerance) {
			constants.ultraBool = false;
		}
    }
    
    public void turn(double target, boolean left){ 
    		double gyroAngle = getGyroAngle();
	    if(left == true) {	
	    		if(gyroAngle <= -target) {
	    			leftGroup.set(0);
	    			rightGroup.set(0);
	    			constants.gyroTurnBool = true;
		    	}else if(gyroAngle <= -target+20){
		    		leftGroup.set(-0.25);
	    			rightGroup.set(-0.25);
	    			constants.gyroTurnBool = false;
		    	}else {
		    		leftGroup.set(-1.0);
	    			rightGroup.set(-1.0);
	    			constants.gyroTurnBool = false;
		    	}
	    }else {
		    	if(gyroAngle >= target) {
		    		leftGroup.set(0);
		    		rightGroup.set(0);
		    		constants.gyroTurnBool = true;
		    	}else if(gyroAngle >= target-20){
		    		leftGroup.set(0.25);
	    			rightGroup.set(0.25);
	    			constants.gyroTurnBool = false;
		    	}else {
		    		leftGroup.set(1.0);
	    			rightGroup.set(1.0);
	    			constants.gyroTurnBool = false;
		    	}
	    }
    }

    public double getAnalogUltraDistance(){
	    	double raw = ultraA.getVoltage();
	    	return (raw/0.009766);//5 is voltage range, 512 is distance range
    }
    
    public void stopMotors() {
	    	leftGroup.set(0);
	    	rightGroup.set(0);
    }
    
    public void pidWrite(double output) {
    		rotateToAngleRate = output;
    }
    
    public double getUltraDistanceInches() {
    		return ultra.getRangeInches();
    }
    
    public double getLeftEncoderRate() {
    		return leftEncoder.getRate();
    }
    
    public double getRightEncoderRate() {
    		return rightEncoder.getRate();
    }
    
    public double getLeftEncoderDist() {
    		return leftEncoder.getDistance();
    }
    
    public double getRightEncoderDist() {
    		return rightEncoder.getDistance();
    }
    
    public double getGyroAngle() {
    		return gyro.getAngle();
    }
    
    public static void resetGyro() {
    		gyro.reset();
    		gyro.zeroYaw();
    }
    
    //--------------------------------------------------------------------------
	static Constants constants = Constants.getInstance();
	
	//Speed Controller Group
	private final SpeedControllerGroup leftGroup;
	private final SpeedControllerGroup rightGroup;

	//Drivetrain Type (Tank)
	private final DifferentialDrive diffDrive;
	
	//Solenoid
	private final DoubleSolenoid shiftSol; //Shifting

	//SENSORS
	//Encoders
	private final Encoder leftEncoder;
	private final Encoder rightEncoder;
	
	//Gyro
	public static AHRS gyro;
	static final double kPGyro = 0.25;
	
	//Ultrasonic
	private final AnalogInput ultraA = new AnalogInput(1);
	private final Ultrasonic ultra = new Ultrasonic(constants.ultraPortIn, constants.ultraPortOut);
	private int ultraValue;
	
	//PID Controllers
	PIDController pid;
	double rotateToAngleRate;
	
	// constants
	static final double kP = 0.00;
	static final double kI = 0.00;
	static final double kD = 0.00;
	static final double kF = 0.00;
	
	static final double kToleranceDegrees = 2.0f;
    
    
}
