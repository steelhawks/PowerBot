package org.usfirst.frc.team2601.robot.commands.AutonCommands;

import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.commands.AutonWait;
import org.usfirst.frc.team2601.robot.commands.MoveArmPivotUp;
import org.usfirst.frc.team2601.robot.commands.ArmPivot.ArmPivotMShoot;
import org.usfirst.frc.team2601.robot.commands.ArmPivot.ArmPivotMLimits;
import org.usfirst.frc.team2601.robot.commands.arm.RollerIntake;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonTurn;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncGyroPlease;
import org.usfirst.frc.team2601.robot.commands.drivetrain.ShiftGear;
import org.usfirst.frc.team2601.robot.commands.drivetrain.UltraGyroMoveStraight;
import org.usfirst.frc.team2601.robot.commands.elevator.AutoElevator;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ScaleSwitchLLLPos1 extends CommandGroup {

    public ScaleSwitchLLLPos1() {
    	Robot.drivetrain.frontLeftM.setSafetyEnabled(false);
		Robot.drivetrain.midLeftM.setSafetyEnabled(false);
		//Robot.drivetrain.backLeftM.setSafetyEnabled(false);
		Robot.drivetrain.frontRightM.setSafetyEnabled(false);
		Robot.drivetrain.midRightM.setSafetyEnabled(false);
		//Robot.drivetrain.backRightM.setSafetyEnabled(false);
		
		Robot.drivetrain.frontLeftM.setExpiration(120);
		Robot.drivetrain.midLeftM.setExpiration(120);
		//Robot.drivetrain.backLeftM.setExpiration(120);
		Robot.drivetrain.frontRightM.setExpiration(120);
		Robot.drivetrain.midRightM.setExpiration(120);
		//Robot.drivetrain.backRightM.setExpiration(120);
		addParallel(new ArmPivotMLimits(50));
		addSequential(new EncGyroPlease(7500,7500,1.0,true));//Forward towards switch
    	addSequential(new ShiftGear());//Shift low to slow it down to turn
    	addSequential(new EncGyroPlease(900,900,1.0,true));//Go forward in low
    	addSequential(new AutonWait(0.25));//Delay
    	addSequential(new AutonTurn(88, false));//Turn right in low
    	addSequential(new EncGyroPlease(200,200,1.0,false));
    	addSequential(new AutonWait(0.5));//elevator up
    	addSequential(new EncGyroPlease(200,200,1.0,true));
		addSequential(new AutonWait(0.5));//elevator down
    	addSequential(new AutonTurn(85, false));//Turn right the switch
    	addSequential(new EncGyroPlease(800,800,1.0,true));//Travel towards the switch
    	addSequential(new AutonTurn(45,true));
    	addParallel(new RollerIntake(false));
    	addSequential(new EncGyroPlease(400,400,1.0,true));
    	//addSequential(new AutoElevator(1000,true));
    	//addSequential(new AutonWait(0.75));//Wait for the mechanism to place the prism
    	//addParallel(new AutoElevator(1000,false));
    	//addSequential(new AutonTurn(175, false));//Turn around back towards another prism
    	//addParallel(new RollerIntake(1.5, true));
    	//addSequential(new EncGyroPlease(800,800,1.0,true));//Go towards the prism
    	addParallel(new RollerIntake(false));
    	addSequential(new ArmPivotMShoot(50));
    	
    	//addSequential(new AutonWait(0.5));//Wait for the mechanism to grab the prism
    	//addSequential(new AutonTurn(20,true));
    }
}
