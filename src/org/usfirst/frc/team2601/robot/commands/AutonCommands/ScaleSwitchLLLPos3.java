package org.usfirst.frc.team2601.robot.commands.AutonCommands;

import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.commands.AutoAlignIntake;
import org.usfirst.frc.team2601.robot.commands.AutonWait;
import org.usfirst.frc.team2601.robot.commands.IntakeForward;
import org.usfirst.frc.team2601.robot.commands.ArmPivot.AutoPivot;
import org.usfirst.frc.team2601.robot.commands.arm.RollerOuttake;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonTurn;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncGyroPlease;
import org.usfirst.frc.team2601.robot.commands.drivetrain.ShiftGear;
import org.usfirst.frc.team2601.robot.commands.elevator.AutoElevator;
import org.usfirst.frc.team2601.robot.commands.elevator.AutoLimitElevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ScaleSwitchLLLPos3 extends CommandGroup {

    public ScaleSwitchLLLPos3() {
    	Robot.drivetrain.frontLeftM.setSafetyEnabled(false);
		Robot.drivetrain.midLeftM.setSafetyEnabled(false);
		Robot.drivetrain.frontRightM.setSafetyEnabled(false);
		Robot.drivetrain.midRightM.setSafetyEnabled(false);
		Robot.drivetrain.frontLeftM.setExpiration(120);
		Robot.drivetrain.midLeftM.setExpiration(120);
		Robot.drivetrain.frontRightM.setExpiration(120);
		Robot.drivetrain.midRightM.setExpiration(120);
		addSequential(new EncGyroPlease(9550,9550,1.0,true));//Forward towards switch
    	addSequential(new AutonWait(0.1));//Delay
		addSequential(new ShiftGear());//Shift low to slow it down to turn
    	addSequential(new AutonTurn(88, true));//Turn left in low 88
    	addSequential(new ShiftGear());//Shift to high gear
    	addSequential(new EncGyroPlease(7850,7850,1.0,true));//Drive across the field
    	addSequential(new AutonWait(0.1));//Delay
    	addSequential(new ShiftGear());//Shift to turn
    	addSequential(new AutonTurn(85, false));//Turn right in low 88
    	addSequential(new EncGyroPlease(975,975,1.0,true));//Go forwards towards the scale
		addParallel(new AutoPivot(950,false));//Lower the arms
    	addSequential(new AutoElevator(28000,true));//Raise the elevator
    	addSequential(new RollerOuttake(0.15,true));//Shoot the cube
		addParallel(new AutoLimitElevator(7000,false));//Lower the elevator
    	addSequential(new EncGyroPlease(500,500,1.0,false));//Move back
    	addSequential(new AutonTurn(180, true));//Turn around towards the switch and the cubes
    	addSequential(new EncGyroPlease(800,800,1.0,true));//Go forward towards the cube
    	addSequential(new AutoAlignIntake());//Align to the cube, pick up, raise elevator
    	addSequential(new RollerOuttake(0.2,false));//Shoot the cube 
    }
}
