package org.usfirst.frc.team2601.robot.commands.drivetrain;

import org.usfirst.frc.team2601.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonTurn extends Command {
	int P, I, D = 1;
	int integral, setpoint, previous_error = 0;
	double error, rcw, derivative = 0;
	
	public AutonTurn() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
	}
	public void setSetpoint(int setpoint) {
		this.setpoint = setpoint;
	}
    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }
    public void PID() {
    	error = setpoint - Robot.drivetrain.getGyroAngle();//Error = target - actual
    	this.integral += (error*.02);//Integral is increased by error * time
    	derivative = (error - this.previous_error) / .02;
    	this.rcw = P*error + I*this.integral + D*derivative;
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	PID();
    	Robot.drivetrain.diffDrive.arcadeDrive(0, rcw);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
