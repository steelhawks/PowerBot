package org.usfirst.frc.team2601.robot.commands.AutonCommands;

import org.usfirst.frc.team2601.robot.commands.AutonWait;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonTurn;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncGyroPlease;
import org.usfirst.frc.team2601.robot.commands.drivetrain.UltraGyroMoveStraight;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestAuton extends CommandGroup {

    public TestAuton() {
    //Command for testing all the methods that are being used in autonommous 	
    	addSequential(new UltraGyroMoveStraight(50,0.65,true));
    	addSequential(new AutonWait(0.5));
    	addSequential(new AutonTurn(90, false));
    	addSequential(new EncGyroPlease(1000,1000,0.75,true));
    	addSequential(new AutonWait(0.5));
    	addSequential(new AutonTurn(90, false));
    	addSequential(new UltraGyroMoveStraight(50,0.65,true));
    	addSequential(new AutonWait(0.5));
    	addSequential(new AutonTurn(90, false));
    	addSequential(new EncGyroPlease(1000,1000,0.75,true));
    	addSequential(new AutonWait(0.5));
    	addSequential(new AutonTurn(90, false));
    	addSequential(new UltraGyroMoveStraight(50,0.65,true));
    }
}
