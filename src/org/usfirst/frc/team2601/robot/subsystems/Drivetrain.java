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
	//SENSORS
	//Encoders
	public Encoder leftEnc = new Encoder(constants.leftEncPortA, constants.leftEncPortB, false, EncodingType.k4X);
	double leftEncDist;
	double leftEncVal;
	
	public Encoder rightEnc = new Encoder(constants.rightEncPortA, constants.rightEncPortB, false, EncodingType.k4X);
	double rightEncDist;
	double rightEncVal;
	//Gyro
	public AHRS gyro = new AHRS(SPI.Port.kMXP);
	double gyroRate;
	double gyroAngle;
	double kPGyro = 0.25;
	//Ultrasonic
	public AnalogInput ultraA = new AnalogInput(1);
	public Ultrasonic ultra = new Ultrasonic(constants.ultraPortIn, constants.ultraPortOut);
	double ultraValue;
	//PID Controllers
	PIDController turnController;
	double rotateToAngleRate;
	
	static final double kP = 0.03;
	static final double kI = 0.00;
	static final double kD = 0.00;
	static final double kF = 0.00;
	
	static final double kToleranceDegrees = 2.0f;
	public Drivetrain() {
		shiftSol.set(DoubleSolenoid.Value.kReverse);//Setting to low gear by default
		//Resetting encoder values
		leftEnc.reset();
		rightEnc.reset();
		ultra.setEnabled(true);
		ultra.setAutomaticMode(true);
		
		//Resetting gyro values
		gyro.reset();
		gyro.zeroYaw();
		
		turnController = new PIDController(kP,kI,kD,kF,gyro,(PIDOutput) this);
		turnController.setInputRange(-180.0f, 180.0f);
		turnController.setOutputRange(-1.0, 1.0);
		turnController.setAbsoluteTolerance(kToleranceDegrees);
		turnController.setContinuous(true);
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
   
    	gyroAngle = gyro.getAngle();
		SmartDashboard.putNumber("GyroAngle", gyroAngle);
		SmartDashboard.putNumber("GetBoardAxis", gyro.getBoardYawAxis().board_axis.getValue());
		
		SmartDashboard.putNumber("Ultra Distance", ultraA.getVoltage()/.009766);
		
		ultraValue = ultra.getRangeInches();
		SmartDashboard.putNumber("Vex Distance", ultraValue);
		// Output Enc values to SD
		leftEncVal = leftEnc.getRate();
		SmartDashboard.putNumber("LeftEncValue", leftEncVal);

		leftEncDist = leftEnc.getDistance();
		SmartDashboard.putNumber("LeftEncDist", leftEncDist);

		rightEncVal = rightEnc.getRate();
		SmartDashboard.putNumber("RightEncValue", rightEncVal);

		rightEncDist = rightEnc.getDistance();
		SmartDashboard.putNumber("RightEncDist", rightEncDist);

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
    	gyroAngle = gyro.getAngle();
		double ultraDist = ultra.getRangeInches();
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
    public void turn(double degreeSetpoint) {//0.0f set constants.rotateToAngle == true in the command init()
    	turnController.setSetpoint(degreeSetpoint);
    	double currentRotationRate;
    	if(constants.rotateToAngle) {
    		turnController.enable();
    		currentRotationRate = constants.autonTurnSpeed;//speed at which the robot turns
    		constants.rotateToAngle = false;
    	}else {
    		turnController.disable();
    		currentRotationRate = constants.autonTurnSpeed;
    		constants.rotateToAngle = false;
    	}
    	
    }
    public double getUltraDistance(){
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
}
