package org.usfirst.frc.team2601.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Constants {
    private static Constants instance = null;
	
	public static Constants getInstance(){
		if (instance == null){
			instance = new Constants();
		}
		return instance;
	}
	//Bot Type boolean
	public final static boolean autonBot = true;
	
	//Robot Position
	public final static int robotPos = 1;
	
	//Left Drivetrain Motor Ports	
	public final static int frontLeftMPort = 3;//0
	public final static int midLeftMPort = 4;//1
	public final static int backLeftMPort = 5;//2
	
	//Right Drivetrain Motor Ports
	public final static int frontRightMPort = 0;//3
	public final static int midRightMPort = 1;//4
	public final static int backRightMPort = 2;//5
	
	//Joystick Objects
	public static Joystick dJS; 
	public static Joystick oBB;
	
	//Joystick Ports
	public final static int dJSPort = 0;
	public final static int oBBPort = 1;
	
	//Driver Joystick Buttons (chuck says it should be 2 :)
	public final static int shiftB = 1; 
	
	//Operator Button Board Buttons
	public final static int wormDriveB = 4;
	public final static int elevatorB = 6;
	public final static int scalerB = 5;
	public final static int armPistonB = 10;
	
	//Solenoid Ports
	public final static int shiftSolPortOn = 0;
	public final static int shiftSolPortOff = 1;
	public final static int armSolPortOn = 2;
	public final static int armSolPortOff = 3;
	
	//NIDEC Brushless Motor Ports
	public final static int brushlessPWMPort = 0;
	public final static int brushlessDIOPort = 0;
	
	//Arm Motor Ports
	public final static int leftArmMPort = 6;
	public final static int rightArmMPort = 7;
	
	//Elevator Motadwor Ports
	public final static int wormDriveMPort = 8;
	public final static int elevatorMPort = 9;
	
	//Scaler Motor Ports
	public final static int scalerM1Port = 10;
	public final static int scalerM2Port = 11;
	
	//Encoder Ports
	public final static int leftEncPortA = 1;
	public final static int leftEncPortB = 2;
	public final static int rightEncPortA = 3;
	public final static int rightEncPortB = 4;
	
	//Ultrasonic Ports
	public final static int ultraPortIn = 5;
	public final static int ultraPortOut = 6;
	
	//Booleans
	public static boolean autonBool;
	
	//Tolerances
	public final static int ultraTolerance = 1;
	
	//gyro constants
	public final static double gyroSlowAngle = 20;
	public final static double gyroSlowSpeed = 0.18;
	public final static double gyroStartSpeed = 0.45;
	public final static double kPGyro = 0.08;
	
	public static int autonTurnSpeed;
	public static boolean gyroTurnBool;
	public Constants(){
		
	}
}