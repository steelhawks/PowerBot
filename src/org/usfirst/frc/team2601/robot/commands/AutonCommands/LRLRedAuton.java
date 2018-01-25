package org.usfirst.frc.team2601.robot.commands.AutonCommands;

import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.commands.AutonWait;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonTurn;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncGyroPlease;
import org.usfirst.frc.team2601.robot.commands.drivetrain.ShiftGear;
import org.usfirst.frc.team2601.robot.commands.drivetrain.UltraGyroMoveStraight;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LRLRedAuton extends CommandGroup {

    public LRLRedAuton() {
    //Command for testing all the methods that are being used in autonommous 	
    	if(Robot.drivetrain.shiftSol.get() == DoubleSolenoid.Value.kForward){
    		addSequential(new ShiftGear());
    	}else {
    		addSequential(new ShiftGear());
    	}
    	addSequential(new AutonWait(0.25));
    	addSequential(new EncGyroPlease(4200,4200,1.0,true));
    	addSequential(new ShiftGear());
    	addSequential(new EncGyroPlease(1700,1700,1.0,true));
    	addSequential(new AutonWait(0.25));
    	addSequential(new AutonTurn(85, false));
    	addSequential(new EncGyroPlease(700,700,1.0,true));
    	addSequential(new AutonWait(0.25));
    	addSequential(new AutonTurn(85, false));
    	addSequential(new AutonWait(0.25));
       	addSequential(new UltraGyroMoveStraight(15,0.65,true));
       	addSequential(new EncGyroPlease(1000,1000,1.0,false));
       	addSequential(new AutonTurn(180,false));
    }
}
