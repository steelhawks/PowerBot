package org.usfirst.frc.team2601.robot.commands.AutonCommands;

import org.usfirst.frc.team2601.robot.commands.drivetrain.EncGyroPlease;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossAutoLine extends CommandGroup {

    public CrossAutoLine() {
    	addSequential(new EncGyroPlease(6200,6200,0.75,true));
    }
}
