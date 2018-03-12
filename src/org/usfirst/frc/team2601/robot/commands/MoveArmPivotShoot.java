package org.usfirst.frc.team2601.robot.commands;

import org.usfirst.frc.team2601.robot.commands.ArmPivot.ArmPivotMLimits;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveArmPivotShoot extends CommandGroup {

    public MoveArmPivotShoot() {
        addSequential(new ArmPivotMLimits(50));//25
    }
}
