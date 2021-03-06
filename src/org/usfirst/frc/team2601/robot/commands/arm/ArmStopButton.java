package org.usfirst.frc.team2601.robot.commands.arm;

import org.usfirst.frc.team2601.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmStopButton extends Command {

    public ArmStopButton() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.arms);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.arms.armStop();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	if(Robot.arms.cubeir.getValue() > 1300) {
    		Robot.arms.armSol.set(DoubleSolenoid.Value.kReverse);
		}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
