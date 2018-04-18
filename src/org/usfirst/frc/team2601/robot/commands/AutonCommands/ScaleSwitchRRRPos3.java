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
public class ScaleSwitchRRRPos3 extends CommandGroup {

    public ScaleSwitchRRRPos3() {
    	Robot.drivetrain.frontLeftM.setSafetyEnabled(false);
		Robot.drivetrain.midLeftM.setSafetyEnabled(false);
		Robot.drivetrain.frontRightM.setSafetyEnabled(false);
		Robot.drivetrain.midRightM.setSafetyEnabled(false);
		Robot.drivetrain.frontLeftM.setExpiration(120);
		Robot.drivetrain.midLeftM.setExpiration(120);
		Robot.drivetrain.frontRightM.setExpiration(120);
		Robot.drivetrain.midRightM.setExpiration(120);
		addSequential(new EncGyroPlease(13000,13000,1.0,true));//Forward towards scale
    	addSequential(new AutonWait(0.05));//Delay
    	addSequential(new ShiftGear());//Shift low to slow it down to turn
    	addParallel(new AutoPivot(950,false));//Pivot the arms down
    	addSequential(new AutonTurn(88, true));//Turn left in low 88
    	addParallel(new AutoElevator(23500,true));//Raise the elevator
    	addParallel(new AutoPivot(250,true));
    	addSequential(new EncGyroPlease(900,900,1.0,false));//Go backwards away from the scale 
    	//addSequential(new EncGyroPlease(800,800,0.5,true));//Move backwards away from scale
    	addSequential(new RollerOuttake(0.15,true));//Shoot the cube
    	//addParallel(new EncGyroPlease(800,800,0.5,false));//Move backwards away from scale
    	addParallel(new EncGyroPlease(800,800,0.5,true));
    	addSequential(new AutoLimitElevator(7000,false));//Lower the elevator all the way
    	addSequential(new AutonTurn(65, true));//Turn towards the switch
    	addSequential(new EncGyroPlease(800,800,1.0,true));//Travel a little bit towards the switch to pick cubes up	
    	addSequential(new AutoAlignIntake());//Align, intake, raise
    	
    	addSequential(new EncGyroPlease(400,400,1.0,true));//Go forwards
    	addSequential(new RollerOuttake(1.0,false));//Shoot the cube 
    }
}
