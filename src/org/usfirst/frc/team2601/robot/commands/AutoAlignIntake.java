package org.usfirst.frc.team2601.robot.commands;

import org.usfirst.frc.team2601.robot.commands.arm.RollerIntake;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncGyroPlease;
import org.usfirst.frc.team2601.robot.commands.elevator.AutoElevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoAlignIntake extends CommandGroup {

    public AutoAlignIntake() {
    	addSequential(new Align());
    	addSequential(new AutonWait(0.2));
    	addSequential(new IntakeForward(500, 500, 0.75, true));
    	addSequential(new AutonWait(0.1));
    	addParallel(new EncGyroPlease(400,400,0.75,false));
    	addParallel(new AutoElevator(8000,true));
    	addSequential(new RollerIntake(0.85,false));
    	
    }
}
