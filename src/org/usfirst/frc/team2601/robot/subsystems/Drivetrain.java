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
	
	static Constants constants = Constants.getInstance();
	
	//Left Motors
	public WPI_TalonSRX frontLeftM = new WPI_TalonSRX(constants.frontLeftMPort);
	public WPI_TalonSRX midLeftM = new WPI_TalonSRX(constants.midLeftMPort);
	public WPI_TalonSRX backLeftM = new WPI_TalonSRX(constants.backLeftMPort);
	//Right Motors
	public WPI_TalonSRX frontRightM = new WPI_TalonSRX(constants.frontRightMPort);
	public WPI_TalonSRX midRightM = new WPI_TalonSRX(constants.midRightMPort);
	public WPI_TalonSRX backRightM = new WPI_TalonSRX(constants.backRightMPort);
	//Speed Controller Group
	public SpeedControllerGroup leftGroup = new SpeedControllerGroup(frontLeftM, midLeftM, backLeftM);
	public SpeedControllerGroup rightGroup = new SpeedControllerGroup(frontRightM, midRightM, backRightM);
	//Drivetrain Type (Tank)
	public DifferentialDrive diffDrive = new DifferentialDrive(leftGroup, rightGroup);
	//Solenoids (Shifting)
	public DoubleSolenoid shiftSol = new DoubleSolenoid(constants.shiftSolPortOn, constants.shiftSolPortOff);
	//Encoders
	public Encoder leftEnc = new Encoder(constants.leftEncPortA, constants.leftEncPortB, false, EncodingType.k4X);
	public Encoder rightEnc = new Encoder(constants.rightEncPortA, constants.rightEncPortB, false, EncodingType.k4X);
	//Gyro
	public static AHRS gyro = new AHRS(SPI.Port.kMXP);
	double kPGyro = constants.kPGyro;
	//Ultrasonic
	public AnalogInput ultraA = new AnalogInput(1);
	public Ultrasonic ultra = new Ultrasonic(constants.ultraPortIn, constants.ultraPortOut);
	double ultraValue;
	//PID Controllers
	PIDController pid;
	double rotateToAngleRate;
	
	static final double kP = 0.00;
	static final double kI = 0.00;
	static final double kD = 0.00;
	static final double kF = 0.00;
	
	static final double kToleranceDegrees = 2.0f;
	//Constructor for the subsystem
	public Drivetrain() {
		shiftSol.set(DoubleSolenoid.Value.kForward);//Setting to low gear by default
		//Resetting encoder values
		leftEnc.reset();
		rightEnc.reset();
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
	//Set default command for the subsystem
    public void initDefaultCommand() {
    	setDefaultCommand(new DiffDrive());
    }
    //Method for driving 
    public void arcadeDrive(Joystick js) {
    	double x = js.getY();
    	double rotate = js.getTwist(); 
    	diffDrive.arcadeDrive(x, rotate); 
    	//Output gyro values to SB
		SmartDashboard.putNumber("GyroAngle", getGyroAngle());
		SmartDashboard.putNumber("GetBoardAxis", gyro.getBoardYawAxis().board_axis.getValue());
		//Output ultrasonic values to SB
		SmartDashboard.putNumber("Ultra Distance", getAnalogUltraDistance());
		SmartDashboard.putNumber("Vex Ultra Distance", getUltraDistanceInches());
		// Output encoder values to SB
		SmartDashboard.putNumber("LeftEncValue", getLeftEncoderRate());
		SmartDashboard.putNumber("LeftEncDist", getLeftEncoderDist());
		SmartDashboard.putNumber("RightEncValue", getRightEncoderRate());
		SmartDashboard.putNumber("RightEncDist", getRightEncoderDist());
    }
    //Method for shifting
    public void shiftGears() {
    	if(shiftSol.get() == DoubleSolenoid.Value.kForward) {
    		shiftSol.set(DoubleSolenoid.Value.kReverse);
    	}else {
    		shiftSol.set(DoubleSolenoid.Value.kForward);
    	}
    }
    //Method for moving the robot forward in autonomous
    public void ultraGyroMoveStraight(double dist, double speed, boolean forward) {
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
    //Method for turning the robot in autonomous
    public void turn(double target, boolean left){ 
    	double gyroAngle = getGyroAngle();
	    if(left == true) {	
    		if(gyroAngle <= -target) {
    			leftGroup.set(0);
    			rightGroup.set(0);
    			constants.gyroTurnBool = true;
	    	}else if(gyroAngle <= -target+constants.gyroSlowAngle){
	    		leftGroup.set(-constants.gyroSlowSpeed);
    			rightGroup.set(-constants.gyroSlowSpeed);
    			constants.gyroTurnBool = false;
	    	}else {
	    		leftGroup.set(-constants.gyroStartSpeed);
    			rightGroup.set(-constants.gyroStartSpeed);
    			constants.gyroTurnBool = false;
	    	}
	    }else {
	    	if(gyroAngle >= target) {
	    		leftGroup.set(0);
	    		rightGroup.set(0);
	    		constants.gyroTurnBool = true;
	    	}else if(gyroAngle >= target-constants.gyroSlowAngle){
	    		leftGroup.set(constants.gyroSlowSpeed);
    			rightGroup.set(constants.gyroSlowSpeed);
    			constants.gyroTurnBool = false;
	    	}else {
	    		leftGroup.set(constants.gyroStartSpeed);
    			rightGroup.set(constants.gyroStartSpeed);
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
    	return leftEnc.getRate();
    }
    public double getRightEncoderRate() {
    	return rightEnc.getRate();
    }
    public double getLeftEncoderDist() {
    	return leftEnc.getDistance();
    }
    public double getRightEncoderDist() {
    	return rightEnc.getDistance();
    }
    public double getGyroAngle() {
    	return gyro.getAngle();
    }
    public static void resetGyro() {
    	gyro.reset();
    	gyro.zeroYaw();
    }
}
