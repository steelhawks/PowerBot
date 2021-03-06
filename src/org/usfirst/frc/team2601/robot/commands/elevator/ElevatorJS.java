package org.usfirst.frc.team2601.robot.commands.elevator;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.Constants.Operator_Type;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorJS extends Command {

	Constants constants = Constants.getInstance();
	
	boolean setDir;
	
    public ElevatorJS(boolean up) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevator);
    	setDir = up;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(constants.operatorType == Operator_Type.Joystick) {
    		Robot.elevator.elevatorMotorsJS(constants.oJS);
    	}else if(constants.operatorType == Operator_Type.Gamepad){
    		Robot.elevator.elevatorMotorsGamepad(constants.gamepad);
    	}else {
    		Robot.elevator.elevatorMotorsBB(setDir);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
