package org.usfirst.frc.team2601.robot.commands.drivetrain;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class UltraGyroMoveStraight extends Command {

	Constants constants = Constants.getInstance();

	double setDist = 0;
	double setSpeed = 0;
	boolean setForward = true;

    public UltraGyroMoveStraight(double enteredDist, double enteredSpeed, boolean forward) {
        requires(Robot.drivetrain);
    	setDist = enteredDist;
    	setSpeed = enteredSpeed;
    	setForward = forward; 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	constants.autonBool = false;
    	Robot.drivetrain.gyro.reset();
    	Robot.drivetrain.gyro.zeroYaw();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.ultraGyroMoveStraight(setDist, setSpeed, setForward);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return constants.autonBool;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stopMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
