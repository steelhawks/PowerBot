package org.usfirst.frc.team2601.robot;

import edu.wpi.first.wpilibj.Joystick;

public class ButtonBoard extends Joystick{
		
	public ButtonBoard(int port){
		super(port);
	}
	
	/*public F310(int port, boolean presetAxes){
		super(port, 5, 10);
	}
	
	public F310(int port, int numAxes, int numButtons){
		super(port, numAxes, numButtons);
	}*/
	
	public final static int kBBAxisLeftStickX = 0;
    public final static int kBBAxisLeftStickY = 1;
    public final static int kBBAxisRightStickX = 4;
    public final static int kBBAxisRightStickY = 5;

    public final static int kBBButtonA = 1; // Bottom button
    public final static int kBBButtonB = 2; // Right button
    public final static int kBBButtonX = 3; // Left button
    public final static int kBBButtonY = 4; // Top button
    public final static int kBBButtonShoulderL = 5;
    public final static int kBBButtonShoulderR = 6;
    public final static int kBBButtonBack = 7;
    public final static int kBBButtonStart = 8;
    public final static int kBBButtonLeftStick = 9;
    public final static int kBBButtonRightStick = 10;
    public final static int kBBButtonMode = -1; // unknown
    public final static int kBBButtonLogitech = -1;  // unknown
    
    public double getRawAxis(int axis){
    	return super.getRawAxis(axis);
    }
    
    public double getLeftX(){
    	return super.getRawAxis(kBBAxisLeftStickX);
    }
    
    public double getLeftY(){
    	return super.getRawAxis(kBBAxisLeftStickY);
    }
    
    public double getRightX(){
    	return super.getRawAxis(kBBAxisRightStickX);
    }
    
    public double getRightY(){
    	return super.getRawAxis(kBBAxisRightStickY);
    }
}