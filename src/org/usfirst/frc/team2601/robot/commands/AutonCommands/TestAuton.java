package org.usfirst.frc.team2601.robot.commands.AutonCommands;

import org.usfirst.frc.team2601.robot.commands.AutonWait;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonTurn;
import org.usfirst.frc.team2601.robot.commands.drivetrain.UltraGyroMoveStraight;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestAuton extends CommandGroup {

    public TestAuton() {
    	
    	addSequential(new UltraGyroMoveStraight(10,0.75,true));
    	addSequential(new AutonWait(1.0));
    	addSequential(new AutonTurn(90, true));
    }
}
