package org.usfirst.frc.team2601.robot.commands.AutonCommands;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Constants.Robot_Type;
import org.usfirst.frc.team2601.robot.commands.AutonWait;
import org.usfirst.frc.team2601.robot.commands.IntakeForward;
import org.usfirst.frc.team2601.robot.commands.ArmPivot.AutoPivot;
import org.usfirst.frc.team2601.robot.commands.arm.RollerIntake;
import org.usfirst.frc.team2601.robot.commands.arm.RollerOuttake;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonTurn;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncGyroPlease;
import org.usfirst.frc.team2601.robot.commands.drivetrain.ShiftGear;
import org.usfirst.frc.team2601.robot.commands.elevator.AutoElevator;
import org.usfirst.frc.team2601.robot.commands.elevator.AutoElevatorTimeout;
import org.usfirst.frc.team2601.robot.commands.elevator.AutoLimitElevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterSwitchLPos2 extends CommandGroup {

	Constants constants = Constants.getInstance();
	
    public CenterSwitchLPos2() {
    		addSequential(new ShiftGear());
	    	addSequential(new AutoPivot(35,false));//90 degrees
    		addSequential(new AutonWait(0.1));
	    	addSequential(new EncGyroPlease(700,700,0.75,true));
	    	addSequential(new AutonWait(0.2));
	    	addSequential(new AutonTurn(35,true));
	    	//addSequential(new AutoElevator(3000,false));
	    	//addSequential(new EncGyroPlease(2900,2900,0.75,true));
	    	addSequential(new EncGyroPlease(5800,5800,0.75,true));
	    	addSequential(new AutonWait(0.2));
	    	addSequential(new AutonTurn(35,false));
	    	addSequential(new EncGyroPlease(300,300,0.75,true));
	    	addSequential(new RollerOuttake(0.25, false));
	    	
	    	addSequential(new EncGyroPlease(3000,3000,0.75,false));
	    	addSequential(new AutonWait(0.05));
	    	addSequential(new AutonTurn(40,false));
	    	
	    	addSequential(new AutoLimitElevator(7000,false));
	    	//addSequential(new AutonWait(0.2));
	    	//addSequential(new IntakeForward(550,550,0.75,true));
	    	addParallel(new RollerIntake(1.6,false));
	    	addSequential(new EncGyroPlease(3550,3550,0.75,true));
	    	addParallel(new RollerIntake(0.2,false));
	    	addSequential(new AutonTurn(10,true));
	    	addParallel(new RollerIntake(0.2,false));
	    	addSequential(new AutonTurn(10,false));
	    	//addSequential(new AutonWait(0.05));
	    	//addParallel(new EncGyroPlease(550,550,0.75,true));
	    	addSequential(new AutoElevator(10000,true));
	    	//addSequential(new AutoElevator(9500,true));
    		addSequential(new EncGyroPlease(3550,3550,0.75,false));
	    	addSequential(new AutonTurn(45,true));
	    	addSequential(new EncGyroPlease(4000,4000,0.75,true));
	    	addSequential(new RollerOuttake(1.0, false));
    }
}
