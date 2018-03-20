package org.usfirst.frc.team2601.robot.commands.AutonCommands;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Constants.Robot_Type;
import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.commands.AutonWait;
import org.usfirst.frc.team2601.robot.commands.IntakeForward;
import org.usfirst.frc.team2601.robot.commands.MoveArmPivotUp;
import org.usfirst.frc.team2601.robot.commands.ArmPivot.ArmPivotMShoot;
import org.usfirst.frc.team2601.robot.commands.ArmPivot.AutoPivot;
import org.usfirst.frc.team2601.robot.commands.ArmPivot.ArmPivotMLimits;
import org.usfirst.frc.team2601.robot.commands.arm.RollerIntake;
import org.usfirst.frc.team2601.robot.commands.arm.RollerOuttake;
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

	Constants constants = Constants.getInstance();
	
    public ScaleSwitchLLLPos1() {
    	if(constants.robotType == Robot_Type.Alpha) {
	    	Robot.drivetrain.frontLeftM.setSafetyEnabled(false);
			Robot.drivetrain.midLeftM.setSafetyEnabled(false);
			Robot.drivetrain.frontRightM.setSafetyEnabled(false);
			Robot.drivetrain.midRightM.setSafetyEnabled(false);
			Robot.drivetrain.frontLeftM.setExpiration(120);
			Robot.drivetrain.midLeftM.setExpiration(120);
			Robot.drivetrain.frontRightM.setExpiration(120);
			Robot.drivetrain.midRightM.setExpiration(120);
			addSequential(new EncGyroPlease(13000,13000,1.0,true));//Forward towards switch
	    	addSequential(new AutonWait(0.5));//Delay
			addSequential(new ShiftGear());//Shift low to slow it down to turn
	    	addSequential(new AutonTurn(88, false));//Turn right in low 88
	    	addSequential(new EncGyroPlease(900,900,1.0,false));
	    	addSequential(new AutoPivot(25,false));
	    	addSequential(new AutoElevator(30000,true));//elevator up
	    	addSequential(new RollerOuttake(1.5,false));
	    	addSequential(new AutoElevator(30000,false));//elevator down
	    	addSequential(new EncGyroPlease(400,400,1.0,true));
	    	addSequential(new AutonTurn(70, false));//Turn right the switch
	    	/*addParallel(new EncGyroPlease(5500,5500,0.75,true));//Travel towards the switch
	    	addSequential(new RollerIntake(4.0, false));*/
	    	addSequential(new IntakeForward(5500,5500,0.75,true));
	    	addSequential(new AutoElevator(10000,true));//elevator up
	    	//addSequential(new AutoPivot(35,false));
	    	addSequential(new RollerOuttake(1.5,false));
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
