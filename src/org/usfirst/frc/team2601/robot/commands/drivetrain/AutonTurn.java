package org.usfirst.frc.team2601.robot.commands.drivetrain;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonTurn extends Command {
	double setTarget = 0;
	boolean leftOrNot = false;
	Constants constants = Constants.getInstance();
	public AutonTurn(double enteredTarget, boolean left) {
    	requires(Robot.drivetrain);
    	setTarget = enteredTarget;
    	leftOrNot = left; 
   	}
    // Called just before this Command runs the first time
    protected void initialize() {
    	Constants.gyroTurnBool = false;
    	Robot.drivetrain.gyro.reset();
    	Robot.drivetrain.gyro.zeroYaw();
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.turn(setTarget, leftOrNot);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return constants.gyroTurnBool;
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
