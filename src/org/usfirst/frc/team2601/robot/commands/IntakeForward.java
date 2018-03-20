package org.usfirst.frc.team2601.robot.commands;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeForward extends Command {
	
Constants constants = Constants.getInstance();
	
	double setLeftDist = 0;
	double setRightDist = 0;
	double setSpeed = 0;
	boolean setForward = true;
	
    public IntakeForward(double leftDist, double rightDist, double speed, boolean forward) {
        requires(Robot.drivetrain);
        setLeftDist = leftDist;
        setRightDist = rightDist;
        setSpeed = speed;
        setForward = forward;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.leftEnc.reset();
    	Robot.drivetrain.rightEnc.reset();
    	Robot.drivetrain.gyro.reset();
    	Robot.drivetrain.gyro.zeroYaw();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.arms.rollerIntake(true);
    	Robot.drivetrain.intakeForward(setLeftDist, setRightDist, setSpeed, setForward);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {///MUST TEST AT COMP
       
    	if(Robot.arms.isCubeIn() == true) {	
    		Robot.drivetrain.stopMotors();
        	return true;
        }else {
        	return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	Robot.drivetrain.stopMotors();
    	Robot.arms.stopMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
