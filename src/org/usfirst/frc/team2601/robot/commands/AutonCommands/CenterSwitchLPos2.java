package org.usfirst.frc.team2601.robot.commands.AutonCommands;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Constants.Robot_Type;
import org.usfirst.frc.team2601.robot.commands.AutoAlignIntake;
import org.usfirst.frc.team2601.robot.commands.AutonWait;
import org.usfirst.frc.team2601.robot.commands.IntakeForward;
import org.usfirst.frc.team2601.robot.commands.ArmPivot.AutoPIDPivot;
import org.usfirst.frc.team2601.robot.commands.ArmPivot.AutoPivot;
import org.usfirst.frc.team2601.robot.commands.arm.ArmPiston;
import org.usfirst.frc.team2601.robot.commands.arm.RollerIntake;
import org.usfirst.frc.team2601.robot.commands.arm.RollerOuttake;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonTurn;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncGyroPlease;
import org.usfirst.frc.team2601.robot.commands.drivetrain.ShiftGear;
import org.usfirst.frc.team2601.robot.commands.elevator.AutoElevator;
import org.usfirst.frc.team2601.robot.commands.elevator.AutoElevatorTimeout;
import org.usfirst.frc.team2601.robot.commands.elevator.AutoLimitElevator;
import org.usfirst.frc.team2601.robot.commands.elevator.ScaleAutoElevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterSwitchLPos2 extends CommandGroup {

	Constants constants = Constants.getInstance();
	
    public CenterSwitchLPos2() {
    		addSequential(new ShiftGear());//Shift to low gear
    		addSequential(new AutonWait(0.1));//Delay
	    	addSequential(new EncGyroPlease(700,700,0.75,true));//Go forward
	    	addSequential(new AutonWait(0.1));//Delay
	    	addSequential(new AutonTurn(35,true));//Turn and face towards switch
	    	addSequential(new AutonWait(0.1));//Delay
	    	addSequential(new EncGyroPlease(4800,4800,0.75,true));
	    	addParallel(new AutoPivot(850,false));//90 degrees
	    	addSequential(new AutonTurn(35,false));//Turn to face perpendicular to the switch
	    	addSequential(new EncGyroPlease(300,300,0.75,true));//Go up to the switch
	    	addSequential(new RollerOuttake(0.1, false));//Shoot the cube
	    	addSequential(new ArmPiston());//Open the pistons for the arms
	    	addSequential(new EncGyroPlease(4100,4100,0.75,false));//Back off 
	    	addSequential(new AutonWait(0.05));//Delay
	    	addParallel(new AutoLimitElevator(7000,false));//Lower the elevator all the way
	    	addSequential(new AutonTurn(45,false));//Turn towards the cubestack//35
	    	addParallel(new RollerIntake(1.6,false));//Secure the cube
	    	addSequential(new EncGyroPlease(4200,4200,0.75,true));//Drive forward 
	    	addSequential(new ArmPiston());//Close arm pistons
	    	addSequential(new RollerIntake(0.05,true));
	    	addSequential(new AutonWait(0.05));
	    	addParallel(new AutoElevator(12000,true));//Lift the elevator to switch height
	    	addSequential(new EncGyroPlease(3000,3000,0.75,false));//Go backwards(3400,3400)
	    	addSequential(new AutonTurn(40,true));//Turn back towards the switch
	    	addSequential(new EncGyroPlease(3900,3900,0.75,true));//Go towards the switch (4400,4400)
	    	addSequential(new RollerOuttake(0.15, false));
	    	addParallel(new ArmPiston());
	    	addSequential(new EncGyroPlease(1000, 1000, 1.0, false));
	    	addParallel(new AutoLimitElevator(7000,false));
	    	addSequential(new AutonTurn(40,false));
	    	addSequential(new IntakeForward(500, 500, 0.85, true));
	    	addSequential(new RollerIntake(0.1,true));
	    	addSequential(new EncGyroPlease(600,600,1.0,false));
    }
}
