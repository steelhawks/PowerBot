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
public class ScaleSwitchRLRPos1 extends CommandGroup {

    public ScaleSwitchRLRPos1() {
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
    	addSequential(new AutonTurn(85, false));//Turn right in low 88
    	//addSequential(new EncGyroPlease(475,475,1.0,true));//Go towards the switch
		addParallel(new AutoPivot(950,false));//Pivot arms down a little 
    	addSequential(new AutoElevator(3500,true));//Raise the elevator
    	addSequential(new RollerOuttake(0.15,true));//Shoot the cube
		addParallel(new AutoLimitElevator(7000,false));//Lower the elevator
    	addSequential(new EncGyroPlease(500,500,1.0,false));//Go backwards
       	addSequential(new AutoAlignIntake());//Align, intake, raise
       	addSequential(new EncGyroPlease(200,200,1.0,true));
    	//Unused
    	//addParallel(new AutonTurn(10,false));
    	//addSequential(new AutoElevator(6500,true));
    	
    	addSequential(new RollerOuttake(0.2,false));//Shoot out the cube 
    
		/*addSequential(new EncGyroPlease(12500,12500,1.0,true));//Forward towards scale
    	addSequential(new AutonWait(0.15));//Delay
    	addSequential(new ShiftGear());//Shift low to slow it down to turn
    	addSequential(new AutonTurn(88, false));//Turn right in low 88
    	addSequential(new EncGyroPlease(900,900,1.0,false));//Go backwards 
    	addParallel(new AutoPivot(950,false));//Pivot the arm down
    	addSequential(new AutoElevator(23500,true));//Raise the elevator
    	addSequential(new EncGyroPlease(800,800,0.5,true));//Slowly move forward towards scale 
    	addSequential(new RollerOuttake(0.25,true));//Shoot the cube
    	addParallel(new EncGyroPlease(800,800,0.5,false));//Move backwards
    	addSequential(new AutoLimitElevator(7000,false));//Lower the elevator
    	addSequential(new AutonTurn(65, false));//Turn towards the switch
    	*/
    	//Unused
    	/*addParallel(new EncGyroPlease(5500,5500,0.75,true));//Travel towards the switch
    	addSequential(new RollerIntake(4.0, false));*/
    	//addSequential(new IntakeForward(5500,5500,0.75,true));
    	/*
    	addSequential(new EncGyroPlease(500,500,1.0,true));//Move towards the switch
    	addSequential(new AutoAlignIntake());//Align, pick up, raise
    	addSequential(new EncGyroPlease(500,500,1.0,false));//Go backwards
    	addParallel(new AutoElevator(17000,true));//Raise the elevator
    	addSequential(new AutonTurn(155, true));//Turn while raising elevator
    	addSequential(new RollerOuttake(0.5,false));//Shoot the cube into the scale
    	*/
    }
}
