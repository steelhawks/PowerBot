package org.usfirst.frc.team2601.robot.commands.AutonCommands;

import org.usfirst.frc.team2601.robot.commands.drivetrain.UltraForwardGyro;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestAuton extends CommandGroup {

    public TestAuton() {
    	//addSequential();
    	addSequential(new UltraForwardGyro(10,0.75,true));
    }
}
