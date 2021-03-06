package org.usfirst.frc.team2601.robot.commands.ArmPivot;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Constants.Operator_Type;
import org.usfirst.frc.team2601.robot.OI;
import org.usfirst.frc.team2601.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PivotJS extends Command {

	public OI oi = new OI();
	Constants constants = Constants.getInstance();
	
    public PivotJS() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.pivot);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*if(constants.operatorType == Operator_Type.Joystick) {
    		Robot.pivot.armPivotJS(oi.pivotJS);
    	}else if (constants.operatorType == Operator_Type.Gamepad) {
    		Robot.pivot.armPivotGamepad(constants.gamepad);
    	}else {
    		Robot.pivot.armPivotBB(constants.BB);
    	}*/
    	Robot.pivot.armPivotGamepad(constants.gamepad);
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
