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
public class ScaleSwitchLRLPos3 extends CommandGroup {

    public ScaleSwitchLRLPos3() {
    	Robot.drivetrain.frontLeftM.setSafetyEnabled(false);
		Robot.drivetrain.midLeftM.setSafetyEnabled(false);
		Robot.drivetrain.frontRightM.setSafetyEnabled(false);
		Robot.drivetrain.midRightM.setSafetyEnabled(false);
		Robot.drivetrain.frontLeftM.setExpiration(120);
		Robot.drivetrain.midLeftM.setExpiration(120);
		Robot.drivetrain.frontRightM.setExpiration(120);
		Robot.drivetrain.midRightM.setExpiration(120);
		addSequential(new EncGyroPlease(12500,12500,1.0,true));//Forward towards scale
    	addSequential(new AutonWait(0.15));//Delay
    	addSequential(new ShiftGear());//Shift low to slow it down to turn
    	addSequential(new AutonTurn(88, true));//Turn left in low 88
    	addSequential(new EncGyroPlease(900,900,1.0,false));//Move backwards
    	addParallel(new AutoPivot(950,false));//Pivot arm down
    	addSequential(new AutoElevator(23500,true));//Raise the elevator
    	addSequential(new EncGyroPlease(800,800,0.5,true));//Move forward into scale
    	addSequential(new RollerOuttake(0.25,true));//Shoot the cube
    	addParallel(new EncGyroPlease(800,800,0.5,false));//Back up
    	addSequential(new AutoLimitElevator(7000,false));// Lower the elevator
    	addSequential(new AutonTurn(65, true));//Turn towards the switch to pick cubes up
    	
    	//Unused
    	/*addParallel(new EncGyroPlease(5500,5500,0.75,true));//Travel towards the switch
    	addSequential(new RollerIntake(4.0, false));*/
    	//addSequential(new IntakeForward(5500,5500,0.75,true));
    	
    	addSequential(new EncGyroPlease(500,500,1.0,true));//Move closer towards cubes
    	addSequential(new AutoAlignIntake());//Align, pick up, raise elevator
    	addSequential(new EncGyroPlease(500,500,1.0,false));//Back up
    	addParallel(new AutoElevator(17000,true));//Raise the elevator
    	addSequential(new AutonTurn(155, false));//Turn while raising the elevator to face scale
    	addSequential(new RollerOuttake(0.5,false));//Shoot the cube 
   }
}
