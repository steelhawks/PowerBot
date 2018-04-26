package org.usfirst.frc.team2601.robot.commands.AutonCommands;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Constants.Robot_Type;
import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.commands.AutoAlignIntake;
import org.usfirst.frc.team2601.robot.commands.AutonWait;
import org.usfirst.frc.team2601.robot.commands.IntakeForward;
import org.usfirst.frc.team2601.robot.commands.ArmPivot.AutoPivot;
import org.usfirst.frc.team2601.robot.commands.arm.RollerIntake;
import org.usfirst.frc.team2601.robot.commands.arm.RollerOuttake;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonTurn;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncGyroPlease;
import org.usfirst.frc.team2601.robot.commands.drivetrain.ShiftGear;
import org.usfirst.frc.team2601.robot.commands.drivetrain.UltraGyroMoveStraight;
import org.usfirst.frc.team2601.robot.commands.elevator.AutoElevator;
import org.usfirst.frc.team2601.robot.commands.elevator.AutoLimitElevator;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ScaleSwitchLLLPos1 extends CommandGroup {

	Constants constants = Constants.getInstance();
	
    public ScaleSwitchLLLPos1() {
    	//Alpha (SH) Code 
    	if(constants.robotType == Robot_Type.Alpha) {
	    	Robot.drivetrain.frontLeftM.setSafetyEnabled(false);
			Robot.drivetrain.midLeftM.setSafetyEnabled(false);
			Robot.drivetrain.frontRightM.setSafetyEnabled(false);
			Robot.drivetrain.midRightM.setSafetyEnabled(false);
			Robot.drivetrain.frontLeftM.setExpiration(120);
			Robot.drivetrain.midLeftM.setExpiration(120);
			Robot.drivetrain.frontRightM.setExpiration(120);
			Robot.drivetrain.midRightM.setExpiration(120);
			addSequential(new EncGyroPlease(11500,11500,1.0,true));//Forward towards the null territory 
	    	addSequential(new AutonWait(0.05));//Delay
	    	addParallel(new AutoPivot(950,false));//Pivot the arms down
	    	addSequential(new ShiftGear());//Shift low to slow it down to turn
	    	addSequential(new AutoElevator(25500,true));//Raise the elevator
	    	addSequential(new AutonTurn(45, false));//Turn right in low 88
	    	addSequential(new EncGyroPlease(700,700,0.5,true));
	    	addSequential(new RollerOuttake(0.15,false));
	    	addParallel(new AutoLimitElevator(23500,false));
	    	addSequential(new AutonTurn(90,false));
	    	addSequential(new AutoAlignIntake());
	    	addSequential(new EncGyroPlease(400,400,0.75,true));
	    	addSequential(new RollerOuttake(0.15,true));
	    	/*
			addSequential(new EncGyroPlease(2000,2000,1.0,false));
			addSequential(new AutoElevator(26500,true));
			addSequential(new AutonTurn(90,true));
			addSequential(new RollerOuttake(0.15,false));
	    	 */
	    	/*
			addSequential(new EncGyroPlease(13000,13000,1.0,true));//Forward towards the null territory 
	    	addSequential(new AutonWait(0.05));//Delay
	    	addSequential(new ShiftGear());//Shift low to slow it down to turn
	    	addParallel(new AutoPivot(950,false));//Pivot the arms down
	    	addSequential(new AutonTurn(88, false));//Turn right in low 88
	    	addParallel(new AutoElevator(23500,true));//Raise the elevator
	    	addParallel(new AutoPivot(250,true));
	    	addSequential(new EncGyroPlease(900,900,1.0,false));//Go backwards away from the scale 
	    	//addSequential(new EncGyroPlease(800,800,0.5,true));//Move backwards away from scale
	    	addSequential(new RollerOuttake(0.15,true));//Shoot the cube
	    	//addParallel(new EncGyroPlease(800,800,0.5,false));//Move backwards away from scale
	    	addParallel(new EncGyroPlease(800,800,0.5,true));
	    	addSequential(new AutoLimitElevator(7000,false));//Lower the elevator all the way
	    	addSequential(new AutonTurn(65, false));//Turn towards the switch, where the cube is
	    	addSequential(new EncGyroPlease(800,800,1.0,true));//Drive towards the cube
	    	
	    	addSequential(new AutoAlignIntake());//Align to the cube, pick it up, raise elevator
	    	
	    	addSequential(new EncGyroPlease(400,400,1.0,true));//Go towards the switch
	    	addSequential(new RollerOuttake(1.0,false));//Outtake the switch*/
	    //Beta Code
    	}else {
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
			//addParallel(new ArmPivotMLimits(50));
			addSequential(new EncGyroPlease(15000,15000,1.0,true));//Forward towards switch
	    	addSequential(new ShiftGear());//Shift low to slow it down to turn
	    	addSequential(new EncGyroPlease(1800,1800,1.0,true));//Go forward in low
	    	addSequential(new AutonWait(0.25));//Delay
	    	addSequential(new AutonTurn(88, false));//Turn right in low
	    	addSequential(new EncGyroPlease(750,750,1.0,false));
	    	addSequential(new AutoPivot(35,false));
	    	//addSequential(new AutoElevator(10000,true));//elevator up
	    	addParallel(new RollerOuttake(1.5,false));
	    	/*addSequential(new AutonTurn(45, false));//Turn right the switch
	    	addParallel(new EncGyroPlease(3000,3000,1.0,true));//Travel towards the switch
	    	addSequential(new RollerIntake(1.0, false));
	    	//addSequential(new IntakeForward(400,400,0.75,true));
	    	//addSequential(new AutoPivot(35,false));
	    	addSequential(new RollerOuttake(1.5,false));
	    	*///addSequential(new AutonWait(0.5));//Wait for the mechanism to grab the prism
	    	//addSequential(new AutonTurn(20,true));
    	}
    }
}
