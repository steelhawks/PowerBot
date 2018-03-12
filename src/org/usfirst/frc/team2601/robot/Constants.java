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
	public final static boolean autonBot = false;
	
	//Robot Position For Autonomous
	public final static int robotPos = 1;
	
	//Operator Joystick Boolean
	public final static boolean joystick = true;
	
	//Autonomous Boolean 
	public final static boolean doubleScale = false;
	
	//Left Drivetrain Motor Ports
	public final static int frontRightMPort = 0;//3 originally
	public final static int midRightMPort = 1;//4 originally
	public final static int backRightMPort = 2;//5 originally
	
	//Right Drivetrain Motor Ports
	public final static int frontLeftMPort = 3;//0 originally
	public final static int midLeftMPort = 4;//1 originally
	public final static int backLeftMPort = 5;//2 originally
	
	//Joystick Objects
	public static Joystick oBB;
	
	//Joystick Ports
	public final static int dJSPort = 0;
	public final static int oBBPort = 1;
	
	//Driver Joystick Button
	public final static int shiftB = 1; 
	
	//Operator Button Board Buttons
	public static int scalerB = 11;
	public static int armIntakePistonsB = 1;
	public static int armPivotDownB = 7;
	public static int armPivotUpB = 8;
	
	//Solenoid Ports
	public final static int shiftSolPortOn = 0;
	public final static int shiftSolPortOff = 1;
	public final static int armSolPortOn = 2;
	public final static int armSolPortOff = 3;
	
	//Arm Motor Ports
	public final static int leftArmMPort = 6;
	public final static int rightArmMPort = 7;
	public final static int pivotMPort = 0; //PWM channel Spark
	
	//Elevator Motor Ports
	public final static int elevatorM1Port = 8;
	public final static int elevatorM2Port = 9;
	public final static int elevatorM3Port = 10;
	public final static int elevatorM4Port = 11;
	
	//Encoder Ports
	public final static int leftEncPortA = 1;
 	public final static int leftEncPortB = 2;
	public final static int rightEncPortA = 3;
	public final static int rightEncPortB = 4;
	
	//Ultrasonic Ports
	public final static int ultraPortIn = 5;
	public final static int ultraPortOut = 6;
	
	//Hall Effect Sensor
	public final static int pivotSensorPort = 0;
	
	//limit Switches
	public final static int upperLimitPort = 8;
	public final static int lowerLimitPort = 9;
	
	//Booleans
	public static boolean autonBool;
	public static boolean autonEl;
	public static boolean autonArm;
	
	//Tolerances
	public final static int ultraTolerance = 1;
	
	//Arm Pivot Constants
	public final static double upSwitchPivot = 25;
	public final static double startUpPivot = 50;
	//public static boolean down;
	public static boolean up;
	public static int pivotPos;
	
	//public final static double startDownPivot = 50;
	//public final static double downSwitchPivot = 25;
	
	//Gyro Constants
	public final static double gyroSlowAngle = 20;
	public static double gyroSlowSpeed = 0.2;
	public final static double gyroStartSpeed = 0.45;
	public final static double kPGyro = 0.08;
	
	//elevatorLimits;
	public final static double elevatorUpperLimit = 10000;
	public final static double elevatorLowerLimit = -20000;
	
	public static int autonTurnSpeed;
	public static boolean gyroTurnBool;
	public Constants(){
		/*if(joystick == false) {	
			//Operator Button Board Buttons
			elevatorB = 6;
			scalerB = 5;
			armIntakeMotorsB = 4; 
			armIntakePistonsB = 3;
			cubeHolderB = 10;
		}else {
			//Operator Joystick Buttons
			//elevatorB = ;
			//scalerB = 3;
			//armIntakeMotorsB = ; 
			armIntakePistonsB = 1;
			cubeHolderB = 3;
			armPivotUpB = 7;
			armPivotDownB = 8;
		//}*/
	}
}