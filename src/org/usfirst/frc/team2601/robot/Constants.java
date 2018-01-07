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
	//Solenoid Ports
	public final static int shiftSolPortOn = 0;
	public final static int shiftSolPortOff = 1;
	//NIDEC Motor Ports
	public final static int brushlessPWMPort = 0;
	public final static int brushlessDIOPort = 0;
	public Constants(){
	
	}
}