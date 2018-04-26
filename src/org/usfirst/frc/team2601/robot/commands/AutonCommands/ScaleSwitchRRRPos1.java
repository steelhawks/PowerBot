package org.usfirst.frc.team2601.robot.commands.AutonCommands;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.Constants.Robot_Type;
import org.usfirst.frc.team2601.robot.commands.AutoAlignIntake;
import org.usfirst.frc.team2601.robot.commands.AutonWait;
import org.usfirst.frc.team2601.robot.commands.IntakeForward;
import org.usfirst.frc.team2601.robot.commands.ArmPivot.AutoPivot;
import org.usfirst.frc.team2601.robot.commands.arm.RollerIntake;
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
public class ScaleSwitchRRRPos1 extends CommandGroup {

	Constants constants = Constants.getInstance();
	
    public ScaleSwitchRRRPos1() {
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
			addSequential(new EncGyroPlease(9550,9550,1.0,true));//Forward towards switch
	    	addSequential(new AutonWait(0.1));//Delay
			addSequential(new ShiftGear());//Shift low to slow it down to turn
	    	addSequential(new AutonTurn(88, false));//Turn right in low 88
	    	addSequential(new ShiftGear());//Shift to high 
	    	addSequential(new EncGyroPlease(7850,7850,1.0,true));//Go across the field
	    	addSequential(new AutonWait(0.1));//Delay
	    	addSequential(new ShiftGear());//Shift back to low
	    	addSequential(new AutonTurn(85, true));//Turn left in low 88
	    	addSequential(new EncGyroPlease(975,975,1.0,true));//Go towards the scale
    		addParallel(new AutoPivot(950,false));//Pivot arms down a little 
	    	addSequential(new AutoElevator(25500,true));//Raise the elevator
	    	addSequential(new RollerOuttake(0.15,true));//Shoot the cube
			addParallel(new AutoLimitElevator(7000,false));//Lower the elevator
	    	addSequential(new EncGyroPlease(500,500,1.0,false));//Go backwards
	    	//addParallel(new AutoPivot(825,false));//Pivot arms down all the way
	    	addSequential(new AutonTurn(180, false));//Turn towards the switch
	    	addSequential(new EncGyroPlease(800,800,1.0,true));//Go towards the switch
	    	addSequential(new AutoAlignIntake());//Align, intake, raise
	    	addSequential(new EncGyroPlease(400,400,0.75,true));
	    	addSequential(new RollerOuttake(0.2,false));//Shoot out the cube 
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
	    	addSequential(new AutonWait(1.0));
			//addSequential(new AutoElevator(10000,false));//elevator down
	    	addSequential(new AutonWait(1.0));
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
