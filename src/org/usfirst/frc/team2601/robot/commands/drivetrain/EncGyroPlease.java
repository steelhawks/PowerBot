package org.usfirst.frc.team2601.robot.commands.drivetrain;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class EncGyroPlease extends Command {

	Constants constants = Constants.getInstance();
	
	double setLeftDist = 0;
	double setRightDist = 0;
	double setSpeed = 0;
	boolean setForward = true;
	
    public EncGyroPlease(double leftDist, double rightDist, double speed, boolean forward) {
        requires(Robot.drivetrain);
        setLeftDist = leftDist;
        setRightDist = rightDist;
        setSpeed = speed;
        setForward = forward;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	constants.autonBool = false;
    	Robot.drivetrain.leftEnc.reset();
    	Robot.drivetrain.rightEnc.reset();
    	Robot.drivetrain.gyro.reset();
    	Robot.drivetrain.gyro.zeroYaw();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.encGyroMove(setLeftDist, setRightDist, setSpeed, setForward);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return constants.autonBool;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
