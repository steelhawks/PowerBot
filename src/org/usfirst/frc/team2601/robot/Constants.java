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
	//ROBOT POSITION
	public final static int robotPos = 1;
	//Left Motor Ports
	public final static int frontLeftMPort = 0;
	public final static int midLeftMPort = 1;
	public final static int backLeftMPort = 2;
	//Right Motors Ports
	public final static int frontRightMPort = 3;
	public final static int midRightMPort = 4;
	public final static int backRightMPort = 5;
	//Joystick Objects
	public static Joystick dJS; 
	//Joystick Ports
	public final static int dJSPort = 0;
	//Driver Joystick Buttons
	public final static int shiftB = 1;
	//Solenoid Ports
	public final static int shiftSolPortOn = 0;
	public final static int shiftSolPortOff = 1;
	//NIDEC Brushless Motor Ports
	public final static int brushlessPWMPort = 0;
	public final static int brushlessDIOPort = 0;
	//Encoder Ports
	public final static int leftEncPortA = 1;
	public final static int leftEncPortB = 2;
	public final static int rightEncPortA = 3;
	public final static int rightEncPortB = 4;
	//Ultrasonic Ports
	public final static int ultraPortIn = 5;
	public final static int ultraPortOut = 6;
	//Booleans
	public static boolean ultraBool;
	public static boolean rotateToAngle;
	//Tolerances
	public final static int ultraTolerance = 1;
	
	public static int autonTurnSpeed;
	public Constants(){
	
	}
}