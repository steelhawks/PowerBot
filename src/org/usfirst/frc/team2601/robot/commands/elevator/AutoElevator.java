package org.usfirst.frc.team2601.robot.commands.elevator;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoElevator extends Command {

	boolean up;
	double setPos;
	Constants constants = Constants.getInstance();
	
    public AutoElevator(double pos,boolean upInput) {
       	requires(Robot.elevator);
       	up = upInput;
       	setPos = pos;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevator.elevatorM4.getSensorCollection().setQuadraturePosition(0,0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.elevator.autoElevator(setPos, up);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return constants.autonEl;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.stopMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
