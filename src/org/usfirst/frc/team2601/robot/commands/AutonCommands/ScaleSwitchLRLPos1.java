package org.usfirst.frc.team2601.robot.commands.AutonCommands;

import org.usfirst.frc.team2601.robot.commands.ArmPivot.ArmPivotMLimits;
import org.usfirst.frc.team2601.robot.commands.ArmPivot.AutoPivot;
import org.usfirst.frc.team2601.robot.commands.arm.RollerIntake;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncGyroPlease;
import org.usfirst.frc.team2601.robot.commands.elevator.AutoElevator;
import org.usfirst.frc.team2601.robot.commands.elevator.AutoElevatorTimeout;
import org.usfirst.frc.team2601.robot.subsystems.ArmPivot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ScaleSwitchLRLPos1 extends CommandGroup {

    public ScaleSwitchLRLPos1() {
    	addSequential(new RollerIntake(true));
    	//addSequential(new ArmIntakePiston());
    	//addSequential(new AutoElevator(1000,true));
    	//addSequential(new ArmPivotMUp(200));
    	//addSequential(new ArmPivotMDown(200));
    	//addSequential(new AutoPivot(200,true));
    	//addSequential(new AutoElevatorTimeout(1000,1.0));
    }
}
