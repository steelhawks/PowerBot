package org.usfirst.frc.team2601.robot.commands.AutonCommands;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Constants.Robot_Type;
import org.usfirst.frc.team2601.robot.commands.AutonWait;
import org.usfirst.frc.team2601.robot.commands.ArmPivot.AutoPivot;
import org.usfirst.frc.team2601.robot.commands.arm.RollerIntake;
import org.usfirst.frc.team2601.robot.commands.arm.RollerOuttake;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonTurn;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncGyroPlease;
import org.usfirst.frc.team2601.robot.commands.drivetrain.ShiftGear;
import org.usfirst.frc.team2601.robot.commands.elevator.AutoElevator;
import org.usfirst.frc.team2601.robot.commands.elevator.AutoElevatorTimeout;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterSwitchLPos2 extends CommandGroup {

	Constants constants = Constants.getInstance();
	
    public CenterSwitchLPos2() {
    	if(constants.robotType == Robot_Type.Alpha) {
    		addSequential(new ShiftGear());
	    	addSequential(new AutonWait(0.5));
	    	addSequential(new EncGyroPlease(600,600,1.0,true));
	    	addSequential(new AutonWait(0.5));
	    	addSequential(new AutonTurn(35,true));
	    	//addSequential(new AutoElevator(3000,false));
	    	//addSequential(new EncGyroPlease(2900,2900,0.75,true));
	    	addSequential(new EncGyroPlease(5800,5800,0.75,true));
	    	addSequential(new AutonWait(1.0));
	    	addSequential(new AutonTurn(35,false));
	    	//addSequential(new AutoElevatorTimeout(1.0,false));
	    	addSequential(new EncGyroPlease(300,300,0.75,true));
	    	addSequential(new AutoPivot(35,false));//90 degrees
	    	addSequential(new RollerOuttake(1.5, false));
    	}else {
    		addSequential(new ShiftGear());
	    	addSequential(new AutonWait(0.5));
	    	addSequential(new EncGyroPlease(600,600,1.0,true));
	    	addSequential(new AutonWait(0.5));
	    	addSequential(new AutonTurn(35,true));
	    	//addSequential(new AutoElevator(3000,false));
	    	//addSequential(new EncGyroPlease(2900,2900,0.75,true));
	    	addSequential(new EncGyroPlease(5800,5800,0.75,true));
	    	addSequential(new AutonWait(1.0));
	    	addSequential(new AutonTurn(35,false));
	    	//addSequential(new AutoElevatorTimeout(1.0,false));
	    	addSequential(new EncGyroPlease(300,300,0.75,true));
	    	addSequential(new AutoPivot(35,false));//90 degrees
	    	addSequential(new RollerOuttake(1.5, false));
    	}
    }
}
