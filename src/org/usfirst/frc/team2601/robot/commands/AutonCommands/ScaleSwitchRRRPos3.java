package org.usfirst.frc.team2601.robot.commands.AutonCommands;

import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.commands.AutonWait;
import org.usfirst.frc.team2601.robot.commands.IntakeForward;
import org.usfirst.frc.team2601.robot.commands.ArmPivot.AutoPivot;
import org.usfirst.frc.team2601.robot.commands.arm.RollerOuttake;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonTurn;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncGyroPlease;
import org.usfirst.frc.team2601.robot.commands.drivetrain.ShiftGear;
import org.usfirst.frc.team2601.robot.commands.elevator.AutoElevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ScaleSwitchRRRPos3 extends CommandGroup {

    public ScaleSwitchRRRPos3() {
    	Robot.drivetrain.frontLeftM.setSafetyEnabled(false);
		Robot.drivetrain.midLeftM.setSafetyEnabled(false);
		Robot.drivetrain.frontRightM.setSafetyEnabled(false);
		Robot.drivetrain.midRightM.setSafetyEnabled(false);
		Robot.drivetrain.frontLeftM.setExpiration(120);
		Robot.drivetrain.midLeftM.setExpiration(120);
		Robot.drivetrain.frontRightM.setExpiration(120);
		Robot.drivetrain.midRightM.setExpiration(120);
    	addSequential(new AutoPivot(35,false));
		addSequential(new EncGyroPlease(9550,9550,1.0,true));//Forward towards switch
    	addSequential(new AutonWait(0.2));//Delay
		addSequential(new ShiftGear());//Shift low to slow it down to turn
    	addSequential(new AutonTurn(88, true));//Turn right in low 88
    	addSequential(new ShiftGear());
    	addSequential(new EncGyroPlease(7850,7850,1.0,true));
    	addSequential(new AutonWait(0.2));//Delay
    	addSequential(new ShiftGear());
    	addSequential(new AutonTurn(85, false));//Turn left in low 88
    	addSequential(new EncGyroPlease(975,975,1.0,true));
    	addSequential(new AutoElevator(23500,true));//elevator up
    	addSequential(new EncGyroPlease(150,150,0.3,true));
    	addSequential(new RollerOuttake(0.75,true));
    	addSequential(new AutoElevator(24200,false));//elevator down
    	addSequential(new EncGyroPlease(500,500,1.0,false));
    	addSequential(new AutonTurn(180, true));//Turn right the switch
    	//addParallel(new EncGyroPlease(550,550,0.75,true));//Travel towards the switch
    	//addSequential(new RollerIntake(1.5, false));
    	addSequential(new IntakeForward(550,550,0.75,true));
    	addSequential(new AutonWait(0.5));
    	addParallel(new AutonTurn(10,true));
    	addSequential(new AutoElevator(10000,true));
    	addSequential(new RollerOuttake(1.0,false));
    }
}
