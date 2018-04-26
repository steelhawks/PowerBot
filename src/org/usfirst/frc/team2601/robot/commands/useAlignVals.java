package org.usfirst.frc.team2601.robot.commands;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class useAlignVals extends Command {

	Constants constants = Constants.getInstance();
	
    public useAlignVals() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.vision);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.vision.align = false;
    	Robot.drivetrain.gyro.reset();
    	Robot.drivetrain.gyro.zeroYaw();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.vision.moveToAngle(constants.xPos, constants.angle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.vision.align;
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
