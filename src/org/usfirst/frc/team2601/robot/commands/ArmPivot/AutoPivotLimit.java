package org.usfirst.frc.team2601.robot.commands.ArmPivot;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoPivotLimit extends Command {

	Constants constants = Constants.getInstance();
	
    public AutoPivotLimit() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.pivot);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return constants.autonArm;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.pivot.stopPivotMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
