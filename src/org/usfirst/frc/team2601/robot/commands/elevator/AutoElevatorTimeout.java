package org.usfirst.frc.team2601.robot.commands.elevator;

import org.usfirst.frc.team2601.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoElevatorTimeout extends Command {
	
	double setPos;
	boolean setDir;
	
    public AutoElevatorTimeout(double timeout, boolean upInput) {
    	requires(Robot.elevator);
    	setTimeout(timeout);
    	setDir = upInput;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elevator.autoElevator(20,setDir);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
