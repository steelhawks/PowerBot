package org.usfirst.frc.team2601.robot.commands.ArmPivot;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoPIDPivot extends Command {

	Constants constants = Constants.getInstance();
	double setPos;
	boolean setDir;//up == true
	
    public AutoPIDPivot(double pos, boolean up) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.pivot);
    	setPos = pos;
    	setDir = up;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.pivot.rotCounter.reset();
    	//Robot.pivot.pivotEnc.reset();
    	Robot.pivot.armPivotM.getSensorCollection().setQuadraturePosition(0,0);
    	constants.PIDVal = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.pivot.usePID(setPos, setDir);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return constants.autonArm;
    }

    // Called once after isFinished returns true
    protected void end() {
		//Robot.pivot.stopPivotMotors();
    	//constants.holdArmAngle = true;
    	//constants.PIDVal = true;
    	Robot.pivot.usePID(setPos, setDir);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
