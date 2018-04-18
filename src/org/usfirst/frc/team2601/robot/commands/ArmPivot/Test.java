package org.usfirst.frc.team2601.robot.commands.ArmPivot;

import org.usfirst.frc.team2601.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Test extends Command {

    public Test() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.pivot);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.pivot.armPivotM.getSensorCollection().setQuadraturePosition(0, 0);
    	Robot.pivot.armPivotM.set(ControlMode.Position, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.pivot.usePIDOutput(-1000);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.pivot.armPivotM.set(ControlMode.PercentOutput, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.pivot.armPivotM.set(ControlMode.PercentOutput, 0);
    }
}
