package org.usfirst.frc.team2601.robot.commands.AutonCommands;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Constants.Robot_Type;
import org.usfirst.frc.team2601.robot.commands.Align;
import org.usfirst.frc.team2601.robot.commands.AutoAlignIntake;
import org.usfirst.frc.team2601.robot.commands.AutonWait;
import org.usfirst.frc.team2601.robot.commands.IntakeForward;
import org.usfirst.frc.team2601.robot.commands.ArmPivot.AutoPivot;
import org.usfirst.frc.team2601.robot.commands.arm.ArmPiston;
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
public class CenterSwitchRPos2 extends CommandGroup {

	Constants constants = Constants.getInstance();
	
    public CenterSwitchRPos2() {
    		
    		/*addSequential(new AutoPivot(225,false));
    		addSequential(new AutonWait(5));
    		addSequential(new AutoPivot (725, false));
    		*/
    		addSequential(new ShiftGear());
    		addSequential(new EncGyroPlease(700,700,0.75,true));
	    	addSequential(new AutonWait(0.1));
	    	addSequential(new AutonTurn(35,false));
	    	//addSequential(new AutoElevator(3000,false));
	    	//addSequential(new EncGyroPlease(2900,2900,0.75,true));
	    	addSequential(new EncGyroPlease(4900,4900,0.75,true));
	    	addSequential(new AutonWait(0.1));
	    	addParallel(new AutoPivot(800,false));//90 degrees
   	    	addSequential(new AutonTurn(35,true));
	    	//addSequential(new AutoElevatorTimeout(1.0,false));
	    	addSequential(new EncGyroPlease(700,700,0.75,true));
	    	addSequential(new RollerOuttake(0.1, false));
	    	addSequential(new ArmPiston());//Open the pistons for the arms
	    	addSequential(new EncGyroPlease(4100,4100,0.75,false));
	    	addSequential(new AutonWait(0.05));
	    	addParallel(new AutoLimitElevator(8000,false));//Lower the elevator all the way
	    	addSequential(new AutonTurn(45,true));//Turn towards the cubestack//35
	    	addParallel(new RollerIntake(1.6,false));//Secure the cube
	    	addSequential(new EncGyroPlease(4200,4200,0.75,true));//Drive forward 
	    	addSequential(new ArmPiston());//Close arm pistons
	    	addSequential(new AutonWait(0.05));
	    	addParallel(new AutoElevator(11000,true));
	    	addSequential(new EncGyroPlease(3400,3400,0.75,false));
	    	addSequential(new AutonTurn(40,false));
	    	addSequential(new EncGyroPlease(4200,4200,0.75,true));
	    	addSequential(new RollerOuttake(0.15, false));
	    	addSequential(new ArmPiston());
    	}
}
