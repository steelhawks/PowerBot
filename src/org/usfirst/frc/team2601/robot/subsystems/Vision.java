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

/**
 *
 */
public class Vision extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Constants constants = Constants.getInstance();
	NetworkTableInstance server = NetworkTableInstance.getDefault();
	NetworkTableEntry centerX = server.getEntry("centerX");

	public boolean align = false;
	public int i = 0;
	private boolean runProcessing = false;
	private double area = 0.0;
	private double distance = 0.0;
	
    public void initDefaultCommand() {;
    }
    //Vision
    public void getGearPosition(){
    	server.getTable("CVResultsTable");
    	
    	Number[] xValues = new Double[1];
		//19.5 cm
			try{
				
				//System.out.println("PAST IF");
		    	double targetAngle = 0;
				double currAngle = xValues[0].doubleValue();
		    	//double mid = Math.abs((xValues[0].doubleValue() + xValues[1].doubleValue())/2);
				double offset = 2;
				
				SmartDashboard.putBoolean("aligned", align);
				if(currAngle > targetAngle + offset){//turn left
					Robot.drivetrain.leftGroup.set(-0.15);//-0.15
					Robot.drivetrain.rightGroup.set(-0.15);
			    	
			    	align = false;
			    }else if(currAngle < targetAngle - offset){//turn right
			    	Robot.drivetrain.leftGroup.set(0.15);//0.15
					Robot.drivetrain.rightGroup.set(0.15);
			       		
			    	align = false;
			    }else if((currAngle >= (targetAngle + offset) || currAngle >= (targetAngle - offset) || currAngle == targetAngle)){
					Robot.drivetrain.leftGroup.set(0);
					Robot.drivetrain.rightGroup.set(0);
		        	System.out.println("aligned" + i );
			    	align = true;
				}
				
			}
			catch(ArrayIndexOutOfBoundsException exp){
				//System.out.println("No values being published");
			}
			
			i++;
			Timer.delay(0.05);
	}
    
}
