/*

PURPOSE

This code aligns the robot with the game piece to pick it up. We use the X-Position (xPos) 
from Network Tables from the Jetson to determine how much we have to turn the robot to 
align with the game piece. It is done using a two-step dual situation system, which 
determines the speed of the turns. This is done by using the distance from the game piece 
(dual situation, where the "alignThreshold" is determined), and the X-Position of the game 
piece (two-step, which determines the speed of the turn). Once aligned with the game piece, 
the function to move forward and grab the game piece is executed (from another file).

*/

package org.usfirst.frc.team2601.robot.subsystems;

import java.awt.Frame;

import java.util.ArrayList;
import java.util.Collections;

import org.opencv.imgproc.Imgproc;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.commands.IntakeForward;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.HttpCamera;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.*;
import org.usfirst.frc.team2601.robot.commands.IntakeForward;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Vision extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Constants constants = Constants.getInstance();
	NetworkTableInstance server = NetworkTableInstance.getDefault();
	NetworkTableEntry centerX = server.getEntry("centerX");

	private final String NETWORK_TABLE_X_POSITION  ="CenterPoint X";
	private final String NETWORK_TABLE_ANGLE = "Angle";
	private final String NETWORK_TABLE_DISTANCE = "Distance";
	
	public boolean align = false;
	public int i = 0;
	private boolean runProcessing = false;
	private double area = 0.0;
	private double distance = 0.0;
	
	private boolean visionOn = Boolean.FALSE;
	private NetworkTable tableData;
	public Vision() {
	}
    public void initDefaultCommand() {
    	
    }    
    //Vision -- aligning to the cube 
    public void getCubePosition(){

    	// This gets its position data from Network Tables key 'CVResultsTable'. This needs to be available in Network Tables 
    	
    	//Method to make vision not run when motors are moving
    	boolean isMotorMoving = Boolean.FALSE;
    	System.out.println(Robot.drivetrain.leftGroup.get());
    	System.out.println(Robot.drivetrain.rightGroup.get());

    	if(	Math.abs(Robot.drivetrain.leftGroup.get()) > 0.009 || 
    			Math.abs(Robot.drivetrain.rightGroup.get()) > 0.009 ) {
    		isMotorMoving = Boolean.TRUE;
    	} 
    	System.out.println(isMotorMoving);
    	 	
    	tableData = server.getTable("CVResultsTable");
    	
    	//  check CVResultTable exists with the values
    	
    	/*if( isMotorMoving ) {
    		System.out.println("MOTORS MOVING");
    		return;
    	}   	
    	*/
    	double xPos = 0.0;
    	double distance = 0.0;
    	double angle = 0.0;
    	int alignThreshold = 55;
    	boolean adjustmentRequired = Boolean.FALSE;
    	
		try{
			
			// update variables using data from tableData
					
			// if X position > 160 then we need to turn RIGHT
			// if X position < 160 we need to turn LEFT
			xPos = tableData.getEntry(NETWORK_TABLE_X_POSITION).getDouble(0.0);
			angle = tableData.getEntry(NETWORK_TABLE_ANGLE).getDouble(0.0);
			distance = tableData.getEntry(NETWORK_TABLE_DISTANCE).getDouble(0.0);
			// diffX is the offset from the centerline 
			double diffX = Math.abs(160 - xPos);
			System.out.println(distance);
			System.out.println(angle);
			if(distance < 120) {
				alignThreshold = 80;
			} 
			else {
				alignThreshold = 55;
			}
			constants.xPos = xPos;
			constants.angle = angle;
			System.out.println(alignThreshold);			
			/*
			if(xPos < 157.5 && diffX > alignThreshold) {
				System.out.println(xPos + " FAR LEFT");
				Robot.drivetrain.leftGroup.set(0.28);
				Robot.drivetrain.rightGroup.set(0.28);
				align = false;
			}else if( xPos > 162.5 && diffX > alignThreshold) {
				System.out.println(xPos + " FAR RIGHT");
				Robot.drivetrain.leftGroup.set(-0.28);
				Robot.drivetrain.rightGroup.set(-0.28);
				align = false;
			}
			else if(xPos < 157.5 && diffX < alignThreshold) {
				System.out.println(xPos + " LEFT CLOSE");
				Robot.drivetrain.leftGroup.set(0.2);
				Robot.drivetrain.rightGroup.set(0.2);
				align = false;
			}
			else if( xPos > 162.5 && diffX < alignThreshold) {
				System.out.println(xPos + " RIGHT CLOSE");
				Robot.drivetrain.leftGroup.set(-0.2);
				Robot.drivetrain.rightGroup.set(-0.2);
				align = false;
			} 
			else {
				System.out.println(xPos + "ALIGNED");
				Robot.drivetrain.leftGroup.set(0);
				Robot.drivetrain.rightGroup.set(0);
				align = true;
			}*/
		}catch(ArrayIndexOutOfBoundsException exp){
			System.out.println("No values being published");
		}
			
		Timer.delay(0.05);
	}
    public void clearNetworkTables() {
    	tableData.getEntry(NETWORK_TABLE_X_POSITION).delete();
    	tableData.getEntry(NETWORK_TABLE_DISTANCE).delete();
    	tableData.getEntry(NETWORK_TABLE_ANGLE).delete();
    }
    public void moveToAngle(double xPos, double angle) {
    	if(Robot.drivetrain.gyro.getAngle() <= angle && xPos < 157.5) {
			System.out.println("TurnLeft");
			Robot.drivetrain.leftGroup.set(0.28);
			Robot.drivetrain.rightGroup.set(0.28);
			align = false;
		}else if(Robot.drivetrain.gyro.getAngle() <= angle &&  xPos > 162.5) {
			System.out.println("TurnRight");
			Robot.drivetrain.leftGroup.set(-0.28);
			Robot.drivetrain.rightGroup.set(-0.28);
			align = false;
		}else {
			Robot.drivetrain.leftGroup.set(0);
			Robot.drivetrain.rightGroup.set(0);
			align = true;
		}
    }
}