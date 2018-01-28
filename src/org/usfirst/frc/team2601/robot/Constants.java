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
	//Robot Type Boolean (Alpha and Beta = false | AutonBot and TestBoard = true)
	public final static boolean autonBot = true;
	
	//Robot Position
	public final static int robotPos = 2;
	
	//Autonomous Booleans 
	public final static boolean doubleScale = true;
	
	//Left Drivetrain Motor Ports
	public final static int frontRightMPort = 0;//3 originally
	public final static int midRightMPort = 1;//4 originally
	public final static int backRightMPort = 2;//5 originally
	
	//Right Drivetrain Motor Ports
	public final static int frontLeftMPort = 3;//0 originally
	public final static int midLeftMPort = 4;//1 originally
	public final static int backLeftMPort = 5;//2 originally
	
	//Joystick Objects
	//public static Joystick dJS; 
	public static Joystick oBB;
	
	//Joystick Ports
	public final static int dJSPort = 0;
	public final static int oBBPort = 1;
	
	//Driver Joystick Button
	public final static int shiftB = 1; 
	
	//Operator Button Board Buttons
	public final static int elevatorB = 6;
	public final static int scalerB = 5;
	public final static int armMotorsB = 4; 
	public final static int armPistonsB = 3;
	public final static int armPushB = 2;
	
	//Solenoid Ports
	public final static int shiftSolPortOn = 0;
	public final static int shiftSolPortOff = 1;
	public final static int armSolPortOn = 2;
	public final static int armSolPortOff = 3;
	public final static int pushArmSolPortOn = 4;
	public final static int pushArmSolPortOff = 5;
	
	//NIDEC Brushless Motor Ports
	public final static int brushlessPWMPort = 0;
	public final static int brushlessDIOPort = 0;
	
	//Arm Motor Ports
	public final static int leftArmMPort = 6;
	public final static int rightArmMPort = 7;
	
	//Elevator Motor Ports
	public final static int elevatorMPort = 8;
	
	//Scaler Motor Ports
	public final static int scalerM1Port = 9;
	public final static int scalerM2Port = 10;
	
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
	
	//Gyro Constants
	public final static double gyroSlowAngle = 20;
	public static double gyroSlowSpeed = 0.2;
	public final static double gyroStartSpeed = 0.45;
	public final static double kPGyro = 0.08;
	
	public static int autonTurnSpeed;
	public static boolean gyroTurnBool;
	public Constants(){
		
	}
}