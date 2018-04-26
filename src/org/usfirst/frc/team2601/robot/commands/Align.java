package org.usfirst.frc.team2601.robot.commands;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Align extends Command {

	Constants constants = Constants.getInstance();
	
    public Align() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.vision);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.vision.align = false;
    	Robot.arms.armSol.set(DoubleSolenoid.Value.kForward);
    	Robot.drivetrain.shiftSol.set(DoubleSolenoid.Value.kReverse);
    	Robot.drivetrain.gyro.reset();
    	Robot.drivetrain.gyro.zeroYaw();
    	constants.xPos = 0;
    	constants.angle = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.vision.getCubePosition();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Robot.drivetrain.intakeForward(500, 500, 0.5, true);
    }
    

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
