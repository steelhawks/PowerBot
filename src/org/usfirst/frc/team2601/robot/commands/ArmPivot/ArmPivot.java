package org.usfirst.frc.team2601.robot.commands.ArmPivot;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmPivot extends Command {

	Constants constants = Constants.getInstance();
	
    public ArmPivot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.pivot);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.pivot.rotCounter.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(constants.pivotPos == 1) {//full up
    		Robot.pivot.armPivotUp(50);
    	}else if (constants.pivotPos == 2){//full down
    		Robot.pivot.armPivotDown(50);
    	}else if (constants.pivotPos == 3){//up then Shoot
    		Robot.pivot.armPivotUp(25);
    	}else if (constants.pivotPos == 4){//down then Shoot
    		Robot.pivot.armPivotUp(25);
    	}
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
