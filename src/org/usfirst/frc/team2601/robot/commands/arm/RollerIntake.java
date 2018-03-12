package org.usfirst.frc.team2601.robot.commands.arm;

import org.usfirst.frc.team2601.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RollerIntake extends Command {

	boolean dir;
	
    public RollerIntake(boolean setdir) {
    	requires(Robot.arms);
    	//setTimeout(timeout);
    	setdir = dir;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.arms.slowIntake = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.arms.rollerIntake(dir);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.arms.isCubeIn();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
