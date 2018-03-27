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
	//MUST TEST INTAKE AUTON AT COMP
	
	public enum Operator_Type {Joystick, BB, Gamepad};
	public Operator_Type operatorType = Operator_Type.Gamepad;
	public enum Robot_Type {Beta,Alpha};
	public Robot_Type robotType = Robot_Type.Alpha;
	//Robot Type Boolean (Alpha and Beta = false | AutonBot and TestBoard = true)
	public final static boolean autonBot = false;
	
	//Robot Position For Autonomous
	public final static int robotPos = 2;
		
	//Autonomous Boolean f
	public final static boolean doubleScale = false;
	public final static boolean onlySwitch = true;
	
	//Left Drivetrain Motor Ports
	public final static int frontRightMPort = 0;//3 originally
	public final static int midRightMPort = 1;//4 originally
	public final static int backRightMPort = 2;//5 originally
	
	//Right Drivetrain Motor Ports
	public final static int frontLeftMPort = 3;//0 originally
	public final static int midLeftMPort = 4;//1 originally
	public final static int backLeftMPort = 5;//2 originally
	
	//Joystick Objects
	public static Joystick oJS;
	public static ButtonBoard BB;
	public static F310 gamepad;
	
	//Joystick Ports
	public final static int dJSPort = 0;
	public final static int gamepadPort = 1;
	
	//Driver Joystick Button
	public final static int shiftB = 1; 
	
	//Operator Joystick Buttons
	//public static int scalerB = 11;
	public static boolean armUp;
	public static int armPivotDownB = 7;
	public static int armPivotUpB = 8;
	public static int armIntakeB = 10;
	public static int armShootB = 9;
	public static int armPistonB = 2;
	
	//Operator ButtonBoard Buttons
	public static int armPivotDownBB = 3;
	public static int armPivotUpBB = 4;
	
	public static boolean slowBtnOn;
	
	//Solenoid Ports
	public final static int shiftSolPortOn = 0;
	public final static int shiftSolPortOff = 1;
	public final static int armSolPortOn = 2;
	public final static int armSolPortOff = 3;
	
	//Arm Motor Ports
	public final static int leftArmMPort = 6;
	public final static int rightArmMPort = 7;
	public final static int pivotM1Port = 0; //PWM channel Spark
	//public final static int pivotM2Port = 1;
	
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
	public final static int autoPivotLimitPort = 7;
	
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
	public static double gyroSlowSpeed = 0.4;
	public final static double gyroStartSpeed = 0.65;
	public final static double kPGyro = 0.08;
	
	//elevatorLimits;
	public final static double elevatorUpperLimit = 35000;
	public final static double elevatorLowerLimit = -10;
	
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