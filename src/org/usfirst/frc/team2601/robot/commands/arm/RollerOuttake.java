package org.usfirst.frc.team2601.robot.commands.arm;

import org.usfirst.frc.team2601.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RollerOuttake extends Command {

	boolean dir;
	boolean speed;
	
    public RollerOuttake(double timeout,boolean setSpeed) {
    	requires(Robot.arms);
    	setTimeout(timeout);
    	setSpeed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.arms.slowIntake = false;
		Robot.arms.armSol.set(DoubleSolenoid.Value.kReverse);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.arms.rollerOuttake(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.arms.stopMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
